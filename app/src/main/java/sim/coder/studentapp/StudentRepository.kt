package sim.coder.studentapp
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import sim.coder.studentapp.database.StudentDatabase
import sim.coder.studentapp.model.Student

import java.util.concurrent.Executors

private const val DATABASE_NAME = "student-database"
class StudentRepository private constructor(context: Context){
    private  val database: StudentDatabase = Room.databaseBuilder(
        context.applicationContext,StudentDatabase::class.java,
        DATABASE_NAME
    ).build()

    private  val studentDao=database.studentDao()
    private  var executor = Executors.newSingleThreadExecutor()


    fun addnewstudent(student: Student){
        executor.execute{
            studentDao.addStudent(student)
        }
    }


    fun deleteUser(student: Student){
        executor.execute{
            studentDao.deleteStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        executor.execute {
            studentDao.updateStudent(student)
        }
    }


    fun getStudent(): LiveData<List<Student>> = studentDao.getStudents()

    companion object {
        private var INSTANCE: StudentRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository(context)
            }
        }
        fun get(): StudentRepository {
            return INSTANCE ?:
            throw IllegalStateException("StudentRepository must be initialized")
        }
    }
}