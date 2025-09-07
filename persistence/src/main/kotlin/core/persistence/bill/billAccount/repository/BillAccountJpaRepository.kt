package core.persistence.bill.billAccount.repository

import core.persistence.bill.billAccount.entity.BillAccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BillAccountJpaRepository : JpaRepository<BillAccountEntity, Long>
