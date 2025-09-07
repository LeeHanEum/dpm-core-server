package core.application.session.domain.port.outbound

import core.application.cohort.domain.model.CohortId
import core.application.session.domain.model.Session
import core.application.session.domain.model.SessionId
import java.time.Instant

interface SessionPersistencePort {
    fun findNextSessionBy(startOfToday: Instant): Session?

    fun findAllSessions(cohortId: CohortId): List<Session>

    fun findSessionById(sessionId: SessionId): Session?

    fun save(session: Session): Session

    fun findAllSessionWeeks(cohortId: CohortId): List<Int>
}
