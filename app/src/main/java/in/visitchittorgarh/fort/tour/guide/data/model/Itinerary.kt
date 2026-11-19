package `in`.visitchittorgarh.fort.tour.guide.data.model

import java.io.Serializable

data class Itinerary(
    val id: String,
    val title: String,
    val hindiTitle: String,
    val badge: String,
    val duration: String,
    val description: String,
    val totalStops: Int,
    val imageRes: Int,
    val stops: List<ItineraryStop>
) : Serializable

data class ItineraryStop(
    val step: Int,
    val monumentId: String,
    val title: String,
    val durationMinutes: String,
    val description: String,
    val tip: String
) : Serializable

