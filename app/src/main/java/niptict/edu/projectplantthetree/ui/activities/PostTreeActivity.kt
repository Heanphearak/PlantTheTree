package niptict.edu.projectplantthetree.internet


import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_post_tree.*
import niptict.edu.projectplantthetree.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest
import android.content.Intent as Intent1


class PostTreeActivity : AppCompatActivity(){

    private val REQUEST_IMAGE_CAPTURE = 1
    private var myLongitude: Double? = null
    private var myLatitude: Double? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_tree)

        button_submit.setOnClickListener {
            Log.d("lat",myLatitude.toString())
            Log.d("long",myLongitude.toString())
        }

        take_photo_tree.setOnClickListener {
              dispatchTakePictureIntent()
              setUpMap()
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {

                val currentLatLng = LatLng(location.latitude, location.longitude)

                myLongitude = currentLatLng.longitude
                myLatitude = currentLatLng.latitude
            }
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
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            tree_photo.setImageBitmap(imageBitmap)


        }
    }


}


