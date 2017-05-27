// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text.util;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Collections;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.webkit.WebView;
import java.util.regex.Matcher;
import java.util.Locale;
import java.util.Iterator;
import android.support.v4.util.PatternsCompat;
import java.util.ArrayList;
import android.text.util.Linkify;
import android.text.style.URLSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.util.Linkify$TransformFilter;
import android.text.util.Linkify$MatchFilter;
import android.support.annotation.Nullable;
import java.util.regex.Pattern;
import android.text.method.MovementMethod;
import android.text.method.LinkMovementMethod;
import android.support.annotation.NonNull;
import android.widget.TextView;
import java.util.Comparator;

public final class LinkifyCompat
{
    private static final Comparator<LinkSpec> COMPARATOR;
    private static final String[] EMPTY_STRING;
    
    static {
        EMPTY_STRING = new String[0];
        COMPARATOR = new Comparator<LinkSpec>() {
            @Override
            public final int compare(final LinkSpec linkSpec, final LinkSpec linkSpec2) {
                int n = -1;
                if (linkSpec.start >= linkSpec2.start) {
                    if (linkSpec.start > linkSpec2.start) {
                        n = 1;
                    }
                    else if (linkSpec.end < linkSpec2.end) {
                        n = 1;
                    }
                    else if (linkSpec.end <= linkSpec2.end) {
                        n = 0;
                    }
                }
                return n;
            }
        };
    }
    
    private static void addLinkMovementMethod(@NonNull final TextView textView) {
        final MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    
    public static final void addLinks(@NonNull final TextView textView, @NonNull final Pattern pattern, @Nullable final String s) {
        addLinks(textView, pattern, s, null, null, null);
    }
    
    public static final void addLinks(@NonNull final TextView textView, @NonNull final Pattern pattern, @Nullable final String s, @Nullable final Linkify$MatchFilter linkify$MatchFilter, @Nullable final Linkify$TransformFilter linkify$TransformFilter) {
        addLinks(textView, pattern, s, null, linkify$MatchFilter, linkify$TransformFilter);
    }
    
    public static final void addLinks(@NonNull final TextView textView, @NonNull final Pattern pattern, @Nullable final String s, @Nullable final String[] array, @Nullable final Linkify$MatchFilter linkify$MatchFilter, @Nullable final Linkify$TransformFilter linkify$TransformFilter) {
        final SpannableString value = SpannableString.valueOf(textView.getText());
        if (addLinks((Spannable)value, pattern, s, array, linkify$MatchFilter, linkify$TransformFilter)) {
            textView.setText((CharSequence)value);
            addLinkMovementMethod(textView);
        }
    }
    
    public static final boolean addLinks(@NonNull final Spannable spannable, final int n) {
        boolean b;
        if (n == 0) {
            b = false;
        }
        else {
            final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
            for (int i = -1 + array.length; i >= 0; --i) {
                spannable.removeSpan((Object)array[i]);
            }
            if ((n & 0x4) != 0x0) {
                Linkify.addLinks(spannable, 4);
            }
            final ArrayList<LinkSpec> list = new ArrayList<LinkSpec>();
            if ((n & 0x1) != 0x0) {
                gatherLinks(list, spannable, PatternsCompat.AUTOLINK_WEB_URL, new String[] { "http://", "https://", "rtsp://" }, Linkify.sUrlMatchFilter, null);
            }
            if ((n & 0x2) != 0x0) {
                gatherLinks(list, spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] { "mailto:" }, null, null);
            }
            if ((n & 0x8) != 0x0) {
                gatherMapLinks(list, spannable);
            }
            pruneOverlaps(list, spannable);
            if (list.size() == 0) {
                b = false;
            }
            else {
                for (final LinkSpec linkSpec : list) {
                    if (linkSpec.frameworkAddedSpan == null) {
                        applyLink(linkSpec.url, linkSpec.start, linkSpec.end, spannable);
                    }
                }
                b = true;
            }
        }
        return b;
    }
    
    public static final boolean addLinks(@NonNull final Spannable spannable, @NonNull final Pattern pattern, @Nullable final String s) {
        return addLinks(spannable, pattern, s, null, null, null);
    }
    
