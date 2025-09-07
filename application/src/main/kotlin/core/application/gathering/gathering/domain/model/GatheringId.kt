package core.application.gathering.gathering.domain.model

@JvmInline
value class GatheringId(val value: Long) {
    override fun toString(): String = value.toString()
}
