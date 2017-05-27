// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate.simplelist;

import android.widget.CompoundButton$OnCheckedChangeListener;
import com.koushikdutta.boilerplate.R;
import android.widget.CompoundButton;
import android.view.View;
import android.content.res.Resources;

public class SimpleListCheckboxItem extends SimpleListOneLineItem
{
    boolean checked;
    boolean showCheck;
    
    public SimpleListCheckboxItem(final Resources resources) {
        super(resources);
        this.showCheck = true;
    }
    
    public SimpleListCheckboxItem(final SimpleListFragment simpleListFragment) {
        this(simpleListFragment.getAdapter());
    }
    
    public SimpleListCheckboxItem(final SimpleListFragmentAdapter simpleListFragmentAdapter) {
        super(simpleListFragmentAdapter);
        this.showCheck = true;
    }
    
    @Override
    protected void bindView(final View view) {
        super.bindView(view);
        final CompoundButton compoundButton = (CompoundButton)view.findViewById(R.id.checkbox);
        compoundButton.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)null);
        compoundButton.setChecked(this.checked);
        int visibility;
        if (this.showCheck) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        compoundButton.setVisibility(visibility);
        compoundButton.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean checked) {
                SimpleListCheckboxItem.this.checked = checked;
                SimpleListCheckboxItem.this.onClick();
            }
        });
    }
    
    public SimpleListCheckboxItem checked(final boolean checked) {
        this.checked = checked;
        this.notifyDataSetChanged();
        return this;
    }
    
    public boolean checked() {
        return this.checked;
    }
    
    @Override
    int getViewType() {
        return R.layout.simple_list_fragment_one_line_checkbox_item;
    }
    
    @Override
    protected void onClick() {
        this.checked(!this.checked());
        super.onClick();
    }
    
    public SimpleListCheckboxItem showCheck(final boolean showCheck) {
        this.showCheck = showCheck;
        this.notifyDataSetChanged();
        return this;
    }
    
    public boolean showCheck() {
        return this.showCheck;
    }
}
