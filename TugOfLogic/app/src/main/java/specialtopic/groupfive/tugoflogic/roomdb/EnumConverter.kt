package specialtopic.groupfive.tugoflogic.roomdb

import androidx.room.TypeConverter

class EnumConverter {
    companion object {
        fun fromTugSideString(value: String?): TugSide? {
            return value?.let {
                if (it == "agree") TugSide.Agree
                else TugSide.Disagree
            }
        }

        fun tugSideToString(side: TugSide?): String? {
            return side?.let {
                if (it == TugSide.Agree) "agree"
                else "disagree"
            }
        }

        fun fromPlayerTypeString(value: String?): PlayerType? {
            return value?.let {
                if (it == "instructor") PlayerType.Instructor
                else PlayerType.Student
            }
        }

        fun playerTypeToString(side: PlayerType?): String? {
            return side?.let {
                if (it == PlayerType.Instructor) "instructor"
                else "student"
            }
        }
    }
}