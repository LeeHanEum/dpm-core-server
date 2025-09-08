package core.domain.member.member.port.inbound

import core.domain.member.member.model.MemberId
import core.domain.member.member.model.query.MemberNameAuthorityQueryModel

interface MemberQueryUseCase {
    /**
     * 멤버 식별자에 해당하는 멤버의 이름과 권한 정보를 조회함.
     *
     * @author LeeHanEum
     * @since 2025.07.27
     */
    fun getMemberNameAuthorityByMemberId(memberId: MemberId): List<MemberNameAuthorityQueryModel>
}
