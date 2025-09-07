package core.application.bill.bill.domain.port.inbound

import core.application.bill.bill.domain.model.BillId

data class UpdateMemberListDepositCommand(
    val billId: BillId,
    val members: List<UpdateMemberListDepositMemberCommand>,
)
