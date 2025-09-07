package core.application.session.domain.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode

class InvalidAttendanceCodeException(
    code: ExceptionCode = SessionExceptionCode.INVALID_ATTENDANCE_CODE,
) : BusinessException(code)
