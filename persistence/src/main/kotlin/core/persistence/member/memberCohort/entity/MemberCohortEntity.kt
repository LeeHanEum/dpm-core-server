package core.persistence.member.memberCohort.entity

import core.domain.cohort.model.CohortId
import core.domain.member.member.model.MemberId
import core.domain.member.memberCohort.model.MemberCohort
import core.domain.member.memberCohort.model.MemberCohortId
import core.persistence.cohort.entity.CohortEntity
import core.persistence.member.member.entity.MemberEntity
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
