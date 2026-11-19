package `in`.visitchittorgarh.fort.tour.guide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.Itinerary

class ItineraryAdapter(
    private val itineraries: List<Itinerary>,
    private val onNavigateClick: (Itinerary) -> Unit
) : RecyclerView.Adapter<ItineraryAdapter.ItineraryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItineraryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_itinerary_card, parent, false)
        return ItineraryViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItineraryViewHolder, position: Int) {
        val item = itineraries[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = itineraries.size

    inner class ItineraryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgItinerary: ImageView = itemView.findViewById(R.id.imgItinerary)
        private val txtBadge: TextView = itemView.findViewById(R.id.txtBadge)
        private val txtItineraryTitle: TextView = itemView.findViewById(R.id.txtItineraryTitle)
        private val txtItineraryDuration: TextView = itemView.findViewById(R.id.txtItineraryDuration)
        private val txtItineraryDesc: TextView = itemView.findViewById(R.id.txtItineraryDesc)
        private val layoutStopsContainer: LinearLayout = itemView.findViewById(R.id.layoutStopsContainer)
        private val btnStartTour: MaterialButton = itemView.findViewById(R.id.btnStartTour)

        fun bind(itinerary: Itinerary) {
            imgItinerary.setImageResource(itinerary.imageRes)
            txtBadge.text = itinerary.badge.uppercase()
            txtItineraryTitle.text = itinerary.title
            txtItineraryDuration.text = "⏳ ${itinerary.duration} • ${itinerary.totalStops} Major Landmarks"
            txtItineraryDesc.text = itinerary.description

            layoutStopsContainer.removeAllViews()
            val inflater = LayoutInflater.from(itemView.context)

            itinerary.stops.forEach { stop ->
                val stopView = inflater.inflate(R.layout.item_itinerary_stop_row, layoutStopsContainer, false)
                val txtStep: TextView = stopView.findViewById(R.id.txtStepNumber)
                val txtStopTitle: TextView = stopView.findViewById(R.id.txtStopTitle)
                val txtStopDesc: TextView = stopView.findViewById(R.id.txtStopDesc)
                val txtStopTip: TextView = stopView.findViewById(R.id.txtStopTip)

                txtStep.text = "${stop.step}"
                txtStopTitle.text = "${stop.title} (${stop.durationMinutes})"
                txtStopDesc.text = stop.description
                txtStopTip.text = "💡 ${stop.tip}"

                layoutStopsContainer.addView(stopView)
            }

            btnStartTour.setOnClickListener {
                onNavigateClick(itinerary)
            }
        }
    }
}

