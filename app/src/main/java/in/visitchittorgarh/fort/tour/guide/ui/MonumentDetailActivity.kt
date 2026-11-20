package `in`.visitchittorgarh.fort.tour.guide.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.Monument
import `in`.visitchittorgarh.fort.tour.guide.data.repository.ChittorgarhDataRepository
import `in`.visitchittorgarh.fort.tour.guide.data.repository.FavoritesManager
import `in`.visitchittorgarh.fort.tour.guide.util.AudioNarrator
import `in`.visitchittorgarh.fort.tour.guide.util.LanguageManager

class MonumentDetailActivity : AppCompatActivity() {

    private lateinit var monument: Monument
    private lateinit var favoritesManager: FavoritesManager
    private var audioNarrator: AudioNarrator? = null
    private var isPlayingAudio = false

    private lateinit var imgDetailHeader: ImageView
    private lateinit var btnBack: ImageView
    private lateinit var btnShare: ImageView
    private lateinit var btnDetailFavorite: ImageView
    private lateinit var txtDetailCategory: TextView
    private lateinit var txtDetailTitle: TextView
    private lateinit var txtDetailHindiTitle: TextView
    private lateinit var txtAudioStatus: TextView
    private lateinit var fabPlayAudio: FloatingActionButton
    private lateinit var btnSpeedSlow: TextView
    private lateinit var btnSpeedNormal: TextView
    private lateinit var btnSpeedFast: TextView
    private lateinit var btnDetailDirections: MaterialButton
    private lateinit var txtDetailTimings: TextView
    private lateinit var txtDetailBestTime: TextView
    private lateinit var txtDetailTagline: TextView
    private lateinit var txtDetailHistory: TextView
    private lateinit var txtDetailArchitecture: TextView
    private lateinit var layoutHighlights: LinearLayout

