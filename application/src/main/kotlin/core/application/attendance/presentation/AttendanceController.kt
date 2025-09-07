package core.application.attendance.presentation

import core.application.attendance.application.AttendanceCommandService
import core.application.attendance.application.AttendanceQueryService
import core.application.attendance.domain.model.AttendanceStatus
import core.application.attendance.domain.port.inbound.command.AttendanceRecordCommand
import core.application.attendance.domain.port.inbound.query.GetAttendancesBySessionWeekQuery
import core.application.attendance.domain.port.inbound.query.GetDetailAttendanceBySessionQuery
import core.application.attendance.domain.port.inbound.query.GetDetailMemberAttendancesQuery
import core.application.attendance.domain.port.inbound.query.GetMemberAttendancesQuery
import core.application.attendance.domain.port.inbound.query.GetMyAttendanceBySessionQuery
import core.application.attendance.presentation.dto.request.AttendanceRecordRequest
import core.application.attendance.presentation.dto.request.AttendanceStatusUpdateRequest
import core.application.attendance.presentation.dto.response.AttendanceResponse
import core.application.attendance.presentation.dto.response.DetailAttendancesBySessionResponse
import core.application.attendance.presentation.dto.response.DetailMemberAttendancesResponse
import core.application.attendance.presentation.dto.response.MemberAttendancesResponse
import core.application.attendance.presentation.dto.response.MyDetailAttendanceBySessionResponse
import core.application.attendance.presentation.dto.response.SessionAttendancesResponse
import core.application.attendance.presentation.mapper.AttendanceMapper.toAttendanceResponse
import core.application.attendance.presentation.mapper.AttendanceMapper.toAttendanceStatusUpdateCommand
import core.application.common.exception.CustomResponse
import core.application.member.member.domain.model.MemberId
import core.application.security.annotation.CurrentMemberId
import core.application.session.domain.model.SessionId
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class AttendanceController(
    private val attendanceQueryService: AttendanceQueryService,
    private val attendanceCommandService: AttendanceCommandService,
) : AttendanceApi {
    @PreAuthorize("hasRole('ROLE_DEEPER')")
    @PostMapping("/v1/sessions/{sessionId}/attendances")
    override fun createAttendance(
        @PathVariable sessionId: SessionId,
        @CurrentMemberId memberId: MemberId,
        @RequestBody request: AttendanceRecordRequest,
    ): CustomResponse<AttendanceResponse> {
        val attendedAt = Instant.now()
        val attendanceStatus =
            attendanceCommandService.attendSession(
                AttendanceRecordCommand(
                    sessionId = sessionId,
                    memberId = memberId,
                    attendedAt = attendedAt,
                    attendanceCode = request.attendanceCode,
                ),
            )

        return CustomResponse.ok(toAttendanceResponse(attendanceStatus, attendedAt))
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/v1/sessions/{sessionId}/attendances")
    override fun getAttendancesBySessionId(
        @PathVariable sessionId: SessionId,
        @CurrentMemberId memberId: MemberId,
        @RequestParam(name = "statuses", required = false) statuses: List<AttendanceStatus>?,
        @RequestParam(name = "teams", required = false) teams: List<Int>?,
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "onlyMyTeam", required = false) onlyMyTeam: Boolean?,
        @RequestParam(name = "cursorId", required = false) cursorId: Long?,
    ): CustomResponse<SessionAttendancesResponse> {
        val response =
            attendanceQueryService.getAttendancesBySession(
                GetAttendancesBySessionWeekQuery(
                    sessionId = sessionId,
                    memberId = memberId,
                    statuses = statuses,
                    teams = teams,
                    name = name,
                    onlyMyTeam = onlyMyTeam,
                    cursorId = cursorId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/v1/members/attendances")
    override fun getMemberAttendances(
        @CurrentMemberId memberId: MemberId,
        @RequestParam(name = "statuses", required = false) statuses: List<AttendanceStatus>?,
        @RequestParam(name = "teams", required = false) teams: List<Int>?,
        @RequestParam(name = "name", required = false) name: String?,
        @RequestParam(name = "onlyMyTeam", required = false) onlyMyTeam: Boolean?,
        @RequestParam(name = "cursorId", required = false) cursorId: Long?,
    ): CustomResponse<MemberAttendancesResponse> {
        val response =
            attendanceQueryService.getMemberAttendances(
                GetMemberAttendancesQuery(
                    memberId = memberId,
                    statuses = statuses,
                    teams = teams,
                    name = name,
                    onlyMyTeam = onlyMyTeam,
                    cursorId = cursorId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/v1/sessions/{sessionId}/attendances/{memberId}")
    override fun getAttendanceBySessionIdAndMemberId(
        @PathVariable sessionId: SessionId,
        @PathVariable memberId: MemberId,
    ): CustomResponse<DetailAttendancesBySessionResponse> {
        val response =
            attendanceQueryService.getDetailAttendanceBySession(
                GetDetailAttendanceBySessionQuery(
                    sessionId = sessionId,
                    memberId = memberId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("!hasRole('ROLE_GUEST')")
    @GetMapping("/v1/sessions/{sessionId}/attendances/me")
    override fun getMyAttendanceBySessionId(
        @PathVariable sessionId: SessionId,
        @CurrentMemberId memberId: MemberId,
    ): CustomResponse<MyDetailAttendanceBySessionResponse> {
        val response =
            attendanceQueryService.getMyDetailAttendanceBySession(
                GetMyAttendanceBySessionQuery(
                    sessionId = sessionId,
                    memberId = memberId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @GetMapping("/v1/members/{memberId}/attendances")
    override fun getDetailMemberAttendances(
        @PathVariable memberId: MemberId,
    ): CustomResponse<DetailMemberAttendancesResponse> {
        val response =
            attendanceQueryService.getDetailMemberAttendances(
                GetDetailMemberAttendancesQuery(
                    memberId = memberId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("!hasRole('ROLE_GUEST')")
    @GetMapping("/v1/members/me/attendances")
    override fun getMyDetailAttendances(
        @CurrentMemberId memberId: MemberId,
    ): CustomResponse<DetailMemberAttendancesResponse> {
        val response =
            attendanceQueryService.getDetailMemberAttendances(
                GetDetailMemberAttendancesQuery(
                    memberId = memberId,
                ),
            )

        return CustomResponse.ok(response)
    }

    @PreAuthorize("hasRole('ROLE_ORGANIZER')")
    @PatchMapping("/v1/sessions/{sessionId}/attendances/{memberId}")
    override fun updateAttendance(
        @PathVariable sessionId: SessionId,
        @PathVariable memberId: MemberId,
        @RequestBody request: AttendanceStatusUpdateRequest,
    ): CustomResponse<Void> {
        attendanceCommandService.updateAttendanceStatus(
            toAttendanceStatusUpdateCommand(sessionId, memberId, request),
        )

        return CustomResponse.noContent()
    }
}
