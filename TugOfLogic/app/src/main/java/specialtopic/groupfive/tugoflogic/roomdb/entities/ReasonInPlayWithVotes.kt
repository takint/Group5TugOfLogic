package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ReasonInPlayWithVotes (
    @Embedded val rip: ReasonInPlay,
    @Relation(
        parentColumn = "id",
        entityColumn = "RipId"
    )
    val voteTickets: List<VoteTicket>
)