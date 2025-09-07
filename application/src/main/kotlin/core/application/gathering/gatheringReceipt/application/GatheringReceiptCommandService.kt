package core.application.gathering.gatheringReceipt.application

import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gathering.domain.port.inbound.command.ReceiptCommand
import core.application.gathering.gatheringReceipt.domain.model.GatheringReceipt
import core.application.gathering.gatheringReceipt.domain.port.GatheringReceiptPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GatheringReceiptCommandService(
    private val gatheringReceiptPersistencePort: GatheringReceiptPersistencePort,
) {
    //    TODO 준원 : 영수증 사진 저장 로직 추가
    fun saveReceiptDetails(
        receipt: ReceiptCommand,
        gathering: Gathering,
    ) = gatheringReceiptPersistencePort.save(GatheringReceipt.create(receipt, gathering.id!!), gathering)

    fun updateSplitAmount(receipt: GatheringReceipt): Boolean =
        gatheringReceiptPersistencePort.updateSplitAmount(
            receipt,
        ) != 0
}
