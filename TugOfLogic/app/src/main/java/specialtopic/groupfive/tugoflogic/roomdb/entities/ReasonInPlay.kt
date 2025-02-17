package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import specialtopic.groupfive.tugoflogic.roomdb.TugSide

@Entity(tableName = "reason_in_plays")
data class ReasonInPlay(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val ripId: Int,

    @ColumnInfo(name = "mainClaimId")
    var mainClaimId: Int,

    @ColumnInfo(name = "studentId")
    val studentId: Int,

    @ColumnInfo(name = "reasonStatement")
    var reasonStatement: String,

    @ColumnInfo(name = "description")
    var description: String,

    // Agree or Disagree with the MainClaim
    @ColumnInfo(name = "logicSide")
    var logicSide: String
) {
    // Additional process
}