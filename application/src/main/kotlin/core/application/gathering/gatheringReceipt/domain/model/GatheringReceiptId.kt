package core.application.gathering.gatheringReceipt.domain.model

@JvmInline
value class GatheringReceiptId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
