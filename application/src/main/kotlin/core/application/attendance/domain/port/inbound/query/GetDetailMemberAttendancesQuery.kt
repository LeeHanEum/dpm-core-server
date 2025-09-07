package core.application.attendance.domain.port.inbound.query

import core.application.member.member.domain.model.MemberId

data class GetDetailMemberAttendancesQuery(
    val memberId: MemberId,
)
