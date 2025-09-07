package core.application.member.memberTeam.application

import core.application.member.member.domain.model.MemberId
import core.application.member.memberTeam.domain.model.MemberTeam
import core.application.member.memberTeam.domain.port.outbound.MemberTeamPersistencePort
import core.application.team.domain.model.TeamId
import org.springframework.stereotype.Service

@Service
class MemberTeamService(
    private val memberTeamPersistencePort: MemberTeamPersistencePort,
) {
    /**
     * 팀에 멤버를 추가함.
     *
     * @author LeeHanEum
     * @since 2025.08.02
     */
    fun addMemberToTeam(
        memberId: MemberId,
        teamId: TeamId,
    ) {
        memberTeamPersistencePort.save(MemberTeam.of(memberId, teamId))
    }
}
