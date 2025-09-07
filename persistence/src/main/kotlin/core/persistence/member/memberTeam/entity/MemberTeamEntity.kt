package core.persistence.member.memberTeam.entity

import core.application.member.member.domain.model.MemberId
import core.application.member.memberTeam.domain.model.MemberTeam
import core.application.member.memberTeam.domain.model.MemberTeamId
import core.application.team.domain.model.TeamId
import core.persistence.member.member.entity.MemberEntity
import core.persistence.team.entity.TeamEntity
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
@Table(name = "member_teams")
class MemberTeamEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_team_id", nullable = false, updatable = false)
    val id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val member: MemberEntity,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    val team: TeamEntity,
) {
    fun toDomain() =
        MemberTeam(
            id = MemberTeamId(this.id),
            memberId = MemberId(this.member.id),
            teamId = TeamId(this.team.id),
        )
}
