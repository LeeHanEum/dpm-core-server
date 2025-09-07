package core.application.member.member.application

import core.application.authority.domain.model.AuthorityType.DEEPER
import core.application.member.member.application.exception.MemberNotFoundException
import core.application.member.member.domain.port.outbound.MemberPersistencePort
import core.application.member.member.presentation.request.InitMemberDataRequest
import core.application.member.memberAuthority.application.MemberAuthorityService
import core.application.member.memberCohort.application.MemberCohortService
import core.application.member.memberTeam.application.MemberTeamService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
    private val memberPersistencePort: MemberPersistencePort,
    private val memberQueryService: MemberQueryService,
    private val memberAuthorityService: MemberAuthorityService,
    private val memberTeamService: MemberTeamService,
    private val memberCohortService: MemberCohortService,
) {
    /**
     * 회원 가입 시 팀 정보 및 파트 정보를 주입하고, 멤버를 ACTIVE 상태로 변경함. (DEV)
     *
     * @throws MemberNotFoundException
     * @throws AuthorityNotFoundException
     *
     * @author LeeHanEum
     * @since 2025.08.02
     */
    fun initMemberDataAndApprove(request: InitMemberDataRequest) {
        request.members.forEach {
            memberPersistencePort.save(
                memberQueryService.getMemberById(it.memberId).apply {
                    updatePart(it.memberPart)
                    activate()
                },
            )
            memberAuthorityService.setMemberAuthorityByMemberId(it.memberId, DEEPER)
            memberTeamService.addMemberToTeam(it.memberId, request.teamId)
            memberCohortService.addMemberToCohort(it.memberId)
        }
    }
}
