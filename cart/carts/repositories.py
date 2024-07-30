from datetime import datetime, timezone
from operator import and_
from typing import Optional, List

from cart.utils.repositories import BaseRepository
from carts.models import Cart, CartItem, CartStatus


class CartRepository(BaseRepository):
    def create_cart(self, user_id: int) -> Optional[Cart]:
        cart = Cart(user_id=user_id)
        self.session.add(cart)
        self.session.commit()
        return cart

    def search_cart(
            self, user_id, status_list: Optional[List[CartStatus]] = None
    ) -> List[Cart]:
        query = self.session.query(Cart).filter(Cart.user_id == user_id)

        if status_list:
            query = query.filter(Cart.status.in_(status_list))

        return query.all()

    def update_cart_status(self, cart: Cart, newStatus: CartStatus) -> Cart:
        cart.status = newStatus
        self.session.commit()
        return cart

    def get_cart_by_id(self, cart_id) -> Optional[Cart]:
        return self.session.query(Cart).filter(Cart.id == cart_id).one()

    def filter_cart_by_last_updated_at_greater_than(self, threshold_time: datetime):
        return (
            self.session.query(Cart)
            .filter(Cart.status == CartStatus.OPEN)
            .filter(Cart.last_updated_at < threshold_time)
            .all()
        )


class CartItemRepository(BaseRepository):
    def create_cart_item(
            self, product_id: int, cart_id: int, quantity: int
    ) -> Optional[CartItem]:
        cart_item = CartItem(cart_id=cart_id, product_id=product_id, quantity=quantity)
        self.session.add(cart_item)
        self.session.commit()
        return cart_item

    def get_cart_item_by_product_and_cart_id(
            self, product_id: int, cart_id: int
    ) -> CartItem:
        return (
            self.session.query(CartItem)
            .filter(CartItem.cart_id == cart_id)
            .filter(CartItem.product_id == product_id)
            .first()
        )

    def update_field(self, cart_item: CartItem, fieldGetter, updated_field):
        field = fieldGetter(cart_item)
        field = updated_field;
        self.session.commit()
        return cart_item

    def get_cart_item_by_id(self, cart_item_id: int, cart_id: int):
        return (
            self.session.query(CartItem)
            .filter(and_(CartItem.id == cart_item_id, CartItem.cart_id == cart_id))
            .one()
        )
