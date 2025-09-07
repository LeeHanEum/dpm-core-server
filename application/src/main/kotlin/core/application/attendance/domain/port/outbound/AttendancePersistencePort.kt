package core.application.attendance.domain.port.outbound

import core.application.attendance.application.query.model.MemberAttendanceQueryModel
import core.application.attendance.application.query.model.MemberDetailAttendanceQueryModel
import core.application.attendance.application.query.model.MemberSessionAttendanceQueryModel
import core.application.attendance.application.query.model.MyDetailAttendanceQueryModel
import core.application.attendance.application.query.model.SessionAttendanceQueryModel
import core.application.attendance.application.query.model.SessionDetailAttendanceQueryModel
import core.application.attendance.domain.model.Attendance
import core.application.attendance.domain.port.inbound.query.GetAttendancesBySessionWeekQuery
import core.application.attendance.domain.port.inbound.query.GetDetailAttendanceBySessionQuery
import core.application.attendance.domain.port.inbound.query.GetDetailMemberAttendancesQuery
import core.application.attendance.domain.port.inbound.query.GetMemberAttendancesQuery
import core.application.attendance.domain.port.inbound.query.GetMyAttendanceBySessionQuery
import core.application.member.member.domain.model.MemberId
import core.application.session.domain.model.SessionId

interface AttendancePersistencePort {
    fun findAttendanceBy(
        sessionId: SessionId,
        memberId: MemberId,
    ): Attendance?

    fun save(attendance: Attendance)

    fun findSessionAttendancesByQuery(
        query: GetAttendancesBySessionWeekQuery,
        myTeamNumber: Int?,
    ): List<SessionAttendanceQueryModel>

    fun findMemberAttendancesByQuery(
        query: GetMemberAttendancesQuery,
        myTeamNumber: Int?,
    ): List<MemberAttendanceQueryModel>

    fun findDetailAttendanceBySession(query: GetDetailAttendanceBySessionQuery): SessionDetailAttendanceQueryModel?

    fun findDetailMemberAttendance(query: GetDetailMemberAttendancesQuery): MemberDetailAttendanceQueryModel?

    fun findMemberSessionAttendances(query: GetDetailMemberAttendancesQuery): List<MemberSessionAttendanceQueryModel>

    fun findMyDetailAttendanceBySession(query: GetMyAttendanceBySessionQuery): MyDetailAttendanceQueryModel?

    fun saveInBatch(attendances: List<Attendance>)
}
