// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup;
import android.net.Uri$Builder;
import java.util.List;
import android.content.res.Resources;
import android.view.View;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v4.content.ContextCompat;
import java.io.InputStream;
import java.io.IOException;
import android.content.res.Resources$NotFoundException;
import java.io.FileNotFoundException;
import android.net.Uri;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.text.style.TextAppearanceSpan;
import android.text.SpannableString;
import android.support.v7.appcompat.R;
import android.util.TypedValue;
import android.graphics.drawable.Drawable;
import android.database.Cursor;
import android.content.res.ColorStateList;
import android.app.SearchableInfo;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable$ConstantState;
import java.util.WeakHashMap;
import android.view.View$OnClickListener;
import android.support.v4.widget.ResourceCursorAdapter;

class SuggestionsAdapter extends ResourceCursorAdapter implements View$OnClickListener
{
    private static final boolean DBG = false;
    static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = "SuggestionsAdapter";
    private static final int QUERY_LIMIT = 50;
    static final int REFINE_ALL = 2;
    static final int REFINE_BY_ENTRY = 1;
    static final int REFINE_NONE;
    private boolean mClosed;
    private final int mCommitIconResId;
    private int mFlagsCol;
    private int mIconName1Col;
    private int mIconName2Col;
    private final WeakHashMap<String, Drawable$ConstantState> mOutsideDrawablesCache;
    private final Context mProviderContext;
    private int mQueryRefinement;
    private final SearchManager mSearchManager;
    private final SearchView mSearchView;
    private final SearchableInfo mSearchable;
    private int mText1Col;
    private int mText2Col;
    private int mText2UrlCol;
    private ColorStateList mUrlColor;
    
    public SuggestionsAdapter(final Context mProviderContext, final SearchView mSearchView, final SearchableInfo mSearchable, final WeakHashMap<String, Drawable$ConstantState> mOutsideDrawablesCache) {
        super(mProviderContext, mSearchView.getSuggestionRowLayout(), null, true);
        this.mClosed = false;
        this.mQueryRefinement = 1;
        this.mText1Col = -1;
        this.mText2Col = -1;
        this.mText2UrlCol = -1;
        this.mIconName1Col = -1;
        this.mIconName2Col = -1;
        this.mFlagsCol = -1;
        this.mSearchManager = (SearchManager)this.mContext.getSystemService("search");
        this.mSearchView = mSearchView;
        this.mSearchable = mSearchable;
        this.mCommitIconResId = mSearchView.getSuggestionCommitIconResId();
        this.mProviderContext = mProviderContext;
        this.mOutsideDrawablesCache = mOutsideDrawablesCache;
    }
    
    private Drawable checkIconCache(final String s) {
        final Drawable$ConstantState drawable$ConstantState = this.mOutsideDrawablesCache.get(s);
        Drawable drawable;
        if (drawable$ConstantState == null) {
            drawable = null;
        }
        else {
            drawable = drawable$ConstantState.newDrawable();
        }
        return drawable;
    }
    
