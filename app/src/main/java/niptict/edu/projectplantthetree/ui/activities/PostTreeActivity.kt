package niptict.edu.projectplantthetree.activities

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_post_tree.*
import niptict.edu.projectplantthetree.R
import android.content.Intent as Intent1


class PostTreeActivity : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_tree)

        take_photo_tree.setOnClickListener {
           dispatchTakePictureIntent()
        }


    }
    private fun dispatchTakePictureIntent() {
        Intent1(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras!!.get("data") as Bitmap
            Log.d("check",imageBitmap.toString())
            tree_photo.setImageBitmap(imageBitmap)

        }
    }

}


