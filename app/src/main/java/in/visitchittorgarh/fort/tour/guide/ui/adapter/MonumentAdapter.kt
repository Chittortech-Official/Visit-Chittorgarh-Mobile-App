package `in`.visitchittorgarh.fort.tour.guide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.Monument
import `in`.visitchittorgarh.fort.tour.guide.data.repository.FavoritesManager
import `in`.visitchittorgarh.fort.tour.guide.util.LanguageManager

class MonumentAdapter(
    private var allMonuments: List<Monument>,
    private val favoritesManager: FavoritesManager,
    private val onItemClick: (Monument) -> Unit,
    private val onAudioClick: (Monument) -> Unit,
    private val onDirectionsClick: (Monument) -> Unit,
    private val onFavoriteClick: (Monument, Boolean) -> Unit
) : RecyclerView.Adapter<MonumentAdapter.MonumentViewHolder>() {

    private var filteredList: List<Monument> = allMonuments
    private var currentCategory: String = "All"
    private var currentQuery: String = ""

    fun filter(query: String? = null, category: String? = null) {
        if (query != null) currentQuery = query.trim()
        if (category != null) currentCategory = category

        filteredList = allMonuments.filter { monument ->
            val matchesCategory = when (currentCategory) {
                "All" -> true
                "Favorites" -> favoritesManager.isFavorite(monument.id)
                else -> monument.category.contains(currentCategory, ignoreCase = true)
            }

            val matchesQuery = if (currentQuery.isEmpty()) {
                true
            } else {
                monument.name.contains(currentQuery, ignoreCase = true) ||
                        monument.hindiName.contains(currentQuery, ignoreCase = true) ||
                        monument.tagline.contains(currentQuery, ignoreCase = true) ||
                        monument.category.contains(currentQuery, ignoreCase = true)
            }

            matchesCategory && matchesQuery
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_monument_card, parent, false)
        return MonumentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MonumentViewHolder, position: Int) {
        val monument = filteredList[position]
        holder.bind(monument)
    }

    override fun getItemCount(): Int = filteredList.size

    inner class MonumentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMonument: ImageView = itemView.findViewById(R.id.imgMonument)
        private val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        private val btnFavorite: ImageView = itemView.findViewById(R.id.btnFavorite)
        private val txtName: TextView = itemView.findViewById(R.id.txtName)
        private val txtHindiName: TextView = itemView.findViewById(R.id.txtHindiName)
        private val txtTagline: TextView = itemView.findViewById(R.id.txtTagline)
        private val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        private val btnQuickAudio: MaterialButton = itemView.findViewById(R.id.btnQuickAudio)
        private val btnDirections: MaterialButton = itemView.findViewById(R.id.btnDirections)

        fun bind(monument: Monument) {
            val context = itemView.context
            val currentLang = LanguageManager.getSelectedLanguageCode(context)
            imgMonument.setImageResource(monument.imageRes)
            txtCategory.text = monument.category.uppercase()

            if (currentLang == "hi") {
                txtName.text = monument.hindiName
                txtHindiName.text = monument.name
            } else {
                txtName.text = monument.name
                txtHindiName.text = monument.hindiName
            }
            txtTagline.text = monument.tagline
            txtDescription.text = monument.description

            updateFavoriteIcon(favoritesManager.isFavorite(monument.id))

            btnFavorite.setOnClickListener {
                val newStatus = favoritesManager.toggleFavorite(monument.id)
                updateFavoriteIcon(newStatus)
                onFavoriteClick(monument, newStatus)
            }

            itemView.setOnClickListener {
                onItemClick(monument)
            }

            btnQuickAudio.setOnClickListener {
                onAudioClick(monument)
            }

            btnDirections.setOnClickListener {
                onDirectionsClick(monument)
            }
        }

        private fun updateFavoriteIcon(isFav: Boolean) {
            if (isFav) {
                btnFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                btnFavorite.setImageResource(R.drawable.ic_favorite_border)
            }
        }
    }
}

