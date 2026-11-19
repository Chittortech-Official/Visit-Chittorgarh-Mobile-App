package `in`.visitchittorgarh.fort.tour.guide.data.model

import java.io.Serializable

data class Monument(
    val id: String,
    val name: String,
    val hindiName: String,
    val category: String,
    val imageRes: Int,
    val tagline: String,
    val description: String,
    val history: String,
    val architecture: String,
    val audioStory: String,
    val timings: String = "9:00 AM – 5:00 PM",
    val entryFee: String = "Included in Fort ASI Ticket (₹40 Indian / ₹600 Foreigner)",
    val bestTime: String = "Early Morning or Sunset",
    val latitude: Double,
    val longitude: Double,
    val highlights: List<String> = emptyList()
) : Serializable

