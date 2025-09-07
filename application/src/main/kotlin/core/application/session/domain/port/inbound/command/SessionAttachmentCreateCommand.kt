package core.application.session.domain.port.inbound.command

import core.application.session.domain.model.SessionId

data class SessionAttachmentCreateCommand(
    val sessionId: SessionId,
    val title: String,
    val path: String,
    val idx: Int? = null,
)
