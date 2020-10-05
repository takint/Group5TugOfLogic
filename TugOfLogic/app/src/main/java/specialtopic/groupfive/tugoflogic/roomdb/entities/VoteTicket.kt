package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.*
import specialtopic.groupfive.tugoflogic.roomdb.TugSide

@Entity(tableName = "vote_tickets")
data class VoteTicket(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val voteId: Int,

    val gameId: Int,

    val userId: Int,

    val mainClaimId: Int?, //(null if vote for Rip)

    val RipId: Int?, // (null if vote for MC)

    val statementToVote: String,

    // Agree or Disagree
    val voteSide: TugSide
) {
    // Additional process
}