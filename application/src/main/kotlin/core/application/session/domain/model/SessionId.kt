package core.application.session.domain.model

import core.application.session.domain.exception.InvalidSessionIdException

@JvmInline
value class SessionId(
    val value: Long,
) {
    init {
        if (value <= 0) {
            throw InvalidSessionIdException()
        }
    }

    override fun toString(): String = value.toString()
}
