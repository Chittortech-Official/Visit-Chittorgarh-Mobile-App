# Chittorgarh Fort: Official Native Travel & Audio Guide 🏰

Official Native Android Application for [VisitChittorgarh.in](https://www.visitchittorgarh.in/), verified and published by **ChittorTech**.

## 📱 100% Native Kotlin Architecture
- **Explore & Citadel Monuments:** High-performance native RecyclerView with category filter chips (Victory Towers, Palaces, Temples, Water Reservoirs, Historic Gates) and instant search.
- **Offline-First Audio Guide:** Integrated Android Text-to-Speech (TTS) audio narration for all major monuments with speed controls (0.85x, 1.0x, 1.2x) and speech callbacks.
- **Curated Itineraries & Circuits:** Self-guided Full Day Royal Circuit, Express Highlights, and Sunset Photography Trail with step-by-step ordered stops, duration, and Google Maps GPS navigation.
- **Official Branding & Imagery:** Authentic assets, photography, and color palette matching `visitchittorgarh.in` (Royal Dark Navy, Mewari Amber Gold `#C49A32`, Sandstone `#F8F5EF`).
- **Tourist Services & Booking:** Direct integration for verified Mewari ASI guides, sanitized private cabs (Sedan, SUV, Tempo Traveller), local cuisine info, and 24/7 emergency helplines.
- **Offline Reliability:** Instant dialer access to Fort Control Room (`01472-241564`), Tourist Police (`112`), and Tourist Helpline (`+91 7597451057`).
- **Favorites & Bookmarks:** Persistent local bookmarking using Android SharedPreferences.

## 🛠️ Project Setup in Android Studio
1. Open **Android Studio**.
2. Select **Open** and choose this repository directory (`Visit-Chittorgarh-Mobile-App`).
3. Android Studio will automatically detect the Gradle files and prompt **Sync Project with Gradle Files**.
4. To build debug APK:
   ```bash
   ./gradlew assembleDebug
   ```
5. To build release bundle:
   ```bash
   ./gradlew bundleRelease
   ```

## 📦 Package Details
- **Application ID:** `in.visitchittorgarh.fort.tour.guide`
- **Target SDK:** 36 (Android 15+)
- **Min SDK:** 24 (Android 7.0+)
- **Language:** Kotlin 2.3+ / Modern Android Gradle Plugin (AGP 9.0+)

---
© 2026 ChittorTech & VisitChittorgarh.in. All Rights Reserved.
