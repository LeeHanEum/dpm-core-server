package core.persistence.member.memberOAuth.repository

import core.domain.member.member.model.Member
import core.domain.member.member.model.MemberOAuth
import core.domain.member.member.port.MemberOAuthPersistencePort
import core.persistence.member.memberOAuth.entity.MemberOAuthEntity
import org.springframework.stereotype.Repository

@Repository
class MemberOAuthRepository(
    private val memberOAuthJpaRepository: MemberOAuthJpaRepository,
) : MemberOAuthPersistencePort {
    override fun save(
        memberOAuth: MemberOAuth,
        member: Member,
    ) {
        memberOAuthJpaRepository.save(MemberOAuthEntity.of(memberOAuth, member))
    }
}
