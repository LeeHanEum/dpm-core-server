package core.domain.attendance.port.inbound.command

import core.domain.attendance.model.AttendanceStatus
import core.domain.member.member.model.MemberId
import core.domain.session.model.SessionId

data class AttendanceStatusUpdateCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
    val attendanceStatus: AttendanceStatus,
)
