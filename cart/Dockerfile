FROM python:3.8.1

ENV PYTHONUNBUFFERED 1
ENV PYTHONPATH /app
EXPOSE 8000
WORKDIR /app

COPY poetry.lock pyproject.toml ./
RUN pip install poetry==1.0.* && \
    poetry config virtualenvs.create false && \
    poetry install --no-dev

RUN pip install mysql-connector-python-rf

COPY . ./

RUN alembic init /app/cart/db/migrations || true

CMD cd cart && \
    uvicorn --host=0.0.0.0 main:app