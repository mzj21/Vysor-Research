// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import java.util.ArrayList;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.annotation.Nullable;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v4.view.PagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.SystemProperties;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.provider.Settings$Secure;
import android.support.v7.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity
{
    int[] dots;
    NonSwipeableViewPager pager;
    
    public StartActivity() {
        this.dots = new int[] { 2131558520, 2131558521, 2131558522, 2131558523 };
    }
    
    private boolean adbIsEnabled() {
        boolean b = true;
        if (Settings$Secure.getInt(this.getContentResolver(), "adb_enabled", 0) != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    private boolean deviceIsPluggedIn() {
        boolean b = true;
        final int intExtra = this.getBaseContext().registerReceiver((BroadcastReceiver)null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1);
        if (intExtra != 2 && intExtra != (b ? 1 : 0)) {
            b = false;
        }
        return b;
    }
    
    private void gotoPage() {
        if (!this.adbIsEnabled()) {
            this.pager.setCurrentItem(1);
        }
        else if (!this.deviceIsPluggedIn()) {
            this.pager.setCurrentItem(2);
            this.startService(new Intent((Context)this, (Class)PowerMonitorService.class));
        }
        else {
            this.pager.setCurrentItem(4);
        }
    }
    
    private boolean isDeviceReady() {
        return this.adbIsEnabled() && this.deviceIsPluggedIn() && this.ptpIsEnabled();
    }
    
    private boolean ptpIsEnabled() {
        final String value = SystemProperties.get("persist.sys.usb.config");
        return value == null || !value.contains("mtp");
    }
    
    void addArgs(final Fragment fragment, final int n) {
        final Bundle arguments = new Bundle();
        arguments.putInt("layout", n);
        fragment.setArguments(arguments);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968627);
        (this.pager = (NonSwipeableViewPager)this.findViewById(2131558519)).allowSwiping(true);
        final PagerAdapter adapter = new PagerAdapter();
        final WelcomeFragment welcomeFragment = new WelcomeFragment();
        this.addArgs(welcomeFragment, 2130968631);
        final SimpleFragment simpleFragment = new SimpleFragment();
        this.addArgs(simpleFragment, 2130968603);
        final DebuggingFragment debuggingFragment = new DebuggingFragment();
        this.addArgs(debuggingFragment, 2130968606);
        this.addArgs(new PtpFragment(), 2130968607);
        final ChromeFragment chromeFragment = new ChromeFragment();
        this.addArgs(chromeFragment, 2130968630);
        adapter.fragments.add(welcomeFragment);
        adapter.fragments.add(debuggingFragment);
        adapter.fragments.add(simpleFragment);
        adapter.fragments.add(chromeFragment);
        adapter.notifyDataSetChanged();
        this.pager.setAdapter(adapter);
        if (this.isDeviceReady()) {
            this.pager.setCurrentItem(-1 + this.dots.length);
        }
        else if (this.getIntent().getBooleanExtra("goto", false)) {
            this.gotoPage();
        }
    }
    
    public static class ChromeFragment extends SimpleFragment
    {
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            onCreateView.findViewById(2131558527).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    final Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", "https://goo.gl/FvMqnY");
                    intent.setType("text/plain");
                    ChromeFragment.this.startActivity(intent);
                }
            });
            return onCreateView;
        }
    }
    
    public static class DebuggingFragment extends SimpleFragment
    {
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            onCreateView.findViewById(2131558500).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    final Intent intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.DevelopmentSettings");
                    DebuggingFragment.this.getActivity().startService(new Intent((Context)DebuggingFragment.this.getActivity(), (Class)UsbDebuggingMonitorService.class));
                    DebuggingFragment.this.startActivity(intent);
                }
            });
            return onCreateView;
        }
    }
    
    class PagerAdapter extends FragmentPagerAdapter implements OnPageChangeListener
    {
        ArrayList<Fragment> fragments;
        
        public PagerAdapter() {
            super(StartActivity.this.getSupportFragmentManager());
            this.fragments = new ArrayList<Fragment>();
        }
        
        @Override
        public int getCount() {
            return this.fragments.size();
        }
        
        @Override
        public Fragment getItem(final int n) {
            return this.fragments.get(n);
        }
        
        @Override
        public void onPageScrollStateChanged(final int n) {
        }
        
        @Override
        public void onPageScrolled(final int n, final float n2, final int n3) {
        }
        
        @Override
        public void onPageSelected(final int n) {
        }
        
        @Override
        public void setPrimaryItem(final ViewGroup viewGroup, final int n, final Object o) {
            super.setPrimaryItem(viewGroup, n, o);
            for (int i = 0; i < StartActivity.this.dots.length; ++i) {
                StartActivity.this.findViewById(StartActivity.this.dots[i]).setEnabled(n == i);
            }
        }
    }
    
    public static class PtpFragment extends SimpleFragment
    {
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            onCreateView.findViewById(2131558501).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    final Intent intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.UsbSettings");
                    PtpFragment.this.getActivity().startService(new Intent((Context)PtpFragment.this.getActivity(), (Class)PtpMonitorService.class));
                    PtpFragment.this.startActivity(intent);
                }
            });
            return onCreateView;
        }
    }
    
    public static class SimpleFragment extends Fragment
    {
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            return layoutInflater.inflate(this.getArguments().getInt("layout"), viewGroup, false);
        }
    }
    
    public static class WelcomeFragment extends SimpleFragment
    {
        @Nullable
        @Override
        public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
            final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            onCreateView.findViewById(2131558528).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    ((StartActivity)WelcomeFragment.this.getActivity()).gotoPage();
                }
            });
            return onCreateView;
        }
    }
}
