package core.application.session.application.query

import core.application.session.domain.model.SessionId

data class SessionWeekQueryModel(
    val sessionId: SessionId,
    val week: Int,
)
