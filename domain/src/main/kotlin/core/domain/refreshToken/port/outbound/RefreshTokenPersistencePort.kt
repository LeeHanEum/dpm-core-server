package core.domain.refreshToken.port.outbound

import core.domain.member.member.model.MemberId
import core.domain.refreshToken.model.RefreshToken

interface RefreshTokenPersistencePort {
    fun save(refreshToken: RefreshToken): RefreshToken

    fun findByMemberId(memberId: MemberId): RefreshToken?

    fun findByToken(token: String): RefreshToken?

    fun deleteByMemberId(memberId: MemberId)
}
