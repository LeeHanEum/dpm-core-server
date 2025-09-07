package core.application.member.member.domain.port.inbound

import core.application.authority.domain.model.AuthorityId
import core.application.member.member.domain.model.MemberId

interface MemberQueryByAuthorityUseCase {
    /**
     * 권한 식별자 목록에 해당하는 모든 멤버 식별자를 조회함.
     *
     * @author leehaneum
     * @since 2025.07.22
     */
    fun findAllMemberIdByAuthorityIds(authorityIds: List<AuthorityId>): List<MemberId>
}
