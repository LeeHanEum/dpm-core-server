package core.application.gathering.gathering.domain.port.outbound

import core.application.bill.bill.domain.model.Bill
import core.application.bill.bill.domain.model.BillId
import core.application.gathering.gathering.domain.model.Gathering
import core.application.gathering.gathering.domain.model.GatheringId
import core.application.gathering.gathering.domain.model.query.SubmittedParticipantGathering
import core.application.member.member.domain.model.MemberId

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
