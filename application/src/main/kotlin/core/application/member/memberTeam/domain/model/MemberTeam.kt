package core.application.member.memberTeam.domain.model

import core.application.member.member.domain.model.MemberId
import core.application.team.domain.model.TeamId

class MemberTeam(
    val id: MemberTeamId? = null,
    val memberId: MemberId,
    val teamId: TeamId,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MemberTeam) return false

        return id == other.id &&
            memberId == other.memberId &&
            teamId == other.teamId
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + memberId.hashCode()
        result = 31 * result + teamId.hashCode()
        return result
    }

    override fun toString(): String = "MemberTeam(id=$id, memberId=$memberId, teamId=$teamId)"

    companion object {
        fun of(
            memberId: MemberId,
            teamId: TeamId,
        ): MemberTeam =
            MemberTeam(
                memberId = memberId,
                teamId = teamId,
            )
    }
}
