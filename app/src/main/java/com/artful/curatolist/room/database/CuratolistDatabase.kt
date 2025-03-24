package com.artful.curatolist.room.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.artful.curatolist.room.data.ArtListArtWorkCrossRef
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.room.dao.ArtworkItemDao
import com.artful.curatolist.room.data.ArtworkList
import com.artful.curatolist.room.dao.ArtworkListDao
import com.artful.curatolist.room.dao.ItemListJoinDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

@Database(
    entities = [ArtworkList::class, ArtworkItem::class, ArtListArtWorkCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class CuratolistDatabase: RoomDatabase() {
    abstract fun artworkListDao(): ArtworkListDao
    abstract fun artworkItemDao(): ArtworkItemDao
    abstract fun joinDao(): ItemListJoinDao

    private val databaseScope = CoroutineScope(Dispatchers.IO)

    companion object {
        @Volatile
        private var INSTANCE: CuratolistDatabase? = null
        fun getDatabase(context: Context): CuratolistDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    CuratolistDatabase::class.java,
                    "curatolist_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("Curatolist Database", "Database Created!!!")
                        db.execSQL("INSERT INTO artwork_lists (listName, icon, isDefault) VALUES ('Favorites', 'heart', 1)")
                    }
                }).fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                instance
            }
        }
    }
}
