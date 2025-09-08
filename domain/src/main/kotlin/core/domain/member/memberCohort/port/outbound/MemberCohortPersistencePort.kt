package core.domain.member.memberCohort.port.outbound

import core.domain.member.memberCohort.model.MemberCohort

interface MemberCohortPersistencePort {
    fun save(memberCohort: MemberCohort)
}
