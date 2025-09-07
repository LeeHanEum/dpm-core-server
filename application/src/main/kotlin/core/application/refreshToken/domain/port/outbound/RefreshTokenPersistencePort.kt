package core.application.refreshToken.domain.port.outbound

import core.application.member.member.domain.model.MemberId
import core.application.refreshToken.domain.model.RefreshToken

interface RefreshTokenPersistencePort {
    fun save(refreshToken: RefreshToken): RefreshToken

    fun findByMemberId(memberId: MemberId): RefreshToken?

    fun findByToken(token: String): RefreshToken?

    fun deleteByMemberId(memberId: MemberId)
}
