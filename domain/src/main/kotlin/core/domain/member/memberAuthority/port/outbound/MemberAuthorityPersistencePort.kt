package core.domain.member.memberAuthority.port.outbound

import core.domain.member.memberAuthority.model.MemberAuthority

interface MemberAuthorityPersistencePort {
    fun findAuthorityNamesByMemberId(memberId: Long): List<String>

    fun save(memberAuthority: MemberAuthority)
}
