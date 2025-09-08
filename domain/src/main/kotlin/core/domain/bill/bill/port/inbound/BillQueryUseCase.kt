package core.domain.bill.bill.port.inbound

import core.domain.bill.bill.model.Bill
import core.domain.bill.bill.model.BillId
import core.domain.bill.bill.presentation.dto.response.BillListResponse
import core.domain.member.member.model.MemberId

interface BillQueryUseCase {
    fun getById(billId: BillId): Bill

    fun getBillByMemberId(memberId: MemberId): BillListResponse
}
