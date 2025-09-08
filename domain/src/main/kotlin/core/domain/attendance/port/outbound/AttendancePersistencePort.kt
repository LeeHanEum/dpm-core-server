package core.domain.attendance.port.outbound

import core.domain.attendance.model.Attendance
import core.domain.attendance.port.inbound.query.GetAttendancesBySessionWeekQuery
import core.domain.attendance.port.inbound.query.GetDetailAttendanceBySessionQuery
import core.domain.attendance.port.inbound.query.GetDetailMemberAttendancesQuery
import core.domain.attendance.port.inbound.query.GetMemberAttendancesQuery
import core.domain.attendance.port.inbound.query.GetMyAttendanceBySessionQuery
import core.domain.member.member.model.MemberId
import core.domain.session.model.SessionId


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
