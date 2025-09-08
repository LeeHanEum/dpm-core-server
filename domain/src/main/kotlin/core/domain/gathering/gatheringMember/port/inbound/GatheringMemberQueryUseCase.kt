package core.domain.gathering.gatheringMember.port.inbound

import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gatheringMember.model.GatheringMember

interface GatheringMemberQueryUseCase {
    fun getGatheringMemberByGatheringId(gatheringId: GatheringId): List<GatheringMember>
}
