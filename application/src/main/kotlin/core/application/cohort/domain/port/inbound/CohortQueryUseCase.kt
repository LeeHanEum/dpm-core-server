package core.application.cohort.domain.port.inbound

import core.application.cohort.domain.model.CohortId

interface CohortQueryUseCase {
    fun getLatestCohortId(): CohortId
}
