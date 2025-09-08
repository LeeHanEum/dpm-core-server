package core.domain.session.port.outbound

import core.domain.cohort.model.CohortId
import core.domain.session.model.Session
import core.domain.session.model.SessionId
import java.time.Instant

interface SessionPersistencePort {
    fun findNextSessionBy(startOfToday: Instant): Session?

    fun findAllSessions(cohortId: CohortId): List<Session>

    fun findSessionById(sessionId: SessionId): Session?

    fun save(session: Session): Session

    fun findAllSessionWeeks(cohortId: CohortId): List<Int>
}
