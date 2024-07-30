from apscheduler.schedulers.background import BackgroundScheduler

from cart.db.database import SessionLocal
from carts.repositories import CartRepository
from carts.services import CartCronService


def close_too_old_carts():
    cart_cron_service = CartCronService(CartRepository(SessionLocal()))
    scheduler = BackgroundScheduler()
    scheduler.add_job(cart_cron_service.close_cartes_older_than, 'cron', second=1)
    scheduler.start()
