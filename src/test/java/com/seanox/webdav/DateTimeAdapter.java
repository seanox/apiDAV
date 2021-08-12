package com.seanox.webdav;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Testing private parts and/or components visible only in the package requires
 * an adapter for access.<br>
 * <br>
 * Why are the tests not in com.seanox.webdav?<br>
 * Spring Test is used for the tests. For this @ComponentScan must scan the
 * package. For the release version, however, it should be ensured that the
 * library com.seanox.webdav also works without @ComponentScan and therefore
 * another package is used for the tests of the package com.seanox.webdav.<br>
 * <br>
 * DateTimeAdapter 1.1.0 20210812<br>
 * Copyright (C) 2021 Seanox Software Solutions<br>
 * All rights reserved.
 *
 * @author  Seanox Software Solutions
 * @version 1.1.0 20210812
 */
public class DateTimeAdapter {

    public static TimeZone DEFAULT_TIME_ZONE = DateTime.DEFAULT_TIME_ZONE;

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private DateTimeAdapter() {
    }

    public static String formatDate(final Date date, final String format) {
        return DateTime.formatDate(DEFAULT_TIME_ZONE, date, format);
    }

    public static String formatDate(final TimeZone timeZone, final Date date, final String format) {
        return DateTime.formatDate(timeZone, date, format);
    }

    public static Date parseDate(final String date, final String format)
            throws ParseException {
        return DateTimeAdapter.parseDate(DEFAULT_TIME_ZONE, date, format);
    }

    public static Date parseDate(final TimeZone timeZone, final String date, final String format)
            throws ParseException {
        final SimpleDateFormat pattern = new SimpleDateFormat(format, Locale.US);
        pattern.setTimeZone(Objects.nonNull(timeZone) ? timeZone : DEFAULT_TIME_ZONE);
        return pattern.parse(date);
    }

    public static Date getApplicationBuildDate()
            throws Exception {
        final Field field = Sitemap.class.getDeclaredField("CREATION_DATE");
        field.setAccessible(true);
        return (Date)field.get(null);
    }
}