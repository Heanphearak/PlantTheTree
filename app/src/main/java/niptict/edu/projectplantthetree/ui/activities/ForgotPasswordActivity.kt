package niptict.edu.projectplantthetree.ui.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_forgot_password.*
import niptict.edu.projectplantthetree.R

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btn_send.visibility = View.VISIBLE
        btn_send_was_input.visibility = View.GONE

        edit_enter_email_phone.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isEmpty())
                {
                    btn_send.visibility = View.VISIBLE
                    btn_send_was_input.visibility = View.GONE
                }
                else{
                    btn_send_was_input.visibility = View.VISIBLE
                    btn_send.visibility = View.GONE
                }

            }
        })
    }
}
