package core.application.gathering.gatheringMember.domain.port.inbound

import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gatheringMember.domain.model.GatheringMember

interface GatheringMemberQueryUseCase {
    fun getGatheringMemberByGatheringId(gatheringId: GatheringId): List<GatheringMember>
}
