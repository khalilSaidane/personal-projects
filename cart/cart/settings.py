import os
import databases
from starlette.config import Config
from starlette.datastructures import CommaSeparatedStrings, Secret
import logging

config = Config("../.env")
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

DEBUG = config('DEBUG', cast=bool, default=True)
TESTING = config('TESTING', cast=bool, default=False)
SECRET_KEY = config('SECRET_KEY', cast=Secret)
ALLOWED_HOSTS = config('ALLOWED_HOSTS', cast=CommaSeparatedStrings)
PROJECT_NAME = "cart"
PREFIX = ""
VERSION = "0.1.1-SNAPSHOT"


DATABASE_URL = config('DATABASE_URL', cast=databases.DatabaseURL)
if TESTING:
    DATABASE_URL = databases.DatabaseURL(url="sqlite:///test.db")
db_config = {
    "pool_size": 20, "max_overflow": 0
}

logging.basicConfig()
logging.getLogger().setLevel(logging.INFO)
