package core.domain.bill.billAccount.model

@JvmInline
value class BillAccountId(val value: Long) {
    override fun toString(): String = value.toString()
}
