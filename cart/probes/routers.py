from fastapi import APIRouter, Depends
from fastapi_utils.cbv import cbv

from cart.dependencies.database import get_db
from probes.exceptions import ReadyNessProbeException

probes_router = APIRouter()


@cbv(probes_router)
class ProbesView:

    @probes_router.get("/liveness")
    def liveness(self):
        return {"status": "alive"}

    @probes_router.get("/readiness")
    def readiness(self, session=Depends(get_db)):
        try:
            session.execute("SELECT 1")
            return {"status": "healthy"}
        except Exception as e:
            raise ReadyNessProbeException(e)
