package core.persistence.bill.bill.repository

import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import core.application.bill.bill.domain.model.Bill
import core.application.bill.bill.domain.model.BillId
import core.application.bill.bill.domain.port.outbound.BillPersistencePort
import core.persistence.bill.bill.entity.BillEntity
import core.persistence.bill.exception.BillException
import core.persistence.bill.bill.repository.BillJpaRepository
import org.jooq.DSLContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BillRepository(
    private val billJpaRepository: BillJpaRepository,
    private val queryFactory: SpringDataQueryFactory,
    private val dsl: DSLContext,
) : BillPersistencePort {
    override fun save(bill: Bill): BillId = BillId(billJpaRepository.save(BillEntity.from(bill)).id)

    override fun findById(billId: BillId): Bill =
        billJpaRepository.findByIdOrNull(billId.value)?.toDomain() ?: throw BillException.BillNotFoundException()

    override fun findAllBills(): List<Bill> = billJpaRepository.findAll().map { it.toDomain() }

    override fun closeBillParticipation(bill: Bill): Int =
        queryFactory
            .updateQuery(BillEntity::class) {
                set(col(BillEntity::billStatus), bill.billStatus.name)
                set(col(BillEntity::updatedAt), bill.updatedAt)
                where(col(BillEntity::id).equal(bill.id?.value ?: throw BillException.BillIdRequiredException()))
            }.executeUpdate()
}
