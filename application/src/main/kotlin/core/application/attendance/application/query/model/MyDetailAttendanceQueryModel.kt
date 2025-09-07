package core.application.attendance.application.query.model

import java.time.Instant

data class MyDetailAttendanceQueryModel(
    val attendanceStatus: String,
    val attendedAt: Instant?,
    val sessionWeek: Int,
    val sessionEventName: String,
    val sessionDate: Instant,
    val sessionPlace: String,
)
