package core.persistence.gathering.gatheringReceipt.repository

import core.domain.gathering.gathering.model.GatheringId
import core.persistence.gathering.gatheringReceipt.entity.GatheringReceiptEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringReceiptJpaRepository : JpaRepository<GatheringReceiptEntity, Long> {
    fun findByGatheringId(gatheringId: GatheringId): GatheringReceiptEntity?
}
