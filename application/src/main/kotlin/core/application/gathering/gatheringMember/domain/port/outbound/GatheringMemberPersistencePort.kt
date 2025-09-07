package core.application.gathering.gatheringMember.domain.port.outbound

import core.application.bill.bill.domain.port.inbound.query.BillMemberIsInvitationSubmittedQueryModel
import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gatheringMember.domain.model.GatheringMember
import core.application.gathering.gatheringMember.domain.port.inbound.query.GatheringMemberIsJoinQueryModel
import core.application.member.member.domain.model.MemberId

interface GatheringMemberPersistencePort {
    fun save(
        gatheringMember: GatheringMember,
        gathering: Gathering,
    )

    fun findByGatheringId(gatheringId: GatheringId): List<GatheringMember>

    fun findByGatheringIdAndMemberId(
        gatheringId: GatheringId,
        memberId: MemberId,
    ): GatheringMember

    fun updateGatheringMemberById(gatheringMember: GatheringMember)

    fun findMemberIdsByGatheringId(gatheringId: GatheringId): List<MemberId>

    fun findGatheringMemberWithIsJoinByGatheringIdAndMemberId(
        gatheringId: GatheringId,
        memberId: MemberId,
    ): List<GatheringMemberIsJoinQueryModel>

    fun markAsGatheringParticipationSubmitConfirm(gatheringMember: GatheringMember)

    fun findGatheringMemberWithIsInvitationSubmittedByGatheringIdAndMemberId(
        gatheringId: GatheringId,
        memberId: MemberId,
    ): List<BillMemberIsInvitationSubmittedQueryModel>

    fun findGatheringMembersByGatheringIdsAndMemberIds(
        gatheringIds: List<GatheringId>,
        memberIds: List<MemberId>,
    ): List<GatheringMember>
}
