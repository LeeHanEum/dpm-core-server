package core.application.gathering.gatheringMember.application

import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gatheringMember.domain.model.GatheringMember
import core.application.gathering.gatheringMember.domain.port.outbound.GatheringMemberPersistencePort
import core.application.member.member.domain.model.MemberId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GatheringMemberCommandService(
    private val gatheringMemberPersistencePort: GatheringMemberPersistencePort,
) {
    fun saveEachGatheringMembers(
        memberIds: List<MemberId>,
        gathering: Gathering,
    ) = memberIds.map { memberId ->
        gatheringMemberPersistencePort.save(GatheringMember.create(gathering.id!!, memberId), gathering)
    }

    fun markAsChecked(gatheringMember: GatheringMember) {
        gatheringMember.markAsChecked()
        gatheringMemberPersistencePort.updateGatheringMemberById(gatheringMember)
    }

    fun markAsJoined(
        gatheringMember: GatheringMember,
        isJoined: Boolean,
    ) {
        gatheringMember.markAsJoined(isJoined)
        gatheringMemberPersistencePort.updateGatheringMemberById(gatheringMember)
    }

    fun markAsGatheringParticipationSubmitConfirm(gatheringMember: GatheringMember) {
        gatheringMember.gatheringParticipationSubmittedConfirm()
        gatheringMemberPersistencePort.markAsGatheringParticipationSubmitConfirm(gatheringMember)
    }

    fun updateDeposit(gatheringMember: GatheringMember) {
        gatheringMemberPersistencePort.updateGatheringMemberById(gatheringMember)
    }
}
