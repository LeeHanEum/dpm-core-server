package core.domain.member.memberTeam.model

@JvmInline
value class MemberTeamId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
