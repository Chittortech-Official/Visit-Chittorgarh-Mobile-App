package `in`.visitchittorgarh.fort.tour.guide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import `in`.visitchittorgarh.fort.tour.guide.data.model.Itinerary
import `in`.visitchittorgarh.fort.tour.guide.data.model.Monument
import `in`.visitchittorgarh.fort.tour.guide.data.model.ServiceItem
import `in`.visitchittorgarh.fort.tour.guide.data.repository.ChittorgarhDataRepository
import `in`.visitchittorgarh.fort.tour.guide.data.repository.FavoritesManager
import `in`.visitchittorgarh.fort.tour.guide.ui.MonumentDetailActivity
import `in`.visitchittorgarh.fort.tour.guide.ui.adapter.AudioStoryAdapter
import `in`.visitchittorgarh.fort.tour.guide.ui.adapter.ItineraryAdapter
import `in`.visitchittorgarh.fort.tour.guide.ui.adapter.MonumentAdapter
import `in`.visitchittorgarh.fort.tour.guide.ui.adapter.ServiceAdapter
import `in`.visitchittorgarh.fort.tour.guide.util.AudioNarrator

class MainActivity : AppCompatActivity() {

    private lateinit var favoritesManager: FavoritesManager
    private var audioNarrator: AudioNarrator? = null

    // Drawer & Navigation
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var btnHamburger: ImageView

    // Tabs
    private lateinit var layoutTabExplore: LinearLayout
    private lateinit var layoutTabAudio: LinearLayout
    private lateinit var layoutTabItineraries: LinearLayout
    private lateinit var layoutTabServices: NestedScrollView
    private lateinit var bottomNavigation: BottomNavigationView

    // Header buttons
    private lateinit var btnHeaderWhatsApp: ImageView
    private lateinit var btnHeaderCall: ImageView

    // Tab 1 Views
    private lateinit var editSearch: EditText
    private lateinit var recyclerMonuments: RecyclerView
    private lateinit var monumentAdapter: MonumentAdapter
    private val categoryChips = mutableListOf<TextView>()

    // Tab 2 Views (Audio Guide)
    private lateinit var txtAudioNowPlayingTitle: TextView
    private lateinit var txtAudioNowPlayingStatus: TextView
    private lateinit var txtNarrationPreview: TextView
    private lateinit var fabTabPlayAudio: FloatingActionButton
    private lateinit var recyclerAudioStories: RecyclerView
    private lateinit var audioStoryAdapter: AudioStoryAdapter
    private var currentPlayingMonument: Monument? = null

    // Tab 3 Views (Itineraries)
    private lateinit var recyclerItineraries: RecyclerView
    private lateinit var itineraryAdapter: ItineraryAdapter

    // Tab 4 Views (Services)
    private lateinit var recyclerServices: RecyclerView
    private lateinit var serviceAdapter: ServiceAdapter

    private var backPressedTime: Long = 0

    companion object {
        const val HELPLINE_NUMBER = "+917597451057"
        const val FORT_CONTROL_ROOM = "01472241564"
        const val POLICE_NUMBER = "112"
        const val WHATSAPP_NUMBER = "917597451057"
        const val WEBSITE_URL = "https://www.visitchittorgarh.in/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Edge-to-edge: app draws behind system bars, we adjust manually
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_main)

        favoritesManager = FavoritesManager(this)

