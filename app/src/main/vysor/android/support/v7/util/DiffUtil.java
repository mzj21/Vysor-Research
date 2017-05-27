// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.util;

import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import java.util.Iterator;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class DiffUtil
{
    private static final Comparator<Snake> SNAKE_COMPARATOR;
    
    static {
        SNAKE_COMPARATOR = new Comparator<Snake>() {
            @Override
            public int compare(final Snake snake, final Snake snake2) {
                int n = snake.x - snake2.x;
                if (n == 0) {
                    n = snake.y - snake2.y;
                }
                return n;
            }
        };
    }
    
    public static DiffResult calculateDiff(final Callback callback) {
        return calculateDiff(callback, true);
    }
    
    public static DiffResult calculateDiff(final Callback callback, final boolean b) {
        final int oldListSize = callback.getOldListSize();
        final int newListSize = callback.getNewListSize();
        final ArrayList<Object> list = new ArrayList<Object>();
        final ArrayList<Range> list2 = new ArrayList<Range>();
        list2.add(new Range(0, oldListSize, 0, newListSize));
        final int n = oldListSize + newListSize + Math.abs(oldListSize - newListSize);
        final int[] array = new int[n * 2];
        final int[] array2 = new int[n * 2];
        final ArrayList<Range> list3 = (ArrayList<Range>)new ArrayList<Object>();
        while (!list2.isEmpty()) {
            final Range range = list2.remove(-1 + list2.size());
            final Snake diffPartial = diffPartial(callback, range.oldListStart, range.oldListEnd, range.newListStart, range.newListEnd, array, array2, n);
            if (diffPartial != null) {
                if (diffPartial.size > 0) {
                    list.add(diffPartial);
                }
                diffPartial.x += range.oldListStart;
                diffPartial.y += range.newListStart;
                Range range2;
                if (list3.isEmpty()) {
                    range2 = new Range();
                }
                else {
                    range2 = list3.remove(-1 + list3.size());
                }
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                if (diffPartial.reverse) {
                    range2.oldListEnd = diffPartial.x;
                    range2.newListEnd = diffPartial.y;
                }
                else if (diffPartial.removal) {
                    range2.oldListEnd = -1 + diffPartial.x;
                    range2.newListEnd = diffPartial.y;
                }
                else {
                    range2.oldListEnd = diffPartial.x;
                    range2.newListEnd = -1 + diffPartial.y;
                }
                list2.add(range2);
                if (diffPartial.reverse) {
                    if (diffPartial.removal) {
                        range.oldListStart = 1 + (diffPartial.x + diffPartial.size);
                        range.newListStart = diffPartial.y + diffPartial.size;
                    }
                    else {
                        range.oldListStart = diffPartial.x + diffPartial.size;
                        range.newListStart = 1 + (diffPartial.y + diffPartial.size);
                    }
                }
                else {
                    range.oldListStart = diffPartial.x + diffPartial.size;
                    range.newListStart = diffPartial.y + diffPartial.size;
                }
                list2.add(range);
            }
            else {
                list3.add(range);
            }
        }
        Collections.sort(list, (Comparator<? super Object>)DiffUtil.SNAKE_COMPARATOR);
        return new DiffResult(callback, (List<Snake>)list, array, array2, b);
    }
    
    private static Snake diffPartial(final Callback callback, final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2, final int n5) {
        final int n6 = n2 - n;
        final int n7 = n4 - n3;
        if (n2 - n >= 1 && n4 - n3 >= 1) {
            final int n8 = n6 - n7;
            final int n9 = (1 + (n6 + n7)) / 2;
            Arrays.fill(array, -1 + (n5 - n9), 1 + (n5 + n9), 0);
            Arrays.fill(array2, n8 + (-1 + (n5 - n9)), n8 + (1 + (n5 + n9)), n6);
            final boolean b = n8 % 2 != 0;
            for (int i = 0; i <= n9; ++i) {
                for (int j = -i; j <= i; j += 2) {
                    int n10;
                    boolean removal;
                    if (j == -i || (j != i && array[-1 + (n5 + j)] < array[1 + (n5 + j)])) {
                        n10 = array[1 + (n5 + j)];
                        removal = false;
                    }
                    else {
                        n10 = 1 + array[-1 + (n5 + j)];
                        removal = true;
                    }
                    for (int n11 = n10 - j; n10 < n6 && n11 < n7 && callback.areItemsTheSame(n + n10, n3 + n11); ++n10, ++n11) {}
                    array[n5 + j] = n10;
                    if (b && j >= 1 + (n8 - i) && j <= -1 + (n8 + i) && array[n5 + j] >= array2[n5 + j]) {
                        final Snake snake = new Snake();
                        snake.x = array2[n5 + j];
                        snake.y = snake.x - j;
                        snake.size = array[n5 + j] - array2[n5 + j];
                        snake.removal = removal;
                        snake.reverse = false;
                        return snake;
                    }
                }
                for (int k = -i; k <= i; k += 2) {
                    final int n12 = k + n8;
                    int n13;
                    boolean removal2;
                    if (n12 == i + n8 || (n12 != n8 + -i && array2[-1 + (n5 + n12)] < array2[1 + (n5 + n12)])) {
                        n13 = array2[-1 + (n5 + n12)];
                        removal2 = false;
                    }
                    else {
                        n13 = -1 + array2[1 + (n5 + n12)];
                        removal2 = true;
                    }
                    for (int n14 = n13 - n12; n13 > 0 && n14 > 0 && callback.areItemsTheSame(-1 + (n + n13), -1 + (n3 + n14)); --n13, --n14) {}
                    array2[n5 + n12] = n13;
                    if (!b && k + n8 >= -i && k + n8 <= i && array[n5 + n12] >= array2[n5 + n12]) {
                        final Snake snake = new Snake();
                        snake.x = array2[n5 + n12];
                        snake.y = snake.x - n12;
                        snake.size = array[n5 + n12] - array2[n5 + n12];
                        snake.removal = removal2;
                        snake.reverse = true;
                        return snake;
                    }
                }
            }
            throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
        }
        return null;
    }
    
    public abstract static class Callback
    {
        public abstract boolean areContentsTheSame(final int p0, final int p1);
        
        public abstract boolean areItemsTheSame(final int p0, final int p1);
        
        @Nullable
        public Object getChangePayload(final int n, final int n2) {
            return null;
        }
        
        public abstract int getNewListSize();
        
        public abstract int getOldListSize();
    }
    
    public static class DiffResult
    {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List<Snake> mSnakes;
        
        DiffResult(final Callback mCallback, final List<Snake> mSnakes, final int[] mOldItemStatuses, final int[] mNewItemStatuses, final boolean mDetectMoves) {
            this.mSnakes = mSnakes;
            this.mOldItemStatuses = mOldItemStatuses;
            this.mNewItemStatuses = mNewItemStatuses;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = mCallback;
            this.mOldListSize = mCallback.getOldListSize();
            this.mNewListSize = mCallback.getNewListSize();
            this.mDetectMoves = mDetectMoves;
            this.addRootSnake();
            this.findMatchingItems();
        }
        
        private void addRootSnake() {
            Snake snake;
            if (this.mSnakes.isEmpty()) {
                snake = null;
            }
            else {
                snake = this.mSnakes.get(0);
            }
            if (snake == null || snake.x != 0 || snake.y != 0) {
                final Snake snake2 = new Snake();
                snake2.x = 0;
                snake2.y = 0;
                snake2.removal = false;
                snake2.size = 0;
                snake2.reverse = false;
                this.mSnakes.add(0, snake2);
            }
        }
        
        private void dispatchAdditions(final List<PostponedUpdate> list, final ListUpdateCallback listUpdateCallback, final int n, final int n2, final int n3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onInserted(n, n2);
            }
            else {
                for (int i = n2 - 1; i >= 0; --i) {
                    final int n4 = 0x1F & this.mNewItemStatuses[n3 + i];
                    switch (n4) {
                        default: {
                            throw new IllegalStateException("unknown flag for pos " + (n3 + i) + " " + Long.toBinaryString(n4));
                        }
                        case 0: {
                            listUpdateCallback.onInserted(n, 1);
                            for (final PostponedUpdate postponedUpdate : list) {
                                ++postponedUpdate.currentPos;
                            }
                            break;
                        }
                        case 4:
                        case 8: {
                            final int n5 = this.mNewItemStatuses[n3 + i] >> 5;
                            listUpdateCallback.onMoved(removePostponedUpdate(list, n5, true).currentPos, n);
                            if (n4 == 4) {
                                listUpdateCallback.onChanged(n, 1, this.mCallback.getChangePayload(n5, n3 + i));
                                break;
                            }
                            break;
                        }
                        case 16: {
                            list.add(new PostponedUpdate(n3 + i, n, false));
                            break;
                        }
                    }
                }
            }
        }
        
        private void dispatchRemovals(final List<PostponedUpdate> list, final ListUpdateCallback listUpdateCallback, final int n, final int n2, final int n3) {
            if (!this.mDetectMoves) {
                listUpdateCallback.onRemoved(n, n2);
            }
            else {
                for (int i = n2 - 1; i >= 0; --i) {
                    final int n4 = 0x1F & this.mOldItemStatuses[n3 + i];
                    switch (n4) {
                        default: {
                            throw new IllegalStateException("unknown flag for pos " + (n3 + i) + " " + Long.toBinaryString(n4));
                        }
                        case 0: {
                            listUpdateCallback.onRemoved(n + i, 1);
                            for (final PostponedUpdate postponedUpdate : list) {
                                --postponedUpdate.currentPos;
                            }
                            break;
                        }
                        case 4:
                        case 8: {
                            final int n5 = this.mOldItemStatuses[n3 + i] >> 5;
                            final PostponedUpdate removePostponedUpdate = removePostponedUpdate(list, n5, false);
                            listUpdateCallback.onMoved(n + i, -1 + removePostponedUpdate.currentPos);
                            if (n4 == 4) {
                                listUpdateCallback.onChanged(-1 + removePostponedUpdate.currentPos, 1, this.mCallback.getChangePayload(n3 + i, n5));
                                break;
                            }
                            break;
                        }
                        case 16: {
                            list.add(new PostponedUpdate(n3 + i, n + i, true));
                            break;
                        }
                    }
                }
            }
        }
        
        private void findAddition(final int n, final int n2, final int n3) {
            if (this.mOldItemStatuses[n - 1] == 0) {
                this.findMatchingItem(n, n2, n3, false);
            }
        }
        
        private boolean findMatchingItem(final int n, final int n2, final int n3, final boolean b) {
            int n4;
            int x;
            int y;
            if (b) {
                n4 = n2 - 1;
                x = n;
                y = n2 - 1;
            }
            else {
                n4 = n - 1;
                x = n - 1;
                y = n2;
            }
            for (int i = n3; i >= 0; --i) {
                final Snake snake = this.mSnakes.get(i);
                final int n5 = snake.x + snake.size;
                final int n6 = snake.y + snake.size;
                if (b) {
                    for (int j = x - 1; j >= n5; --j) {
                        if (this.mCallback.areItemsTheSame(j, n4)) {
                            int n7;
                            if (this.mCallback.areContentsTheSame(j, n4)) {
                                n7 = 8;
                            }
                            else {
                                n7 = 4;
                            }
                            this.mNewItemStatuses[n4] = (0x10 | j << 5);
                            this.mOldItemStatuses[j] = (n7 | n4 << 5);
                            return true;
                        }
                    }
                }
                else {
                    for (int k = y - 1; k >= n6; --k) {
                        if (this.mCallback.areItemsTheSame(n4, k)) {
                            int n8;
                            if (this.mCallback.areContentsTheSame(n4, k)) {
                                n8 = 8;
                            }
                            else {
                                n8 = 4;
                            }
                            this.mOldItemStatuses[n - 1] = (0x10 | k << 5);
                            this.mNewItemStatuses[k] = (n8 | n - 1 << 5);
                            return true;
                        }
                    }
                }
                x = snake.x;
                y = snake.y;
            }
            return false;
        }
        
        private void findMatchingItems() {
            int i = this.mOldListSize;
            int j = this.mNewListSize;
            for (int k = -1 + this.mSnakes.size(); k >= 0; --k) {
                final Snake snake = this.mSnakes.get(k);
                final int n = snake.x + snake.size;
                final int n2 = snake.y + snake.size;
                if (this.mDetectMoves) {
                    while (i > n) {
                        this.findAddition(i, j, k);
                        --i;
                    }
                    while (j > n2) {
                        this.findRemoval(i, j, k);
                        --j;
                    }
                }
                for (int l = 0; l < snake.size; ++l) {
                    final int n3 = l + snake.x;
                    final int n4 = l + snake.y;
                    int n5;
                    if (this.mCallback.areContentsTheSame(n3, n4)) {
                        n5 = 1;
                    }
                    else {
                        n5 = 2;
                    }
                    this.mOldItemStatuses[n3] = (n5 | n4 << 5);
                    this.mNewItemStatuses[n4] = (n5 | n3 << 5);
                }
                i = snake.x;
                j = snake.y;
            }
        }
        
        private void findRemoval(final int n, final int n2, final int n3) {
            if (this.mNewItemStatuses[n2 - 1] == 0) {
                this.findMatchingItem(n, n2, n3, true);
            }
        }
        
        private static PostponedUpdate removePostponedUpdate(final List<PostponedUpdate> list, final int n, final boolean b) {
            for (int i = -1 + list.size(); i >= 0; --i) {
                final PostponedUpdate postponedUpdate = list.get(i);
                if (postponedUpdate.posInOwnerList == n && postponedUpdate.removal == b) {
                    list.remove(i);
                    for (int j = i; j < list.size(); ++j) {
                        final PostponedUpdate postponedUpdate2 = list.get(j);
                        final int currentPos = postponedUpdate2.currentPos;
                        int n2;
                        if (b) {
                            n2 = 1;
                        }
                        else {
                            n2 = -1;
                        }
                        postponedUpdate2.currentPos = n2 + currentPos;
                    }
                    return postponedUpdate;
                }
            }
            return null;
        }
        
        public void dispatchUpdatesTo(final ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback)listUpdateCallback;
            }
            else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            final ArrayList<PostponedUpdate> list = new ArrayList<PostponedUpdate>();
            int n = this.mOldListSize;
            int n2 = this.mNewListSize;
            for (int i = -1 + this.mSnakes.size(); i >= 0; --i) {
                final Snake snake = this.mSnakes.get(i);
                final int size = snake.size;
                final int n3 = size + snake.x;
                final int n4 = size + snake.y;
                if (n3 < n) {
                    this.dispatchRemovals(list, batchingListUpdateCallback, n3, n - n3, n3);
                }
                if (n4 < n2) {
                    this.dispatchAdditions(list, batchingListUpdateCallback, n3, n2 - n4, n4);
                }
                for (int j = size - 1; j >= 0; --j) {
                    if ((0x1F & this.mOldItemStatuses[j + snake.x]) == 0x2) {
                        batchingListUpdateCallback.onChanged(j + snake.x, 1, this.mCallback.getChangePayload(j + snake.x, j + snake.y));
                    }
                }
                n = snake.x;
                n2 = snake.y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }
        
        public void dispatchUpdatesTo(final RecyclerView.Adapter adapter) {
            this.dispatchUpdatesTo(new ListUpdateCallback() {
                @Override
                public void onChanged(final int n, final int n2, final Object o) {
                    adapter.notifyItemRangeChanged(n, n2, o);
                }
                
                @Override
                public void onInserted(final int n, final int n2) {
                    adapter.notifyItemRangeInserted(n, n2);
                }
                
                @Override
                public void onMoved(final int n, final int n2) {
                    adapter.notifyItemMoved(n, n2);
                }
                
                @Override
                public void onRemoved(final int n, final int n2) {
                    adapter.notifyItemRangeRemoved(n, n2);
                }
            });
        }
        
        @VisibleForTesting
        List<Snake> getSnakes() {
            return this.mSnakes;
        }
    }
    
    private static class PostponedUpdate
    {
        int currentPos;
        int posInOwnerList;
        boolean removal;
        
        public PostponedUpdate(final int posInOwnerList, final int currentPos, final boolean removal) {
            this.posInOwnerList = posInOwnerList;
            this.currentPos = currentPos;
            this.removal = removal;
        }
    }
    
    static class Range
    {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;
        
        public Range() {
        }
        
        public Range(final int oldListStart, final int oldListEnd, final int newListStart, final int newListEnd) {
            this.oldListStart = oldListStart;
            this.oldListEnd = oldListEnd;
            this.newListStart = newListStart;
            this.newListEnd = newListEnd;
        }
    }
    
    static class Snake
    {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;
    }
}
