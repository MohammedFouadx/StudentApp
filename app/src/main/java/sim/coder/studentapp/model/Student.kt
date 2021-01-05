package sim.coder.studentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var number: Int = 0,
    var name: String = "",
    var passed: Boolean = false
)