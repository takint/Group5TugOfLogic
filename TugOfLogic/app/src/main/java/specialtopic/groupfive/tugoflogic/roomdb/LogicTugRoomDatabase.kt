package specialtopic.groupfive.tugoflogic.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import specialtopic.groupfive.tugoflogic.roomdb.dao.*
import specialtopic.groupfive.tugoflogic.roomdb.entities.*

@Database(
    entities = [
        MainClaim::class,
        ReasonInPlay::class,
        TugGame::class,
        User::class,
        VoteTicket::class
    ], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LogicTugRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): TugGameDao
    abstract fun mainClaimDao(): MainClaimDao
    abstract fun reasonInPlayDao(): ReasonInPlayDao
    abstract fun voteTicketDao(): VoteTicketDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: LogicTugRoomDatabase? = null

        fun getDatabase(context: Context): LogicTugRoomDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LogicTugRoomDatabase::class.java,
                        "tugoflogic.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE!!
        }
    }

}