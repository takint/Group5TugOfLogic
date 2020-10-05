package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TugGameWithMainClaims (
    @Embedded val rip: TugGame,
    @Relation(
        parentColumn = "id",
        entityColumn = "gameId"
    )
    val mainClaims: List<MainClaim>
)