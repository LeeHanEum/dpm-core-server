package core.persistence.gathering.gathering.repository

import core.persistence.gathering.gathering.entity.GatheringEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringJpaRepository : JpaRepository<GatheringEntity, Long> {
    fun findByBillId(billId: Long): List<GatheringEntity>
}
