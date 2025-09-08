package core.domain.gathering.gatheringMember.model

@JvmInline
value class GatheringMemberId(val value: Long) {
    override fun toString(): String = value.toString()
}
