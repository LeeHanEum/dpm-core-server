package core.application.member.memberTeam.domain.port.outbound

import core.application.member.memberTeam.domain.model.MemberTeam

interface MemberTeamPersistencePort {
    fun save(memberTeam: MemberTeam)
}