    public static final boolean addLinks(@NonNull final Spannable spannable, @NonNull final Pattern pattern, @Nullable final String s, @Nullable final Linkify$MatchFilter linkify$MatchFilter, @Nullable final Linkify$TransformFilter linkify$TransformFilter) {
        return addLinks(spannable, pattern, s, null, linkify$MatchFilter, linkify$TransformFilter);
    }
    
    public static final boolean addLinks(@NonNull final Spannable spannable, @NonNull final Pattern pattern, @Nullable String s, @Nullable String[] empty_STRING, @Nullable final Linkify$MatchFilter linkify$MatchFilter, @Nullable final Linkify$TransformFilter linkify$TransformFilter) {
        if (s == null) {
            s = "";
        }
        if (empty_STRING == null || empty_STRING.length < 1) {
            empty_STRING = LinkifyCompat.EMPTY_STRING;
        }
        final String[] array = new String[1 + empty_STRING.length];
        array[0] = s.toLowerCase(Locale.ROOT);
        for (int i = 0; i < empty_STRING.length; ++i) {
            final String s2 = empty_STRING[i];
            final int n = i + 1;
            String lowerCase;
            if (s2 == null) {
                lowerCase = "";
            }
            else {
                lowerCase = s2.toLowerCase(Locale.ROOT);
            }
            array[n] = lowerCase;
        }
        boolean b = false;
        final Matcher matcher = pattern.matcher((CharSequence)spannable);
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            boolean acceptMatch = true;
            if (linkify$MatchFilter != null) {
                acceptMatch = linkify$MatchFilter.acceptMatch((CharSequence)spannable, start, end);
            }
            if (acceptMatch) {
                applyLink(makeUrl(matcher.group(0), array, matcher, linkify$TransformFilter), start, end, spannable);
                b = true;
            }
        }
        return b;
    }
    
    public static final boolean addLinks(@NonNull final TextView textView, final int n) {
        boolean b = false;
        if (n != 0) {
            final CharSequence text = textView.getText();
            if (text instanceof Spannable) {
                final boolean addLinks = addLinks((Spannable)text, n);
                b = false;
                if (addLinks) {
                    addLinkMovementMethod(textView);
                    b = true;
                }
            }
            else {
                final SpannableString value = SpannableString.valueOf(text);
                final boolean addLinks2 = addLinks((Spannable)value, n);
                b = false;
                if (addLinks2) {
                    addLinkMovementMethod(textView);
                    textView.setText((CharSequence)value);
                    b = true;
                }
            }
        }
        return b;
    }
    
    private static void applyLink(final String s, final int n, final int n2, final Spannable spannable) {
        spannable.setSpan((Object)new URLSpan(s), n, n2, 33);
    }
    
    private static void gatherLinks(final ArrayList<LinkSpec> list, final Spannable spannable, final Pattern pattern, final String[] array, final Linkify$MatchFilter linkify$MatchFilter, final Linkify$TransformFilter linkify$TransformFilter) {
        final Matcher matcher = pattern.matcher((CharSequence)spannable);
        while (matcher.find()) {
            final int start = matcher.start();
            final int end = matcher.end();
            if (linkify$MatchFilter == null || linkify$MatchFilter.acceptMatch((CharSequence)spannable, start, end)) {
                final LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), array, matcher, linkify$TransformFilter);
                linkSpec.start = start;
                linkSpec.end = end;
                list.add(linkSpec);
            }
        }
    }
    
    private static final void gatherMapLinks(final ArrayList<LinkSpec> list, final Spannable spannable) {
        String s = spannable.toString();
        int n = 0;
        while (true) {
            String address;
            LinkSpec linkSpec;
            try {
                while (true) {
                    address = WebView.findAddress(s);
                    if (address == null) {
                        break;
                    }
                    final int index = s.indexOf(address);
                    if (index < 0) {
                        break;
                    }
                    linkSpec = new LinkSpec();
                    final int n2 = index + address.length();
                    linkSpec.start = n + index;
                    linkSpec.end = n + n2;
                    s = s.substring(n2);
                    n += n2;
                    final String s2 = address;
                    final String s3 = "UTF-8";
                    final String s4 = URLEncoder.encode(s2, s3);
                    final LinkSpec linkSpec2 = linkSpec;
                    final StringBuilder sb = new StringBuilder();
                    final String s5 = "geo:0,0?q=";
                    final StringBuilder sb2 = sb.append(s5);
                    final String s6 = s4;
                    final StringBuilder sb3 = sb2.append(s6);
                    final String s7 = sb3.toString();
                    linkSpec2.url = s7;
                    final ArrayList<LinkSpec> list2 = list;
                    final LinkSpec linkSpec3 = linkSpec;
                    list2.add(linkSpec3);
                }
                return;
            }
            catch (UnsupportedOperationException ex) {
                return;
            }
            try {
                final String s2 = address;
                final String s3 = "UTF-8";
                final String s4 = URLEncoder.encode(s2, s3);
                final LinkSpec linkSpec2 = linkSpec;
                final StringBuilder sb = new StringBuilder();
                final String s5 = "geo:0,0?q=";
                final StringBuilder sb2 = sb.append(s5);
                final String s6 = s4;
                final StringBuilder sb3 = sb2.append(s6);
                final String s7 = sb3.toString();
                linkSpec2.url = s7;
                final ArrayList<LinkSpec> list2 = list;
                final LinkSpec linkSpec3 = linkSpec;
                list2.add(linkSpec3);
                continue;
            }
            catch (UnsupportedEncodingException ex2) {
                continue;
            }
            break;
        }
    }
    
    private static String makeUrl(@NonNull String s, @NonNull final String[] array, final Matcher matcher, @Nullable final Linkify$TransformFilter linkify$TransformFilter) {
        if (linkify$TransformFilter != null) {
            s = linkify$TransformFilter.transformUrl(matcher, s);
        }
        int n = 0;
        boolean b;
        while (true) {
            final int length = array.length;
            b = false;
            if (n >= length) {
                break;
            }
            if (s.regionMatches(true, 0, array[n], 0, array[n].length())) {
                b = true;
                if (!s.regionMatches(false, 0, array[n], 0, array[n].length())) {
                    s = array[n] + s.substring(array[n].length());
                    break;
                }
                break;
            }
            else {
                ++n;
            }
        }
        if (!b && array.length > 0) {
            s = array[0] + s;
        }
        return s;
    }
    
    private static final void pruneOverlaps(final ArrayList<LinkSpec> list, final Spannable spannable) {
        final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
        for (int i = 0; i < array.length; ++i) {
            final LinkSpec linkSpec = new LinkSpec();
            linkSpec.frameworkAddedSpan = array[i];
            linkSpec.start = spannable.getSpanStart((Object)array[i]);
            linkSpec.end = spannable.getSpanEnd((Object)array[i]);
            list.add(linkSpec);
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)LinkifyCompat.COMPARATOR);
        int size = list.size();
        int j = 0;
        while (j < size - 1) {
            final LinkSpec linkSpec2 = list.get(j);
            final LinkSpec linkSpec3 = list.get(j + 1);
            int n = -1;
            if (linkSpec2.start <= linkSpec3.start && linkSpec2.end > linkSpec3.start) {
                if (linkSpec3.end <= linkSpec2.end) {
                    n = j + 1;
                }
                else if (linkSpec2.end - linkSpec2.start > linkSpec3.end - linkSpec3.start) {
                    n = j + 1;
                }
                else if (linkSpec2.end - linkSpec2.start < linkSpec3.end - linkSpec3.start) {
                    n = j;
                }
                if (n != -1) {
                    final URLSpan frameworkAddedSpan = list.get(n).frameworkAddedSpan;
                    if (frameworkAddedSpan != null) {
                        spannable.removeSpan((Object)frameworkAddedSpan);
                    }
                    list.remove(n);
                    --size;
                    continue;
                }
            }
            ++j;
        }
    }
    
    private static class LinkSpec
    {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface LinkifyMask {
    }
}
