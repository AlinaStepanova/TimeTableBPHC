/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.timetablebphc.courseDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.timetablebphc.dao.CourseDao
import com.example.timetablebphc.dao.QuizDao
import com.example.timetablebphc.dao.TimeTableDao
import com.example.timetablebphc.dao.HomeDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(entities = [Course::class, Quiz ::class], version = 1)
@TypeConverters(Converters::class)
abstract class CourseRoomDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao
    abstract fun homeDao(): HomeDao
    abstract fun timeTableDao(): TimeTableDao
    abstract fun quizDao(): QuizDao

    companion object {

        @Volatile
        private var INSTANCE: CourseRoomDatabase? = null

        fun getInstance(context: Context): CourseRoomDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CourseRoomDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}
