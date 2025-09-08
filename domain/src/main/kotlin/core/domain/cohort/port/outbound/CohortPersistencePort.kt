package core.domain.cohort.port.outbound

import core.domain.cohort.model.Cohort

interface CohortPersistencePort {
    fun findByValue(value: String): Cohort
}
