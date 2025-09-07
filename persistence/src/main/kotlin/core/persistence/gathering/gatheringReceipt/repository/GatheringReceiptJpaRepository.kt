package core.persistence.gathering.gatheringReceipt.repository

import core.application.gathering.gathering.domain.model.GatheringId
import core.persistence.gathering.gatheringReceipt.entity.GatheringReceiptEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringReceiptJpaRepository : JpaRepository<GatheringReceiptEntity, Long> {
    fun findByGatheringId(gatheringId: GatheringId): GatheringReceiptEntity?
}
