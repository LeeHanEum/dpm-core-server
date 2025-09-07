package core.application.attendance.domain.port.inbound.query

import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId

data class GetMyAttendanceBySessionQuery(
    val sessionId: SessionId,
    val memberId: MemberId,
)
