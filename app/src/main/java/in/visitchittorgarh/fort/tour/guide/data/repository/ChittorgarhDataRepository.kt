package `in`.visitchittorgarh.fort.tour.guide.data.repository

import `in`.visitchittorgarh.fort.tour.guide.R
import `in`.visitchittorgarh.fort.tour.guide.data.model.Itinerary
import `in`.visitchittorgarh.fort.tour.guide.data.model.ItineraryStop
import `in`.visitchittorgarh.fort.tour.guide.data.model.Monument
import `in`.visitchittorgarh.fort.tour.guide.data.model.ServiceItem

object ChittorgarhDataRepository {

    val monuments: List<Monument> = listOf(
        Monument(
            id = "vijay_stambh",
            name = "Vijay Stambha (Tower of Victory)",
            hindiName = "विजय स्तम्भ",
            category = "Victory Tower",
            imageRes = R.drawable.vijay_stambh_new,
            tagline = "The 9-storey marvel commemorating Maharana Kumbha's historic victory (1448 AD)",
            description = "Erected between 1440 and 1448 AD by the legendary Maharana Kumbha, Vijay Stambha stands as the definitive architectural emblem of Rajput triumph over the joint armies of Malwa and Gujarat led by Mahmud Khilji. Towering 37.19 meters (122 feet) high, its nine intricately carved storeys represent an open-air stone encyclopedia of Hindu iconography.",
            history = "Maharana Kumbha was a prolific warrior-king, polymath, and poet who never lost a battle. After his decisive victory at the Battle of Sarangpur in 1437 AD against the combined sultanates of Malwa and Gujarat, he commissioned this triumphant obelisk dedicated to Lord Vishnu. Renowned royal architect Jaita and his sons Napa, Puja, and Poma meticulously sculpted thousands of divine figures across its red sandstone and white marble surfaces.",
            architecture = "The tower sits on a 47 square foot base and rises 122 feet through nine distinct stories connected by 157 narrow, spiraling stone steps. Each level features pillared balconies offering panoramic vistas of the 700-acre fort plateau and the plains below. The interior walls are inscribed with detailed genealogical chronicles and verses composed by the royal court scholar Atri and his son Mahesh.",
            audioStory = "Welcome to Vijay Stambha, the Tower of Victory. As you stand before this towering 122-foot monument, you are witnessing the pinnacle of 15th-century Mewari architecture. Built by Maharana Kumbha in 1448 to celebrate his battlefield triumphs, every tier depicts intricate celestial musicians, gods, and sacred symbols. Take a deep breath of the fort breeze as you imagine centuries of Rajput sentinels watching over the kingdom from its highest balconies.",
            timings = "9:00 AM – 5:00 PM (Fort open 4:00 AM – 10:00 PM)",
            entryFee = "Included in Fort ASI Entry Ticket (₹40 Indian / ₹600 Foreigner)",
            bestTime = "Golden Hour (4:30 PM – 6:00 PM) for radiant sandstone reflections",
            latitude = 24.8879,
            longitude = 74.6293,
            highlights = listOf(
                "37.19 meters (122 feet) tall with 9 ornate tiers",
                "157 internal spiraling steps leading to the top deck",
                "Sculptures of Vishnu, Shiva, Brahma, and celestial apsaras",
                "Inscriptions by poets Atri and Mahesh detailing Mewar lineage",
                "Spectacular sunset photography vantage point"
            )
        ),

        Monument(
            id = "padmini_palace",
            name = "Rani Padmini Palace",
            hindiName = "रानी पद्मिनी महल",
            category = "Royal Palace",
            imageRes = R.drawable.padmini_palace_new,
            tagline = "The historic lakeside palace echoing the valor, beauty, and immortal sacrifice of 1303 AD",
            description = "Situated serenely on the southern rim of the fort beside a picturesque lotus reservoir, Rani Padmini Palace is one of the most culturally revered monuments of Rajasthan. It was here that legendary Queen Padmini resided alongside King Rawal Ratan Singh, and whose unmatched grace and bravery inspired centuries of Rajput ballads.",
            history = "In 1303 AD, Sultan Alauddin Khilji laid siege to Chittorgarh after hearing accounts of Rani Padmini's legendary beauty. Desiring peace, the Rajput king permitted Khilji to view Padmini's reflection across the water in a mirror pavilion. When Khilji treacherously arrested the king at the outer gate, Rajput heroes Gora and Badal executed a daring rescue. Rather than submitting to foreign captivity, Queen Padmini led thousands of royal women into the sacred fires of Jauhar.",
            architecture = "The palace complex consists of a three-storey white pavilion surrounded by serene water, designed to keep royal chambers naturally cooled during scorching desert summers. Symmetrical arched chhatris, open jharokhas, and manicured green courtyard gardens make it an enduring testament to early Mewar hydraulic palace design.",
            audioStory = "You are gazing across the serene lotus pool of Rani Padmini Palace. This tranquil waterside sanctuary was the backdrop to one of the most pivotal chapters in Indian history. In 1303 AD, Rani Padmini demonstrated supreme courage and dignity when Chittorgarh was surrounded by the forces of Alauddin Khilji. Her refusal to submit and her embrace of Jauhar elevated her into an eternal symbol of Rajput pride and womanhood.",
            timings = "9:00 AM – 5:00 PM",
            entryFee = "Included in Fort ASI Entry Ticket",
            bestTime = "Morning 9:00 AM to 11:30 AM or Afternoon 3:30 PM",
            latitude = 24.8767,
            longitude = 74.6267,
            highlights = listOf(
                "Three-storey pavilion poised over the lotus water pool",
                "Site of the famous mirror reflection viewing chamber",
                "Lush royal court gardens surrounded by battlements",
                "Scene of the legendary rescue led by warriors Gora and Badal",
                "Panoramic waterfront reflections for royal photography"
            )
        ),

        Monument(
            id = "kumbha_palace",
            name = "Maharana Kumbha Palace",
            hindiName = "राणा कुंभा महल",
            category = "Royal Palace",
            imageRes = R.drawable.kumbha_palace_new,
            tagline = "The ancient seat of Mewar royalty, birthplace of Maharana Udai Singh & shelter of Panna Dhai",
            description = "The oldest standing royal residential palace inside Chittorgarh Fort, Maharana Kumbha Palace is a sprawling complex of courtyards, Zenana mahal (queens' quarters), elephant stables, and underground vaulted chambers. It is revered as the birthplace of Maharana Udai Singh (founder of Udaipur) and the royal home where mystic saint-poetess Meera Bai lived.",
            history = "Originally constructed by Rana Hamir and substantially expanded by Maharana Kumbha in the 15th century, this palace witnessed the pinnacle of Mewari dynasty affairs. It was here that Panna Dhai famously sacrificed her own son Chandan to save infant Prince Udai Singh from assassination by the treacherous Banvir. The subterranean cellars beneath the palace mark the sacred ground where Rani Padmini performed the first Jauhar of 1303.",
            architecture = "Built using dressed yellow sandstone and limestone masonry, the palace features distinctive cantilevered balconies, decorative elephant-brackets, sun-burst Mewar crests, and reinforced defensive entry points. The complex includes the Suraj Gokhra (sun viewing balcony), Shiva temple, and cavernous multi-level vaulted basements.",
            audioStory = "Step into the royal courtyards of Maharana Kumbha Palace. These massive sandstone walls once resonated with the councils of brave Mewari rulers, the celestial bhajans of Meera Bai, and the infant laughter of Maharana Udai Singh. Look towards the eastern gates where royal cavalry once stood ready. Deep beneath where you stand lies the vaulted Jauhar cellar, where the brave queens of Chittor chose honor above defeat.",
            timings = "9:00 AM – 5:00 PM (Light & Sound show held here at 7:00 PM)",
            entryFee = "Included in Fort ASI Entry Ticket",
            bestTime = "Morning or Evening during the Sound & Light Show",
            latitude = 24.8893,
            longitude = 74.6258,
            highlights = listOf(
                "Oldest royal palace complex within Chittorgarh Fort",
                "Birthplace of Maharana Udai Singh II",
                "Site of Panna Dhai's supreme sacrifice of loyalty",
                "Subterranean Jauhar Kund underground tunnels",
                "Night venue for the grand Hindi and English Sound & Light Show"
            )
        ),

        Monument(
            id = "gaumukh_reservoir",
            name = "Gaumukh Sacred Reservoir",
            hindiName = "गौमुख कुंड",
            category = "Historic Reservoir",
            imageRes = R.drawable.gaumukh_reservoir4,
            tagline = "A perennial natural spring gushing from a stone cow's mouth along the cliffside",
            description = "Carved directly into the western edge of the 500-foot basalt cliff, Gaumukh Kund is a sacred deep water tank fed by a subterranean natural perennial spring. Water continuously cascades from a carved stone cow's mouth ('Gau-mukh') into a crystalline stone reservoir, providing life-sustaining water to fort residents for over a millennium.",
            history = "For Rajput royalty and pilgrims alike, a pilgrimage to holy shrines was incomplete without taking a sacred dip in Gaumukh Kund. During medieval sieges, the fort's ingenious interconnected reservoir system—comprising 84 ponds and stepwells—enabled 50,000 soldiers and residents to withstand blockades lasting up to eight months without surrendering.",
            architecture = "Accessible via a scenic stone staircase descending from the cliff plateau, the reservoir is framed by miniature stone shrines, arched pillared pavilions, and the imposing battlements hanging above the valley. Green algae and shimmering fish dwell in its crystal waters, offering an extraordinary cliff-edge ambiance.",
            audioStory = "Listen to the soothing sound of flowing water here at Gaumukh Kund. High atop this rocky cliff, a pure underground mountain stream emerges through the carved stone mouth of a cow. For over a thousand years, this living spring refreshed heroic warriors, holy ascetics, and visiting royalty. Feeding the fish here is considered an auspicious tradition by travelers.",
            timings = "Open daily 5:00 AM – 7:00 PM",
            entryFee = "Free (Included in Fort Entry)",
            bestTime = "Early Morning sunrise or Sunset",
            latitude = 24.8845,
            longitude = 74.6272,
            highlights = listOf(
                "Natural perennial water spring flowing from sculpted stone cow's mouth",
                "Spectacular cliff-edge views over Chittorgarh township",
                "Sacred reservoir with friendly fish that visitors feed",
                "Key lifeline during historic medieval siege defense",
                "Ideal spot for tranquil contemplation and cooling breezes"
            )
        ),

        Monument(
            id = "kirti_stambh",
            name = "Kirti Stambh (Tower of Fame)",
            hindiName = "कीर्ति स्तम्भ",
            category = "Victory Tower",
            imageRes = R.drawable.kirti_stambh,
            tagline = "The magnificent 12th-century 22-meter Digambar Jain monument honoring Adinatha",
            description = "Erected in the 12th century AD by a wealthy Jain merchant named Jijaji Kathod, Kirti Stambh (Tower of Fame) predates the Vijay Stambha by nearly two centuries. Standing 22 meters (72 feet) high, this 7-tiered architectural masterpiece is dedicated to Bhagwan Adinatha (Rishabhanatha), the first Jain Tirthankara.",
            history = "Chittorgarh has long been a major center of Jain pilgrimage and culture under the patronage of Mewar kings who staunchly defended freedom of worship. Kirti Stambh was built as a pilgrimage landmark and spiritual monument celebrating the ideals of Ahimsa (non-violence) and spiritual enlightenment.",
            architecture = "The tower sits on a 30-foot base and narrows gracefully towards its top lantern pavilion supported by four decorative columns. The exterior facade is completely encrusted with high-relief carvings of seated and standing Tirthankaras, celestial musicians, and ornate floral creepers, reflecting the Solanki-Mewari temple style.",
            audioStory = "Welcome to Kirti Stambh, the Tower of Fame. Built in the 12th century, this tower is dedicated to Lord Adinatha, the first Jain Tirthankara. Look closely at the delicate sculptures adorning every inch of its seven storeys: each figure illustrates deep devotion to truth, peace, and renunciation. It stands as a testament to the timeless coexistence of Rajput valor and Jain philosophy in Mewar.",
            timings = "9:00 AM – 5:00 PM",
            entryFee = "Included in Fort Entry",
            bestTime = "Morning light (8:00 AM – 11:00 AM)",
            latitude = 24.8942,
            longitude = 74.6319,
            highlights = listOf(
                "22 meters (72 feet) tall, built in the 12th century AD",
                "7 storeys featuring intricate Digambar Jain sculptures",
                "Dedicated to Bhagwan Adinatha, 1st Jain Tirthankara",
                "Exquisite Solanki-Maru-Gurjara stone craftsmanship",
                "Surrounded by ancient Jain temple ruins"
            )
        ),

        Monument(
            id = "meera_temple",
            name = "Meera Bai & Kumbha Shyam Temple",
            hindiName = "मीरा बाई एवं कुंभा श्याम मंदिर",
            category = "Sacred Temple",
            imageRes = R.drawable.meera_temple,
            tagline = "The divine shrine where mystic poetess Meera Bai sang immortal devotional bhajans to Krishna",
            description = "Located adjacent to the Rana Kumbha Palace, this enchanting temple complex includes the large 8th-century Kumbha Shyam Temple dedicated to Lord Varaha (Vishnu) and an intimate, lovingly crafted temple dedicated to Meera Bai, the iconic Rajput princess and Bhakti movement saint.",
            history = "Meera Bai was married to Prince Bhoj Raj of Mewar in 1516 AD. Unmoved by royal wealth and courtly politics, she devoted her heart completely to Lord Krishna ('Giridhar Gopal'). Despite severe royal disapproval after her husband's death, she fearlessly sang and danced in divine ecstasy within this sanctum. Legend recounts that when offered a cup of poison, Krishna turned it to nectar.",
            architecture = "Built in classical Indo-Aryan Nagar architecture, the temple boasts a vaulted pyramidal spire (Shikhara), an open mandapa supported by carved pillars, and a serene inner sanctum housing an idol of Lord Krishna alongside Meera Bai. In the courtyard stands a carved chhatri dedicated to Meera's spiritual guru, Saint Ravidas.",
            audioStory = "Pause for a moment in the tranquil courtyard of Meera Bai Temple. Here, in the early 16th century, Princess Meera transcended royal boundaries through pure love for Lord Krishna. Her soulful bhajans composed within these stone arches are sung across India to this day. Feel the profound peace and devotion that still permeates this sacred sanctum.",
            timings = "6:00 AM – 7:00 PM (Daily Aarti: 7:00 AM & 6:30 PM)",
            entryFee = "Free",
            bestTime = "Morning Darshan (7:00 AM – 9:30 AM)",
            latitude = 24.8872,
            longitude = 74.6289,
            highlights = listOf(
                "Historic sanctum where Meera Bai sang immortal Krishna bhajans",
                "Memorial chhatri dedicated to Saint Ravidas",
                "Magnificent 8th-century Kumbha Shyam temple shikhara",
                "Intricate stone carvings of Vishnu avatars and dancers",
                "Peaceful spiritual atmosphere with active daily aarti"
            )
        ),

        Monument(
            id = "fateh_prakash",
            name = "Fateh Prakash Palace & Museum",
            hindiName = "फतेह प्रकाश महल संग्रहालय",
            category = "Royal Palace",
            imageRes = R.drawable.fateh_prakash_new,
            tagline = "Grand royal residence housing the Government Archeological & Arms Museum",
            description = "Commissioned in modern royal style by Maharana Fateh Singh (reigned 1884–1930), this majestic palace now houses the Government Archaeological Museum. Its grand halls showcase an extraordinary collection of ancient Rajput armor, medieval cannons, rare miniature Mewari paintings, coins, and post-Gupta stone sculptures unearthed across the fort.",
            history = "Serving as an official state residence during visits by British viceroys and royal dignitaries, the palace features a fusion of modern Rajput aesthetics with British imperial influences. In 1968, the Department of Archaeology converted the main palace into the official fort museum to preserve and display artifacts spanning 1,500 years of Mewar history.",
            architecture = "Features towering domed chhatris on each corner, vast high-ceilinged darbar halls, stained glass skylights, polished marble pillars, and expansive formal courtyards facing the fort's main thoroughfare.",
            audioStory = "You are approaching the elegant facade of Fateh Prakash Palace. Named after Maharana Fateh Singh, this palace is a magnificent bridge between ancient Mewar royalty and modern heritage. Step inside the museum to behold genuine battle armor of Rajput warriors, Mughal coins, ancient stone deities, and vibrant Mewari paintings that bring centuries of history to life.",
            timings = "10:00 AM – 4:30 PM (Closed on Mondays & State Holidays)",
            entryFee = "₹20 for Indians, ₹100 for Foreigners (Museum entry)",
            bestTime = "Midday (11:00 AM – 2:00 PM)",
            latitude = 24.8885,
            longitude = 74.6275,
            highlights = listOf(
                "Rare collection of Rajput swords, shields, daggers, and armor",
                "9th to 12th-century stone sculptures of Brahma, Vishnu, and Jain deities",
                "Detailed historical dioramas and maps of the three fort sieges",
                "Beautiful Rajput jharokhas and grand marble pavilions",
                "Official ASI and Government of Rajasthan certified museum"
            )
        ),

        Monument(
            id = "fort_gates",
            name = "The Seven Grand Gates (Saat Pol)",
            hindiName = "सात ऐतिहासिक पोल (प्रवेश द्वार)",
            category = "Historic Gate",
            imageRes = R.drawable.fort_legacy,
            tagline = "Padan, Bhairon, Hanuman, Ganesh, Jorla, Lakshman & Ram Pol protecting the fort",
            description = "Rising along a zigzag 1-kilometer incline from the base town to the plateau summit, Chittorgarh Fort is fortified by seven colossal gateways ('Pols'). Designed as an insurmountable military defense system, each gate features massive iron-spiked wooden doors, murder holes for hot oil, and warrior memorials.",
            history = "Each gate honors legendary commanders who fought to the last breath. Between Padan Pol and Bhairon Pol stands the chhatri of Rawat Bagh Singh, who led the defense against Sultan Bahadur Shah of Gujarat in 1535. Further up at Bhairon Pol and Hanuman Pol stand the memorials of teenaged hero Patta of Kelwa and Jaimal of Badnore, immortalized for their superhuman bravery in the 1567 siege against Emperor Akbar.",
            architecture = "Constructed of immense ashlar granite blocks with pointed Gothic-Rajput arches, ramparts, watchtowers, and parapets. Ram Pol, the final ceremonial gateway, boasts exquisite Hindu decorative motifs, elephant brackets, and stone pillars.",
            audioStory = "As you journey up the winding hill through the seven gates of Chittorgarh, notice how each turn bends sharply at right angles. This military design stopped enemy war elephants from building ramming speed. Pay your respects at the stone chhatris of Jaimal and Patta—valiant heroes whose bravery so stunned Emperor Akbar that he erected stone statues of them outside his palace gates in Agra.",
            timings = "Always accessible (Vehicles drive through the gates)",
            entryFee = "Vehicle Toll at base checkpost",
            bestTime = "Anytime while ascending or descending the fort hill",
            latitude = 24.8950,
            longitude = 74.6240,
            highlights = listOf(
                "Seven military defense gates: Padan, Bhairon, Hanuman, Ganesh, Jorla, Lakshman, and Ram Pol",
                "Historic memorials (chhatris) of Jaimal Rathore and Patta Chundawat",
                "Memorial of Rawat Bagh Singh, hero of the 1535 defense",
                "Anti-elephant iron spiked doors and narrow archways",
                "Scenic driving ascent with valley viewpoints"
            )
        ),

        Monument(
            id = "sanwariya_ji",
            name = "Sanwariya Seth Temple (Mandaphiya)",
            hindiName = "श्री सांवलिया सेठ मंदिर (मंडफिया)",
            category = "Sacred Temple",
            imageRes = R.drawable.sanvliya_ji_temple,
            tagline = "The world-famous Krishna pilgrimage shrine 35 km from Chittorgarh",
            description = "Located in Mandaphiya just 35 km from Chittorgarh, the Sanwariya Seth Temple is one of the most revered Krishna temples in Northern India. Devotees regard Sanwariya Seth as their supreme divine business partner, offering prayers for prosperity, ethics, and well-being.",
            history = "In 1840 AD, a local milkman named Bholaram Gurjar had a divine dream pointing to sacred idols buried beneath the soil of Bagund village. Upon excavation, three breathtaking black stone murti of Lord Krishna were uncovered: one installed at Mandaphiya, another at Bhadsora, and the third at Chapar.",
            architecture = "Constructed in lavish white Makrana marble and pink sandstone, the multi-storey temple displays breathtaking carved ceilings, silver-plated sanctum doorways, high shikharas, and sprawling gardens capable of welcoming over 50,000 pilgrims daily.",
            audioStory = "Welcome to the abode of Shri Sanwariya Seth. Known affectionately across Rajasthan as the divine business partner, millions of devotees travel here from around the globe to seek blessings before starting new businesses, marriages, or ventures. Experience the divine vibration of the mangla aarti and the melodious Krishna bhajans.",
            timings = "5:00 AM – 12:00 PM & 2:30 PM – 10:00 PM",
            entryFee = "Free entry (VIP darshan queue available)",
            bestTime = "Early Morning Mangla Aarti (5:30 AM) or Evening Sandhya Aarti (7:00 PM)",
            latitude = 24.6212,
            longitude = 74.4552,
            highlights = listOf(
                "35 km from Chittorgarh via 4-lane highway",
                "Magnificent black stone murti of Lord Krishna",
                "Lavish Makrana marble halls and silver sanctum doors",
                "Free Prasad Mahaprasad bhandara for all visitors",
                "Popular 1-day extension trip from Chittorgarh Fort"
            )
        ),

        Monument(
            id = "bassi_wildlife",
            name = "Bassi Wildlife Sanctuary & Orai Dam",
            hindiName = "बस्सी वन्यजीव अभयारण्य एवं बांध",
            category = "Nature & Wildlife",
            imageRes = R.drawable.bassi_wildlife_sanctuary_chittorgarh,
            tagline = "Pristine nature reserve with leopards, antelopes, migratory birds & scenic water reservoir",
            description = "Nestled along the western Vindhyachal ranges just 25 km east of Chittorgarh, Bassi Wildlife Sanctuary covers 15,290 hectares of deciduous dry teak forest, rolling hills, and the sparkling Orai and Bassi dams. It provides a peaceful nature escape after exploring the heritage fort.",
            history = "Historically protected as the royal hunting preserve of the Rawats of Bassi, the reserve was formally declared a wildlife sanctuary by the Government of Rajasthan in 1988 to conserve indigenous fauna including leopards, four-horned antelopes (Chowsingha), and migratory aquatic birds.",
            architecture = "Eco-tourism watchtowers, boating stations, and scenic sunset viewpoints around the dam offer tranquil photographic panoramas without commercial crowds.",
            audioStory = "Breathe in the fresh forest air of Bassi Wildlife Sanctuary. Resting at the foot of the Vindhya mountains, this pristine reserve is home to agile leopards, chinkaras, and rare four-horned antelopes. The serene waters of Bassi Dam provide a peaceful sanctuary where migratory waterfowl gather every winter.",
            timings = "6:30 AM – 5:30 PM",
            entryFee = "₹50 Indian / ₹300 Foreigner + Vehicle Entry",
            bestTime = "October to March (Winter bird watching)",
            latitude = 25.0125,
            longitude = 74.7892,
            highlights = listOf(
                "25 km east of Chittorgarh near Bassi town",
                "Panthers, wild boars, jackals, hyenas, and antelopes",
                "Orai Dam scenic boating and picnic banks",
                "Ideal companion visit for photographers and families",
                "Tranquil atmosphere away from city hustle"
            )
        )
    )

