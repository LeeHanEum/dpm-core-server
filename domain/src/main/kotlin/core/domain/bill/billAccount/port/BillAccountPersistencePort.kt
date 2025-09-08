package core.domain.bill.billAccount.port

import core.domain.bill.billAccount.model.BillAccount
import core.domain.bill.billAccount.model.BillAccountId

interface BillAccountPersistencePort {
    fun findById(billAccountId: BillAccountId): BillAccount?
}
