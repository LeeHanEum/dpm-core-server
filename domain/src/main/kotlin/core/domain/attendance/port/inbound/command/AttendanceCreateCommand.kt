package core.domain.attendance.port.inbound.command

import core.domain.member.member.model.MemberId
import core.domain.session.model.SessionId

data class AttendanceCreateCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
)
