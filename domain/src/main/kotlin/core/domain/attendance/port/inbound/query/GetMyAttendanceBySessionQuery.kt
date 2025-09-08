package core.domain.attendance.port.inbound.query

import core.domain.member.member.model.MemberId
import core.domain.session.model.SessionId

data class GetMyAttendanceBySessionQuery(
    val sessionId: SessionId,
    val memberId: MemberId,
)
