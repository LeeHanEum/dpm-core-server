package core.domain.gathering.gatheringReceipt.port

import core.domain.gathering.gathering.model.Gathering
import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gatheringReceipt.model.GatheringReceipt
import core.domain.gathering.gatheringReceipt.model.GatheringReceiptId

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
