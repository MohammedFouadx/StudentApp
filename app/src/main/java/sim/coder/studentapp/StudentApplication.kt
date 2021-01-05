package sim.coder.studentapp

import android.app.Application
import sim.coder.studentapp.StudentRepository

class StudentApplication:Application (){
    override fun onCreate() {
        super.onCreate()
        StudentRepository.initialize(this)
    }


}