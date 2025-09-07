package core.application.gathering.gathering.domain.port.inbound

import core.application.authority.domain.model.AuthorityId
import core.application.bill.bill.domain.model.BillId
import core.application.bill.bill.domain.port.inbound.UpdateMemberDepositCommand
import core.application.bill.bill.domain.port.inbound.UpdateMemberListDepositCommand
import core.application.bill.bill.presentation.dto.request.UpdateGatheringJoinsRequest
import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gathering.domain.port.inbound.command.GatheringCreateCommand
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt
import core.application.member.member.domain.model.MemberId

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
