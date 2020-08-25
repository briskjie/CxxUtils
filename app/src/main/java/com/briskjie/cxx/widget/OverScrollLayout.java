package com.briskjie.cxx.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * recycleview 左右回弹，适配左右滑动
 */
public class OverScrollLayout extends LinearLayout {

    private static final int ANIM_TIME = 400;

    private RecyclerView childView;

    private Rect original = new Rect();

    private boolean isMoved = false;

    private float startXpos;

    /**
     * 阻尼系数
     */
    private static final float DAMPING_COEFFICIENT = 0.1f;

    private boolean needIntercept;

    private ScrollListener mScrollListener;

    public OverScrollLayout(Context context) {
        this(context, null);
    }

    public OverScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OverScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        childView = (RecyclerView) getChildAt(0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        original.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
    }

    public void setScrollListener(ScrollListener listener) {
        mScrollListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float touchXpos = ev.getX();
        if (touchXpos >= original.right || touchXpos <= original.left) {
            if (isMoved) {
                recoverLayout();
            }
            return true;
        }
        needIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startXpos = ev.getX();
                return super.dispatchTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                int scrollXpos = (int) (ev.getX() - startXpos);
                boolean pullDown = scrollXpos > 0 && canPullDown();
                boolean pullUp = scrollXpos < 0 && canPullUp();
                if (pullDown || pullUp) {
                    cancelChild(ev);
                    int offset = (int) (scrollXpos * DAMPING_COEFFICIENT);
                    childView.layout(original.left + offset, original.top,
                            original.right + offset, original.bottom);
                    if (mScrollListener != null) {
                        mScrollListener.onScroll();
                    }
                    isMoved = true;
                    needIntercept = true;
                    return true;
                } else {
                    startXpos = ev.getX();
                    isMoved = false;
                    needIntercept = false;
                    return super.dispatchTouchEvent(ev);
                }
            case MotionEvent.ACTION_UP:
                if (isMoved) {
                    recoverLayout();
                }
                return needIntercept || super.dispatchTouchEvent(ev);
            default:
                return true;
        }
    }

    /**
     * 取消子view已经处理的事件
     *
     * @param ev event
     */
    private void cancelChild(MotionEvent ev) {
        ev.setAction(MotionEvent.ACTION_CANCEL);
        super.dispatchTouchEvent(ev);
    }

    /**
     * 位置还原
     */
    private void recoverLayout() {
        TranslateAnimation anim = new TranslateAnimation(childView.getLeft() - original.left, 0,
                0, 0);
        anim.setDuration(ANIM_TIME);
        childView.startAnimation(anim);
        childView.layout(original.left, original.top, original.right, original.bottom);
        isMoved = false;
    }

    /**
     * 判断是否可以下拉
     *
     * @return true：可以，false:不可以
     */
    private boolean canPullDown() {
        final int firstVisiblePosition
                = ((LinearLayoutManager) childView.getLayoutManager()).findFirstVisibleItemPosition();
        if (firstVisiblePosition != 0 && childView.getAdapter().getItemCount() != 0) {
            return false;
        }
        int mostTop = (childView.getChildCount() > 0) ? childView.getChildAt(0).getLeft() : 0;
        return mostTop >= 0;
    }

    /**
     * 判断是否可以上拉
     *
     * @return true：可以，false:不可以
     */
    private boolean canPullUp() {
        final int lastItemPosition = childView.getAdapter().getItemCount() - 1;
        final int lastVisiblePosition
                = ((LinearLayoutManager) childView.getLayoutManager()).findLastVisibleItemPosition();
        if (lastVisiblePosition >= lastItemPosition) {
            final int childIndex = lastVisiblePosition
                    - ((LinearLayoutManager) childView.getLayoutManager()).findFirstVisibleItemPosition();
            final int childCount = childView.getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = childView.getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getRight() <= childView.getRight() - childView.getLeft();
            }
        }
        return false;
    }

    public interface ScrollListener {
        /**
         * 滚动事件回调
         */
        void onScroll();
    }

}