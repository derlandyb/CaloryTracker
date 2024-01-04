package br.com.derlandybelchior.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.derlandybelchior.data.local.dao.TrackerDao
import br.com.derlandybelchior.data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase: RoomDatabase(){
    abstract val dao: TrackerDao
}