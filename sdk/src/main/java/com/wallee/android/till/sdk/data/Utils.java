package com.wallee.android.till.sdk.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Objects;

class Utils {
    static @Nullable String checkLength(String s, String name, int maxLength) {
        if (s != null && s.length() > maxLength) {
            throw new IllegalArgumentException(name + " must be less than " + maxLength + " characters:\n" + s);
        }
        return s;
    }
    static @NonNull String checkLengthNonNull(String s, String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        return s;
    }
    static String checkAscii(String s, String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        if (! s.matches(ASCII)) {
            throw new IllegalArgumentException(name + " can contain only ASCII characters which are printable (Hexadecimal code range 20 to 7E and TAB char):\n" + s);
        }
        return s;
    }

    static String checkPrintableNoLineBreaks(String s, String name) {
        if (s != null) {
            if (! s.matches(UTF8_SINGLE_LINE)) {
                throw new IllegalArgumentException(name + " can only contain printable characters without linebreaks:\n" + s);
            }
        }
        return s;
    }
    static String checkPrintableNoLineBreaksNonNull(String s, String name) {
        requireNonNull(s, name);
        return checkPrintableNoLineBreaks(s, name);
    }

    static String check(String s, String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        return checkPrintableNoLineBreaks(s, name);
    }
    static String checkNullable(String s, String name, int maxLength) {
        checkLength(s, name, maxLength);
        return checkPrintableNoLineBreaks(s, name);
    }

    static BigDecimal check(BigDecimal n, String name) {
        requireNonNull(n, name);
        if (n.scale() > 8) {
            throw new IllegalArgumentException(name + " must have 8 or less fractional decimals: " + n);
        }
        if ((n.precision() - n.scale()) > 11) {
            throw new IllegalArgumentException(name + " must have 11 or less digits before the decimal separator: " + n);
        }
        return n;
    }

    static <T> T requireNonNull(T t, String name) {
        Objects.requireNonNull(t, name + " must not be null");
        if (t instanceof String) {
            if (((String)t).isEmpty()) {
                throw new IllegalArgumentException(name + " must not be empty");
            }
        }
        return t;
    }

    // below patterns taken from wallee io.wallee.lib.common.base.validation.StringValidationType
    public static final String UTF8_NON_PRINTABLE = "\\p{C}\\p{Cn}";

    /**
     * Matcher which matches all unicode line breaks.
     */
    public static final String UNICODE_LINE_BREAKS = "\\p{Zl}\\p{Zp}";

    /**
     * Matches all ASCII chars which are printable including spaces, but without line breaks.
     */
    public static final String ASCII = "[\t\\x20-\\x7e]*";

    /**
     * Matches all printable UTF-8 chars including spaces and line breaks.
     */
    public static final String UTF8 = "([[^" + UTF8_NON_PRINTABLE + "][\\n\\r\\t]])*";

    /**
     * Matches all printable UTF-8 chars including spaces but without line breaks.
     */
    public static final String UTF8_SINGLE_LINE = "([[^" + UTF8_NON_PRINTABLE + UNICODE_LINE_BREAKS + "][\t]])*";
}
