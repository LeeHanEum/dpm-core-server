package core.persistence.member.memberCohort.entity

import core.application.cohort.domain.model.CohortId
import core.persistence.cohort.entity.CohortEntity
import core.application.member.member.domain.model.MemberId
import core.persistence.member.member.entity.MemberEntity
import core.application.member.memberCohort.domain.model.MemberCohort
import core.application.member.memberCohort.domain.model.MemberCohortId
import jakarta.persistence.Column
import jakarta.persistence.ConstraintMode
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "member_cohorts")
class MemberCohortEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_cohort_id", nullable = false, updatable = false)
    val id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val member: MemberEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohort_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val cohort: CohortEntity,
) {
    fun toDomain() =
        MemberCohort(
            id = MemberCohortId(this.id),
            memberId = MemberId(this.member.id),
            cohortId = CohortId(this.cohort.id),
        )
}
