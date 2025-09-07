package core.persistence.bill.bill.repository

import core.persistence.bill.bill.entity.BillEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BillJpaRepository : JpaRepository<BillEntity, Long>
