package core.application.session.application

import core.application.cohort.domain.port.inbound.CohortQueryUseCase
import core.application.session.application.query.SessionWeekQueryModel
import core.application.session.application.exception.SessionNotFoundException
import core.application.session.domain.model.Session
import core.application.session.domain.model.SessionId
import core.application.session.domain.port.outbound.SessionPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Service
@Transactional(readOnly = true)
class SessionQueryService(
    private val cohortQueryUseCase: CohortQueryUseCase,
    private val sessionPersistencePort: SessionPersistencePort,
) {
    fun getNextSession(): Session? {
        val koreaZone = ZoneId.of("Asia/Seoul")
        val today = LocalDate.ofInstant(Instant.now(), koreaZone)
        val startOfToday = today.atStartOfDay(koreaZone).toInstant()

        return sessionPersistencePort.findNextSessionBy(startOfToday)
    }

    fun getAllSessions(): List<Session> {
        val cohortId = cohortQueryUseCase.getLatestCohortId()

        return sessionPersistencePort.findAllSessions(cohortId)
    }

    fun getSessionById(sessionId: SessionId): Session =
        sessionPersistencePort.findSessionById(sessionId)
            ?: throw SessionNotFoundException()

    fun getAttendanceTime(sessionId: SessionId): Instant =
        sessionPersistencePort
            .findSessionById(sessionId)
            ?.attendancePolicy
            ?.attendanceStart
            ?: throw SessionNotFoundException()

    fun getSessionWeeks(): List<SessionWeekQueryModel> {
        val cohortId = cohortQueryUseCase.getLatestCohortId()

        return sessionPersistencePort
            .findAllSessions(cohortId)
            .map { SessionWeekQueryModel(sessionId = it.id!!, week = it.week) }
    }
}
