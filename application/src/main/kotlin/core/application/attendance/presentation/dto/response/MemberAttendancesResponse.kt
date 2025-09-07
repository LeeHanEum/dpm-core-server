package core.application.attendance.presentation.dto.response

data class MemberAttendancesResponse(
    val members: List<MemberAttendanceResponse>,
    val hasNext: Boolean,
    val nextCursorId: Long?,
)
