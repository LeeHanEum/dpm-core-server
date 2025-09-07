package core.application.bill.billAccount.presentation.mapper

import core.application.bill.billAccount.domain.model.BillAccount
import core.application.bill.billAccount.domain.model.BillAccountId
import core.application.bill.billAccount.presentation.dto.response.BillAccountResponse
import core.application.bill.exception.BillAccountException

object BillAccountMapper {
    fun toBillAccount(billAccountId: Long): BillAccount =
        BillAccount(
            id = BillAccountId(billAccountId),
        )

    fun toBillAccountResponse(billAccount: BillAccount): BillAccountResponse =
        BillAccountResponse(
            id = billAccount.id?.value ?: throw BillAccountException.BillAccountIdRequiredException(),
            billAccountValue = billAccount.billAccountValue,
            accountHolderName = billAccount.accountHolderName,
            bankName = billAccount.bankName,
            accountType = billAccount.accountType.value,
        )
}
