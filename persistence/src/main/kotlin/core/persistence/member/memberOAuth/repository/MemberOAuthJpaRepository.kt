package core.persistence.member.memberOAuth.repository

import core.persistence.member.memberOAuth.entity.MemberOAuthEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberOAuthJpaRepository : JpaRepository<MemberOAuthEntity, Long>
