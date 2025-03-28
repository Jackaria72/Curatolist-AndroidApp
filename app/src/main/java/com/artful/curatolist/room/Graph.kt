package com.artful.curatolist.room

import android.content.Context
import com.artful.curatolist.room.database.CuratolistDatabase
import com.artful.curatolist.room.repository.RoomRepository

object Graph {
    lateinit var db:CuratolistDatabase
        private set

    val repository by lazy {
        RoomRepository(
            artworkListDao = db.artworkListDao(),
            artworkItemDao = db.artworkItemDao(),
            joinDao = db.joinDao()
        )
    }

    fun provide(context:Context){
        db = CuratolistDatabase.getDatabase(context)
    }
}