    private CharSequence formatUrl(final CharSequence charSequence) {
        if (this.mUrlColor == null) {
            final TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, typedValue, true);
            this.mUrlColor = this.mContext.getResources().getColorStateList(typedValue.resourceId);
        }
        final SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan((Object)new TextAppearanceSpan((String)null, 0, 0, this.mUrlColor, (ColorStateList)null), 0, charSequence.length(), 33);
        return (CharSequence)spannableString;
    }
    
    private Drawable getActivityIcon(final ComponentName componentName) {
        while (true) {
            final PackageManager packageManager = this.mContext.getPackageManager();
            ActivityInfo activityInfo;
            int iconResource;
            try {
                activityInfo = packageManager.getActivityInfo(componentName, 128);
                iconResource = activityInfo.getIconResource();
                if (iconResource == 0) {
                    return null;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.w("SuggestionsAdapter", ex.toString());
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable == null) {
                Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
                drawable = null;
                return drawable;
            }
            return drawable;
        }
    }
    
    private Drawable getActivityIconWithCache(final ComponentName componentName) {
        final String flattenToShortString = componentName.flattenToShortString();
        Drawable drawable;
        if (this.mOutsideDrawablesCache.containsKey(flattenToShortString)) {
            final Drawable$ConstantState drawable$ConstantState = this.mOutsideDrawablesCache.get(flattenToShortString);
            drawable = null;
            if (drawable$ConstantState != null) {
                drawable = drawable$ConstantState.newDrawable(this.mProviderContext.getResources());
            }
        }
        else {
            final Drawable activityIcon = this.getActivityIcon(componentName);
            Drawable$ConstantState constantState;
            if (activityIcon == null) {
                constantState = null;
            }
            else {
                constantState = activityIcon.getConstantState();
            }
            this.mOutsideDrawablesCache.put(flattenToShortString, constantState);
            drawable = activityIcon;
        }
        return drawable;
    }
    
    public static String getColumnString(final Cursor cursor, final String s) {
        return getStringOrNull(cursor, cursor.getColumnIndex(s));
    }
    
    private Drawable getDefaultIcon1(final Cursor cursor) {
        Drawable drawable = this.getActivityIconWithCache(this.mSearchable.getSearchActivity());
        if (drawable == null) {
            drawable = this.mContext.getPackageManager().getDefaultActivityIcon();
        }
        return drawable;
    }
    
    private Drawable getDrawable(final Uri uri) {
        while (true) {
            try {
                if ("android.resource".equals(uri.getScheme())) {
                    try {
                        return this.getDrawableFromResourceUri(uri);
                    }
                    catch (Resources$NotFoundException ex4) {
                        throw new FileNotFoundException("Resource does not exist: " + uri);
                    }
                }
            }
            catch (FileNotFoundException ex) {
                Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + ex.getMessage());
                return null;
            }
            final InputStream openInputStream = this.mProviderContext.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                break;
            }
            try {
                final Drawable drawable = Drawable.createFromStream(openInputStream, (String)null);
                try {
                    openInputStream.close();
                    return drawable;
                }
                catch (IOException ex2) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, (Throwable)ex2);
                    return drawable;
                }
                return drawable;
            }
            finally {
                try {
                    openInputStream.close();
                }
                catch (IOException ex3) {
                    Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, (Throwable)ex3);
                }
            }
        }
        throw new FileNotFoundException("Failed to open " + uri);
    }
    
    private Drawable getDrawableFromResourceValue(final String s) {
        Drawable drawable;
        if (s == null || s.length() == 0 || "0".equals(s)) {
            drawable = null;
        }
        else {
            try {
                final int int1 = Integer.parseInt(s);
                final String string = "android.resource://" + this.mProviderContext.getPackageName() + "/" + int1;
                drawable = this.checkIconCache(string);
                if (drawable == null) {
                    drawable = ContextCompat.getDrawable(this.mProviderContext, int1);
                    this.storeInIconCache(string, drawable);
                }
            }
            catch (NumberFormatException ex) {
                drawable = this.checkIconCache(s);
                if (drawable == null) {
                    drawable = this.getDrawable(Uri.parse(s));
                    this.storeInIconCache(s, drawable);
                }
            }
            catch (Resources$NotFoundException ex2) {
                Log.w("SuggestionsAdapter", "Icon resource not found: " + s);
                drawable = null;
            }
        }
        return drawable;
    }
    
    private Drawable getIcon1(final Cursor cursor) {
        Drawable drawable;
        if (this.mIconName1Col == -1) {
            drawable = null;
        }
        else {
            drawable = this.getDrawableFromResourceValue(cursor.getString(this.mIconName1Col));
            if (drawable == null) {
                drawable = this.getDefaultIcon1(cursor);
            }
        }
        return drawable;
    }
    
    private Drawable getIcon2(final Cursor cursor) {
        Drawable drawableFromResourceValue;
        if (this.mIconName2Col == -1) {
            drawableFromResourceValue = null;
        }
        else {
            drawableFromResourceValue = this.getDrawableFromResourceValue(cursor.getString(this.mIconName2Col));
        }
        return drawableFromResourceValue;
    }
    
    private static String getStringOrNull(final Cursor cursor, final int n) {
        String string = null;
        if (n != -1) {
            try {
                string = cursor.getString(n);
            }
            catch (Exception ex) {
                Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", (Throwable)ex);
                string = null;
            }
        }
        return string;
    }
    
    private void setViewDrawable(final ImageView imageView, final Drawable imageDrawable, final int visibility) {
        imageView.setImageDrawable(imageDrawable);
        if (imageDrawable == null) {
            imageView.setVisibility(visibility);
        }
        else {
            imageView.setVisibility(0);
            imageDrawable.setVisible(false, false);
            imageDrawable.setVisible(true, false);
        }
    }
    
    private void setViewText(final TextView textView, final CharSequence text) {
        textView.setText(text);
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(8);
        }
        else {
            textView.setVisibility(0);
        }
    }
    
    private void storeInIconCache(final String s, final Drawable drawable) {
        if (drawable != null) {
            this.mOutsideDrawablesCache.put(s, drawable.getConstantState());
        }
    }
    
    private void updateSpinnerState(final Cursor cursor) {
        Bundle extras;
        if (cursor != null) {
            extras = cursor.getExtras();
        }
        else {
            extras = null;
        }
        if (extras == null || extras.getBoolean("in_progress")) {}
    }
    
    public void bindView(final View view, final Context context, final Cursor cursor) {
        final ChildViewCache childViewCache = (ChildViewCache)view.getTag();
        final int mFlagsCol = this.mFlagsCol;
        int int1 = 0;
        if (mFlagsCol != -1) {
            int1 = cursor.getInt(this.mFlagsCol);
        }
        if (childViewCache.mText1 != null) {
            this.setViewText(childViewCache.mText1, getStringOrNull(cursor, this.mText1Col));
        }
        if (childViewCache.mText2 != null) {
            final String stringOrNull = getStringOrNull(cursor, this.mText2UrlCol);
            CharSequence charSequence;
            if (stringOrNull != null) {
                charSequence = this.formatUrl(stringOrNull);
            }
            else {
                charSequence = getStringOrNull(cursor, this.mText2Col);
            }
            if (TextUtils.isEmpty(charSequence)) {
                if (childViewCache.mText1 != null) {
                    childViewCache.mText1.setSingleLine(false);
                    childViewCache.mText1.setMaxLines(2);
                }
            }
            else if (childViewCache.mText1 != null) {
                childViewCache.mText1.setSingleLine(true);
                childViewCache.mText1.setMaxLines(1);
            }
            this.setViewText(childViewCache.mText2, charSequence);
        }
        if (childViewCache.mIcon1 != null) {
            this.setViewDrawable(childViewCache.mIcon1, this.getIcon1(cursor), 4);
        }
        if (childViewCache.mIcon2 != null) {
            this.setViewDrawable(childViewCache.mIcon2, this.getIcon2(cursor), 8);
        }
        if (this.mQueryRefinement == 2 || (this.mQueryRefinement == 1 && (int1 & 0x1) != 0x0)) {
            childViewCache.mIconRefine.setVisibility(0);
            childViewCache.mIconRefine.setTag((Object)childViewCache.mText1.getText());
            childViewCache.mIconRefine.setOnClickListener((View$OnClickListener)this);
        }
        else {
            childViewCache.mIconRefine.setVisibility(8);
        }
    }
    
    public void changeCursor(final Cursor cursor) {
        if (this.mClosed) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
            }
        }
        else {
            try {
                super.changeCursor(cursor);
                if (cursor != null) {
                    this.mText1Col = cursor.getColumnIndex("suggest_text_1");
                    this.mText2Col = cursor.getColumnIndex("suggest_text_2");
                    this.mText2UrlCol = cursor.getColumnIndex("suggest_text_2_url");
                    this.mIconName1Col = cursor.getColumnIndex("suggest_icon_1");
                    this.mIconName2Col = cursor.getColumnIndex("suggest_icon_2");
                    this.mFlagsCol = cursor.getColumnIndex("suggest_flags");
                }
            }
            catch (Exception ex) {
                Log.e("SuggestionsAdapter", "error changing cursor and caching columns", (Throwable)ex);
            }
        }
    }
    
    public void close() {
        this.changeCursor(null);
        this.mClosed = true;
    }
    
    public CharSequence convertToString(final Cursor cursor) {
        CharSequence columnString;
        if (cursor == null) {
            columnString = null;
        }
        else {
            columnString = getColumnString(cursor, "suggest_intent_query");
            if (columnString == null) {
                if (this.mSearchable.shouldRewriteQueryFromData()) {
                    final String columnString2 = getColumnString(cursor, "suggest_intent_data");
                    if (columnString2 != null) {
                        columnString = columnString2;
                        return columnString;
                    }
                }
                if (this.mSearchable.shouldRewriteQueryFromText()) {
                    final String columnString3 = getColumnString(cursor, "suggest_text_1");
                    if (columnString3 != null) {
                        columnString = columnString3;
                        return columnString;
                    }
                }
                columnString = null;
            }
        }
        return columnString;
    }
    
    Drawable getDrawableFromResourceUri(final Uri uri) throws FileNotFoundException {
        final String authority = uri.getAuthority();
        if (TextUtils.isEmpty((CharSequence)authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        Resources resourcesForApplication;
        List pathSegments;
        try {
            resourcesForApplication = this.mContext.getPackageManager().getResourcesForApplication(authority);
            pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
        final int size = pathSegments.size();
        while (true) {
            Label_0219: {
                if (size != 1) {
                    break Label_0219;
                }
                try {
                    final int n = Integer.parseInt(pathSegments.get(0));
                    if (n == 0) {
                        throw new FileNotFoundException("No resource found for: " + uri);
                    }
                    return resourcesForApplication.getDrawable(n);
                }
                catch (NumberFormatException ex2) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            }
            if (size == 2) {
                final int n = resourcesForApplication.getIdentifier((String)pathSegments.get(1), (String)pathSegments.get(0), authority);
                continue;
            }
            break;
        }
        throw new FileNotFoundException("More than two path segments: " + uri);
    }
    
    public int getQueryRefinement() {
        return this.mQueryRefinement;
    }
    
    Cursor getSearchManagerSuggestions(final SearchableInfo searchableInfo, final String s, final int n) {
        Cursor query = null;
        if (searchableInfo != null) {
            final String suggestAuthority = searchableInfo.getSuggestAuthority();
            query = null;
            if (suggestAuthority != null) {
                final Uri$Builder fragment = new Uri$Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
                final String suggestPath = searchableInfo.getSuggestPath();
                if (suggestPath != null) {
                    fragment.appendEncodedPath(suggestPath);
                }
                fragment.appendPath("search_suggest_query");
                final String suggestSelection = searchableInfo.getSuggestSelection();
                String[] array;
                if (suggestSelection != null) {
                    array = new String[] { s };
                }
                else {
                    fragment.appendPath(s);
                    array = null;
                }
                if (n > 0) {
                    fragment.appendQueryParameter("limit", String.valueOf(n));
                }
                query = this.mContext.getContentResolver().query(fragment.build(), (String[])null, suggestSelection, array, (String)null);
            }
        }
        return query;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        try {
            return super.getView(n, view, viewGroup);
        }
        catch (RuntimeException ex) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", (Throwable)ex);
            final View view2 = this.newView(this.mContext, this.mCursor, viewGroup);
            if (view2 != null) {
                ((ChildViewCache)view2.getTag()).mText1.setText((CharSequence)ex.toString());
                return view2;
            }
            return view2;
        }
    }
    
    public boolean hasStableIds() {
        return false;
    }
    
    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup viewGroup) {
        final View view = super.newView(context, cursor, viewGroup);
        view.setTag((Object)new ChildViewCache(view));
        ((ImageView)view.findViewById(R.id.edit_query)).setImageResource(this.mCommitIconResId);
        return view;
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.updateSpinnerState(this.getCursor());
    }
    
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        this.updateSpinnerState(this.getCursor());
    }
    
    public void onClick(final View view) {
        final Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.mSearchView.onQueryRefine((CharSequence)tag);
        }
    }
    
    public Cursor runQueryOnBackgroundThread(final CharSequence charSequence) {
        String string;
        if (charSequence == null) {
            string = "";
        }
        else {
            string = charSequence.toString();
        }
        final int visibility = this.mSearchView.getVisibility();
        Cursor cursor = null;
        if (visibility == 0) {
            final int windowVisibility = this.mSearchView.getWindowVisibility();
            cursor = null;
            if (windowVisibility == 0) {
                try {
                    final Cursor searchManagerSuggestions = this.getSearchManagerSuggestions(this.mSearchable, string, 50);
                    cursor = null;
                    if (searchManagerSuggestions != null) {
                        searchManagerSuggestions.getCount();
                        cursor = searchManagerSuggestions;
                    }
                }
                catch (RuntimeException ex) {
                    Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", (Throwable)ex);
                    cursor = null;
                }
            }
        }
        return cursor;
    }
    
    public void setQueryRefinement(final int mQueryRefinement) {
        this.mQueryRefinement = mQueryRefinement;
    }
    
    private static final class ChildViewCache
    {
        public final ImageView mIcon1;
        public final ImageView mIcon2;
        public final ImageView mIconRefine;
        public final TextView mText1;
        public final TextView mText2;
        
        public ChildViewCache(final View view) {
            this.mText1 = (TextView)view.findViewById(16908308);
            this.mText2 = (TextView)view.findViewById(16908309);
            this.mIcon1 = (ImageView)view.findViewById(16908295);
            this.mIcon2 = (ImageView)view.findViewById(16908296);
            this.mIconRefine = (ImageView)view.findViewById(R.id.edit_query);
        }
    }
}
