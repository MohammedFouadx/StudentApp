package sim.coder.studentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sim.coder.studentapp.fragment.StudentListFragment
import java.lang.reflect.Array.newInstance

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = StudentListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(
                R.id.fragment_container,
                fragment
            ).commit()
        }

    }
}
