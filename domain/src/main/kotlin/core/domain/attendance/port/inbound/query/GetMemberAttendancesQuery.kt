package core.domain.attendance.port.inbound.query

import core.domain.attendance.model.AttendanceStatus
import core.domain.member.member.model.MemberId

data class GetMemberAttendancesQuery(
    val memberId: MemberId,
    val statuses: List<AttendanceStatus>?,
    val teams: List<Int>?,
    val name: String?,
    val onlyMyTeam: Boolean?,
    val cursorId: Long?,
)
