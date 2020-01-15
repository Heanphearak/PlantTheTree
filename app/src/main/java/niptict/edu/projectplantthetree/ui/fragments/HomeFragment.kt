package niptict.edu.projectplantthetree.fragments


import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_home.view.*
import niptict.edu.projectplantthetree.R
import niptict.edu.projectplantthetree.adapters.CustomInfoWindowAdapter
import niptict.edu.projectplantthetree.internet.PostTreeActivity
import java.util.ArrayList

class HomeFragment : androidx.fragment.app.Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var arrayList = ArrayList<LatLng>()
    val sydney = LatLng(11.6945292,104.9405128)
    val phnompenh = LatLng(11.8219769,104.6861913)
    val kompot = LatLng(11.6158617,104.9938497)
    val kep = LatLng(13.507646,104.3008438)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        arrayList.add(sydney)
        arrayList.add(phnompenh)
        arrayList.add(kompot)
        arrayList.add(kep)

        v.add_tree.setOnClickListener {
            val intentToPostTreeActivity = Intent(requireContext(),
                PostTreeActivity::class.java)
            startActivity(intentToPostTreeActivity)
        }

        return v
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val markerOpt = MarkerOptions()
        for (i in 0 until arrayList.size){
            Log.d("lat->",arrayList[i].toString())
            mMap.addMarker(
                markerOpt.position(arrayList[i]).title("Marker").icon(bitmapDescriptorFromVector(
                    this.context!!, R.drawable.ic_tree)))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList[i]))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(2.5f))

            val adapter = CustomInfoWindowAdapter(this)
            mMap.setInfoWindowAdapter(adapter)

            mMap.addMarker(markerOpt).showInfoWindow()
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}
