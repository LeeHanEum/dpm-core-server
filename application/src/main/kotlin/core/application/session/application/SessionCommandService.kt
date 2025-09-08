package core.application.session.application

import core.application.session.domain.event.SessionCreateEvent
import core.application.session.application.exception.InvalidSessionIdException
import core.application.session.application.exception.SessionNotFoundException
import core.application.session.domain.model.Session
import core.application.session.domain.model.SessionId
import core.application.session.domain.port.inbound.command.SessionCreateCommand
import core.application.session.domain.port.outbound.SessionPersistencePort
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional
class SessionCommandService(
    private val sessionPersistencePort: SessionPersistencePort,
    private val eventPublisher: ApplicationEventPublisher,
) {
    fun updateSessionStartTime(
        sessionId: SessionId,
        attendanceStartTime: Instant,
    ) {
        val session =
            sessionPersistencePort.findSessionById(sessionId)
                ?: throw SessionNotFoundException()

        session.updateAttendanceStartTime(attendanceStartTime)

        sessionPersistencePort.save(session)
    }

    fun createSession(command: SessionCreateCommand) {
        val newSession = Session.create(command)

        val savedSession = sessionPersistencePort.save(newSession)

        eventPublisher.publishEvent(SessionCreateEvent(savedSession.id ?: throw InvalidSessionIdException()))
    }
}
