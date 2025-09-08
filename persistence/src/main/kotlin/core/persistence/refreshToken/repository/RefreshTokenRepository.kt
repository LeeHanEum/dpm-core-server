package core.persistence.refreshToken.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.deleteQuery
import core.domain.common.jdsl.singleQueryOrNull
import core.domain.member.member.model.MemberId
import core.domain.refreshTokenmodel.refreshTokenmodel.refreshTokenport.outbound.RefreshTokenPersistencePort
import core.persistence.refreshToken.entity.RefreshTokenEntity
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenRepository(
    private val refreshTokenJpaRepository: RefreshTokenJpaRepository,
    private val queryFactory: SpringDataQueryFactory,
) : RefreshTokenPersistencePort {
    override fun save(refreshToken: RefreshToken): RefreshToken =
        refreshTokenJpaRepository.save(RefreshTokenEntity.from(refreshToken)).toDomain()

    override fun findByMemberId(memberId: MemberId): RefreshToken? =
        queryFactory
            .singleQueryOrNull {
                select(entity(RefreshTokenEntity::class))
                from(entity(RefreshTokenEntity::class))
                where(col(RefreshTokenEntity::memberId).equal(memberId.value))
            }?.toDomain()

    override fun findByToken(token: String): RefreshToken? =
        queryFactory
            .singleQueryOrNull {
                select(entity(RefreshTokenEntity::class))
                from(entity(RefreshTokenEntity::class))
                where(col(RefreshTokenEntity::token).equal(token))
            }?.toDomain()

    override fun deleteByMemberId(memberId: MemberId) {
        queryFactory
            .deleteQuery<RefreshTokenEntity> {
                where(col(RefreshTokenEntity::memberId).equal(memberId.value))
            }.executeUpdate()
    }
}
