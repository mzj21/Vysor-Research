// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.util.LruCache;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.content.res.Resources$Theme;
import android.support.v7.content.res.AppCompatResources;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.ColorFilter;
import android.util.AttributeSet;
import android.content.res.XmlResourceParser;
import android.content.res.Resources;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.os.Build$VERSION;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.ColorUtils;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.annotation.DrawableRes;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.appcompat.R;
import android.util.TypedValue;
import android.content.res.ColorStateList;
import android.util.SparseArray;
import android.graphics.drawable.Drawable$ConstantState;
import java.lang.ref.WeakReference;
import android.support.v4.util.LongSparseArray;
import android.content.Context;
import java.util.WeakHashMap;
import android.support.v4.util.ArrayMap;
import android.graphics.PorterDuff$Mode;

public final class AppCompatDrawableManager
{
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final boolean DEBUG = false;
    private static final PorterDuff$Mode DEFAULT_MODE;
    private static AppCompatDrawableManager INSTANCE;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManager";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private ArrayMap<String, InflateDelegate> mDelegates;
    private final Object mDrawableCacheLock;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>> mDrawableCaches;
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray<String> mKnownDrawableIdTags;
    private WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists;
    private TypedValue mTypedValue;
    
    static {
        DEFAULT_MODE = PorterDuff$Mode.SRC_IN;
        COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
        TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light };
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
        TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material };
        TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
    }
    
    public AppCompatDrawableManager() {
        this.mDrawableCacheLock = new Object();
        this.mDrawableCaches = new WeakHashMap<Context, LongSparseArray<WeakReference<Drawable$ConstantState>>>(0);
    }
    
    private void addDelegate(@NonNull final String s, @NonNull final InflateDelegate inflateDelegate) {
        if (this.mDelegates == null) {
            this.mDelegates = new ArrayMap<String, InflateDelegate>();
        }
        this.mDelegates.put(s, inflateDelegate);
    }
    
    private boolean addDrawableToCache(@NonNull final Context context, final long n, @NonNull final Drawable drawable) {
        final Drawable$ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            synchronized (this.mDrawableCacheLock) {
                LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
                if (longSparseArray == null) {
                    longSparseArray = new LongSparseArray<WeakReference<Drawable$ConstantState>>();
                    this.mDrawableCaches.put(context, longSparseArray);
                }
                longSparseArray.put(n, new WeakReference<Drawable$ConstantState>(constantState));
                // monitorexit(this.mDrawableCacheLock)
                return true;
            }
        }
        return false;
    }
    
    private void addTintListToCache(@NonNull final Context context, @DrawableRes final int n, @NonNull final ColorStateList list) {
        if (this.mTintLists == null) {
            this.mTintLists = new WeakHashMap<Context, SparseArray<ColorStateList>>();
        }
        SparseArray sparseArray = this.mTintLists.get(context);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            this.mTintLists.put(context, (SparseArray<ColorStateList>)sparseArray);
        }
        sparseArray.append(n, (Object)list);
    }
    
    private static boolean arrayContains(final int[] array, final int n) {
        final int length = array.length;
        int n2 = 0;
        boolean b;
        while (true) {
            b = false;
            if (n2 >= length) {
                break;
            }
            if (array[n2] == n) {
                b = true;
                break;
            }
            ++n2;
        }
        return b;
    }
    
    private void checkVectorDrawableSetup(@NonNull final Context context) {
        if (!this.mHasCheckedVectorDrawableSetup) {
            this.mHasCheckedVectorDrawableSetup = true;
            final Drawable drawable = this.getDrawable(context, R.drawable.abc_vector_test);
            if (drawable == null || !isVectorDrawable(drawable)) {
                this.mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }
    
    private ColorStateList createBorderlessButtonColorStateList(@NonNull final Context context, @Nullable final ColorStateList list) {
        return this.createButtonColorStateList(context, 0, null);
    }
    
    private ColorStateList createButtonColorStateList(@NonNull final Context context, @ColorInt int colorForState, @Nullable final ColorStateList list) {
        final int[][] array = new int[4][];
        final int[] array2 = new int[4];
        final int themeAttrColor = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlHighlight);
        int n = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorButtonNormal);
        array[0] = ThemeUtils.DISABLED_STATE_SET;
        if (list != null) {
            n = list.getColorForState(array[0], 0);
        }
        array2[0] = n;
        final int n2 = 0 + 1;
        array[n2] = ThemeUtils.PRESSED_STATE_SET;
        int colorForState2;
        if (list == null) {
            colorForState2 = colorForState;
        }
        else {
            colorForState2 = list.getColorForState(array[n2], 0);
        }
        array2[n2] = ColorUtils.compositeColors(themeAttrColor, colorForState2);
        final int n3 = n2 + 1;
        array[n3] = ThemeUtils.FOCUSED_STATE_SET;
        int colorForState3;
        if (list == null) {
            colorForState3 = colorForState;
        }
        else {
            colorForState3 = list.getColorForState(array[n3], 0);
        }
        array2[n3] = ColorUtils.compositeColors(themeAttrColor, colorForState3);
        final int n4 = n3 + 1;
        array[n4] = ThemeUtils.EMPTY_STATE_SET;
        if (list != null) {
            colorForState = list.getColorForState(array[n4], 0);
        }
        array2[n4] = colorForState;
        return new ColorStateList(array, array2);
    }
    
    private static long createCacheKey(final TypedValue typedValue) {
        return typedValue.assetCookie << 32 | typedValue.data;
    }
    
    private ColorStateList createColoredButtonColorStateList(@NonNull final Context context, @Nullable final ColorStateList list) {
        return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorAccent), list);
    }
    
    private ColorStateList createDefaultButtonColorStateList(@NonNull final Context context, @Nullable final ColorStateList list) {
        return this.createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, R.attr.colorButtonNormal), list);
    }
    
    private Drawable createDrawableIfNeeded(@NonNull final Context context, @DrawableRes final int n) {
        if (this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        final TypedValue mTypedValue = this.mTypedValue;
        context.getResources().getValue(n, mTypedValue, true);
        final long cacheKey = createCacheKey(mTypedValue);
        Object cachedDrawable = this.getCachedDrawable(context, cacheKey);
        Object o;
        if (cachedDrawable != null) {
            o = cachedDrawable;
        }
        else {
            if (n == R.drawable.abc_cab_background_top_material) {
                cachedDrawable = new LayerDrawable(new Drawable[] { this.getDrawable(context, R.drawable.abc_cab_background_internal_bg), this.getDrawable(context, R.drawable.abc_cab_background_top_mtrl_alpha) });
            }
            if (cachedDrawable != null) {
                ((Drawable)cachedDrawable).setChangingConfigurations(mTypedValue.changingConfigurations);
                this.addDrawableToCache(context, cacheKey, (Drawable)cachedDrawable);
            }
            o = cachedDrawable;
        }
        return (Drawable)o;
    }
    
    private static PorterDuffColorFilter createTintFilter(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int[] array) {
        PorterDuffColorFilter porterDuffColorFilter;
        if (list == null || porterDuff$Mode == null) {
            porterDuffColorFilter = null;
        }
        else {
            porterDuffColorFilter = getPorterDuffColorFilter(list.getColorForState(array, 0), porterDuff$Mode);
        }
        return porterDuffColorFilter;
    }
    
    public static AppCompatDrawableManager get() {
        if (AppCompatDrawableManager.INSTANCE == null) {
            installDefaultInflateDelegates(AppCompatDrawableManager.INSTANCE = new AppCompatDrawableManager());
        }
        return AppCompatDrawableManager.INSTANCE;
    }
    
    private Drawable getCachedDrawable(@NonNull final Context context, final long n) {
        Drawable drawable = null;
        Label_0101: {
            final LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray;
            synchronized (this.mDrawableCacheLock) {
                longSparseArray = this.mDrawableCaches.get(context);
                if (longSparseArray == null) {
                    // monitorexit(this.mDrawableCacheLock)
                    drawable = null;
                    return drawable;
                }
                final WeakReference<Drawable$ConstantState> weakReference = longSparseArray.get(n);
                if (weakReference == null) {
                    break Label_0101;
                }
                final Drawable$ConstantState drawable$ConstantState = weakReference.get();
                if (drawable$ConstantState != null) {
                    drawable = drawable$ConstantState.newDrawable(context.getResources());
                    return drawable;
                }
            }
            longSparseArray.delete(n);
        }
        // monitorexit(o)
        return drawable;
    }
    
    public static PorterDuffColorFilter getPorterDuffColorFilter(final int n, final PorterDuff$Mode porterDuff$Mode) {
        PorterDuffColorFilter value = AppCompatDrawableManager.COLOR_FILTER_CACHE.get(n, porterDuff$Mode);
        if (value == null) {
            value = new PorterDuffColorFilter(n, porterDuff$Mode);
            AppCompatDrawableManager.COLOR_FILTER_CACHE.put(n, porterDuff$Mode, value);
        }
        return value;
    }
    
    private ColorStateList getTintListFromCache(@NonNull final Context context, @DrawableRes final int n) {
        final WeakHashMap<Context, SparseArray<ColorStateList>> mTintLists = this.mTintLists;
        ColorStateList list = null;
        if (mTintLists != null) {
            final SparseArray<ColorStateList> sparseArray = this.mTintLists.get(context);
            list = null;
            if (sparseArray != null) {
                list = (ColorStateList)sparseArray.get(n);
            }
        }
        return list;
    }
    
    static PorterDuff$Mode getTintMode(final int n) {
        final int abc_switch_thumb_material = R.drawable.abc_switch_thumb_material;
        PorterDuff$Mode multiply = null;
        if (n == abc_switch_thumb_material) {
            multiply = PorterDuff$Mode.MULTIPLY;
        }
        return multiply;
    }
    
    private static void installDefaultInflateDelegates(@NonNull final AppCompatDrawableManager appCompatDrawableManager) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT < 23) {
            appCompatDrawableManager.addDelegate("vector", (InflateDelegate)new VdcInflateDelegate());
            if (sdk_INT >= 11) {
                appCompatDrawableManager.addDelegate("animated-vector", (InflateDelegate)new AvdcInflateDelegate());
            }
        }
    }
    
    private static boolean isVectorDrawable(@NonNull final Drawable drawable) {
        return drawable instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
    
    private Drawable loadDrawableFromDelegates(@NonNull final Context context, @DrawableRes final int n) {
        Drawable drawable;
        if (this.mDelegates != null && !this.mDelegates.isEmpty()) {
            if (this.mKnownDrawableIdTags != null) {
                final String s = (String)this.mKnownDrawableIdTags.get(n);
                if ("appcompat_skip_skip".equals(s) || (s != null && this.mDelegates.get(s) == null)) {
                    drawable = null;
                    return drawable;
                }
            }
            else {
                this.mKnownDrawableIdTags = (SparseArray<String>)new SparseArray();
            }
            if (this.mTypedValue == null) {
                this.mTypedValue = new TypedValue();
            }
            final TypedValue mTypedValue = this.mTypedValue;
            final Resources resources = context.getResources();
            resources.getValue(n, mTypedValue, true);
            final long cacheKey = createCacheKey(mTypedValue);
            drawable = this.getCachedDrawable(context, cacheKey);
            if (drawable == null) {
                Label_0230: {
                    if (mTypedValue.string != null && mTypedValue.string.toString().endsWith(".xml")) {
                        XmlResourceParser xml = null;
                        AttributeSet attributeSet = null;
                        Label_0247: {
                            try {
                                xml = resources.getXml(n);
                                attributeSet = Xml.asAttributeSet((XmlPullParser)xml);
                                int next;
                                do {
                                    next = ((XmlPullParser)xml).next();
                                } while (next != 2 && next != 1);
                                if (next != 2) {
                                    throw new XmlPullParserException("No start tag found");
                                }
                                break Label_0247;
                            }
                            catch (Exception ex) {
                                Log.e("AppCompatDrawableManager", "Exception while inflating drawable", (Throwable)ex);
                            }
                            break Label_0230;
                        }
                        final String name = ((XmlPullParser)xml).getName();
                        this.mKnownDrawableIdTags.append(n, (Object)name);
                        final InflateDelegate inflateDelegate = this.mDelegates.get(name);
                        if (inflateDelegate != null) {
                            drawable = inflateDelegate.createFromXmlInner(context, (XmlPullParser)xml, attributeSet, context.getTheme());
                        }
                        if (drawable != null) {
                            drawable.setChangingConfigurations(mTypedValue.changingConfigurations);
                            if (this.addDrawableToCache(context, cacheKey, drawable)) {}
                        }
                    }
                }
                if (drawable == null) {
                    this.mKnownDrawableIdTags.append(n, (Object)"appcompat_skip_skip");
                }
            }
        }
        else {
            drawable = null;
        }
        return drawable;
    }
    
    private void removeDelegate(@NonNull final String s, @NonNull final InflateDelegate inflateDelegate) {
        if (this.mDelegates != null && this.mDelegates.get(s) == inflateDelegate) {
            this.mDelegates.remove(s);
        }
    }
    
    private static void setPorterDuffColorFilter(Drawable mutate, final int n, PorterDuff$Mode default_MODE) {
        if (DrawableUtils.canSafelyMutateDrawable(mutate)) {
            mutate = mutate.mutate();
        }
        if (default_MODE == null) {
            default_MODE = AppCompatDrawableManager.DEFAULT_MODE;
        }
        mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(n, default_MODE));
    }
    
    private Drawable tintDrawable(@NonNull final Context context, @DrawableRes final int n, final boolean b, @NonNull Drawable drawable) {
        final ColorStateList tintList = this.getTintList(context, n);
        if (tintList != null) {
            if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                drawable = drawable.mutate();
            }
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawable, tintList);
            final PorterDuff$Mode tintMode = getTintMode(n);
            if (tintMode != null) {
                DrawableCompat.setTintMode(drawable, tintMode);
            }
        }
        else if (n == R.drawable.abc_seekbar_track_material) {
            final LayerDrawable layerDrawable = (LayerDrawable)drawable;
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
        }
        else if (n == R.drawable.abc_ratingbar_material || n == R.drawable.abc_ratingbar_indicator_material || n == R.drawable.abc_ratingbar_small_material) {
            final LayerDrawable layerDrawable2 = (LayerDrawable)drawable;
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
        }
        else if (!tintDrawableUsingColorFilter(context, n, drawable) && b) {
            drawable = null;
        }
        return drawable;
    }
    
    static void tintDrawable(final Drawable drawable, final TintInfo tintInfo, final int[] array) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable) {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        }
        else {
            if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                ColorStateList mTintList;
                if (tintInfo.mHasTintList) {
                    mTintList = tintInfo.mTintList;
                }
                else {
                    mTintList = null;
                }
                PorterDuff$Mode porterDuff$Mode;
                if (tintInfo.mHasTintMode) {
                    porterDuff$Mode = tintInfo.mTintMode;
                }
                else {
                    porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
                }
                drawable.setColorFilter((ColorFilter)createTintFilter(mTintList, porterDuff$Mode, array));
            }
            else {
                drawable.clearColorFilter();
            }
            if (Build$VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }
    
    static boolean tintDrawableUsingColorFilter(@NonNull final Context context, @DrawableRes final int n, @NonNull Drawable mutate) {
        PorterDuff$Mode porterDuff$Mode = AppCompatDrawableManager.DEFAULT_MODE;
        int round = -1;
        int n2;
        int n3;
        if (arrayContains(AppCompatDrawableManager.COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n)) {
            n2 = R.attr.colorControlNormal;
            n3 = 1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_CONTROL_ACTIVATED, n)) {
            n2 = R.attr.colorControlActivated;
            n3 = 1;
        }
        else if (arrayContains(AppCompatDrawableManager.COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n)) {
            n2 = 16842801;
            n3 = 1;
            porterDuff$Mode = PorterDuff$Mode.MULTIPLY;
        }
        else if (n == R.drawable.abc_list_divider_mtrl_alpha) {
            n2 = 16842800;
            n3 = 1;
            round = Math.round(40.8f);
        }
        else {
            final int abc_dialog_material_background = R.drawable.abc_dialog_material_background;
            n2 = 0;
            n3 = 0;
            if (n == abc_dialog_material_background) {
                n2 = 16842801;
                n3 = 1;
            }
        }
        boolean b;
        if (n3 != 0) {
            if (DrawableUtils.canSafelyMutateDrawable(mutate)) {
                mutate = mutate.mutate();
            }
            mutate.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, n2), porterDuff$Mode));
            if (round != -1) {
                mutate.setAlpha(round);
            }
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public Drawable getDrawable(@NonNull final Context context, @DrawableRes final int n) {
        return this.getDrawable(context, n, false);
    }
    
    Drawable getDrawable(@NonNull final Context context, @DrawableRes final int n, final boolean b) {
        this.checkVectorDrawableSetup(context);
        Drawable drawable = this.loadDrawableFromDelegates(context, n);
        if (drawable == null) {
            drawable = this.createDrawableIfNeeded(context, n);
        }
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(context, n);
        }
        if (drawable != null) {
            drawable = this.tintDrawable(context, n, b, drawable);
        }
        if (drawable != null) {
            DrawableUtils.fixDrawable(drawable);
        }
        return drawable;
    }
    
    ColorStateList getTintList(@NonNull final Context context, @DrawableRes final int n) {
        return this.getTintList(context, n, null);
    }
    
    ColorStateList getTintList(@NonNull final Context context, @DrawableRes final int n, @Nullable final ColorStateList list) {
        boolean b;
        if (list == null) {
            b = true;
        }
        else {
            b = false;
        }
        ColorStateList list2;
        if (b) {
            list2 = this.getTintListFromCache(context, n);
        }
        else {
            list2 = null;
        }
        if (list2 == null) {
            if (n == R.drawable.abc_edit_text_material) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_edittext);
            }
            else if (n == R.drawable.abc_switch_track_mtrl_alpha) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_switch_track);
            }
            else if (n == R.drawable.abc_switch_thumb_material) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_switch_thumb);
            }
            else if (n == R.drawable.abc_btn_default_mtrl_shape) {
                list2 = this.createDefaultButtonColorStateList(context, list);
            }
            else if (n == R.drawable.abc_btn_borderless_material) {
                list2 = this.createBorderlessButtonColorStateList(context, list);
            }
            else if (n == R.drawable.abc_btn_colored_material) {
                list2 = this.createColoredButtonColorStateList(context, list);
            }
            else if (n == R.drawable.abc_spinner_mtrl_am_alpha || n == R.drawable.abc_spinner_textfield_background_material) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_spinner);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL, n)) {
                list2 = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST, n)) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_default);
            }
            else if (arrayContains(AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST, n)) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_btn_checkable);
            }
            else if (n == R.drawable.abc_seekbar_thumb_material) {
                list2 = AppCompatResources.getColorStateList(context, R.color.abc_tint_seek_thumb);
            }
            if (b && list2 != null) {
                this.addTintListToCache(context, n, list2);
            }
        }
        return list2;
    }
    
    public void onConfigurationChanged(@NonNull final Context context) {
        synchronized (this.mDrawableCacheLock) {
            final LongSparseArray<WeakReference<Drawable$ConstantState>> longSparseArray = this.mDrawableCaches.get(context);
            if (longSparseArray != null) {
                longSparseArray.clear();
            }
        }
    }
    
    Drawable onDrawableLoadedFromResources(@NonNull final Context context, @NonNull final VectorEnabledTintResources vectorEnabledTintResources, @DrawableRes final int n) {
        Drawable drawable = this.loadDrawableFromDelegates(context, n);
        if (drawable == null) {
            drawable = vectorEnabledTintResources.superGetDrawable(n);
        }
        Drawable tintDrawable;
        if (drawable != null) {
            tintDrawable = this.tintDrawable(context, n, false, drawable);
        }
        else {
            tintDrawable = null;
        }
        return tintDrawable;
    }
    
    private static class AvdcInflateDelegate implements InflateDelegate
    {
        @Override
        public Drawable createFromXmlInner(@NonNull final Context context, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", (Throwable)ex);
                return null;
            }
        }
    }
    
    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter>
    {
        public ColorFilterLruCache(final int n) {
            super(n);
        }
        
        private static int generateCacheKey(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return 31 * (n + 31) + porterDuff$Mode.hashCode();
        }
        
        PorterDuffColorFilter get(final int n, final PorterDuff$Mode porterDuff$Mode) {
            return this.get(generateCacheKey(n, porterDuff$Mode));
        }
        
        PorterDuffColorFilter put(final int n, final PorterDuff$Mode porterDuff$Mode, final PorterDuffColorFilter porterDuffColorFilter) {
            return this.put(generateCacheKey(n, porterDuff$Mode), porterDuffColorFilter);
        }
    }
    
    private interface InflateDelegate
    {
        Drawable createFromXmlInner(@NonNull final Context p0, @NonNull final XmlPullParser p1, @NonNull final AttributeSet p2, @Nullable final Resources$Theme p3);
    }
    
    private static class VdcInflateDelegate implements InflateDelegate
    {
        @Override
        public Drawable createFromXmlInner(@NonNull final Context context, @NonNull final XmlPullParser xmlPullParser, @NonNull final AttributeSet set, @Nullable final Resources$Theme resources$Theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, set, resources$Theme);
            }
            catch (Exception ex) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", (Throwable)ex);
                return null;
            }
        }
    }
}
