package core.application.member.memberAuthority.domain.port.outbound

import core.application.member.memberAuthority.domain.model.MemberAuthority

interface MemberAuthorityPersistencePort {
    fun findAuthorityNamesByMemberId(memberId: Long): List<String>

    fun save(memberAuthority: MemberAuthority)
}
