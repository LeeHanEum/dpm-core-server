package core.persistence.gathering.gatheringMember.repository

import core.application.gathering.gathering.domain.model.GatheringId
import core.application.member.member.domain.model.MemberId
import core.persistence.gathering.gatheringMember.entity.GatheringMemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringMemberJpaRepository : JpaRepository<GatheringMemberEntity, Long> {
    fun findByGatheringId(gatheringId: GatheringId): List<GatheringMemberEntity>

    fun findByGatheringIdAndMemberId(
        gatheringId: GatheringId,
        memberId: MemberId,
    ): GatheringMemberEntity?
}
