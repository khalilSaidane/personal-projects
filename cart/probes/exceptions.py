from starlette.exceptions import HTTPException


class ReadyNessProbeException(HTTPException):
    def __init__(self, exception: Exception):
        super().__init__(
            status_code=503,
            detail=f"Service not ready {str(exception)}",
        )
