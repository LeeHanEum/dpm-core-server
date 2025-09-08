package core.domain.attendance.port.inbound.query

import core.domain.member.member.model.MemberId

data class GetDetailMemberAttendancesQuery(
    val memberId: MemberId,
)
