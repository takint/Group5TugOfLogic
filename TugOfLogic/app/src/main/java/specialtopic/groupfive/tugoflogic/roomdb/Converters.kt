package specialtopic.groupfive.tugoflogic.roomdb

import androidx.room.TypeConverter
import java.util.*

/**
 * This converters class will use to query
 * the Game table in-case users would like to get game by StartTime and EndTime
 * SQL store Date by String, and when App get it from SQLite the data need to convert to Date
 * */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}