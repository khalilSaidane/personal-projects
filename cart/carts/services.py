import logging
from datetime import datetime, timedelta, timezone
from typing import List, Optional

from sqlalchemy.orm.exc import NoResultFound

from cart.utils.services import BaseService
from carts.exceptions import (
    CartNotFoundException,
    OpenCartAlreadyExistsForUser,
    CartItemNotFoundException,
)
from carts.models import CartStatus
from carts.repositories import CartRepository, CartItemRepository
from carts.schmeas import Cart, CartRequest
from carts.vaidators import validate_status_transition


class CartItemService(BaseService):
    def __init__(self, cart_item_repository: CartItemRepository):
        self.cart_item_repository = cart_item_repository

    def create_cart_item(self, cart_request: CartRequest):
        existing_item = self.cart_item_repository.get_cart_item_by_product_and_cart_id(
            cart_request.product_id, cart_request.cart_id
        )
        if existing_item:
            summed_quantity = cart_request.quantity + existing_item.quantity
            self.cart_item_repository.update_field(existing_item, summed_quantity)
            return existing_item

        return self.cart_item_repository.create_cart_item(
            cart_request.product_id, cart_request.cart_id, cart_request.quantity
        )

    def get_cart_item_by_id(self, cart_id: int, cart_item_id: int):
        try:
            return self.cart_item_repository.get_cart_item_by_id(cart_item_id, cart_id)
        except NoResultFound:
            raise CartItemNotFoundException(cart_item_id, cart_id)

    def update_cart_item(self, cart_id: int, cart_item_id: int, quantity: int):
        cart_item = self.get_cart_item_by_id(cart_item_id, cart_id)
        return self.cart_item_repository.update_field(cart_item, lambda item: item.quantity, quantity)


class CartService(BaseService):

    def __init__(self, cart_repository: CartRepository):
        self.cart_repository = cart_repository

    def initialise_cart(self, user_id: int) -> Optional[Cart]:
        self._check_existing_open_cart(user_id)
        return self.cart_repository.create_cart(user_id)

    def _check_existing_open_cart(self, user_id):
        open_cart_exists = self.cart_repository.search_cart(user_id, [CartStatus.OPEN])
        if len(open_cart_exists):
            raise OpenCartAlreadyExistsForUser(user_id)

    def get_user_carts(self, user_id: int) -> List[Cart]:
        return self.cart_repository.search_cart(user_id)

    def get_cart_by_id(self, cart_id: int) -> Cart:
        try:
            return self.cart_repository.get_cart_by_id(cart_id)
        except NoResultFound:
            raise CartNotFoundException(cart_id)

    def update_cart_status(self, cart_id: int, new_status: CartStatus) -> Optional[Cart]:
        cart = self.get_cart_by_id(cart_id)
        if cart.status == new_status:
            return cart
        validate_status_transition(cart.status, new_status)
        return self.cart_repository.update_cart_status(cart, new_status)


class CartCronService(BaseService):
    def __init__(self, cart_repository: CartRepository):
        self.cart_repository = cart_repository

    def close_cartes_older_than(self):
        logging.info(f"{datetime.utcnow()} Running cron job to remove olt carts")
        threshold_time = datetime.utcnow() - timedelta(minutes=30)
        threshold_time_utc = threshold_time.replace(tzinfo=timezone.utc)
        carts_to_be_closed = self.cart_repository \
            .filter_cart_by_last_updated_at_greater_than(threshold_time_utc)
        for cart in carts_to_be_closed:
            self.cart_repository.update_cart_status(cart, CartStatus.CLOSED)
            logging.info(f"Closing cart {cart.id} for user {cart.user_id}")
