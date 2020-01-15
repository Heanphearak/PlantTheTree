package niptict.edu.projectplantthetree.adapters

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker
import niptict.edu.projectplantthetree.R


class CustomInfoWindowAdapter(private val context: Fragment) : InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val view: View = context.layoutInflater.inflate(R.layout.info_window, null)
        return view
    }

}