    val itineraries: List<Itinerary> = listOf(
        Itinerary(
            id = "full_day_royal",
            title = "The Grand Royal Citadel Tour",
            hindiTitle = "सम्पूर्ण चित्तौड़गढ़ दुर्ग दर्शन",
            badge = "Recommended • Full Day",
            duration = "5 – 6 Hours",
            description = "The ultimate self-guided route covering all major UNESCO palaces, victory towers, sacred shrines, and reservoirs in chronological order.",
            totalStops = 8,
            imageRes = R.drawable.fort,
            stops = listOf(
                ItineraryStop(1, "fort_gates", "Ascend through Seven Pols", "30 mins", "Drive up through Padan Pol to Ram Pol, honoring the memorials of Jaimal & Patta.", "Keep camera ready for scenic curves."),
                ItineraryStop(2, "kumbha_palace", "Maharana Kumbha Palace", "45 mins", "Explore the royal courtyards, Zenana quarters, and birthplace of Maharana Udai Singh.", "Watch for low doorways in ancient chambers."),
                ItineraryStop(3, "fateh_prakash", "Fateh Prakash Palace Museum", "40 mins", "Marvel at ancient Rajput weapons, armor, coins, and rare stone sculptures.", "Photography inside museum requires ticket."),
                ItineraryStop(4, "meera_temple", "Meera Bai & Kumbha Shyam Temple", "30 mins", "Pay homage at the devotional sanctum of devotee poetess Meera Bai.", "Remove shoes at the entrance marble steps."),
                ItineraryStop(5, "vijay_stambh", "Vijay Stambha (Tower of Victory)", "45 mins", "Stand before the 9-storey victory tower of Maharana Kumbha.", "Marvel at the intricate Vishnu sculptures."),
                ItineraryStop(6, "gaumukh_reservoir", "Gaumukh Sacred Reservoir", "35 mins", "Descend to the cliffside spring and feed the sacred fish.", "Steps can be steep, walk carefully."),
                ItineraryStop(7, "padmini_palace", "Rani Padmini Palace & Lotus Pool", "45 mins", "Witness the historic water pavilion echoing the 1303 Jauhar sacrifice.", "Best reflections from eastern walkway."),
                ItineraryStop(8, "kirti_stambh", "Kirti Stambh (Tower of Fame)", "30 mins", "Conclude at the 12th-century Jain tower honoring Lord Adinatha.", "Stunning sunset glow on the stones.")
            )
        ),

        Itinerary(
            id = "express_highlights",
            title = "Express Heritage Highlights",
            hindiTitle = "प्रमुख ऐतिहासिक आकर्षण (फास्ट ट्रैक)",
            badge = "Must See • Half Day",
            duration = "2.5 – 3 Hours",
            description = "Designed for travelers on a tight schedule or day-trippers visiting from Udaipur. Focuses on the top 4 iconic landmarks.",
            totalStops = 4,
            imageRes = R.drawable.vijay_stambh_new,
            stops = listOf(
                ItineraryStop(1, "vijay_stambh", "Vijay Stambha", "45 mins", "The primary icon of Mewar victory and architectural genius.", "Must-see monument #1 in Chittorgarh."),
                ItineraryStop(2, "kumbha_palace", "Rana Kumbha Palace", "40 mins", "The royal cradle of Maharana Pratap's lineage and Jauhar grounds.", "Walk through the royal residential ruins."),
                ItineraryStop(3, "gaumukh_reservoir", "Gaumukh Kund", "25 mins", "The perennial sacred water reservoir on the cliff edge.", "Spectacular panoramic view of Chittor town."),
                ItineraryStop(4, "padmini_palace", "Padmini Palace", "35 mins", "The timeless lakeside pavilion surrounded by lotus water.", "Ideal spot for parting memories.")
            )
        ),

        Itinerary(
            id = "sunset_photo",
            title = "Sunset & Golden Hour Trail",
            hindiTitle = "सूर्यास्त एवं फोटोग्राफी ट्रेल",
            badge = "Golden Hour • Evening",
            duration = "2 Hours",
            description = "Crafted for photographers and sunset lovers seeking radiant amber lighting across fort battlements and water reservoirs.",
            totalStops = 3,
            imageRes = R.drawable.padmini_palace_new,
            stops = listOf(
                ItineraryStop(1, "gaumukh_reservoir", "Gaumukh Cliffside Panoramas", "35 mins", "Capture the golden rays hitting the cliff wall and sacred pond.", "Wide angle lens recommended."),
                ItineraryStop(2, "padmini_palace", "Padmini Palace Lotus Reflection", "40 mins", "Silhouettes and golden reflections of the water palace.", "Capture the pavilion against calm water."),
                ItineraryStop(3, "vijay_stambh", "Vijay Stambha Sunset Illumination", "45 mins", "Watch the red sandstone tower turn glowing amber as the sun dips.", "Stay for the evening floodlights.")
            )
        )
    )

