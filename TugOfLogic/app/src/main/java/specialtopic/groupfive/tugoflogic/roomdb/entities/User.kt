package specialtopic.groupfive.tugoflogic.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import specialtopic.groupfive.tugoflogic.roomdb.PlayerType

@Entity(tableName = "users",
    indices = [
        Index(value = ["username"], unique = true),
        Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val userId: Int,

    // Instructor or Student
    var userType: String,

    var username: String,

    var email: String,

    val password: String,

    var fullName: String,

    val studentClass: String?, //(null if type is instructor)

    val studentNumber: String?, //(null if type is instructor)

    val gamePlayed: Int
) {
    // Additional process

}