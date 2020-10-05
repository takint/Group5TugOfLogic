package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MainClaimWithRips (
    @Embedded val mainClaim: MainClaim,
    @Relation(
        parentColumn = "id",
        entityColumn = "mainClaimId"
    )
    val rips: List<ReasonInPlay>
)