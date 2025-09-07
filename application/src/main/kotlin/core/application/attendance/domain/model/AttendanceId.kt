package core.application.attendance.domain.model

import core.application.attendance.domain.exception.InvalidAttendanceIdException

@JvmInline
value class AttendanceId(
    val value: Long,
) {
    init {
        if (value <= 0) {
            throw InvalidAttendanceIdException()
        }
    }

    override fun toString(): String = value.toString()
}
