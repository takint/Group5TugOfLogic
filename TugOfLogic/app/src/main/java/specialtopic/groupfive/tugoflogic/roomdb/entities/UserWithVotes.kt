package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithVotes(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val voteTickets: List<VoteTicket>
)