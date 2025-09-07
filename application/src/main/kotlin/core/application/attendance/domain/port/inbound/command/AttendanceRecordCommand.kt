package core.application.attendance.domain.port.inbound.command

import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId
import java.time.Instant

data class AttendanceRecordCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
    val attendedAt: Instant,
    val attendanceCode: String,
)
