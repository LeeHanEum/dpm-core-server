package core.application.gathering.gathering.presentation.response

import core.application.gathering.gatheringMember.domain.port.inbound.query.GatheringMemberIsJoinQueryModel

data class GatheringMemberJoinListResponse(
    val members: List<GatheringMemberIsJoinQueryModel>,
) {
    companion object {
        fun from(members: List<GatheringMemberIsJoinQueryModel>): GatheringMemberJoinListResponse =
            GatheringMemberJoinListResponse(members)
    }
}
