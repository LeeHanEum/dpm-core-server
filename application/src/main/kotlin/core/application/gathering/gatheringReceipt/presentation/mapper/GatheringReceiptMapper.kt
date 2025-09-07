package core.application.gathering.gatheringReceipt.presentation.mapper

import core.application.bill.bill.presentation.dto.request.ReceiptForBillCreateRequest
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt

object GatheringReceiptMapper {
//    fun toCreateReceiptResponse(gatheringReceipt: GatheringReceipt): CreateReceiptResponse = CreateReceiptResponse()

    fun toReceipt(receiptForBillCreateRequest: ReceiptForBillCreateRequest): GatheringReceipt =
        GatheringReceipt(
            amount = receiptForBillCreateRequest.amount,
        )
}
