package core.application.gathering.gatheringMember.presentation.mapper

import core.application.bill.bill.presentation.dto.response.BillDetailGatheringMemberResponse
import core.application.gathering.exception.GatheringMemberException
import core.application.gathering.gatheringMember.domain.model.GatheringMember

object GatheringMemberMapper {
//    fun toGatheringMember(gatheringMemberForBillCreateRequest: GatheringMemberForBillCreateRequest): GatheringMember =
//        GatheringMember(
//            memberId = MemberId(gatheringMemberForBillCreateRequest.memberId),
//            isChecked = gatheringMemberForBillCreateRequest.isCompleted,
//            isJoined = gatheringMemberForBillCreateRequest.isJoined,
//        )

    fun toCreateGatheringMemberResponse(gatheringMember: GatheringMember): BillDetailGatheringMemberResponse =
        BillDetailGatheringMemberResponse(
            gatheringMemberId =
                gatheringMember.id
                    ?: throw GatheringMemberException.GatheringMemberIdRequiredException(),
            memberId = gatheringMember.memberId.value,
            isCompleted = gatheringMember.isViewed,
            isJoined = gatheringMember.isJoined,
        )
}
