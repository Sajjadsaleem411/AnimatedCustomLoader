package app.customloader.com.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.customloader.com.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var flagLoader: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        btnShow.setOnClickListener {
            if (!flagLoader) {
                animatedSquare.start()
                btnShow.text = resources.getString(R.string.hideLoader)
                animatedSquare.visibility = View.VISIBLE
                flagLoader=true
            } else {
                animatedSquare.stop()
                btnShow.text = resources.getString(R.string.showLoader)
                animatedSquare.visibility = View.GONE
                flagLoader=false
            }
        }
    }
}
