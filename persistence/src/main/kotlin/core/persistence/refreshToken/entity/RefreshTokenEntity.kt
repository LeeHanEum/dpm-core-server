package core.persistence.refreshToken.entity

import core.domain.member.member.model.MemberId
import core.domain.refreshTokenmodel.RefreshToken
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity(
    @Id
    val memberId: Long,
    @Lob
    @Column(columnDefinition = "TEXT")
    var token: String,
) {
    fun toDomain(): RefreshToken =
        RefreshToken(
            memberId = MemberId(this.memberId),
            token = this.token,
        )

    companion object {
        fun from(domain: RefreshToken) =
            RefreshTokenEntity(
                memberId = domain.memberId.value,
                token = domain.token,
            )
    }
}