    val serviceItems: List<ServiceItem> = listOf(
        ServiceItem(
            id = "vip_foreign_tour",
            title = "Foreign Guest VIP Heritage Experience",
            subtitle = "Multilingual Historian + AC Sedan + Tickets",
            price = "₹2,800 – ₹4,500 (~$35 – $55 USD)",
            details = "All-inclusive royal experience for international visitors. Govt-approved English, French & German speaking historian, sanitized AC private cab, line-skip monument assistance & authentic Mewari heritage lunch.",
            imageRes = R.drawable.fort,
            actionType = "WHATSAPP_VIP"
        ),

        ServiceItem(
            id = "photo_drone_tour",
            title = "Sunrise & Golden Hour Photography Tour",
            subtitle = "Curated Vistas for Creators & Photographers",
            price = "₹1,800 – ₹2,500 (~$22 – $30 USD)",
            details = "Exclusive 3-hour dawn/dusk vantage circuit covering Gaumukh cliffside, Vijay Stambha lighting & hidden fort ramparts. Led by veteran local heritage photographers.",
            imageRes = R.drawable.vijay_stambh_new,
            actionType = "WHATSAPP_PHOTO"
        ),

        ServiceItem(
            id = "guide_booking",
            title = "Authorized Mewari Tour Guide",
            subtitle = "Govt. & ASI Approved Historian Guides",
            price = "From ₹600 – ₹1,200 (~$8 – $15 USD)",
            details = "Certified local Mewari guides with deep knowledge of fort history, hidden passages, architecture, and legends. Available in Hindi, English, Gujarati, and international languages.",
            imageRes = R.drawable.fort,
            actionType = "WHATSAPP_GUIDE"
        ),

        ServiceItem(
            id = "taxi_sedan",
            title = "Sedan Taxi (Dzire / Etios)",
            subtitle = "Sanitized AC Private Cab",
            price = "₹1,500 (~$18 USD) local / ₹2,800 (~$34 USD) Udaipur",
            details = "Comfortable 4-seater air-conditioned sedan for exploring the 700-acre fort plateau with knowledgeable driver. Includes fuel, parking, and toll.",
            imageRes = R.drawable.sedan_taxi,
            actionType = "WHATSAPP_TAXI"
        ),

        ServiceItem(
            id = "taxi_suv",
            title = "SUV Taxi (Innova / Ertiga)",
            subtitle = "Spacious 6-7 Seater for Families",
            price = "₹2,200 (~$26 USD) local / ₹3,800 (~$45 USD) Udaipur",
            details = "Premium family-sized SUV with experienced hill drivers. Ideal for group sight-seeing and round trips to Sanwariya Seth Temple or Menal Waterfalls.",
            imageRes = R.drawable.suv_taxi,
            actionType = "WHATSAPP_TAXI"
        ),

        ServiceItem(
            id = "taxi_tempo",
            title = "Tempo Traveller (12-16 Seater)",
            subtitle = "Group & Family Tour Coach",
            price = "₹3,500 – ₹5,500 (~$42 – $65 USD) Group Tour",
            details = "Push-back seats, ample luggage space, and AC. Perfect for tour groups, wedding guests, and school excursions visiting Mewar.",
            imageRes = R.drawable.tempo_traveller,
            actionType = "WHATSAPP_TAXI"
        ),

        ServiceItem(
            id = "mewari_food",
            title = "Authentic Mewari Dal Baati Churma",
            subtitle = "Pure Desi Ghee Culinary Experience",
            price = "Traditional Dining • Vocal for Local",
            details = "Crispy oven-baked baatis dipped in warm desi ghee, served with spicy panchmel dal, garlic chutney, and crushed sweet churma. Best enjoyed at verified local eateries.",
            imageRes = R.drawable.dal_batti_churma_new,
            actionType = "OPEN_LINK"
        )
    )

    fun getMonumentById(id: String): Monument? {
        return monuments.find { it.id == id }
    }
}

