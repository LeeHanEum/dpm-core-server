package core.application.gathering.gathering.domain.port.inbound

import core.application.bill.bill.domain.model.BillId
import core.application.bill.bill.domain.port.inbound.query.BillMemberIsInvitationSubmittedQueryModel
import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gathering.domain.model.query.SubmittedParticipantGathering
import core.application.gathering.gathering.domain.port.inbound.query.GatheringMemberReceiptQueryModel
import core.application.gathering.gatheringMember.domain.model.GatheringMember
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt
import core.application.member.member.domain.model.MemberId

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
