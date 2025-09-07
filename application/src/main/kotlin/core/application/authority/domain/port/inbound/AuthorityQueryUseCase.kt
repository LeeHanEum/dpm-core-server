package core.application.authority.domain.port.inbound

import core.application.authority.domain.model.AuthorityId
import core.application.authority.domain.model.AuthorityType
import core.application.member.member.domain.model.MemberId

interface AuthorityQueryUseCase {
    fun getAuthoritiesByExternalId(externalId: String): List<String>

    fun getAuthoritiesByMemberId(memberId: MemberId): List<String>

    fun getAuthorityIdByType(authorityType: AuthorityType): AuthorityId
}
