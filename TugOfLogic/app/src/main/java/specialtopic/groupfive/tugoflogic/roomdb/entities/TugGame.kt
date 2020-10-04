package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "games")
data class TugGame(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val gameId: Int,

    val startTime: Date,

    val endTime: Date,

    val numOfPlayer: Int,

    val isCurrent: Boolean
) {
    // Additional process
}