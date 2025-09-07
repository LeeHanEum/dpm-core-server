package core.application.attendance.domain.model

enum class AttendanceStatus {
    PENDING,
    PRESENT,
    LATE,
    ABSENT,
    EXCUSED_ABSENT,
    EARLY_LEAVE,
}
