package core.application.member.member.domain.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode
import core.application.member.member.application.exception.MemberExceptionCode

class InvalidMemberIdException(
    code: ExceptionCode = MemberExceptionCode.INVALID_MEMBER_ID,
) : BusinessException(code)
