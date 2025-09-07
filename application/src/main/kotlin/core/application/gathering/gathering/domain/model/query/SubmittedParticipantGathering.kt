package core.application.gathering.gathering.domain.model.query

import core.application.gathering.gathering.domain.model.GatheringId

data class SubmittedParticipantGathering(
    val gatheringId: GatheringId,
    val isJoined: Boolean?,
)
