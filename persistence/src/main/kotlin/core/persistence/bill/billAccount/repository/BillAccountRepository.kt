package core.persistence.bill.billAccount.repository

import core.domain.bill.bill.model.BillAccount
import core.domain.bill.bill.model.BillAccountId
import core.domain.bill.bill.port.BillAccountPersistencePort
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class BillAccountRepository(
    private val billAccountJpaRepository: BillAccountJpaRepository,
) : BillAccountPersistencePort {
    override fun findById(billAccountId: BillAccountId): BillAccount? =
        billAccountJpaRepository.findById(billAccountId.value).getOrNull()?.toDomain()
}
