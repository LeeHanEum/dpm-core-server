package core.domain.gathering.gatheringReceipt.model

@JvmInline
value class GatheringReceiptId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
