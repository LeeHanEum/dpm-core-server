package core.domain.bill.bill.port.inbound

import core.domain.member.member.model.MemberId

data class UpdateMemberListDepositMemberCommand(
    val memberId: MemberId,
    val isDeposit: Boolean,
)
