package core.persistence.member.memberOAuth.repository

import core.application.member.member.domain.model.Member
import core.application.member.memberOAuth.domain.model.MemberOAuth
import core.application.member.memberOAuth.domain.port.MemberOAuthPersistencePort
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
