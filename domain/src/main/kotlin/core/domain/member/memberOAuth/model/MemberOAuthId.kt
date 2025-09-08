package core.domain.member.memberOAuth.model

@JvmInline
value class MemberOAuthId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
