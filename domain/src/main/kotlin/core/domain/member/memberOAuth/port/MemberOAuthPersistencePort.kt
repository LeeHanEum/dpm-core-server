package core.domain.member.memberOAuth.port

import core.domain.member.member.model.Member
import core.domain.member.memberOAuth.model.MemberOAuth

interface MemberOAuthPersistencePort {
    fun save(
        memberOAuth: MemberOAuth,
        member: Member,
    )
}
