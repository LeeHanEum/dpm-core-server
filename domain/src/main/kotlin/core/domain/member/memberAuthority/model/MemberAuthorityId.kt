package core.domain.member.memberAuthority.model

@JvmInline
value class MemberAuthorityId(
    val value: Long,
) {
    override fun toString(): String = value.toString()
}
