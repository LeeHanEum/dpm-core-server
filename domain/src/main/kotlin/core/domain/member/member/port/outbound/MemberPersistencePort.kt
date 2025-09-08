package core.domain.member.member.port.outbound

import core.domain.authority.model.AuthorityId
import core.domain.member.member.model.Member
import core.domain.member.member.model.MemberId
import core.domain.member.member.model.query.MemberNameAuthorityQueryModel

interface MemberPersistencePort {
    fun save(member: Member): Member

    fun findByEmail(email: String): Member?

    fun findById(memberId: Long): Member?

    fun existsById(memberId: Long): Boolean

    fun existsDeletedMemberById(memberId: Long): Boolean

    fun findAllMemberIdByAuthorityIds(authorityIds: List<AuthorityId>): List<MemberId>

    fun findAllByCohort(value: String): List<MemberId>

    fun findMemberNameAndAuthorityByMemberId(memberId: MemberId): List<MemberNameAuthorityQueryModel>

    fun findMemberTeamByMemberId(memberId: MemberId): Int?
}
