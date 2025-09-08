package core.persistence.gathering.gatheringMember.repository

import core.domain.gathering.gathering.model.GatheringId
import core.domain.member.member.model.MemberId
import core.persistence.gathering.gatheringMember.entity.GatheringMemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GatheringMemberJpaRepository : JpaRepository<GatheringMemberEntity, Long> {
    fun findByGatheringId(gatheringId: GatheringId): List<GatheringMemberEntity>

    fun findByGatheringIdAndMemberId(
        gatheringId: GatheringId,
        memberId: MemberId,
    ): GatheringMemberEntity?
}
