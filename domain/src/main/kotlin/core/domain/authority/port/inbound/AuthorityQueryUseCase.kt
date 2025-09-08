package core.domain.authority.port.inbound

import core.domain.authority.model.AuthorityId
import core.domain.authority.model.AuthorityType
import core.domain.member.member.model.MemberId

interface AuthorityQueryUseCase {
    fun getAuthoritiesByExternalId(externalId: String): List<String>

    fun getAuthoritiesByMemberId(memberId: MemberId): List<String>

    fun getAuthorityIdByType(authorityType: AuthorityType): AuthorityId
}