        initViews()
        setupWindowInsets()   // ← responsive system bar padding
        setupDrawer()
        setupAudio()
        setupBottomNavigation()
        setupExploreTab()
        setupAudioTab()
        setupItinerariesTab()
        setupServicesTab()
        setupBackNavigation()
    }

    private fun initViews() {
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        btnHamburger = findViewById(R.id.btnHamburger)

        layoutTabExplore = findViewById(R.id.layoutTabExplore)
        layoutTabAudio = findViewById(R.id.layoutTabAudio)
        layoutTabItineraries = findViewById(R.id.layoutTabItineraries)
        layoutTabServices = findViewById(R.id.layoutTabServices)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        btnHeaderWhatsApp = findViewById(R.id.btnHeaderWhatsApp)
        btnHeaderCall = findViewById(R.id.btnHeaderCall)

        btnHamburger.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnHeaderWhatsApp.setOnClickListener {
            openWhatsApp("Hello Visit Chittorgarh team! I need assistance regarding my visit to Chittorgarh Fort.")
        }

        btnHeaderCall.setOnClickListener {
            dialNumber(HELPLINE_NUMBER)
        }
    }

    /**
     * Apply WindowInsets so the app is fully responsive across:
     * - Phones with gesture navigation (extra bottom inset)
     * - Phones with 3-button nav (smaller inset)
     * - Notched/punch-hole screens (top inset)
     */
    private fun setupWindowInsets() {
        val appBarLayout = findViewById<View>(R.id.appBarLayout)
        ViewCompat.setOnApplyWindowInsetsListener(drawerLayout) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Status bar → pad the AppBar so content doesn't go under notch
            appBarLayout?.setPadding(0, systemBars.top, 0, 0)
            // Gesture/nav bar → pad BottomNav so it doesn't hide behind nav bar
            bottomNavigation.setPadding(0, 0, 0, systemBars.bottom)
            insets
        }
    }

    private fun setupDrawer() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawer(GravityCompat.START)
            when (menuItem.itemId) {
                R.id.drawer_explore -> {
                    bottomNavigation.selectedItemId = R.id.nav_explore
                }
                R.id.drawer_audio -> {
                    bottomNavigation.selectedItemId = R.id.nav_audio
                }
                R.id.drawer_tours -> {
                    bottomNavigation.selectedItemId = R.id.nav_itinerary
                }
                R.id.drawer_services, R.id.drawer_tickets -> {
                    bottomNavigation.selectedItemId = R.id.nav_services
                }
                R.id.drawer_cabs -> {
                    openWhatsApp("Hello Visit Chittorgarh! I would like to book a private cab for the Chittorgarh Fort tour.")
                }
                R.id.drawer_emergency -> {
                    dialNumber(HELPLINE_NUMBER)
                }
                R.id.drawer_whatsapp -> {
                    openWhatsApp("Namaste Visit Chittorgarh! Please guide me with official tour assistance.")
                }
                R.id.drawer_website -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE_URL)))
                }
            }
            true
        }
    }

    private fun setupAudio() {
        audioNarrator = AudioNarrator(this) { isReady ->
            if (isReady) {
                audioNarrator?.onSpeechStateChange = { isSpeaking, utteranceId ->
                    runOnUiThread {
                        if (isSpeaking) {
                            fabTabPlayAudio.setImageResource(R.drawable.ic_pause)
                            txtAudioNowPlayingStatus.text = "Now narrating..."
                            txtAudioNowPlayingStatus.setTextColor(ContextCompat.getColor(this, R.color.accent_gold))
                            audioStoryAdapter.setPlayingId(utteranceId)
                        } else {
                            fabTabPlayAudio.setImageResource(R.drawable.ic_play_arrow)
                            txtAudioNowPlayingStatus.text = "Paused / Stopped"
                            txtAudioNowPlayingStatus.setTextColor(ContextCompat.getColor(this, R.color.text_muted))
                            audioStoryAdapter.setPlayingId(null)
                        }
                    }
                }
            }
        }
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_explore -> switchTab(0)
                R.id.nav_audio -> switchTab(1)
                R.id.nav_itinerary -> switchTab(2)
                R.id.nav_services -> switchTab(3)
                else -> false
            }
            true
        }
    }

    private fun switchTab(tabIndex: Int) {
        layoutTabExplore.visibility = if (tabIndex == 0) View.VISIBLE else View.GONE
        layoutTabAudio.visibility = if (tabIndex == 1) View.VISIBLE else View.GONE
        layoutTabItineraries.visibility = if (tabIndex == 2) View.VISIBLE else View.GONE
        layoutTabServices.visibility = if (tabIndex == 3) View.VISIBLE else View.GONE

        // Refresh favorites if returning to explore
        if (tabIndex == 0) {
            monumentAdapter.notifyDataSetChanged()
        }
    }

    private fun setupExploreTab() {
        editSearch = findViewById(R.id.editSearch)
        recyclerMonuments = findViewById(R.id.recyclerMonuments)

        monumentAdapter = MonumentAdapter(
            allMonuments = ChittorgarhDataRepository.monuments,
            favoritesManager = favoritesManager,
            onItemClick = { monument ->
                openMonumentDetail(monument.id)
            },
            onAudioClick = { monument ->
                bottomNavigation.selectedItemId = R.id.nav_audio
                playAudioStory(monument)
            },
            onDirectionsClick = { monument ->
                openDirections(monument.latitude, monument.longitude, monument.name)
            },
            onFavoriteClick = { monument, isFav ->
                val msg = if (isFav) getString(R.string.action_saved) else getString(R.string.action_removed)
                Toast.makeText(this, "${monument.name}: $msg", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerMonuments.layoutManager = LinearLayoutManager(this)
        recyclerMonuments.adapter = monumentAdapter

        // Search listener
        editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                monumentAdapter.filter(query = s?.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Chips
        setupCategoryChips()
    }

    private fun setupCategoryChips() {
        val chipAll: TextView = findViewById(R.id.chipAll)
        val chipFavorites: TextView = findViewById(R.id.chipFavorites)
        val chipTowers: TextView = findViewById(R.id.chipTowers)
        val chipPalaces: TextView = findViewById(R.id.chipPalaces)
        val chipTemples: TextView = findViewById(R.id.chipTemples)
        val chipReservoirs: TextView = findViewById(R.id.chipReservoirs)
        val chipGates: TextView = findViewById(R.id.chipGates)

        categoryChips.addAll(listOf(chipAll, chipFavorites, chipTowers, chipPalaces, chipTemples, chipReservoirs, chipGates))

        val map = mapOf(
            chipAll to "All",
            chipFavorites to "Favorites",
            chipTowers to "Tower",
            chipPalaces to "Palace",
            chipTemples to "Temple",
            chipReservoirs to "Reservoir",
            chipGates to "Gate"
        )

        map.forEach { (chip, category) ->
            chip.setOnClickListener {
                selectChip(chip)
                monumentAdapter.filter(category = category)
            }
        }
    }

    private fun selectChip(selectedChip: TextView) {
        categoryChips.forEach { chip ->
            if (chip == selectedChip) {
                chip.setBackgroundResource(R.drawable.bg_chip_selected)
                chip.setTextColor(ContextCompat.getColor(this, R.color.surface_white))
                chip.setTypeface(null, android.graphics.Typeface.BOLD)
            } else {
                chip.setBackgroundResource(R.drawable.bg_chip_unselected)
                chip.setTextColor(ContextCompat.getColor(this, R.color.text_secondary))
                chip.setTypeface(null, android.graphics.Typeface.NORMAL)
            }
        }
    }

    private fun setupAudioTab() {
        txtAudioNowPlayingTitle = findViewById(R.id.txtAudioNowPlayingTitle)
        txtAudioNowPlayingStatus = findViewById(R.id.txtAudioNowPlayingStatus)
        txtNarrationPreview = findViewById(R.id.txtNarrationPreview)
        fabTabPlayAudio = findViewById(R.id.fabTabPlayAudio)
        recyclerAudioStories = findViewById(R.id.recyclerAudioStories)

        val firstStory = ChittorgarhDataRepository.monuments.first()
        currentPlayingMonument = firstStory
        txtAudioNowPlayingTitle.text = firstStory.name
        txtNarrationPreview.text = firstStory.audioStory

        audioStoryAdapter = AudioStoryAdapter(ChittorgarhDataRepository.monuments) { monument ->
            playAudioStory(monument)
        }

        recyclerAudioStories.layoutManager = LinearLayoutManager(this)
        recyclerAudioStories.adapter = audioStoryAdapter

        fabTabPlayAudio.setOnClickListener {
            val mon = currentPlayingMonument ?: return@setOnClickListener
            if (audioNarrator?.isSpeaking() == true) {
                audioNarrator?.stop()
            } else {
                playAudioStory(mon)
            }
        }
    }

    private fun playAudioStory(monument: Monument) {
        currentPlayingMonument = monument
        txtAudioNowPlayingTitle.text = monument.name
        txtNarrationPreview.text = monument.audioStory

        if (audioNarrator?.getCurrentPlayingId() == monument.id && audioNarrator?.isSpeaking() == true) {
            audioNarrator?.stop()
        } else {
            audioNarrator?.play(monument.id, monument.audioStory)
        }
    }

    private fun setupItinerariesTab() {
        recyclerItineraries = findViewById(R.id.recyclerItineraries)

        itineraryAdapter = ItineraryAdapter(ChittorgarhDataRepository.itineraries) { itinerary ->
            openItineraryNavigation(itinerary)
        }

        recyclerItineraries.layoutManager = LinearLayoutManager(this)
        recyclerItineraries.adapter = itineraryAdapter
    }

    private fun openItineraryNavigation(itinerary: Itinerary) {
        val firstStop = itinerary.stops.firstOrNull()
        val mon = firstStop?.let { ChittorgarhDataRepository.getMonumentById(it.monumentId) }
        val lat = mon?.latitude ?: 24.8879
        val lng = mon?.longitude ?: 74.6293
        val uri = Uri.parse("google.navigation:q=$lat,$lng&mode=d")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        try {
            startActivity(mapIntent)
        } catch (e: Exception) {
            val browserUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=$lat,$lng")
            startActivity(Intent(Intent.ACTION_VIEW, browserUri))
        }
    }

    private fun setupServicesTab() {
        val btnCallTouristHelpline: View = findViewById(R.id.btnCallTouristHelpline)
        val btnServiceWhatsApp: View = findViewById(R.id.btnServiceWhatsApp)
        val btnCallFortControl: View = findViewById(R.id.btnCallFortControl)
        val btnCallPolice: View = findViewById(R.id.btnCallPolice)
        val btnOpenWebsite: View = findViewById(R.id.btnOpenWebsite)

        btnCallTouristHelpline.setOnClickListener { dialNumber(HELPLINE_NUMBER) }
        btnCallFortControl.setOnClickListener { dialNumber(FORT_CONTROL_ROOM) }
        btnCallPolice.setOnClickListener { dialNumber(POLICE_NUMBER) }

        btnServiceWhatsApp.setOnClickListener {
            openWhatsApp("Hello Visit Chittorgarh team! I would like to inquire about verified guides, private cabs, and hotel bookings.")
        }

        btnOpenWebsite.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE_URL)))
        }

        recyclerServices = findViewById(R.id.recyclerServices)
        serviceAdapter = ServiceAdapter(ChittorgarhDataRepository.serviceItems) { serviceItem ->
            when (serviceItem.actionType) {
                "WHATSAPP_GUIDE" -> openWhatsApp("Namaste! I would like to book an Authorized Mewari Tour Guide for Chittorgarh Fort.")
                "WHATSAPP_TAXI" -> openWhatsApp("Namaste! I would like to inquire about booking a ${serviceItem.title} for Chittorgarh sight-seeing.")
                else -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE_URL)))
            }
        }

        recyclerServices.layoutManager = LinearLayoutManager(this)
        recyclerServices.adapter = serviceAdapter
    }

    private fun openMonumentDetail(monumentId: String) {
        val intent = Intent(this, MonumentDetailActivity::class.java).apply {
            putExtra(MonumentDetailActivity.EXTRA_MONUMENT_ID, monumentId)
        }
        startActivity(intent)
    }

    private fun openDirections(lat: Double, lng: Double, label: String) {
        val uri = Uri.parse("geo:0,0?q=$lat,$lng(${Uri.encode(label)})")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$lat,$lng")
            startActivity(Intent(Intent.ACTION_VIEW, webUri))
        }
    }

    private fun openWhatsApp(message: String) {
        val uri = Uri.parse("https://wa.me/$WHATSAPP_NUMBER?text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dialNumber(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Could not open phone dialer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBackNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return
                }

                if (bottomNavigation.selectedItemId != R.id.nav_explore) {
                    bottomNavigation.selectedItemId = R.id.nav_explore
                    return
                }

                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    finish()
                } else {
                    backPressedTime = System.currentTimeMillis()
                    Toast.makeText(this@MainActivity, "Press back again to exit Visit Chittorgarh", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        audioNarrator?.shutdown()
    }
}
