package core.application.attendance.domain.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode

class InvalidAttendanceIdException(
    code: ExceptionCode = AttendanceExceptionCode.INVALID_ATTENDANCE_ID,
) : BusinessException(code)
