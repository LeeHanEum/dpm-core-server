package core.application.attendance.domain.port.inbound.command

import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId

data class AttendanceCreateCommand(
    val sessionId: SessionId,
    val memberId: MemberId,
)
