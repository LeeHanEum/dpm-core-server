package core.domain.bill.bill.port.inbound

import core.domain.bill.bill.model.BillId
import core.domain.member.member.model.MemberId

data class UpdateMemberDepositCommand(
    val billId: BillId,
    val memberId: MemberId,
    val isDeposit: Boolean,
    val memo: String?,
)
