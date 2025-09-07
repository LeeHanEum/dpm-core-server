package core.application.member.memberAuthority.application

import core.application.authority.domain.model.AuthorityType
import core.application.authority.domain.port.inbound.AuthorityQueryUseCase
import core.application.member.member.domain.model.MemberId
import core.application.member.memberAuthority.domain.model.MemberAuthority
import core.application.member.memberAuthority.domain.port.outbound.MemberAuthorityPersistencePort
import org.springframework.stereotype.Service

@Service
class MemberAuthorityService(
    private val memberAuthorityPersistencePort: MemberAuthorityPersistencePort,
    private val authorityQueryUseCase: AuthorityQueryUseCase,
) {
    /**
     * 멤버 식별자로 해당 멤버가 소유한 권한 이름 목록을 조회함.
     *
     * @author LeeHanEum
     * @since 2025.07.24
     */
    fun getAuthorityNamesByMemberId(memberId: MemberId): List<String> =
        memberAuthorityPersistencePort
            .findAuthorityNamesByMemberId(memberId.value)

    /**
     * 권한 타입으로 권한 식별자를 조회하고, 해당 권한 식별자로 멤버 권한을 추가함.
     *
     * @author LeeHanEum
     * @since 2025.08.02
     */
    fun setMemberAuthorityByMemberId(
        memberId: MemberId,
        authorityType: AuthorityType,
    ) {
        val authorityId = authorityQueryUseCase.getAuthorityIdByType(authorityType)
        memberAuthorityPersistencePort.save(MemberAuthority.of(memberId, authorityId))
    }
}
