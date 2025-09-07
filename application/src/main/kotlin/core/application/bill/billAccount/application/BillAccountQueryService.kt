package core.application.bill.billAccount.application

import core.application.bill.billAccount.domain.model.BillAccount
import core.application.bill.billAccount.domain.model.BillAccountId
import core.application.bill.billAccount.domain.port.BillAccountPersistencePort
import core.application.bill.exception.BillAccountException
import org.springframework.stereotype.Service

@Service
class BillAccountQueryService(
    private val billAccountPersistencePort: BillAccountPersistencePort,
) {
    fun findBy(billAccountId: BillAccountId): BillAccount =
        billAccountPersistencePort.findById(
            billAccountId,
        ) ?: throw BillAccountException.BillAccountNotFoundException()
}
