package core.application.gathering.gatheringReceipt.domain.port

import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt

interface GatheringReceiptQueryUseCase {
    fun findByGatheringId(gatheringId: GatheringId): GatheringReceipt
}
