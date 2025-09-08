package core.application.member.memberCohort.application

import core.application.cohort.application.exception.CohortNotFoundException
import core.application.cohort.domain.port.inbound.CohortQueryUseCase
import core.application.member.member.domain.model.MemberId
import core.application.member.memberCohort.domain.model.MemberCohort
import core.application.member.memberCohort.domain.port.outbound.MemberCohortPersistencePort
import org.springframework.stereotype.Service

@Service
class MemberCohortService(
    private val memberCohortPersistencePort: MemberCohortPersistencePort,
    private val cohortQueryUseCase: CohortQueryUseCase,
) {
    /**
     * 가장 최신 기수 정보를 조회하고 멤버를 해당 기수에 추가함.
     *
     * @throws CohortNotFoundException
     *
     * @author LeeHanEum
     * @since 2025.08.02
     */
    fun addMemberToCohort(memberId: MemberId) {
        memberCohortPersistencePort.save(
            MemberCohort.of(memberId, cohortQueryUseCase.getLatestCohortId()),
        )
    }
}
