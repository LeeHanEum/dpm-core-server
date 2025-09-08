package core.persistence.member.member.repository

import core.persistence.member.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun findByEmail(email: String): MemberEntity

    fun existsByIdAndDeletedAtIsNotNull(memberId: Long): Boolean
}
