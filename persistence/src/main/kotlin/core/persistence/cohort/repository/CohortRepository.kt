package core.persistence.cohort.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import core.application.cohort.domain.model.CohortId
import core.application.cohort.domain.port.outbound.CohortPersistencePort
import core.persistence.cohort.entity.CohortEntity
import core.application.common.jdsl.singleQueryOrNull
import core.persistence.cohort.repository.CohortJpaRepository
import org.springframework.stereotype.Repository

@Repository
class CohortRepository(
    private val cohortJpaRepository: CohortJpaRepository,
    private val queryFactory: SpringDataQueryFactory,
) : CohortPersistencePort {
    override fun findCohortIdByValue(value: String): CohortId? {
        return queryFactory
            .singleQueryOrNull<Long> {
                select(col(CohortEntity::id))
                from(entity(CohortEntity::class))
                where(col(CohortEntity::value).equal(value))
            }?.let { return CohortId(it) }
    }
}
