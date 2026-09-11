package com.wallee.android.till.sdk.data

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException

/**
 * Accessibility interface requested for one transaction. A request does not guarantee that the
 * terminal supports the selected interface.
 *
 * @property code Numeric accessibility-mode code:
 *
 * - `0`: Standard interface ([DISABLED]).
 * - `1`: Blind mode ([BLIND_MODE]).
 * - `2`: Colour-blind mode ([COLOUR_BLIND_MODE]).
 */
@JsonAdapter(AccessibilityMode.Adapter::class)
enum class AccessibilityMode(val code: Int) {
    /** Standard user interface. */
    DISABLED(0),
    /** Blind mode, including spoken instructions on supported terminals. */
    BLIND_MODE(1),
    /** Colour-blind mode (currently unsupported). */
    COLOUR_BLIND_MODE(2);

    /** Resolves numeric accessibility-mode requests. */
    companion object {
        /**
         * Resolves a numeric accessibility-mode code, using the standard interface for unknown values.
         *
         * @param code the requested mode code.
         * @return the corresponding mode, or [DISABLED] when the code is unknown.
         */
        @JvmStatic
        fun fromCode(code: Int): AccessibilityMode = when (code) {
            1 -> BLIND_MODE
            2 -> COLOUR_BLIND_MODE
            else -> DISABLED
        }
    }

    /** Preserves numeric mode codes and defaults malformed request values to the standard interface. */
    private class Adapter : TypeAdapter<AccessibilityMode>() {
        @Throws(IOException::class)
        override fun read(reader: JsonReader): AccessibilityMode = when (reader.peek()) {
            JsonToken.NUMBER, JsonToken.STRING -> when (reader.nextString()) {
                "1" -> BLIND_MODE
                "2" -> COLOUR_BLIND_MODE
                else -> DISABLED
            }
            else -> {
                reader.skipValue()
                DISABLED
            }
        }

        @Throws(IOException::class)
        override fun write(writer: JsonWriter, mode: AccessibilityMode) {
            writer.value(mode.code)
        }
    }
}
