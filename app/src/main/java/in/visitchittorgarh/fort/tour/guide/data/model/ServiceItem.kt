package `in`.visitchittorgarh.fort.tour.guide.data.model

import java.io.Serializable

data class ServiceItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: String,
    val details: String,
    val imageRes: Int,
    val actionType: String // "WHATSAPP_TAXI", "WHATSAPP_GUIDE", "CALL_HELPLINE", "OPEN_LINK"
) : Serializable

