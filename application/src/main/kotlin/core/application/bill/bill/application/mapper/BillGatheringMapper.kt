package core.application.bill.bill.application.mapper

import core.application.bill.bill.presentation.dto.request.GatheringForBillCreateRequest
import core.application.gathering.gathering.domain.port.inbound.command.GatheringCreateCommand
import core.application.gathering.gathering.domain.port.inbound.command.ReceiptCommand
import core.application.member.member.domain.model.MemberId

object BillGatheringMapper {
    fun GatheringForBillCreateRequest.toCommand(hostUserId: MemberId): GatheringCreateCommand =
        GatheringCreateCommand(
            title = this.title,
            description = this.description,
            hostUserId = hostUserId,
            roundNumber = this.roundNumber,
            heldAt = this.heldAt,
            receipt =
                ReceiptCommand(
                    amount = this.receipt.amount,
                ),
        )
}