    companion object {
        const val EXTRA_MONUMENT_ID = "extra_monument_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monument_detail)

        favoritesManager = FavoritesManager(this)

        val monumentId = intent.getStringExtra(EXTRA_MONUMENT_ID) ?: "vijay_stambh"
        val found = ChittorgarhDataRepository.getMonumentById(monumentId)
        if (found == null) {
            Toast.makeText(this, "Monument not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        monument = found

        initViews()
        setupAudio()
        bindData()
    }

    private fun initViews() {
        imgDetailHeader = findViewById(R.id.imgDetailHeader)
        btnBack = findViewById(R.id.btnBack)
        btnShare = findViewById(R.id.btnShare)
        btnDetailFavorite = findViewById(R.id.btnDetailFavorite)
        txtDetailCategory = findViewById(R.id.txtDetailCategory)
        txtDetailTitle = findViewById(R.id.txtDetailTitle)
        txtDetailHindiTitle = findViewById(R.id.txtDetailHindiTitle)
        txtAudioStatus = findViewById(R.id.txtAudioStatus)
        fabPlayAudio = findViewById(R.id.fabPlayAudio)
        btnSpeedSlow = findViewById(R.id.btnSpeedSlow)
        btnSpeedNormal = findViewById(R.id.btnSpeedNormal)
        btnSpeedFast = findViewById(R.id.btnSpeedFast)
        btnDetailDirections = findViewById(R.id.btnDetailDirections)
        txtDetailTimings = findViewById(R.id.txtDetailTimings)
        txtDetailBestTime = findViewById(R.id.txtDetailBestTime)
        txtDetailTagline = findViewById(R.id.txtDetailTagline)
        txtDetailHistory = findViewById(R.id.txtDetailHistory)
        txtDetailArchitecture = findViewById(R.id.txtDetailArchitecture)
        layoutHighlights = findViewById(R.id.layoutHighlights)

        btnBack.setOnClickListener {
            finish()
        }

        btnShare.setOnClickListener {
            shareMonument()
        }

        btnDetailFavorite.setOnClickListener {
            val newFav = favoritesManager.toggleFavorite(monument.id)
            updateFavoriteIcon(newFav)
            val msg = if (newFav) getString(R.string.action_saved) else getString(R.string.action_removed)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }

        btnDetailDirections.setOnClickListener {
            openInMaps()
        }

        setupSpeedButtons()
    }

    private fun setupAudio() {
        val currentLocale = LanguageManager.getLocale(this)
        audioNarrator = AudioNarrator(this, currentLocale) { isReady ->
            if (isReady) {
                audioNarrator?.onSpeechStateChange = { isSpeaking, _ ->
                    runOnUiThread {
                        isPlayingAudio = isSpeaking
                        if (isSpeaking) {
                            fabPlayAudio.setImageResource(R.drawable.ic_pause)
                            txtAudioStatus.text = "Now narrating ${monument.name}..."
                            txtAudioStatus.setTextColor(ContextCompat.getColor(this, R.color.gold_bright))
                        } else {
                            fabPlayAudio.setImageResource(R.drawable.ic_play_arrow)
                            txtAudioStatus.text = "Tap to listen to audio narration"
                            txtAudioStatus.setTextColor(ContextCompat.getColor(this, R.color.gold_light))
                        }
                    }
                }
            }
        }

        fabPlayAudio.setOnClickListener {
            if (isPlayingAudio) {
                audioNarrator?.stop()
            } else {
                audioNarrator?.play(monument.id, monument.audioStory)
            }
        }
    }

    private fun setupSpeedButtons() {
        btnSpeedSlow.setOnClickListener {
            audioNarrator?.setSpeechRate(0.85f)
            selectSpeedButton(btnSpeedSlow)
        }
        btnSpeedNormal.setOnClickListener {
            audioNarrator?.setSpeechRate(1.0f)
            selectSpeedButton(btnSpeedNormal)
        }
        btnSpeedFast.setOnClickListener {
            audioNarrator?.setSpeechRate(1.2f)
            selectSpeedButton(btnSpeedFast)
        }
    }

    private fun selectSpeedButton(selected: TextView) {
        listOf(btnSpeedSlow, btnSpeedNormal, btnSpeedFast).forEach { btn ->
            if (btn == selected) {
                btn.setBackgroundResource(R.drawable.bg_chip_selected)
                btn.setTextColor(ContextCompat.getColor(this, R.color.surface_white))
            } else {
                btn.setBackgroundResource(R.drawable.bg_chip_unselected)
                btn.setTextColor(ContextCompat.getColor(this, R.color.text_primary))
            }
        }
    }

    private fun bindData() {
        val currentLang = LanguageManager.getSelectedLanguageCode(this)
        imgDetailHeader.setImageResource(monument.imageRes)
        txtDetailCategory.text = monument.category.uppercase()
        if (currentLang == "hi") {
            txtDetailTitle.text = monument.hindiName
            txtDetailHindiTitle.text = monument.name
        } else {
            txtDetailTitle.text = monument.name
            txtDetailHindiTitle.text = monument.hindiName
        }
        txtDetailTimings.text = monument.timings
        txtDetailBestTime.text = monument.bestTime
        txtDetailTagline.text = monument.tagline
        txtDetailHistory.text = monument.history
        txtDetailArchitecture.text = monument.architecture

        updateFavoriteIcon(favoritesManager.isFavorite(monument.id))

        // Highlights list
        layoutHighlights.removeAllViews()
        val inflater = LayoutInflater.from(this)
        monument.highlights.forEach { highlight ->
            val row = inflater.inflate(R.layout.item_highlight_row, layoutHighlights, false)
            val txtRow: TextView = row.findViewById(R.id.txtHighlightText)
            txtRow.text = highlight
            layoutHighlights.addView(row)
        }
    }

    private fun updateFavoriteIcon(isFav: Boolean) {
        if (isFav) {
            btnDetailFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            btnDetailFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun openInMaps() {
        val uri = Uri.parse("geo:0,0?q=${monument.latitude},${monument.longitude}(${Uri.encode(monument.name)})")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${monument.latitude},${monument.longitude}")
            startActivity(Intent(Intent.ACTION_VIEW, webUri))
        }
    }

    private fun shareMonument() {
        val shareText = """
            🏰 ${monument.name} (${monument.hindiName})
            
            ${monument.tagline}
            
            ${monument.description}
            
            Explore more on the official Visit Chittorgarh App & https://www.visitchittorgarh.in/
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Share ${monument.name}"))
    }

    override fun onDestroy() {
        super.onDestroy()
        audioNarrator?.shutdown()
    }
}

