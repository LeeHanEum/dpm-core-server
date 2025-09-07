package core.persistence.bill.exception

import core.application.common.exception.BusinessException

open class BillException(
    code: BillExceptionCode,
) : BusinessException(code) {
    class BillServerException : BillException(BillExceptionCode.SERVER_ERROR)

    class BillNotFoundException : BillException(BillExceptionCode.BILL_NOT_FOUND)

    class BillIdRequiredException : BillException(BillExceptionCode.BILL_ID_REQUIRED)

    class BillCannotCloseParticipationException : BillException(BillExceptionCode.BILL_CANNOT_CLOSE_PARTICIPATION)

    class BillAlreadyCompletedException : BillException(BillExceptionCode.BILL_ALREADY_COMPLETED)

    class BillAlreadyParticipationClosedException : BillException(BillExceptionCode.BILL_ALREADY_PARTICIPATION_CLOSED)
}
