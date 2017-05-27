// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.math.BigDecimal;
import android.content.ComponentName;
import java.util.Collections;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import java.util.Collection;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
import android.content.Context;
import java.util.List;
import java.util.Map;
import android.database.DataSetObservable;

class ActivityChooserModel extends DataSetObservable
{
    static final String ATTRIBUTE_ACTIVITY = "activity";
    static final String ATTRIBUTE_TIME = "time";
    static final String ATTRIBUTE_WEIGHT = "weight";
    static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    static final String LOG_TAG;
    static final String TAG_HISTORICAL_RECORD = "historical-record";
    static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityResolveInfo> mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    boolean mCanReadHistoricalData;
    final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;
    
    static {
        LOG_TAG = ActivityChooserModel.class.getSimpleName();
        sRegistryLock = new Object();
        sDataModelRegistry = new HashMap<String, ActivityChooserModel>();
    }
    
    private ActivityChooserModel(final Context context, final String mHistoryFileName) {
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList<ActivityResolveInfo>();
        this.mHistoricalRecords = new ArrayList<HistoricalRecord>();
        this.mActivitySorter = (ActivitySorter)new DefaultSorter();
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = context.getApplicationContext();
        if (!TextUtils.isEmpty((CharSequence)mHistoryFileName) && !mHistoryFileName.endsWith(".xml")) {
            this.mHistoryFileName = mHistoryFileName + ".xml";
        }
        else {
            this.mHistoryFileName = mHistoryFileName;
        }
    }
    
    private boolean addHistoricalRecord(final HistoricalRecord historicalRecord) {
        final boolean add = this.mHistoricalRecords.add(historicalRecord);
        if (add) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
        return add;
    }
    
    private void ensureConsistentState() {
        final boolean b = this.loadActivitiesIfNeeded() | this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if (b) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }
    
