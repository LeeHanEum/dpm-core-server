package core.application.member.memberCohort.domain.port.outbound

import core.application.member.memberCohort.domain.model.MemberCohort

interface MemberCohortPersistencePort {
    fun save(memberCohort: MemberCohort)
}
