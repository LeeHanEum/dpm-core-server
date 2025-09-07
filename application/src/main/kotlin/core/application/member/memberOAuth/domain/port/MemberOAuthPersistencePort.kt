package core.application.member.memberOAuth.domain.port

import core.application.member.member.domain.model.Member
import core.application.member.memberOAuth.domain.model.MemberOAuth

interface MemberOAuthPersistencePort {
    fun save(
        memberOAuth: MemberOAuth,
        member: Member,
    )
}
