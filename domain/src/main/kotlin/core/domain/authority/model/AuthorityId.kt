package core.domain.authority.model

@JvmInline
value class AuthorityId(val value: Long) {
    override fun toString(): String = value.toString()
}
