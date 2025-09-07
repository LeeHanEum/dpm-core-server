package core.application.gathering.gatheringReceiptPhoto.presentation.mapper

import core.application.bill.bill.presentation.dto.request.ReceiptPhotoForBillCreateRequest
import core.application.gathering.gatheringReceiptPhoto.domain.model.GatheringReceiptPhoto

object GatheringReceiptPhotoMapper {
    fun toReceiptPhoto(receiptPhotoForBillCreateRequest: ReceiptPhotoForBillCreateRequest): GatheringReceiptPhoto =
        GatheringReceiptPhoto()
}
