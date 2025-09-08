package core.domain.member.member.model

@JvmInline
value class MemberId(
    val value: Long,
) {

    override fun toString(): String = value.toString()
}
