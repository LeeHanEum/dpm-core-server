package core.domain.gathering.gathering.port.inbound

import core.domain.bill.bill.model.BillId
import core.domain.bill.bill.port.inbound.query.BillMemberIsInvitationSubmittedQueryModel
import core.domain.gathering.gathering.model.Gathering
import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gathering.model.query.SubmittedParticipantGathering
import core.domain.gathering.gathering.port.inbound.query.GatheringMemberReceiptQueryModel
import core.domain.gathering.gatheringMember.model.GatheringMember
import core.domain.gathering.gatheringReceipt.model.GatheringReceipt
import core.domain.member.member.model.MemberId

interface GatheringQueryUseCase {
    fun getAllGatheringsByGatheringIds(gatheringIds: List<GatheringId>): List<Gathering>

    fun getAllGatheringsByBillId(billId: BillId): List<Gathering>

    fun getAllGatheringIdsByBillId(billId: BillId): List<GatheringId>

    fun findGatheringReceiptByGatheringId(gatheringId: GatheringId): GatheringReceipt

    fun findGatheringMemberByGatheringId(gatheringId: GatheringId): List<GatheringMember>

    fun getGatheringMemberReceiptByBillId(billId: BillId): List<GatheringMemberReceiptQueryModel>

    fun getSubmittedParticipantEachGathering(
        billId: BillId,
        memberId: MemberId,
    ): List<SubmittedParticipantGathering>

    fun getBillMemberSubmittedList(billId: BillId): List<BillMemberIsInvitationSubmittedQueryModel>

    fun getAllGatheringMembersByBillId(billId: BillId): List<GatheringMember>

    fun findTotalSplitAmount(gatheringIds: List<GatheringId>): Int?
}
