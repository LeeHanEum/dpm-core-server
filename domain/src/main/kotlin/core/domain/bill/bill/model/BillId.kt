package core.domain.bill.bill.model

@JvmInline
value class BillId(val value: Long) {
    override fun toString(): String = value.toString()
}
