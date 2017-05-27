// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.boilerplate;

import android.view.View$OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.View$OnClickListener;
import android.view.View;

public final class AnimatedView
{
    public static void setOnClickListener(final View view, final View$OnClickListener view$OnClickListener) {
        view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final ScaleAnimation scaleAnimation = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(250L);
                view.startAnimation((Animation)scaleAnimation);
                view$OnClickListener.onClick(view);
            }
        });
    }
    
    public static void setOnLongClickListener(final View view, final View$OnLongClickListener view$OnLongClickListener) {
        view.setOnLongClickListener((View$OnLongClickListener)new View$OnLongClickListener() {
            public boolean onLongClick(final View view) {
                final ScaleAnimation scaleAnimation = new ScaleAnimation(0.95f, 1.0f, 0.95f, 1.0f, 1, 0.5f, 1, 0.5f);
                scaleAnimation.setDuration(250L);
                view.startAnimation((Animation)scaleAnimation);
                return view$OnLongClickListener.onLongClick(view);
            }
        });
    }
}
