package `in`.visitchittorgarh.fort.tour.guide.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.Monument

class AudioStoryAdapter(
    private val stories: List<Monument>,
    private val onPlayClick: (Monument) -> Unit
) : RecyclerView.Adapter<AudioStoryAdapter.AudioViewHolder>() {

    private var currentPlayingId: String? = null

    fun setPlayingId(id: String?) {
        currentPlayingId = id
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_audio_story, parent, false)
        return AudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val monument = stories[position]
        holder.bind(monument)
    }

    override fun getItemCount(): Int = stories.size

    inner class AudioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val card: MaterialCardView = itemView.findViewById(R.id.cardAudioStory)
        private val imgThumb: ShapeableImageView = itemView.findViewById(R.id.imgThumb)
        private val txtAudioTitle: TextView = itemView.findViewById(R.id.txtAudioTitle)
        private val txtAudioHindi: TextView = itemView.findViewById(R.id.txtAudioHindi)
        private val txtAudioCategory: TextView = itemView.findViewById(R.id.txtAudioCategory)
        private val btnPlayStop: ImageView = itemView.findViewById(R.id.btnPlayStop)

        fun bind(monument: Monument) {
            imgThumb.setImageResource(monument.imageRes)
            txtAudioTitle.text = monument.name
            txtAudioHindi.text = monument.hindiName
            txtAudioCategory.text = "🎧 Audio Story • 2 mins narration"

            val isPlaying = currentPlayingId == monument.id

            if (isPlaying) {
                btnPlayStop.setImageResource(R.drawable.ic_pause)
                btnPlayStop.setColorFilter(ContextCompat.getColor(itemView.context, R.color.ruby_crimson))
                card.strokeColor = ContextCompat.getColor(itemView.context, R.color.accent_gold)
                card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.gold_badge_bg))
            } else {
                btnPlayStop.setImageResource(R.drawable.ic_play_arrow)
                btnPlayStop.setColorFilter(ContextCompat.getColor(itemView.context, R.color.accent_gold))
                card.strokeColor = ContextCompat.getColor(itemView.context, R.color.border_subtle)
                card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.surface_white))
            }

            itemView.setOnClickListener {
                onPlayClick(monument)
            }

            btnPlayStop.setOnClickListener {
                onPlayClick(monument)
            }
        }
    }
}