    public static ActivityChooserModel get(final Context context, final String s) {
        synchronized (ActivityChooserModel.sRegistryLock) {
            ActivityChooserModel activityChooserModel = ActivityChooserModel.sDataModelRegistry.get(s);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, s);
                ActivityChooserModel.sDataModelRegistry.put(s, activityChooserModel);
            }
            return activityChooserModel;
        }
    }
    
    private boolean loadActivitiesIfNeeded() {
        final boolean mReloadActivities = this.mReloadActivities;
        boolean b = false;
        if (mReloadActivities) {
            final Intent mIntent = this.mIntent;
            b = false;
            if (mIntent != null) {
                this.mReloadActivities = false;
                this.mActivities.clear();
                final List queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
                for (int size = queryIntentActivities.size(), i = 0; i < size; ++i) {
                    this.mActivities.add(new ActivityResolveInfo(queryIntentActivities.get(i)));
                }
                b = true;
            }
        }
        return b;
    }
    
    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = false;
            if (!TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
                AsyncTaskCompat.executeParallel((android.os.AsyncTask<Object, Object, Object>)new PersistHistoryAsyncTask(), new ArrayList(this.mHistoricalRecords), this.mHistoryFileName);
            }
        }
    }
    
    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        final int n = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (n > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < n; ++i) {
                final HistoricalRecord historicalRecord = this.mHistoricalRecords.remove(0);
            }
        }
    }
    
    private boolean readHistoricalDataIfNeeded() {
        boolean mReadShareHistoryCalled = true;
        if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = mReadShareHistoryCalled;
            this.readHistoricalDataImpl();
        }
        else {
            mReadShareHistoryCalled = false;
        }
        return mReadShareHistoryCalled;
    }
    
    private void readHistoricalDataImpl() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        android/support/v7/widget/ActivityChooserModel.mContext:Landroid/content/Context;
        //     4: aload_0        
        //     5: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore_2       
        //    12: invokestatic    android/util/Xml.newPullParser:()Lorg/xmlpull/v1/XmlPullParser;
        //    15: astore          11
        //    17: aload           11
        //    19: aload_2        
        //    20: ldc_w           "UTF-8"
        //    23: invokeinterface org/xmlpull/v1/XmlPullParser.setInput:(Ljava/io/InputStream;Ljava/lang/String;)V
        //    28: iconst_0       
        //    29: istore          12
        //    31: iload           12
        //    33: iconst_1       
        //    34: if_icmpeq       55
        //    37: iload           12
        //    39: iconst_2       
        //    40: if_icmpeq       55
        //    43: aload           11
        //    45: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //    50: istore          12
        //    52: goto            31
        //    55: ldc             "historical-records"
        //    57: aload           11
        //    59: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //    64: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    67: ifne            131
        //    70: new             Lorg/xmlpull/v1/XmlPullParserException;
        //    73: dup            
        //    74: ldc_w           "Share records file does not start with historical-records tag."
        //    77: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //    80: athrow         
        //    81: astore          8
        //    83: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //    86: new             Ljava/lang/StringBuilder;
        //    89: dup            
        //    90: invokespecial   java/lang/StringBuilder.<init>:()V
        //    93: ldc_w           "Error reading historical recrod file: "
        //    96: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    99: aload_0        
        //   100: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   109: aload           8
        //   111: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   114: pop            
        //   115: aload_2        
        //   116: ifnull          334
        //   119: aload_2        
        //   120: invokevirtual   java/io/FileInputStream.close:()V
        //   123: goto            334
        //   126: astore          10
        //   128: goto            334
        //   131: aload_0        
        //   132: getfield        android/support/v7/widget/ActivityChooserModel.mHistoricalRecords:Ljava/util/List;
        //   135: astore          13
        //   137: aload           13
        //   139: invokeinterface java/util/List.clear:()V
        //   144: aload           11
        //   146: invokeinterface org/xmlpull/v1/XmlPullParser.next:()I
        //   151: istore          14
        //   153: iload           14
        //   155: iconst_1       
        //   156: if_icmpne       175
        //   159: aload_2        
        //   160: ifnull          334
        //   163: aload_2        
        //   164: invokevirtual   java/io/FileInputStream.close:()V
        //   167: goto            334
        //   170: astore          16
        //   172: goto            334
        //   175: iload           14
        //   177: iconst_3       
        //   178: if_icmpeq       144
        //   181: iload           14
        //   183: iconst_4       
        //   184: if_icmpeq       144
        //   187: ldc             "historical-record"
        //   189: aload           11
        //   191: invokeinterface org/xmlpull/v1/XmlPullParser.getName:()Ljava/lang/String;
        //   196: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   199: ifne            263
        //   202: new             Lorg/xmlpull/v1/XmlPullParserException;
        //   205: dup            
        //   206: ldc_w           "Share records file not well-formed."
        //   209: invokespecial   org/xmlpull/v1/XmlPullParserException.<init>:(Ljava/lang/String;)V
        //   212: athrow         
        //   213: astore          5
        //   215: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
        //   218: new             Ljava/lang/StringBuilder;
        //   221: dup            
        //   222: invokespecial   java/lang/StringBuilder.<init>:()V
        //   225: ldc_w           "Error reading historical recrod file: "
        //   228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   231: aload_0        
        //   232: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
        //   235: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   238: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   241: aload           5
        //   243: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   246: pop            
        //   247: aload_2        
        //   248: ifnull          334
        //   251: aload_2        
        //   252: invokevirtual   java/io/FileInputStream.close:()V
        //   255: goto            334
        //   258: astore          7
        //   260: goto            334
        //   263: aload           13
        //   265: new             Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
        //   268: dup            
        //   269: aload           11
        //   271: aconst_null    
        //   272: ldc             "activity"
        //   274: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   279: aload           11
        //   281: aconst_null    
        //   282: ldc             "time"
        //   284: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   289: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   292: aload           11
        //   294: aconst_null    
        //   295: ldc             "weight"
        //   297: invokeinterface org/xmlpull/v1/XmlPullParser.getAttributeValue:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   302: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   305: invokespecial   android/support/v7/widget/ActivityChooserModel$HistoricalRecord.<init>:(Ljava/lang/String;JF)V
        //   308: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   313: pop            
        //   314: goto            144
        //   317: astore_3       
        //   318: aload_2        
        //   319: ifnull          326
        //   322: aload_2        
        //   323: invokevirtual   java/io/FileInputStream.close:()V
        //   326: aload_3        
        //   327: athrow         
        //   328: astore          4
        //   330: goto            326
        //   333: astore_1       
        //   334: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                   
        //  -----  -----  -----  -----  ---------------------------------------
        //  0      12     333    334    Ljava/io/FileNotFoundException;
        //  12     81     81     131    Lorg/xmlpull/v1/XmlPullParserException;
        //  12     81     213    263    Ljava/io/IOException;
        //  12     81     317    333    Any
        //  83     115    317    333    Any
        //  119    123    126    131    Ljava/io/IOException;
        //  131    153    81     131    Lorg/xmlpull/v1/XmlPullParserException;
        //  131    153    213    263    Ljava/io/IOException;
        //  131    153    317    333    Any
        //  163    167    170    175    Ljava/io/IOException;
        //  187    213    81     131    Lorg/xmlpull/v1/XmlPullParserException;
        //  187    213    213    263    Ljava/io/IOException;
        //  187    213    317    333    Any
        //  215    247    317    333    Any
        //  251    255    258    263    Ljava/io/IOException;
        //  263    314    81     131    Lorg/xmlpull/v1/XmlPullParserException;
        //  263    314    213    263    Ljava/io/IOException;
        //  263    314    317    333    Any
        //  322    326    328    333    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private boolean sortActivitiesIfNeeded() {
        boolean b;
        if (this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList((List<? extends HistoricalRecord>)this.mHistoricalRecords));
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
    
    public Intent chooseActivity(final int n) {
        Intent intent;
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                // monitorexit(this.mInstanceLock)
                intent = null;
            }
            else {
                this.ensureConsistentState();
                final ActivityResolveInfo activityResolveInfo = this.mActivities.get(n);
                final ComponentName component = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
                intent = new Intent(this.mIntent);
                intent.setComponent(component);
                if (this.mActivityChoserModelPolicy != null && this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                    // monitorexit(this.mInstanceLock)
                    intent = null;
                }
                else {
                    this.addHistoricalRecord(new HistoricalRecord(component, System.currentTimeMillis(), 1.0f));
                }
            }
        }
        return intent;
    }
    
    public ResolveInfo getActivity(final int n) {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.get(n).resolveInfo;
        }
    }
    
    public int getActivityCount() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mActivities.size();
        }
    }
    
    public int getActivityIndex(final ResolveInfo resolveInfo) {
        int n = 0;
        while (true) {
            while (true) {
                Label_0076: {
                    synchronized (this.mInstanceLock) {
                        this.ensureConsistentState();
                        final List<ActivityResolveInfo> mActivities = this.mActivities;
                        final int size = mActivities.size();
                        n = 0;
                        if (n < size) {
                            if (mActivities.get(n).resolveInfo != resolveInfo) {
                                break Label_0076;
                            }
                        }
                        else {
                            n = -1;
                        }
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
        return n;
    }
    
    public ResolveInfo getDefaultActivity() {
        ResolveInfo resolveInfo;
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            if (!this.mActivities.isEmpty()) {
                resolveInfo = this.mActivities.get(0).resolveInfo;
            }
            else {
                // monitorexit(this.mInstanceLock)
                resolveInfo = null;
            }
        }
        return resolveInfo;
    }
    
    public int getHistoryMaxSize() {
        synchronized (this.mInstanceLock) {
            return this.mHistoryMaxSize;
        }
    }
    
    public int getHistorySize() {
        synchronized (this.mInstanceLock) {
            this.ensureConsistentState();
            return this.mHistoricalRecords.size();
        }
    }
    
    public Intent getIntent() {
        synchronized (this.mInstanceLock) {
            return this.mIntent;
        }
    }
    
    public void setActivitySorter(final ActivitySorter mActivitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter != mActivitySorter) {
                this.mActivitySorter = mActivitySorter;
                if (this.sortActivitiesIfNeeded()) {
                    this.notifyChanged();
                }
            }
        }
    }
    
    public void setDefaultActivity(final int n) {
        while (true) {
            while (true) {
                synchronized (this.mInstanceLock) {
                    this.ensureConsistentState();
                    final ActivityResolveInfo activityResolveInfo = this.mActivities.get(n);
                    final ActivityResolveInfo activityResolveInfo2 = this.mActivities.get(0);
                    if (activityResolveInfo2 != null) {
                        final float n2 = 5.0f + (activityResolveInfo2.weight - activityResolveInfo.weight);
                        this.addHistoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), n2));
                        return;
                    }
                }
                final float n2 = 1.0f;
                continue;
            }
        }
    }
    
    public void setHistoryMaxSize(final int mHistoryMaxSize) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize != mHistoryMaxSize) {
                this.mHistoryMaxSize = mHistoryMaxSize;
                this.pruneExcessiveHistoricalRecordsIfNeeded();
                if (this.sortActivitiesIfNeeded()) {
                    this.notifyChanged();
                }
            }
        }
    }
    
    public void setIntent(final Intent mIntent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent != mIntent) {
                this.mIntent = mIntent;
                this.mReloadActivities = true;
                this.ensureConsistentState();
            }
        }
    }
    
    public void setOnChooseActivityListener(final OnChooseActivityListener mActivityChoserModelPolicy) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = mActivityChoserModelPolicy;
        }
    }
    
    public interface ActivityChooserModelClient
    {
        void setActivityChooserModel(final ActivityChooserModel p0);
    }
    
    public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo>
    {
        public final ResolveInfo resolveInfo;
        public float weight;
        
        public ActivityResolveInfo(final ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }
        
        @Override
        public int compareTo(final ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (this != o) {
                if (o == null) {
                    b = false;
                }
                else if (this.getClass() != o.getClass()) {
                    b = false;
                }
                else if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo)o).weight)) {
                    b = false;
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return 31 + Float.floatToIntBits(this.weight);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("resolveInfo:").append(this.resolveInfo.toString());
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface ActivitySorter
    {
        void sort(final Intent p0, final List<ActivityResolveInfo> p1, final List<HistoricalRecord> p2);
    }
    
    private final class DefaultSorter implements ActivitySorter
    {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap;
        
        DefaultSorter() {
            this.mPackageNameToActivityMap = new HashMap<ComponentName, ActivityResolveInfo>();
        }
        
        @Override
        public void sort(final Intent intent, final List<ActivityResolveInfo> list, final List<HistoricalRecord> list2) {
            final Map<ComponentName, ActivityResolveInfo> mPackageNameToActivityMap = this.mPackageNameToActivityMap;
            mPackageNameToActivityMap.clear();
            for (int size = list.size(), i = 0; i < size; ++i) {
                final ActivityResolveInfo activityResolveInfo = list.get(i);
                activityResolveInfo.weight = 0.0f;
                mPackageNameToActivityMap.put(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), activityResolveInfo);
            }
            final int n = -1 + list2.size();
            float n2 = 1.0f;
            for (int j = n; j >= 0; --j) {
                final HistoricalRecord historicalRecord = list2.get(j);
                final ActivityResolveInfo activityResolveInfo2 = mPackageNameToActivityMap.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight += n2 * historicalRecord.weight;
                    n2 *= 0.95f;
                }
            }
            Collections.sort((List<Comparable>)list);
        }
    }
    
    public static final class HistoricalRecord
    {
        public final ComponentName activity;
        public final long time;
        public final float weight;
        
        public HistoricalRecord(final ComponentName activity, final long time, final float weight) {
            this.activity = activity;
            this.time = time;
            this.weight = weight;
        }
        
        public HistoricalRecord(final String s, final long n, final float n2) {
            this(ComponentName.unflattenFromString(s), n, n2);
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = true;
            if (this != o) {
                if (o == null) {
                    b = false;
                }
                else if (this.getClass() != o.getClass()) {
                    b = false;
                }
                else {
                    final HistoricalRecord historicalRecord = (HistoricalRecord)o;
                    if (this.activity == null) {
                        if (historicalRecord.activity != null) {
                            b = false;
                            return b;
                        }
                    }
                    else if (!this.activity.equals((Object)historicalRecord.activity)) {
                        b = false;
                        return b;
                    }
                    if (this.time != historicalRecord.time) {
                        b = false;
                    }
                    else if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(historicalRecord.weight)) {
                        b = false;
                    }
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            int hashCode;
            if (this.activity == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.activity.hashCode();
            }
            return 31 * (31 * (hashCode + 31) + (int)(this.time ^ this.time >>> 32)) + Float.floatToIntBits(this.weight);
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("; activity:").append(this.activity);
            sb.append("; time:").append(this.time);
            sb.append("; weight:").append(new BigDecimal(this.weight));
            sb.append("]");
            return sb.toString();
        }
    }
    
    public interface OnChooseActivityListener
    {
        boolean onChooseActivity(final ActivityChooserModel p0, final Intent p1);
    }
    
    private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void>
    {
        public Void doInBackground(final Object... p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: iconst_0       
            //     2: aaload         
            //     3: checkcast       Ljava/util/List;
            //     6: astore_2       
            //     7: aload_1        
            //     8: iconst_1       
            //     9: aaload         
            //    10: checkcast       Ljava/lang/String;
            //    13: astore_3       
            //    14: aload_0        
            //    15: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //    18: getfield        android/support/v7/widget/ActivityChooserModel.mContext:Landroid/content/Context;
            //    21: aload_3        
            //    22: iconst_0       
            //    23: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
            //    26: astore          6
            //    28: invokestatic    android/util/Xml.newSerializer:()Lorg/xmlpull/v1/XmlSerializer;
            //    31: astore          7
            //    33: aload           7
            //    35: aload           6
            //    37: aconst_null    
            //    38: invokeinterface org/xmlpull/v1/XmlSerializer.setOutput:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //    43: aload           7
            //    45: ldc             "UTF-8"
            //    47: iconst_1       
            //    48: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //    51: invokeinterface org/xmlpull/v1/XmlSerializer.startDocument:(Ljava/lang/String;Ljava/lang/Boolean;)V
            //    56: aload           7
            //    58: aconst_null    
            //    59: ldc             "historical-records"
            //    61: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //    66: pop            
            //    67: aload_2        
            //    68: invokeinterface java/util/List.size:()I
            //    73: istore          20
            //    75: iconst_0       
            //    76: istore          21
            //    78: iload           21
            //    80: iload           20
            //    82: if_icmpge       214
            //    85: aload_2        
            //    86: iconst_0       
            //    87: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
            //    92: checkcast       Landroid/support/v7/widget/ActivityChooserModel$HistoricalRecord;
            //    95: astore          24
            //    97: aload           7
            //    99: aconst_null    
            //   100: ldc             "historical-record"
            //   102: invokeinterface org/xmlpull/v1/XmlSerializer.startTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   107: pop            
            //   108: aload           7
            //   110: aconst_null    
            //   111: ldc             "activity"
            //   113: aload           24
            //   115: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.activity:Landroid/content/ComponentName;
            //   118: invokevirtual   android/content/ComponentName.flattenToString:()Ljava/lang/String;
            //   121: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   126: pop            
            //   127: aload           7
            //   129: aconst_null    
            //   130: ldc             "time"
            //   132: aload           24
            //   134: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.time:J
            //   137: invokestatic    java/lang/String.valueOf:(J)Ljava/lang/String;
            //   140: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   145: pop            
            //   146: aload           7
            //   148: aconst_null    
            //   149: ldc             "weight"
            //   151: aload           24
            //   153: getfield        android/support/v7/widget/ActivityChooserModel$HistoricalRecord.weight:F
            //   156: invokestatic    java/lang/String.valueOf:(F)Ljava/lang/String;
            //   159: invokeinterface org/xmlpull/v1/XmlSerializer.attribute:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   164: pop            
            //   165: aload           7
            //   167: aconst_null    
            //   168: ldc             "historical-record"
            //   170: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   175: pop            
            //   176: iinc            21, 1
            //   179: goto            78
            //   182: astore          4
            //   184: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
            //   187: new             Ljava/lang/StringBuilder;
            //   190: dup            
            //   191: invokespecial   java/lang/StringBuilder.<init>:()V
            //   194: ldc             "Error writing historical record file: "
            //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   199: aload_3        
            //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   206: aload           4
            //   208: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   211: pop            
            //   212: aconst_null    
            //   213: areturn        
            //   214: aload           7
            //   216: aconst_null    
            //   217: ldc             "historical-records"
            //   219: invokeinterface org/xmlpull/v1/XmlSerializer.endTag:(Ljava/lang/String;Ljava/lang/String;)Lorg/xmlpull/v1/XmlSerializer;
            //   224: pop            
            //   225: aload           7
            //   227: invokeinterface org/xmlpull/v1/XmlSerializer.endDocument:()V
            //   232: aload_0        
            //   233: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   236: iconst_1       
            //   237: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
            //   240: aload           6
            //   242: ifnull          250
            //   245: aload           6
            //   247: invokevirtual   java/io/FileOutputStream.close:()V
            //   250: goto            212
            //   253: astore          16
            //   255: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
            //   258: new             Ljava/lang/StringBuilder;
            //   261: dup            
            //   262: invokespecial   java/lang/StringBuilder.<init>:()V
            //   265: ldc             "Error writing historical record file: "
            //   267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   270: aload_0        
            //   271: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   274: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
            //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   280: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   283: aload           16
            //   285: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   288: pop            
            //   289: aload_0        
            //   290: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   293: iconst_1       
            //   294: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
            //   297: aload           6
            //   299: ifnull          250
            //   302: aload           6
            //   304: invokevirtual   java/io/FileOutputStream.close:()V
            //   307: goto            250
            //   310: astore          18
            //   312: goto            250
            //   315: astore          13
            //   317: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
            //   320: new             Ljava/lang/StringBuilder;
            //   323: dup            
            //   324: invokespecial   java/lang/StringBuilder.<init>:()V
            //   327: ldc             "Error writing historical record file: "
            //   329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   332: aload_0        
            //   333: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   336: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
            //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   342: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   345: aload           13
            //   347: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   350: pop            
            //   351: aload_0        
            //   352: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   355: iconst_1       
            //   356: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
            //   359: aload           6
            //   361: ifnull          250
            //   364: aload           6
            //   366: invokevirtual   java/io/FileOutputStream.close:()V
            //   369: goto            250
            //   372: astore          15
            //   374: goto            250
            //   377: astore          10
            //   379: getstatic       android/support/v7/widget/ActivityChooserModel.LOG_TAG:Ljava/lang/String;
            //   382: new             Ljava/lang/StringBuilder;
            //   385: dup            
            //   386: invokespecial   java/lang/StringBuilder.<init>:()V
            //   389: ldc             "Error writing historical record file: "
            //   391: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   394: aload_0        
            //   395: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   398: getfield        android/support/v7/widget/ActivityChooserModel.mHistoryFileName:Ljava/lang/String;
            //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   404: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   407: aload           10
            //   409: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   412: pop            
            //   413: aload_0        
            //   414: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   417: iconst_1       
            //   418: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
            //   421: aload           6
            //   423: ifnull          250
            //   426: aload           6
            //   428: invokevirtual   java/io/FileOutputStream.close:()V
            //   431: goto            250
            //   434: astore          12
            //   436: goto            250
            //   439: astore          8
            //   441: aload_0        
            //   442: getfield        android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask.this$0:Landroid/support/v7/widget/ActivityChooserModel;
            //   445: iconst_1       
            //   446: putfield        android/support/v7/widget/ActivityChooserModel.mCanReadHistoricalData:Z
            //   449: aload           6
            //   451: ifnull          459
            //   454: aload           6
            //   456: invokevirtual   java/io/FileOutputStream.close:()V
            //   459: aload           8
            //   461: athrow         
            //   462: astore          23
            //   464: goto            250
            //   467: astore          9
            //   469: goto            459
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  14     28     182    212    Ljava/io/FileNotFoundException;
            //  33     176    253    315    Ljava/lang/IllegalArgumentException;
            //  33     176    315    377    Ljava/lang/IllegalStateException;
            //  33     176    377    439    Ljava/io/IOException;
            //  33     176    439    462    Any
            //  214    232    253    315    Ljava/lang/IllegalArgumentException;
            //  214    232    315    377    Ljava/lang/IllegalStateException;
            //  214    232    377    439    Ljava/io/IOException;
            //  214    232    439    462    Any
            //  245    250    462    467    Ljava/io/IOException;
            //  255    289    439    462    Any
            //  302    307    310    315    Ljava/io/IOException;
            //  317    351    439    462    Any
            //  364    369    372    377    Ljava/io/IOException;
            //  379    413    439    462    Any
            //  426    431    434    439    Ljava/io/IOException;
            //  454    459    467    472    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 206, Size: 206
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:635)
            //     at java.util.ArrayList.get(ArrayList.java:411)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
}
