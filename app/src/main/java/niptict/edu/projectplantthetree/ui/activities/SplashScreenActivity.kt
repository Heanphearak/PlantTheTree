package niptict.edu.projectplantthetree.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import niptict.edu.projectplantthetree.LoginActivity
import niptict.edu.projectplantthetree.R
import niptict.edu.projectplantthetree.RegisterActivity
import niptict.edu.projectplantthetree.internet.MainActivity
import niptict.edu.projectplantthetree.internet.PostTreeActivity
import java.lang.Exception

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long=3000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, LoginActivity::class.java))
            // startActivity(Intent(this,PaymentOptionActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }

}