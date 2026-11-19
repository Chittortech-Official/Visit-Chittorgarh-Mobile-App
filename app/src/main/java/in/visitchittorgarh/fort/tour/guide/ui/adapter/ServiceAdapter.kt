package `in`.visitchittorgarh.fort.tour.guide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.ServiceItem

class ServiceAdapter(
    private val services: List<ServiceItem>,
    private val onActionClick: (ServiceItem) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_service_card, parent, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = services[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = services.size

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgService: ShapeableImageView = itemView.findViewById(R.id.imgService)
        private val txtServiceTitle: TextView = itemView.findViewById(R.id.txtServiceTitle)
        private val txtServiceSubtitle: TextView = itemView.findViewById(R.id.txtServiceSubtitle)
        private val txtServicePrice: TextView = itemView.findViewById(R.id.txtServicePrice)
        private val txtServiceDetails: TextView = itemView.findViewById(R.id.txtServiceDetails)
        private val btnServiceAction: MaterialButton = itemView.findViewById(R.id.btnServiceAction)

        fun bind(item: ServiceItem) {
            imgService.setImageResource(item.imageRes)
            txtServiceTitle.text = item.title
            txtServiceSubtitle.text = item.subtitle
            txtServicePrice.text = item.price
            txtServiceDetails.text = item.details

            when (item.actionType) {
                "WHATSAPP_VIP" -> {
                    btnServiceAction.text = "Book VIP Tour on WhatsApp"
                    btnServiceAction.setIconResource(R.drawable.ic_whatsapp)
                }
                "WHATSAPP_PHOTO" -> {
                    btnServiceAction.text = "Book Photo Tour on WhatsApp"
                    btnServiceAction.setIconResource(R.drawable.ic_whatsapp)
                }
                "WHATSAPP_GUIDE" -> {
                    btnServiceAction.text = "Book Guide on WhatsApp"
                    btnServiceAction.setIconResource(R.drawable.ic_whatsapp)
                }
                "WHATSAPP_TAXI" -> {
                    btnServiceAction.text = "Book Cab on WhatsApp"
                    btnServiceAction.setIconResource(R.drawable.ic_whatsapp)
                }
                else -> {
                    btnServiceAction.text = "Explore Authentic Dining"
                    btnServiceAction.setIconResource(R.drawable.ic_nav_services)
                }
            }

            btnServiceAction.setOnClickListener {
                onActionClick(item)
            }
        }
    }
}

