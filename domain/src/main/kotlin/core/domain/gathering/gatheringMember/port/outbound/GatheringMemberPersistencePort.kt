package core.domain.gathering.gatheringMember.port.outbound

import core.domain.bill.bill.port.inbound.query.BillMemberIsInvitationSubmittedQueryModel
import core.domain.gathering.gathering.model.Gathering
import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gatheringMember.model.GatheringMember
import core.domain.gathering.gatheringMember.port.inbound.query.GatheringMemberIsJoinQueryModel
import core.domain.member.member.model.MemberId

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
