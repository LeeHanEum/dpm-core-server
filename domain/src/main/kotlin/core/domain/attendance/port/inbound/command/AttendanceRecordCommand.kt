package core.domain.attendance.port.inbound.command

import core.domain.member.member.model.MemberId
import core.domain.session.model.SessionId
import java.time.Instant

data class AttendanceRecordCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
    val attendedAt: Instant,
    val attendanceCode: String,
)
