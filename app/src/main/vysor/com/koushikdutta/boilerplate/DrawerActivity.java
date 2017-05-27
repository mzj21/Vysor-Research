// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.KeyEvent;
import android.os.Bundle;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.app.Activity;
import android.animation.ArgbEvaluator;
import android.support.v7.app.ActionBarDrawerToggle;

public abstract class DrawerActivity extends WindowChromeCompatActivity
{
    ActionBarDrawerToggle drawerToggle;
    
    private void resetDrawerToggle() {
        this.getDrawer().setDrawerListener((DrawerLayout.DrawerListener)(this.drawerToggle = new ActionBarDrawerToggle(this, this.getDrawer(), this.getDrawerOpenString(), this.getDrawerCloseString()) {
            ArgbEvaluator evaluator = new ArgbEvaluator();
            boolean hasOpened;
            int originalStatusBarColor;
            
            @Override
            public void onDrawerClosed(final View view) {
                super.onDrawerClosed(view);
                if (this.hasOpened) {
                    WindowChromeUtils.setStatusBarColor(DrawerActivity.this.getWindow(), this.originalStatusBarColor);
                }
                if (!DrawerActivity.this.isFinishing()) {
                    DrawerActivity.this.onDrawerClosed();
                }
            }
            
            @Override
            public void onDrawerSlide(final View view, final float n) {
                super.onDrawerSlide(view, n);
                if (DrawerActivity.this.getDrawer().isDrawerVisible(3)) {
                    DrawerActivity.this.onDrawerOpen();
                    if (this.hasOpened) {
                        WindowChromeUtils.setStatusBarColor(DrawerActivity.this.getWindow(), (int)this.evaluator.evaluate(n, (Object)this.originalStatusBarColor, (Object)1291845632));
                    }
                }
            }
            
            @Override
            public void onDrawerStateChanged(final int n) {
                super.onDrawerStateChanged(n);
                if (n == 1 && !this.hasOpened) {
                    this.hasOpened = true;
                    this.originalStatusBarColor = WindowChromeUtils.getStatusBarColor(DrawerActivity.this.getWindow());
                }
            }
        }));
        this.drawerToggle.syncState();
    }
    
    public FragmentTransaction beginContentFragmentTransaction(final Fragment fragment, final String s) {
        final FragmentTransaction beginTransaction = this.getSupportFragmentManager().beginTransaction();
        if (s != null && this.getSupportFragmentManager().findFragmentById(R.id.main_content) != null) {
            beginTransaction.addToBackStack(s);
        }
        beginTransaction.replace(R.id.main_content, fragment);
        return beginTransaction;
    }
    
    protected abstract Fragment createDrawerFragment();
    
    public Fragment getCurrentContentFragment() {
        Fragment fragmentById = this.getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (fragmentById == null || !fragmentById.isAdded()) {
            fragmentById = null;
        }
        return fragmentById;
    }
    
    public DrawerLayout getDrawer() {
        return (DrawerLayout)this.findViewById(R.id.drawer_layout);
    }
    
    public abstract int getDrawerCloseString();
    
    public Fragment getDrawerFragment() {
        return this.getSupportFragmentManager().findFragmentByTag("drawer");
    }
    
    public abstract int getDrawerOpenString();
    
    public ActionBarDrawerToggle getDrawerToggle() {
        return this.drawerToggle;
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.drawerToggle.onConfigurationChanged(configuration);
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.drawer_activity);
        if (bundle == null) {
            this.getSupportFragmentManager().beginTransaction().replace(R.id.drawer_content, this.createDrawerFragment(), "drawer").commitAllowingStateLoss();
        }
        this.resetDrawerToggle();
    }
    
    protected void onDrawerClosed() {
    }
    
    protected void onDrawerOpen() {
    }
    
    protected void onDrawerReady() {
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        boolean onKeyDown;
        if (this.getDrawer().isDrawerOpen(3)) {
            this.getDrawer().closeDrawers();
            onKeyDown = true;
        }
        else {
            onKeyDown = super.onKeyDown(n, keyEvent);
        }
        return onKeyDown;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        boolean onOptionsItemSelected = true;
        if (!this.drawerToggle.onOptionsItemSelected(menuItem)) {
            if (menuItem.getItemId() == menuItem.getItemId()) {
                this.getSupportFragmentManager().popBackStackImmediate();
            }
            else {
                onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            }
        }
        return onOptionsItemSelected;
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        this.drawerToggle.syncState();
        if (bundle == null) {
            this.onDrawerReady();
        }
    }
    
    @Override
    public void setSupportActionBar(@Nullable final Toolbar supportActionBar) {
        super.setSupportActionBar(supportActionBar);
        this.resetDrawerToggle();
    }
}
