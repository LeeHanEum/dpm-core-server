package core.persistence.member.memberAuthority.repository

import core.persistence.member.memberAuthority.entity.MemberAuthorityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberAuthorityJpaRepository : JpaRepository<MemberAuthorityEntity, Long>
