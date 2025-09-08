package core.domain.bill.bill.port.inbound

import core.domain.bill.bill.model.BillId

data class UpdateMemberListDepositCommand(
    val billId: BillId,
    val members: List<UpdateMemberListDepositMemberCommand>,
)
