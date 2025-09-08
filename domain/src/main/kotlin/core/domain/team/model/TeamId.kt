package core.domain.team.model

@JvmInline
value class TeamId(val value: Long) {
    override fun toString(): String = value.toString()
}
