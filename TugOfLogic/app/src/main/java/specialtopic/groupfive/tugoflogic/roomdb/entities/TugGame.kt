package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.*


@Entity(tableName = "games")
data class TugGame(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @field:Json(name="gameId") val gameId: Int?,

    @field:Json(name="startTime") val startTime: Date,

    @field:Json(name="endTime") val endTime: Date,

    @field:Json(name="numOfPlayer") val numOfPlayer: Int,

    @field:Json(name="isCurrent") val isCurrent: Boolean

) {
    // Additional process
}