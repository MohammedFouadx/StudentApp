package sim.coder.studentapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import sim.coder.studentapp.model.Student

@Database(entities = [Student::class],version = 1 , exportSchema = false)

abstract class  StudentDatabase:RoomDatabase(){
        abstract  fun studentDao():Dao




}

//val migration=object :Migration(1,2){
//        override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE Student ADD COLUMN suspect TEXT NOT NULL DEFAULT ''")
//        }
//}