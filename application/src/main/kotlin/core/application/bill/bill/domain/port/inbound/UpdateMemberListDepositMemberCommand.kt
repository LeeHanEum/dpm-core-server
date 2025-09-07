package core.application.bill.bill.domain.port.inbound

import core.application.member.member.domain.model.MemberId

data class UpdateMemberListDepositMemberCommand(
    val memberId: MemberId,
    val isDeposit: Boolean,
)
