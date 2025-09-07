package core.application.cohort.domain.model

@JvmInline
value class CohortId(val value: Long) {
    override fun toString(): String = value.toString()
}
