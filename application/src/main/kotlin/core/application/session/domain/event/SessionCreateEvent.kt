package core.application.session.domain.event

import core.application.session.domain.model.SessionId

data class SessionCreateEvent(
    val sessionId: SessionId,
)
