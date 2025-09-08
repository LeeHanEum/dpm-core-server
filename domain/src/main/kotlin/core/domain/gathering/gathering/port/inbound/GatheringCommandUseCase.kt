package core.domain.gathering.gathering.port.inbound

import core.domain.authority.model.AuthorityId
import core.domain.bill.bill.model.BillId
import core.domain.bill.bill.port.inbound.UpdateMemberDepositCommand
import core.domain.bill.bill.port.inbound.UpdateMemberListDepositCommand
import core.domain.bill.bill.presentation.dto.request.UpdateGatheringJoinsRequest
import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gathering.port.inbound.command.GatheringCreateCommand
import core.domain.gathering.gatheringReceipt.model.GatheringReceipt
import core.domain.member.member.model.MemberId

interface GatheringCommandUseCase {
    fun saveAllGatherings(
        commands: List<GatheringCreateCommand>,
        invitedAuthorityIds: List<AuthorityId>,
        billId: BillId,
    )

    fun updateGatheringReceiptSplitAmount(receipt: GatheringReceipt): Boolean

    fun markAsCheckedEachGatheringMember(
        gatheringIds: List<GatheringId>,
        memberId: MemberId,
    )

    fun markAsJoinedEachGatheringMember(
        billId: BillId,
        request: UpdateGatheringJoinsRequest,
        memberId: MemberId,
    )

    fun submitBillParticipationConfirmEachGathering(
        billId: BillId,
        memberId: MemberId,
    )

    fun updateMemberDeposit(command: UpdateMemberDepositCommand)

    fun updateMemberListDeposit(command: UpdateMemberListDepositCommand)
}
