package core.application.attendance.presentation.mapper

import core.application.attendance.application.query.model.MemberDetailAttendanceQueryModel
import core.application.attendance.application.query.model.MemberSessionAttendanceQueryModel
import core.application.attendance.application.query.model.MyDetailAttendanceQueryModel
import core.application.attendance.application.query.model.SessionAttendanceQueryModel
import core.application.attendance.application.query.model.SessionDetailAttendanceQueryModel
import core.application.attendance.domain.model.AttendanceStatus
import core.application.attendance.domain.port.inbound.command.AttendanceStatusUpdateCommand
import core.application.attendance.presentation.dto.request.AttendanceStatusUpdateRequest
import core.application.attendance.presentation.dto.response.AttendanceResponse
import core.application.attendance.presentation.dto.response.DetailAttendancesBySessionResponse
import core.application.attendance.presentation.dto.response.DetailMemberAttendancesResponse
import core.application.attendance.presentation.dto.response.DetailMemberInfo
import core.application.attendance.presentation.dto.response.MemberAttendanceResponse
import core.application.attendance.presentation.dto.response.MemberAttendancesResponse
import core.application.attendance.presentation.dto.response.MemberDetailAttendanceCountInfo
import core.application.attendance.presentation.dto.response.MemberDetailSessionInfo
import core.application.attendance.presentation.dto.response.MyDetailAttendanceBySessionResponse
import core.application.attendance.presentation.dto.response.MyDetailAttendanceInfo
import core.application.attendance.presentation.dto.response.MyDetailAttendanceSessionInfo
import core.application.attendance.presentation.dto.response.SessionAttendancesResponse
import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId
import core.application.session.presentation.mapper.TimeMapper.instantToLocalDateTime
import java.time.Instant

object AttendanceMapper {
    fun toAttendanceResponse(
        attendanceStatus: AttendanceStatus,
        attendedAt: Instant,
    ): AttendanceResponse =
        AttendanceResponse(
            attendanceStatus = attendanceStatus.name,
            attendedAt = instantToLocalDateTime(attendedAt),
        )

    fun toSessionAttendancesResponse(
        members: List<SessionAttendanceQueryModel>,
        hasNext: Boolean,
        nextCursorId: Long?,
    ): SessionAttendancesResponse =
        SessionAttendancesResponse(
            members =
                members.map { member ->
                    MemberAttendanceResponse(
                        id = member.id,
                        name = member.name,
                        teamNumber = member.teamNumber,
                        part = member.part,
                        attendanceStatus = member.attendanceStatus,
                    )
                },
            hasNext = hasNext,
            nextCursorId = nextCursorId,
        )

    fun toMemberAttendancesResponse(
        members: List<MemberAttendanceResponse>,
        hasNext: Boolean,
        nextCursorId: Long?,
    ): MemberAttendancesResponse =
        MemberAttendancesResponse(
            members = members,
            hasNext = hasNext,
            nextCursorId = nextCursorId,
        )

    fun toAttendanceStatusUpdateCommand(
        sessionId: SessionId,
        memberId: MemberId,
        request: AttendanceStatusUpdateRequest,
    ): AttendanceStatusUpdateCommand =
        AttendanceStatusUpdateCommand(
            sessionId = sessionId,
            memberId = memberId,
            attendanceStatus = AttendanceStatus.valueOf(request.attendanceStatus),
        )

    fun toDetailAttendanceBySessionResponse(
        model: SessionDetailAttendanceQueryModel,
        evaluation: String,
    ): DetailAttendancesBySessionResponse =
        DetailAttendancesBySessionResponse(
            member =
                DetailAttendancesBySessionResponse.DetailMember(
                    id = model.memberId,
                    name = model.memberName,
                    teamNumber = model.teamNumber,
                    part = model.part,
                    attendanceStatus = evaluation,
                ),
            session =
                DetailAttendancesBySessionResponse.DetailSession(
                    id = model.sessionId,
                    week = model.sessionWeek,
                    eventName = model.sessionEventName,
                    date = instantToLocalDateTime(model.sessionDate),
                ),
            attendance =
                DetailAttendancesBySessionResponse.DetailAttendance(
                    status = model.attendanceStatus,
                    attendedAt = model.attendedAt?.let { instantToLocalDateTime(it) },
                ),
        )

    fun toDetailMemberAttendancesResponse(
        memberAttendanceModel: MemberDetailAttendanceQueryModel,
        sessionAttendancesModel: List<MemberSessionAttendanceQueryModel>,
        evaluation: String,
    ): DetailMemberAttendancesResponse =
        DetailMemberAttendancesResponse(
            member =
                DetailMemberInfo(
                    id = memberAttendanceModel.memberId,
                    name = memberAttendanceModel.memberName,
                    teamNumber = memberAttendanceModel.teamNumber,
                    part = memberAttendanceModel.part,
                    attendanceStatus = evaluation,
                ),
            attendance =
                MemberDetailAttendanceCountInfo(
                    presentCount = memberAttendanceModel.presentCount,
                    lateCount = memberAttendanceModel.lateCount,
                    excusedAbsentCount = memberAttendanceModel.excusedAbsentCount,
                    absentCount = memberAttendanceModel.onlineAbsentCount + memberAttendanceModel.offlineAbsentCount,
                ),
            sessions =
                sessionAttendancesModel.map { session ->
                    MemberDetailSessionInfo(
                        id = session.sessionId,
                        week = session.sessionWeek,
                        eventName = session.sessionEventName,
                        date = instantToLocalDateTime(session.sessionDate),
                        attendanceStatus = session.sessionAttendanceStatus,
                    )
                },
        )

    fun toMyDetailAttendanceBySessionResponse(myAttendanceModel: MyDetailAttendanceQueryModel) =
        MyDetailAttendanceBySessionResponse(
            attendance =
                MyDetailAttendanceInfo(
                    status = myAttendanceModel.attendanceStatus,
                    attendedAt = myAttendanceModel.attendedAt?.let { instantToLocalDateTime(it) },
                ),
            session =
                MyDetailAttendanceSessionInfo(
                    week = myAttendanceModel.sessionWeek,
                    eventName = myAttendanceModel.sessionEventName,
                    date = instantToLocalDateTime(myAttendanceModel.sessionDate),
                    place = myAttendanceModel.sessionPlace,
                ),
        )
}
