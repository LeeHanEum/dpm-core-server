package core.domain.gathering.gatheringReceiptPhoto.model

@JvmInline
value class GatheringReceiptPhotoId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
