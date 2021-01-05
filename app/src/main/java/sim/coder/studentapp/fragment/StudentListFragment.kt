package sim.coder.studentapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_student_list.*
import sim.coder.studentapp.R
import sim.coder.studentapp.InputDealog
import sim.coder.studentapp.model.Student
import sim.coder.studentapp.model.StudentListViewModel


private const val TAG = "StudentListFragment"


class StudentListFragment : Fragment(), InputDealog.Callbacks {

    private lateinit var addNewStudentImageButton : Button
    private lateinit var studentRecyclerView: RecyclerView
    private var adapter: StudentAdapter? = StudentAdapter(emptyList())



    private val studentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentListViewModel::class.java)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new_student -> {

                InputDealog().apply {
                    setTargetFragment(this@StudentListFragment,0)
                    show(this@StudentListFragment.requireFragmentManager(),"input")
                }
                true


            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        addNewStudentImageButton = view.findViewById(R.id.add_new_student)

        studentRecyclerView = view.findViewById(R.id.Student_recycler_view) as RecyclerView
        studentRecyclerView.layoutManager = LinearLayoutManager(context)



        return view

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentViewModel.studentListLiveData .observe(
            viewLifecycleOwner,
            Observer { students ->
                students?.let {
                    Log.i(TAG, "Got stu ${students.size}")
                    updateUI(students)
                }
            })

        addNewStudentImageButton.setOnClickListener {
           InputDealog().apply {
               setTargetFragment(this@StudentListFragment,0)
               show(this@StudentListFragment.requireFragmentManager(),"input")
           }
            true
        }
    }



    private inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view) {
        val studentDegreeText: TextView = itemView.findViewById(R.id.tv_student_degree)
        val studentNameText: TextView = itemView.findViewById(R.id.tv_student_name)
        val studentStatusText: TextView = itemView.findViewById(R.id.tv_student_status)
        val deleteButton: ImageButton = itemView.findViewById(R.id.btn_deleted) as ImageButton
        private lateinit var student: Student
        fun bind(item: Student) {

            this.student = item
            studentDegreeText.text = this.student.number.toString()
            studentNameText.text = this.student.name
            if (this.student.passed)
                studentStatusText.text = "Passed "
            else
                studentStatusText.text = "Failed"

        }


        init {
            deleteButton.setOnClickListener {
                val builder=AlertDialog.Builder(requireContext())
                builder.setPositiveButton("yes"){_,_->

                    studentViewModel.deleteUser(student)
                }

                builder.setNegativeButton("no"){_,_->


                }
                builder.setTitle("Deleted ${student.name}")
                builder.setMessage("Are you sure you want to delete")
                builder.create().show()

            }
        }



    }


    private inner class StudentAdapter(var students: List<Student>) :
        RecyclerView.Adapter<StudentHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
            val view = layoutInflater.inflate(R.layout.student_list_item, parent, false)
            return StudentHolder(view)
        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = students[position]
            holder.bind(student)


        }

        override fun getItemCount(): Int {
            if (students.size <=0){
                add_new_student.visibility = View.VISIBLE
                text_no_student.visibility = View.VISIBLE
                text_add_one.visibility = View.VISIBLE

            }else{
                add_new_student.visibility=View.GONE
                text_no_student.visibility=View.GONE
                text_add_one.visibility=View.GONE
            }
            return students.size

        }





    }

    private fun updateUI(students: List<Student>) {

        adapter = StudentAdapter(students)
        studentRecyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            StudentListFragment()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }



    override fun addNewStudent(student: Student) {
        studentViewModel.addStudent(student)

    }

    fun onStudentDeleted(students: List<Student>){
        add_new_student.visibility = View.VISIBLE
        text_no_student.visibility = View.VISIBLE
        text_add_one.visibility = View.VISIBLE


    }
}