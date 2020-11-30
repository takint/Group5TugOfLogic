package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "main_claims")
data class MainClaim(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @field:Json(name="mainClaimId") val mainClaimId: Int,

    @field:Json(name="gameId") val gameId: Int,

    @field:Json(name="statement") val statement: String,

    @field:Json(name="numOfAgree") val numOfAgree: Int,

    @field:Json(name="numOfDisagree") val numOfDisagree: Int
) {
    // Additional process
}