// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor;

import android.view.View;
import android.view.View$OnClickListener;
import java.io.File;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TipsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968629);
        if (!new File("/system/bin/screenrecord").exists()) {
            this.findViewById(2131558524).setVisibility(0);
        }
        this.findViewById(2131558526).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                TipsActivity.this.finish();
            }
        });
        this.findViewById(2131558525).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                while (true) {
                    try {
                        TipsActivity.this.getPackageManager().setComponentEnabledSetting(TipsActivity.this.getComponentName(), 2, 1);
                        TipsActivity.this.finish();
                        TipsActivity.this.finish();
                    }
                    catch (Exception ex) {
                        continue;
                    }
                    break;
                }
            }
        });
    }
}
