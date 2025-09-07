package core.application.gathering.gatheringReceipt.domain.port

import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceiptId

interface GatheringReceiptPersistencePort {
    fun save(
        gatheringReceipt: GatheringReceipt,
        gathering: Gathering,
    )

    fun findById(gatheringReceiptId: GatheringReceiptId): GatheringReceipt

    fun findByGathering(gatheringId: GatheringId): GatheringReceipt

    fun updateSplitAmount(gatheringReceipt: GatheringReceipt): Int

    fun findSplitAmountByGatheringId(gatheringId: GatheringId): Int?
}
