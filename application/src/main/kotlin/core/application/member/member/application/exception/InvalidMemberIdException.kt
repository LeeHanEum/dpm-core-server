package core.application.member.member.application.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode

class InvalidMemberIdException(
    code: ExceptionCode = MemberExceptionCode.INVALID_MEMBER_ID,
) : BusinessException(code)
