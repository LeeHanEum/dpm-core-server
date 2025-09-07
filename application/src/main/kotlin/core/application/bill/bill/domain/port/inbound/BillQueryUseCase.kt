package core.application.bill.bill.domain.port.inbound

import core.application.bill.bill.domain.model.Bill
import core.application.bill.bill.domain.model.BillId
import core.application.bill.bill.presentation.dto.response.BillListResponse
import core.application.member.member.domain.model.MemberId

interface BillQueryUseCase {
    fun getById(billId: BillId): Bill

    fun getBillByMemberId(memberId: MemberId): BillListResponse
}
