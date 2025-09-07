package core.application.gathering.gatheringReceiptPhoto.domain.model

@JvmInline
value class GatheringReceiptPhotoId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
