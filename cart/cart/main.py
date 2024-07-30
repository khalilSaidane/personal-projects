import sys
# without this we can't run the project from a docker container
# we have to add the parent folder to sys.path
from os.path import abspath, dirname

import uvicorn
from fastapi import FastAPI

from cart import settings
from cart.api import api_router
from carts.cron import close_too_old_carts

parent_path = dirname(dirname(abspath(__file__)))
sys.path.append(parent_path)


def get_application():
    application = FastAPI(
        title=settings.PROJECT_NAME,
        debug=settings.DEBUG,
        version=settings.VERSION,
    )
    application.include_router(api_router, prefix=settings.PREFIX)
    return application


app = get_application()


@app.on_event('startup')
def startup_event():
    close_too_old_carts()


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)
