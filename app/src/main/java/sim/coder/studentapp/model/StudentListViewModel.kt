package sim.coder.studentapp.model

import androidx.lifecycle.ViewModel
import sim.coder.studentapp.StudentRepository

class StudentListViewModel : ViewModel() {

    private  val studentRepository= StudentRepository.get()
    val studentListLiveData=studentRepository.getStudent()

    fun addStudent(student: Student){
        studentRepository.addnewstudent(student)
    }


    fun deleteUser(student: Student){
        studentRepository.deleteUser(student)
    }






}