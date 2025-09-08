package core.domain.session.event

import core.domain.session.model.SessionId

data class SessionCreateEvent(
    val sessionId: SessionId,
)
