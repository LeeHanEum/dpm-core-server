package core.application.bill.bill.presentation.dto.response

import core.application.bill.bill.domain.port.inbound.query.BillMemberIsInvitationSubmittedQueryModel

data class BillMemberSubmittedListResponse(
    val members: List<BillMemberIsInvitationSubmittedQueryModel>,
)
