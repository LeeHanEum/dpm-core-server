package core.domain.session.model

@JvmInline
value class SessionId(
    val value: Long,
) {

    override fun toString(): String = value.toString()
}
