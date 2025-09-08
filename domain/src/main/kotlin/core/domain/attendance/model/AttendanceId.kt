package core.domain.attendance.model

@JvmInline
value class AttendanceId(
    val value: Long,
) {

    override fun toString(): String = value.toString()
}
