package core.application.attendance.application.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode

class InvalidAttendanceIdException(
    code: ExceptionCode = AttendanceExceptionCode.INVALID_ATTENDANCE_ID,
) : BusinessException(code)
