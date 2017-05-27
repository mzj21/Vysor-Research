// 
// Decompiled by Procyon v0.5.30
// 

package com.koushikdutta.vysor.renderscript;

import android.renderscript.Type;
import android.renderscript.RSRuntimeException;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.Short4;
import android.renderscript.Matrix4f;
import android.renderscript.FieldPacker;
import android.renderscript.Element;

public class ScriptC_uchar4ToX extends ScriptC_Base
{
    private static final int mExportForEachIdx_root = 0;
    private static final int mExportVarIdx_gTransformMatrix = 0;
    private static final int mExportVarIdx_gTranslate = 1;
    private Element __U8_4;
    private FieldPacker __rs_fp_U8_4;
    private Element __to;
    private Matrix4f mExportVar_gTransformMatrix;
    private Short4 mExportVar_gTranslate;
    
    public ScriptC_uchar4ToX(final RenderScript renderScript, final String s, final Element _to, final byte[] array, final String s2) {
        super(renderScript, s, array, s2);
        this.__to = _to;
        this.__U8_4 = Element.U8_4(renderScript);
    }
    
    public void forEach_root(final Allocation allocation, final Allocation allocation2) {
        if (!allocation.getType().getElement().isCompatible(this.__U8_4)) {
            throw new RSRuntimeException("Type mismatch with U8_4!");
        }
        if (!allocation2.getType().getElement().isCompatible(this.__to)) {
            throw new RSRuntimeException("Type mismatch with target!");
        }
        final Type type = allocation.getType();
        final Type type2 = allocation2.getType();
        if (type.getCount() != type2.getCount() || type.getX() != type2.getX() || type.getY() != type2.getY() || type.getZ() != type2.getZ() || type.hasFaces() != type2.hasFaces() || type.hasMipmaps() != type2.hasMipmaps()) {
            throw new RSRuntimeException("Dimension mismatch between parameters ain and aout!");
        }
        this.forEach(0, allocation, allocation2, (FieldPacker)null);
    }
    
    public Element getTo() {
        return this.__to;
    }
    
    public Matrix4f get_gTransformMatrix() {
        return this.mExportVar_gTransformMatrix;
    }
    
    public Short4 get_gTranslate() {
        return this.mExportVar_gTranslate;
    }
    
    public void set_gTransformMatrix(final Matrix4f mExportVar_gTransformMatrix) {
        synchronized (this) {
            this.mExportVar_gTransformMatrix = mExportVar_gTransformMatrix;
            final FieldPacker fieldPacker = new FieldPacker(64);
            fieldPacker.addMatrix(mExportVar_gTransformMatrix);
            this.setVar(0, fieldPacker);
        }
    }
    
    public void set_gTranslate(final Short4 mExportVar_gTranslate) {
        synchronized (this) {
            this.mExportVar_gTranslate = mExportVar_gTranslate;
            final FieldPacker fieldPacker = new FieldPacker(4);
            fieldPacker.addU8(mExportVar_gTranslate);
            this.setVar(1, fieldPacker, this.__U8_4, new int[] { 1 });
        }
    }
}
