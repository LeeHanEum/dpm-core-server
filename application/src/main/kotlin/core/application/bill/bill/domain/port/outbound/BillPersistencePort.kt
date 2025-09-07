package core.application.bill.bill.domain.port.outbound

import core.application.bill.bill.domain.model.Bill
import core.application.bill.bill.domain.model.BillId

interface BillPersistencePort {
    fun save(bill: Bill): BillId

    fun findById(billId: BillId): Bill?

    fun findAllBills(): List<Bill>

    fun closeBillParticipation(bill: Bill): Int
}
