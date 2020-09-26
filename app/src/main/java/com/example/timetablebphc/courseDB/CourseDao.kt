package com.example.timetablebphc.courseDB


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.timetablebphc.courseDB.Course
import com.example.timetablebphc.quizDB.Quiz

@Dao
interface CourseDao {

    @Insert
    suspend fun insertCourse(course: Course) : Long

    @Insert
    suspend fun insertQuiz(quiz: Quiz) : Long

    @Update
    suspend fun update(course: Course) : Int

    @Delete
    suspend fun delete(course: Course) : Int

    @Query("DELETE FROM course_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM course_data_table")
    fun getAllCourses():LiveData<List<Course>>

    @Query("SELECT * FROM quiz_data_table")
    fun getAllQuizzes():LiveData<List<Quiz>>
}