package core.application.attendance.domain.port.inbound.command

import core.application.attendance.domain.model.AttendanceStatus
import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId

data class AttendanceStatusUpdateCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
    val attendanceStatus: AttendanceStatus,
)
