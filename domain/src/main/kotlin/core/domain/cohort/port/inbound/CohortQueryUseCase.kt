package core.domain.cohort.port.inbound

import core.domain.cohort.model.CohortId

interface CohortQueryUseCase {
    fun getLatestCohortId(): CohortId
}
