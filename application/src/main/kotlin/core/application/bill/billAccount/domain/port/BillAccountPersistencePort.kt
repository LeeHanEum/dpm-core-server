package core.application.bill.billAccount.domain.port

import core.application.bill.billAccount.domain.model.BillAccount
import core.application.bill.billAccount.domain.model.BillAccountId

interface BillAccountPersistencePort {
    fun findById(billAccountId: BillAccountId): BillAccount?
}
