package core.persistence.session.repository

import core.persistence.session.entity.SessionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SessionJpaRepository : JpaRepository<SessionEntity, Long>
