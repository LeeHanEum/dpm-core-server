package core.application.authority.domain.port.outbound

import core.application.authority.domain.model.Authority
import core.application.authority.domain.model.AuthorityId
import core.application.member.member.domain.model.MemberId

interface AuthorityPersistencePort {
    fun findAll(): List<Authority>

    fun findAllByMemberExternalId(externalId: String): List<String>

    fun findAllByMemberId(memberId: MemberId): List<String>

    fun findAuthorityIdByName(authorityName: String): AuthorityId?
}
