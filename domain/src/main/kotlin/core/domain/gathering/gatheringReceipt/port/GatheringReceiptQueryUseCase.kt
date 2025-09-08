package core.domain.gathering.gatheringReceipt.port

import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gatheringReceipt.model.GatheringReceipt

interface GatheringReceiptQueryUseCase {
    fun findByGatheringId(gatheringId: GatheringId): GatheringReceipt
}
