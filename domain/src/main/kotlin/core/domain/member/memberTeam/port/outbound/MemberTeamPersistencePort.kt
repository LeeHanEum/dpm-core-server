package core.domain.member.memberTeam.port.outbound

import core.domain.member.memberTeam.model.MemberTeam

interface MemberTeamPersistencePort {
    fun save(memberTeam: MemberTeam)
}
