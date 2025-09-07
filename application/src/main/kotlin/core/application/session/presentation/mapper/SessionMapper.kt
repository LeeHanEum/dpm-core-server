package core.application.session.presentation.mapper

import core.application.session.application.query.SessionWeekQueryModel
import core.application.session.domain.model.Session
import core.application.session.domain.port.inbound.command.SessionCreateCommand
import core.application.session.presentation.dto.request.SessionCreateRequest
import core.application.session.presentation.dto.response.AttendanceTimeResponse
import core.application.session.presentation.dto.response.NextSessionResponse
import core.application.session.presentation.dto.response.SessionDetailResponse
import core.application.session.presentation.dto.response.SessionListDetailResponse
import core.application.session.presentation.dto.response.SessionListResponse
import core.application.session.presentation.dto.response.SessionWeekResponse
import core.application.session.presentation.dto.response.SessionWeeksResponse
import core.application.session.presentation.mapper.TimeMapper.instantToLocalDateTime
import core.application.session.presentation.mapper.TimeMapper.localDateTimeToInstant
import java.time.Instant

object SessionMapper {
    fun toNextSessionResponse(session: Session): NextSessionResponse =
        with(session) {
            NextSessionResponse(
                sessionId = id?.value ?: throw IllegalStateException("Session ID cannot be null"),
                week = week,
                eventName = eventName,
                place = place,
                isOnline = isOnline,
                date = instantToLocalDateTime(date),
            )
        }

    fun toSessionListResponse(sessions: List<Session>): SessionListResponse =
        sessions.run {
            if (isEmpty()) return SessionListResponse(sessions = emptyList())

            SessionListResponse(
                sessions =
                    map {
                        SessionListDetailResponse(
                            id = it.id!!.value,
                            week = it.week,
                            eventName = it.eventName,
                            date = instantToLocalDateTime(it.date),
                        )
                    },
            )
        }

    fun toSessionDetailResponse(session: Session): SessionDetailResponse =
        with(session) {
            SessionDetailResponse(
                sessionId = id!!.value,
                week = week,
                eventName = eventName,
                place = place,
                isOnline = isOnline,
                date = instantToLocalDateTime(date),
                attendanceStartTime =
                    instantToLocalDateTime(session.attendancePolicy.attendanceStart),
                attendanceCode = session.attendancePolicy.attendanceCode,
            )
        }

    fun toAttendanceTimeResponse(attendanceStartTime: Instant) =
        AttendanceTimeResponse(
            attendanceStartTime =
                instantToLocalDateTime(attendanceStartTime),
        )

    fun toSessionCreateCommand(
        request: SessionCreateRequest,
        startHour: Long,
    ) = SessionCreateCommand(
        cohortId = request.cohortId,
        date = localDateTimeToInstant(request.date),
        week = request.week,
        place = request.place,
        eventName = request.eventName,
        isOnline = request.isOnline,
        startHour = startHour,
    )

    fun toSessionWeeksResponse(model: List<SessionWeekQueryModel>): SessionWeeksResponse {
        if (model.isEmpty()) {
            return SessionWeeksResponse(
                sessions = emptyList(),
            )
        }

        return SessionWeeksResponse(
            sessions = model.map { SessionWeekResponse(id = it.sessionId, week = it.week) },
        )
    }
}
