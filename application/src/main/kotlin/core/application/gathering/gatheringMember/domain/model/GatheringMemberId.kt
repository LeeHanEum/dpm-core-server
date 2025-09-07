package core.application.gathering.gatheringMember.domain.model

@JvmInline
value class GatheringMemberId(val value: Long) {
    override fun toString(): String = value.toString()
}
