package core.application.member.member.domain.exception

import core.application.common.exception.BusinessException
import core.application.common.exception.ExceptionCode
import core.application.member.member.application.exception.MemberExceptionCode

class CohortMembersNotFoundException(
    code: ExceptionCode = MemberExceptionCode.COHORT_MEMBERS_NOT_FOUND,
) : BusinessException(code)
