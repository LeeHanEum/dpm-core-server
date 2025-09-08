package core.persistence.member.memberCohort.repository

import core.domain.member.memberCohort.model.MemberCohort
import core.domain.member.memberCohort.port.outbound.MemberCohortPersistencePort
import org.jooq.DSLContext
import org.jooq.generated.tables.references.MEMBER_COHORTS
import org.springframework.stereotype.Repository

@Repository
class MemberCohortRepository(
    private val dsl: DSLContext,
) : MemberCohortPersistencePort {
    override fun save(memberCohort: MemberCohort) {
        dsl
            .insertInto(MEMBER_COHORTS)
            .set(MEMBER_COHORTS.MEMBER_ID, memberCohort.memberId.value)
            .set(MEMBER_COHORTS.COHORT_ID, memberCohort.cohortId.value)
            .execute()
    }
}
