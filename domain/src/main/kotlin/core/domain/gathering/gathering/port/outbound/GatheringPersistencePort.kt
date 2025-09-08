package core.domain.gathering.gathering.port.outbound

import core.domain.bill.bill.model.Bill
import core.domain.bill.bill.model.BillId
import core.domain.gathering.gathering.model.Gathering
import core.domain.gathering.gathering.model.GatheringId
import core.domain.gathering.gathering.model.query.SubmittedParticipantGathering
import core.domain.member.member.model.MemberId

interface GatheringPersistencePort {
    fun findGatheringById(id: Long): Gathering

    fun save(
        bill: Bill,
        gathering: Gathering,
    ): Gathering

    fun findById(id: Long): Gathering

    fun findByBillId(billId: BillId): List<Gathering>

    fun saveAll(
        bill: Bill,
        gatherings: List<Gathering>,
    )

    fun findAllByGatheringIds(gatheringIds: List<GatheringId>): List<Gathering>

    fun findAllGatheringIdsByBillId(billId: BillId): List<GatheringId>

    fun getSubmittedParticipantEachGathering(
        billId: BillId,
        memberId: MemberId,
    ): List<SubmittedParticipantGathering>
}
