from fastapi import APIRouter

from carts.routers import cart_router, cart_item_router
from probes.routers import probes_router

api_router = APIRouter()
v1_prefix = "/api/v1"

api_router.include_router(cart_router, tags=["Carts"], prefix=f"{v1_prefix}/carts")
api_router.include_router(cart_item_router, tags=["Cart Items"], prefix=f"{v1_prefix}/cart-items")
api_router.include_router(probes_router, tags=["Probes"], prefix="/probes")
