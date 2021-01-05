package sim.coder.studentapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import sim.coder.studentapp.model.Student


class InputDealog:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view=activity?.layoutInflater?.inflate(R.layout.custom_dialog_layout,null)

        val name=view?.findViewById(R.id.et_student_name) as EditText
        val num=view.findViewById(R.id.et_student_degree) as EditText
        val pass=view.findViewById(R.id.checkbox_passed) as CheckBox

        return  AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(view)
            .setPositiveButton("ADD"){dialog,_->
                val student=Student(0,num.text.toString().toInt(),
                    name.text.toString(),pass.isChecked)
                targetFragment.let {fragment ->
                    (fragment as Callbacks).addNewStudent(student)
                }
            }.setNegativeButton("cancel"){dialog,_->
                dialog.cancel()
            }.create()




    }

    interface Callbacks{

        fun addNewStudent(student: Student)
    }
}