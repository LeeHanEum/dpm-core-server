package core.application.session.domain.model

@JvmInline
value class SessionAttachmentId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
