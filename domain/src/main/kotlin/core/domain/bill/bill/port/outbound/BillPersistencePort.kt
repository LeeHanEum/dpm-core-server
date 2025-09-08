package core.domain.bill.bill.port.outbound

import core.domain.bill.bill.model.Bill
import core.domain.bill.bill.model.BillId

interface BillPersistencePort {
    fun save(bill: Bill): BillId

    fun findById(billId: BillId): Bill?

    fun findAllBills(): List<Bill>

    fun closeBillParticipation(bill: Bill): Int
}
