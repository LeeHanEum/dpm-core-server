package core.persistence.attendance.event.listener

import core.application.attendance.application.AttendanceCommandService
import core.application.session.domain.event.SessionCreateEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class SessionCreateEventListener(
    private val attendanceCommandService: AttendanceCommandService,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handle(event: SessionCreateEvent) {
        attendanceCommandService.createAttendances(
            sessionId = event.sessionId,
        )
    }
}
