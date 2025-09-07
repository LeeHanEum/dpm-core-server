package core.application.session.presentation.dto.response

import core.application.session.domain.model.SessionId

data class SessionWeeksResponse(
    val sessions: List<SessionWeekResponse>,
)

data class SessionWeekResponse(
    val id: SessionId,
    val week: Int,
)
