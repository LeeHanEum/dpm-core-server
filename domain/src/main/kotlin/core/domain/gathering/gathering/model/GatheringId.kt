package core.domain.gathering.gathering.model

@JvmInline
value class GatheringId(val value: Long) {
    override fun toString(): String = value.toString()
}
