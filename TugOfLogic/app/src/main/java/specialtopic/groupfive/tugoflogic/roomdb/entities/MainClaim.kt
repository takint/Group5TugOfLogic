package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_claims")
data class MainClaim(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val mainClaimId: Int,

    val gameId: Int,

    val statement: String,

    val numOfAgree: Int,

    val numOfDisagree: Int
) {
    // Additional process
}