package core.application.bill.bill.domain.port.inbound

import core.application.bill.bill.domain.model.BillId
import core.application.member.member.domain.model.MemberId

data class UpdateMemberDepositCommand(
    val billId: BillId,
    val memberId: MemberId,
    val isDeposit: Boolean,
    val memo: String?,
)
