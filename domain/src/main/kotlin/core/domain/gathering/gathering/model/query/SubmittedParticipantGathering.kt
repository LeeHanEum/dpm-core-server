package core.domain.gathering.gathering.model.query

import core.domain.gathering.gathering.model.GatheringId

data class SubmittedParticipantGathering(
    val gatheringId: GatheringId,
    val isJoined: Boolean?,
)
