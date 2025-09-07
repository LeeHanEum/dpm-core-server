package core.persistence.bill.billAccount.repository

import core.application.bill.billAccount.domain.model.BillAccount
import core.application.bill.billAccount.domain.model.BillAccountId
import core.application.bill.billAccount.domain.port.BillAccountPersistencePort
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class BillAccountRepository(
    private val billAccountJpaRepository: BillAccountJpaRepository,
) : BillAccountPersistencePort {
    override fun findById(billAccountId: BillAccountId): BillAccount? =
        billAccountJpaRepository.findById(billAccountId.value).getOrNull()?.toDomain()
}
