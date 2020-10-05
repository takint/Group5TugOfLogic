package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithRips (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "studentId"
    )
    val rips: List<ReasonInPlay>
)