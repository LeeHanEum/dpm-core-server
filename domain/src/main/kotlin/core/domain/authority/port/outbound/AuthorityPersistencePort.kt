package core.domain.authority.port.outbound

import core.domain.authority.model.Authority
import core.domain.authority.model.AuthorityId
import core.domain.member.member.model.MemberId

interface AuthorityPersistencePort {
    fun findAll(): List<Authority>

    fun findAllByMemberExternalId(externalId: String): List<String>

    fun findAllByMemberId(memberId: MemberId): List<String>

    fun findAuthorityIdByName(authorityName: String): AuthorityId?
}
