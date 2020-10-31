package com.wallee.android.till.sdk.data;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

class Utils {
    @Nullable
    static String checkLength(@Nullable String s, @NonNull String name, int maxLength) {
        if (s != null && s.length() > maxLength) {
            throw new IllegalArgumentException(name + " must be less than " + maxLength + " characters:\n" + s);
        }
        return s;
    }

    @NonNull
    static String checkLengthNonNull(@NonNull String s, @NonNull String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        return s;
    }

    @NonNull
    static String checkAscii(@NonNull String s, String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        if (! s.matches(ASCII)) {
            throw new IllegalArgumentException(name + " can contain only ASCII characters which are printable (Hexadecimal code range 20 to 7E and TAB char):\n" + s);
        }
        return s;
    }

    @Nullable
    static String checkPrintableNoLineBreaks(@Nullable String s, @NonNull String name) {
        if (s != null) {
            if (! s.matches(UTF8_SINGLE_LINE)) {
                throw new IllegalArgumentException(name + " can only contain printable characters without linebreaks:\n" + s);
            }
        }
        return s;
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    static String checkPrintableNoLineBreaksNonNull(@NonNull String s, @NonNull String name) {
        requireNonNull(s, name);
        return checkPrintableNoLineBreaks(s, name);
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    static String check(@NonNull String s, @NonNull String name, int maxLength) {
        requireNonNull(s, name);
        checkLength(s, name, maxLength);
        return checkPrintableNoLineBreaks(s, name);
    }

    @Nullable
    static String checkNullable(@Nullable String s, @NonNull String name, int maxLength) {
        checkLength(s, name, maxLength);
        return checkPrintableNoLineBreaks(s, name);
    }

    @NonNull
    static BigDecimal check(@NonNull BigDecimal n, @NonNull String name) {
        requireNonNull(n, name);
        if (n.scale() > 8) {
            throw new IllegalArgumentException(name + " must have 8 or less fractional decimals: " + n);
        }
        if ((n.precision() - n.scale()) > 11) {
            throw new IllegalArgumentException(name + " must have 11 or less digits before the decimal separator: " + n);
        }
        return n;
    }

    @NonNull
    static <T> T requireNonNull(@NonNull T t, @NonNull String name) {
        Objects.requireNonNull(t, name + " must not be null");
        if (t instanceof String) {
            if (((String)t).isEmpty()) {
                throw new IllegalArgumentException(name + " must not be empty");
            }
        }
        return t;
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    static Date parseTime(@NonNull String s, @NonNull String name) throws ParseException {
        if (s == null) {
            throw new ParseException(name + " must not be null", 0);
        }
        return TRANSACTION_TIME_FORMAT.parse(s);
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

    /**
     * A formatter for parsing transaction date.
     */
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat TRANSACTION_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
}
