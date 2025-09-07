package core.application.attendance.domain.port.inbound.query

import core.application.attendance.domain.model.AttendanceStatus
import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId

data class GetAttendancesBySessionWeekQuery(
    val sessionId: SessionId,
    val memberId: MemberId,
    val statuses: List<AttendanceStatus>?,
    val teams: List<Int>?,
    val name: String?,
    val onlyMyTeam: Boolean?,
    val cursorId: Long?,
)
