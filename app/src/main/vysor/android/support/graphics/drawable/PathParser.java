// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.util.Log;
import android.graphics.Path;
import java.util.ArrayList;

class PathParser
{
    private static final String LOGTAG = "PathParser";
    
    private static void addNode(final ArrayList<PathDataNode> list, final char c, final float[] array) {
        list.add(new PathDataNode(c, array));
    }
    
    public static boolean canMorph(final PathDataNode[] array, final PathDataNode[] array2) {
        boolean b = false;
        if (array != null) {
            b = false;
            if (array2 != null) {
                final int length = array.length;
                final int length2 = array2.length;
                b = false;
                if (length == length2) {
                    for (int i = 0; i < array.length; ++i) {
                        final char type = array[i].type;
                        final char type2 = array2[i].type;
                        b = false;
                        if (type != type2) {
                            return b;
                        }
                        final int length3 = array[i].params.length;
                        final int length4 = array2[i].params.length;
                        b = false;
                        if (length3 != length4) {
                            return b;
                        }
                    }
                    b = true;
                }
            }
        }
        return b;
    }
    
    static float[] copyOfRange(final float[] array, final int n, final int n2) {
        if (n > n2) {
            throw new IllegalArgumentException();
        }
        final int length = array.length;
        if (n < 0 || n > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int n3 = n2 - n;
        final int min = Math.min(n3, length - n);
        final float[] array2 = new float[n3];
        System.arraycopy(array, n, array2, 0, min);
        return array2;
    }
    
    public static PathDataNode[] createNodesFromPathData(final String s) {
        PathDataNode[] array;
        if (s == null) {
            array = null;
        }
        else {
            int n = 0;
            int i = 1;
            final ArrayList<PathDataNode> list = new ArrayList<PathDataNode>();
            while (i < s.length()) {
                final int nextStart = nextStart(s, i);
                final String trim = s.substring(n, nextStart).trim();
                if (trim.length() > 0) {
                    addNode(list, trim.charAt(0), getFloats(trim));
                }
                n = nextStart;
                i = nextStart + 1;
            }
            if (i - n == 1 && n < s.length()) {
                addNode(list, s.charAt(n), new float[0]);
            }
            array = list.toArray(new PathDataNode[list.size()]);
        }
        return array;
    }
    
    public static Path createPathFromPathData(final String s) {
        Path path = new Path();
        final PathDataNode[] nodesFromPathData = createNodesFromPathData(s);
        if (nodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(nodesFromPathData, path);
            return path;
        }
        catch (RuntimeException ex) {
            throw new RuntimeException("Error in parsing " + s, ex);
        }
        path = null;
        return path;
    }
    
    public static PathDataNode[] deepCopyNodes(final PathDataNode[] array) {
        PathDataNode[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            array2 = new PathDataNode[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = new PathDataNode(array[i]);
            }
        }
        return array2;
    }
    
    private static void extract(final String s, final int n, final ExtractFloatResult extractFloatResult) {
        int i = n;
        int n2 = 0;
        extractFloatResult.mEndWithNegOrDot = false;
        int n3 = 0;
        int n4 = 0;
        while (i < s.length()) {
            final int n5 = n4;
            final char char1 = s.charAt(i);
            n4 = 0;
            switch (char1) {
                case 32:
                case 44: {
                    n2 = 1;
                    n4 = 0;
                    break;
                }
                case 45: {
                    n4 = 0;
                    if (i == n) {
                        break;
                    }
                    n4 = 0;
                    if (n5 == 0) {
                        n2 = 1;
                        extractFloatResult.mEndWithNegOrDot = true;
                        n4 = 0;
                        break;
                    }
                    break;
                }
                case 46: {
                    if (n3 == 0) {
                        n3 = 1;
                        n4 = 0;
                        break;
                    }
                    n2 = 1;
                    extractFloatResult.mEndWithNegOrDot = true;
                    n4 = 0;
                    break;
                }
                case 69:
                case 101: {
                    n4 = 1;
                    break;
                }
            }
            if (n2 != 0) {
                break;
            }
            ++i;
        }
        extractFloatResult.mEndPosition = i;
    }
    
    private static float[] getFloats(final String s) {
        int n = 1;
        int n2;
        if (s.charAt(0) == 'z') {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        if (s.charAt(0) != 'Z') {
            n = 0;
        }
        float[] copyOfRange;
        if ((n2 | n) != 0x0) {
            copyOfRange = new float[0];
        }
        else {
            while (true) {
            Label_0126_Outer:
                while (true) {
                    int mEndPosition = 0;
                    int n4 = 0;
                Label_0204:
                    while (true) {
                        int n3 = 0;
                        Label_0197: {
                            try {
                                final float[] array = new float[s.length()];
                                int i = 1;
                                final ExtractFloatResult extractFloatResult = new ExtractFloatResult();
                                final int length = s.length();
                                n3 = 0;
                                while (i < length) {
                                    extract(s, i, extractFloatResult);
                                    mEndPosition = extractFloatResult.mEndPosition;
                                    if (i >= mEndPosition) {
                                        break Label_0197;
                                    }
                                    n4 = n3 + 1;
                                    array[n3] = Float.parseFloat(s.substring(i, mEndPosition));
                                    if (!extractFloatResult.mEndWithNegOrDot) {
                                        break Label_0204;
                                    }
                                    i = mEndPosition;
                                    n3 = n4;
                                }
                                copyOfRange = copyOfRange(array, 0, n3);
                                break;
                            }
                            catch (NumberFormatException ex) {
                                throw new RuntimeException("error in parsing \"" + s + "\"", ex);
                            }
                        }
                        n4 = n3;
                        continue;
                    }
                    int i = mEndPosition + 1;
                    int n3 = n4;
                    continue Label_0126_Outer;
                }
            }
        }
        return copyOfRange;
    }
    
    private static int nextStart(final String s, int i) {
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            if (((char1 - 'A') * (char1 - 'Z') <= '\0' || (char1 - 'a') * (char1 - 'z') <= '\0') && char1 != 'e' && char1 != 'E') {
                break;
            }
            ++i;
        }
        return i;
    }
    
    public static void updateNodes(final PathDataNode[] array, final PathDataNode[] array2) {
        for (int i = 0; i < array2.length; ++i) {
            array[i].type = array2[i].type;
            for (int j = 0; j < array2[i].params.length; ++j) {
                array[i].params[j] = array2[i].params[j];
            }
        }
    }
    
    private static class ExtractFloatResult
    {
        int mEndPosition;
        boolean mEndWithNegOrDot;
    }
    
    public static class PathDataNode
    {
        float[] params;
        char type;
        
        PathDataNode(final char type, final float[] params) {
            this.type = type;
            this.params = params;
        }
        
        PathDataNode(final PathDataNode pathDataNode) {
            this.type = pathDataNode.type;
            this.params = PathParser.copyOfRange(pathDataNode.params, 0, pathDataNode.params.length);
        }
        
        private static void addCommand(final Path path, final float[] array, char c, final char c2, final float[] array2) {
            int n = 2;
            float n2 = array[0];
            float n3 = array[1];
            float n4 = array[2];
            float n5 = array[3];
            float n6 = array[4];
            float n7 = array[5];
            switch (c2) {
                case 'Z':
                case 'z': {
                    path.close();
                    n2 = n6;
                    n3 = n7;
                    n4 = n6;
                    n5 = n7;
                    path.moveTo(n2, n3);
                    break;
                }
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't': {
                    n = 2;
                    break;
                }
                case 'H':
                case 'V':
                case 'h':
                case 'v': {
                    n = 1;
                    break;
                }
                case 'C':
                case 'c': {
                    n = 6;
                    break;
                }
                case 'Q':
                case 'S':
                case 'q':
                case 's': {
                    n = 4;
                    break;
                }
                case 'A':
                case 'a': {
                    n = 7;
                    break;
                }
            }
            for (int i = 0; i < array2.length; i += n) {
                switch (c2) {
                    case 'm': {
                        n2 += array2[i + 0];
                        n3 += array2[i + 1];
                        if (i > 0) {
                            path.rLineTo(array2[i + 0], array2[i + 1]);
                            break;
                        }
                        path.rMoveTo(array2[i + 0], array2[i + 1]);
                        n6 = n2;
                        n7 = n3;
                        break;
                    }
                    case 'M': {
                        n2 = array2[i + 0];
                        n3 = array2[i + 1];
                        if (i > 0) {
                            path.lineTo(array2[i + 0], array2[i + 1]);
                            break;
                        }
                        path.moveTo(array2[i + 0], array2[i + 1]);
                        n6 = n2;
                        n7 = n3;
                        break;
                    }
                    case 'l': {
                        path.rLineTo(array2[i + 0], array2[i + 1]);
                        n2 += array2[i + 0];
                        n3 += array2[i + 1];
                        break;
                    }
                    case 'L': {
                        path.lineTo(array2[i + 0], array2[i + 1]);
                        n2 = array2[i + 0];
                        n3 = array2[i + 1];
                        break;
                    }
                    case 'h': {
                        path.rLineTo(array2[i + 0], 0.0f);
                        n2 += array2[i + 0];
                        break;
                    }
                    case 'H': {
                        path.lineTo(array2[i + 0], n3);
                        n2 = array2[i + 0];
                        break;
                    }
                    case 'v': {
                        path.rLineTo(0.0f, array2[i + 0]);
                        n3 += array2[i + 0];
                        break;
                    }
                    case 'V': {
                        path.lineTo(n2, array2[i + 0]);
                        n3 = array2[i + 0];
                        break;
                    }
                    case 'c': {
                        path.rCubicTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3], array2[i + 4], array2[i + 5]);
                        n4 = n2 + array2[i + 2];
                        n5 = n3 + array2[i + 3];
                        n2 += array2[i + 4];
                        n3 += array2[i + 5];
                        break;
                    }
                    case 'C': {
                        path.cubicTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3], array2[i + 4], array2[i + 5]);
                        n2 = array2[i + 4];
                        n3 = array2[i + 5];
                        n4 = array2[i + 2];
                        n5 = array2[i + 3];
                        break;
                    }
                    case 's': {
                        float n8 = 0.0f;
                        float n9 = 0.0f;
                        Label_1025: {
                            if (c != 'c' && c != 's' && c != 'C') {
                                final char c3 = c;
                                n8 = 0.0f;
                                n9 = 0.0f;
                                if (c3 != 'S') {
                                    break Label_1025;
                                }
                            }
                            n8 = n2 - n4;
                            n9 = n3 - n5;
                        }
                        path.rCubicTo(n8, n9, array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                        n4 = n2 + array2[i + 0];
                        n5 = n3 + array2[i + 1];
                        n2 += array2[i + 2];
                        n3 += array2[i + 3];
                        break;
                    }
                    case 'S': {
                        float n10 = n2;
                        float n11 = n3;
                        if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                            n10 = 2.0f * n2 - n4;
                            n11 = 2.0f * n3 - n5;
                        }
                        path.cubicTo(n10, n11, array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                        n4 = array2[i + 0];
                        n5 = array2[i + 1];
                        n2 = array2[i + 2];
                        n3 = array2[i + 3];
                        break;
                    }
                    case 'q': {
                        path.rQuadTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                        n4 = n2 + array2[i + 0];
                        n5 = n3 + array2[i + 1];
                        n2 += array2[i + 2];
                        n3 += array2[i + 3];
                        break;
                    }
                    case 'Q': {
                        path.quadTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                        n4 = array2[i + 0];
                        n5 = array2[i + 1];
                        n2 = array2[i + 2];
                        n3 = array2[i + 3];
                        break;
                    }
                    case 't': {
                        float n12 = 0.0f;
                        float n13 = 0.0f;
                        Label_1439: {
                            if (c != 'q' && c != 't' && c != 'Q') {
                                final char c4 = c;
                                n12 = 0.0f;
                                n13 = 0.0f;
                                if (c4 != 'T') {
                                    break Label_1439;
                                }
                            }
                            n12 = n2 - n4;
                            n13 = n3 - n5;
                        }
                        path.rQuadTo(n12, n13, array2[i + 0], array2[i + 1]);
                        n4 = n2 + n12;
                        n5 = n3 + n13;
                        n2 += array2[i + 0];
                        n3 += array2[i + 1];
                        break;
                    }
                    case 'T': {
                        float n14 = n2;
                        float n15 = n3;
                        if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                            n14 = 2.0f * n2 - n4;
                            n15 = 2.0f * n3 - n5;
                        }
                        path.quadTo(n14, n15, array2[i + 0], array2[i + 1]);
                        n4 = n14;
                        n5 = n15;
                        n2 = array2[i + 0];
                        n3 = array2[i + 1];
                        break;
                    }
                    case 'a': {
                        drawArc(path, n2, n3, n2 + array2[i + 5], n3 + array2[i + 6], array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3] != 0.0f, array2[i + 4] != 0.0f);
                        n2 += array2[i + 5];
                        n3 += array2[i + 6];
                        n4 = n2;
                        n5 = n3;
                        break;
                    }
                    case 'A': {
                        drawArc(path, n2, n3, array2[i + 5], array2[i + 6], array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3] != 0.0f, array2[i + 4] != 0.0f);
                        n2 = array2[i + 5];
                        n3 = array2[i + 6];
                        n4 = n2;
                        n5 = n3;
                        break;
                    }
                }
                c = c2;
            }
            array[0] = n2;
            array[1] = n3;
            array[2] = n4;
            array[3] = n5;
            array[4] = n6;
            array[5] = n7;
        }
        
        private static void arcToBezier(final Path path, final double n, final double n2, final double n3, final double n4, double n5, double n6, final double n7, final double n8, final double n9) {
            final int n10 = (int)Math.ceil(Math.abs(4.0 * n9 / 3.141592653589793));
            double n11 = n8;
            final double cos = Math.cos(n7);
            final double sin = Math.sin(n7);
            final double cos2 = Math.cos(n11);
            final double sin2 = Math.sin(n11);
            double n12 = sin2 * (cos * -n3) - cos2 * (n4 * sin);
            double n13 = sin2 * (sin * -n3) + cos2 * (n4 * cos);
            final double n14 = n9 / n10;
            for (int i = 0; i < n10; ++i) {
                final double n15 = n11 + n14;
                final double sin3 = Math.sin(n15);
                final double cos3 = Math.cos(n15);
                final double n16 = n + cos3 * (n3 * cos) - sin3 * (n4 * sin);
                final double n17 = n2 + cos3 * (n3 * sin) + sin3 * (n4 * cos);
                final double n18 = sin3 * (cos * -n3) - cos3 * (n4 * sin);
                final double n19 = sin3 * (sin * -n3) + cos3 * (n4 * cos);
                final double tan = Math.tan((n15 - n11) / 2.0);
                final double n20 = Math.sin(n15 - n11) * (Math.sqrt(4.0 + tan * (3.0 * tan)) - 1.0) / 3.0;
                path.cubicTo((float)(n5 + n20 * n12), (float)(n6 + n20 * n13), (float)(n16 - n20 * n18), (float)(n17 - n20 * n19), (float)n16, (float)n17);
                n11 = n15;
                n5 = n16;
                n6 = n17;
                n12 = n18;
                n13 = n19;
            }
        }
        
        private static void drawArc(final Path path, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final boolean b, final boolean b2) {
            final double radians = Math.toRadians(n7);
            final double cos = Math.cos(radians);
            final double sin = Math.sin(radians);
            final double n8 = (cos * n + sin * n2) / n5;
            final double n9 = (sin * -n + cos * n2) / n6;
            final double n10 = (cos * n3 + sin * n4) / n5;
            final double n11 = (sin * -n3 + cos * n4) / n6;
            final double n12 = n8 - n10;
            final double n13 = n9 - n11;
            final double n14 = (n8 + n10) / 2.0;
            final double n15 = (n9 + n11) / 2.0;
            final double n16 = n12 * n12 + n13 * n13;
            if (n16 == 0.0) {
                Log.w("PathParser", " Points are coincident");
            }
            else {
                final double n17 = 1.0 / n16 - 0.25;
                if (n17 < 0.0) {
                    Log.w("PathParser", "Points are too far apart " + n16);
                    final float n18 = (float)(Math.sqrt(n16) / 1.99999);
                    drawArc(path, n, n2, n3, n4, n5 * n18, n6 * n18, n7, b, b2);
                }
                else {
                    final double sqrt = Math.sqrt(n17);
                    final double n19 = sqrt * n12;
                    final double n20 = sqrt * n13;
                    double n21;
                    double n22;
                    if (b == b2) {
                        n21 = n14 - n20;
                        n22 = n15 + n19;
                    }
                    else {
                        n21 = n14 + n20;
                        n22 = n15 - n19;
                    }
                    final double atan2 = Math.atan2(n9 - n22, n8 - n21);
                    double n23 = Math.atan2(n11 - n22, n10 - n21) - atan2;
                    if (b2 != n23 >= 0.0) {
                        if (n23 > 0.0) {
                            n23 -= 6.283185307179586;
                        }
                        else {
                            n23 += 6.283185307179586;
                        }
                    }
                    final double n24 = n21 * n5;
                    final double n25 = n22 * n6;
                    arcToBezier(path, n24 * cos - n25 * sin, n24 * sin + n25 * cos, n5, n6, n, n2, radians, atan2, n23);
                }
            }
        }
        
        public static void nodesToPath(final PathDataNode[] array, final Path path) {
            final float[] array2 = new float[6];
            char type = 'm';
            for (int i = 0; i < array.length; ++i) {
                addCommand(path, array2, type, array[i].type, array[i].params);
                type = array[i].type;
            }
        }
        
        public void interpolatePathDataNode(final PathDataNode pathDataNode, final PathDataNode pathDataNode2, final float n) {
            for (int i = 0; i < pathDataNode.params.length; ++i) {
                this.params[i] = pathDataNode.params[i] * (1.0f - n) + n * pathDataNode2.params[i];
            }
        }
    }
}
