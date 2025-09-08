package core.application.attendance.application

import core.application.attendance.application.exception.AttendanceNotFoundException
import core.application.attendance.domain.model.Attendance
import core.application.attendance.domain.model.AttendanceStatus
import core.application.attendance.domain.port.inbound.command.AttendanceCreateCommand
import core.application.attendance.domain.port.inbound.command.AttendanceRecordCommand
import core.application.attendance.domain.port.inbound.command.AttendanceStatusUpdateCommand
import core.application.attendance.domain.port.outbound.AttendancePersistencePort
import core.application.cohort.application.config.CohortProperties
import core.application.member.member.application.MemberQueryService
import core.application.member.member.application.exception.CohortMembersNotFoundException
import core.application.session.application.SessionQueryService
import core.application.session.application.exception.CheckedAttendanceException
import core.application.session.domain.model.SessionId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AttendanceCommandService(
    private val attendancePersistencePort: AttendancePersistencePort,
    private val sessionQueryService: SessionQueryService,
    private val memberService: MemberQueryService,
    private val cohortProperties: CohortProperties,
) {
    fun attendSession(command: AttendanceRecordCommand): AttendanceStatus {
        val attendance =
            attendancePersistencePort
                .findAttendanceBy(command.sessionId, command.memberId)
                ?.also {
                    if (it.isAttended()) {
                        throw CheckedAttendanceException()
                    }
                } ?: throw AttendanceNotFoundException()

        val status =
            sessionQueryService
                .getSessionById(command.sessionId)
                .attend(command.attendedAt, command.attendanceCode)

        attendance.markAttendance(status, command.attendedAt)
        attendancePersistencePort.save(attendance)

        return status
    }

    fun updateAttendanceStatus(command: AttendanceStatusUpdateCommand) {
        val attendance =
            attendancePersistencePort
                .findAttendanceBy(command.sessionId, command.memberId)
                ?: throw AttendanceNotFoundException()

        attendance.updateStatus(command.attendanceStatus)
        attendancePersistencePort.save(attendance)
    }

    fun createAttendances(sessionId: SessionId) {
        val memberIds = memberService.getMembersByCohort(cohortProperties.value)
        if (memberIds.isEmpty()) {
            throw CohortMembersNotFoundException()
        }

        val attendances =
            memberIds
                .map { memberId ->
                    Attendance.create(
                        AttendanceCreateCommand(sessionId, memberId),
                    )
                }

        attendancePersistencePort.saveInBatch(attendances)
    }
}
