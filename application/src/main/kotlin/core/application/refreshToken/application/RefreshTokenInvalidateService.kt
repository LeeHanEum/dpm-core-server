package core.application.refreshToken.application

import core.application.member.member.domain.model.MemberId
import core.application.refreshToken.domain.port.inbound.RefreshTokenInvalidator
import core.application.refreshToken.domain.port.outbound.RefreshTokenPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RefreshTokenInvalidateService(
    private val refreshTokenPersistencePort: RefreshTokenPersistencePort,
) : RefreshTokenInvalidator {
    override fun destroyRefreshToken(memberId: MemberId) {
        refreshTokenPersistencePort.deleteByMemberId(memberId)
    }
}
