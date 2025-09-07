package core.application.cohort.domain.port.outbound

import core.application.cohort.domain.model.CohortId

interface CohortPersistencePort {
    fun findCohortIdByValue(value: String): CohortId?
}
