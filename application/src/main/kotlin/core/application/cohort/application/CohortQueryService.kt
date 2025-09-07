package core.application.cohort.application

import core.application.cohort.application.config.CohortProperties
import core.application.cohort.domain.exception.CohortNotFoundException
import core.application.cohort.domain.model.CohortId
import core.application.cohort.domain.port.inbound.CohortQueryUseCase
import core.application.cohort.domain.port.outbound.CohortPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CohortQueryService(
    private val cohortPersistencePort: CohortPersistencePort,
    private val cohortProperties: CohortProperties,
) : CohortQueryUseCase {
    override fun getLatestCohortId(): CohortId =
        cohortPersistencePort.findCohortIdByValue(cohortProperties.value)
            ?: throw CohortNotFoundException()
}
