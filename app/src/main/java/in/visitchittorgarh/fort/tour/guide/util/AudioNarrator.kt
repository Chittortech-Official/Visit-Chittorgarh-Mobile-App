package `in`.visitchittorgarh.fort.tour.guide.util

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale

class AudioNarrator(
    context: Context,
    private var currentLocale: Locale = Locale.ENGLISH,
    private val onInitListener: ((Boolean) -> Unit)? = null
) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    private var isInitialized = false
    private var currentPlayingId: String? = null
    var onSpeechStateChange: ((Boolean, String?) -> Unit)? = null

    private var currentRate: Float = 1.0f

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            applyLocale(currentLocale)
            tts?.setSpeechRate(currentRate)

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    currentPlayingId = utteranceId
                    onSpeechStateChange?.invoke(true, utteranceId)
                }

                override fun onDone(utteranceId: String?) {
                    currentPlayingId = null
                    onSpeechStateChange?.invoke(false, utteranceId)
                }

                @Deprecated("Deprecated in Java")
                override fun onError(utteranceId: String?) {
                    currentPlayingId = null
                    onSpeechStateChange?.invoke(false, utteranceId)
                }
            })
            onInitListener?.invoke(isInitialized)
        } else {
            isInitialized = false
            onInitListener?.invoke(false)
        }
    }

    fun setLocale(locale: Locale) {
        currentLocale = locale
        if (isInitialized) {
            applyLocale(locale)
        }
    }

    private fun applyLocale(locale: Locale) {
        val result = tts?.setLanguage(locale)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            // Fallback to English if device TTS has not downloaded this language voice pack
            tts?.setLanguage(Locale.ENGLISH)
        }
        isInitialized = true
    }

    fun play(id: String, text: String) {
        if (!isInitialized) return
        stop()
        currentPlayingId = id
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, id)
    }

    fun stop() {
        if (tts?.isSpeaking == true) {
            tts?.stop()
        }
        val prev = currentPlayingId
        currentPlayingId = null
        if (prev != null) {
            onSpeechStateChange?.invoke(false, prev)
        }
    }

    fun isSpeaking(): Boolean {
        return tts?.isSpeaking == true
    }

    fun getCurrentPlayingId(): String? = currentPlayingId

    fun setSpeechRate(rate: Float) {
        currentRate = rate
        tts?.setSpeechRate(rate)
    }

    fun shutdown() {
        stop()
        tts?.shutdown()
        tts = null
    }
}
