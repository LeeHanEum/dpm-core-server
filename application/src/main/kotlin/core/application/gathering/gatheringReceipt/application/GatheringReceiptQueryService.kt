package core.application.gathering.gatheringReceipt.application

import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceiptId
import core.application.gathering.gatheringReceipt.domain.port.GatheringReceiptPersistencePort
import core.application.gathering.gatheringReceipt.domain.port.GatheringReceiptQueryUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class GatheringReceiptQueryService(
    private val gatheringReceiptPersistencePort: GatheringReceiptPersistencePort,
) : GatheringReceiptQueryUseCase {
    fun findById(gatheringReceiptId: GatheringReceiptId) = gatheringReceiptPersistencePort.findById(gatheringReceiptId)

    override fun findByGatheringId(gatheringId: GatheringId): GatheringReceipt =
        gatheringReceiptPersistencePort.findByGathering(gatheringId)

    fun getSplitAmountByGatheringId(gatheringId: GatheringId): Int? =
        gatheringReceiptPersistencePort.findSplitAmountByGatheringId(gatheringId)
}
