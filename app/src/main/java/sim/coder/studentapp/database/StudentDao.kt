package sim.coder.studentapp.database


import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import sim.coder.studentapp.model.Student

@Dao
interface  Dao{
    @Insert
    fun addStudent(student: Student)

    @Query("SELECT * FROM Student")
    fun getStudents():LiveData<List<Student>>

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)


}