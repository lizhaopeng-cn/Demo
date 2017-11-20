package com.lzp.filterlist;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp48947 on 2017/11/4.
 */

public class LabelsView extends ViewGroup implements View.OnClickListener {

    private Context mContext;

    private int mTextPaddingLeft;
    private int mTextPaddingTop;
    private int mTextPaddingRight;
    private int mTextPaddingBottom;
    private int mWordMargin;
    private int mLineMargin;
    private ColorStateList mTextColor;
    private float mTextSize;
    private int mLabelBgResId;

    private List<View> labelViews = new ArrayList<>();

    private OnLabelClickListener mLabelClickListener;

    private int labelHeight; //标签列表的高度
    private int maxLine; //标签列表的最大行数
    private FilterListView filterListView;

    public LabelsView(Context context) {
        super(context);
        mContext = context;
    }

    public LabelsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        getAttrs(context, attrs);
    }

    public LabelsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.labels_view);

            mTextPaddingLeft = mTypedArray.getDimensionPixelOffset(
                    R.styleable.labels_view_labelTextPaddingLeft, 0);
            mTextPaddingTop = mTypedArray.getDimensionPixelOffset(
                    R.styleable.labels_view_labelTextPaddingTop, 0);
            mTextPaddingRight = mTypedArray.getDimensionPixelOffset(
                    R.styleable.labels_view_labelTextPaddingRight, 0);
            mTextPaddingBottom = mTypedArray.getDimensionPixelOffset(
                    R.styleable.labels_view_labelTextPaddingBottom, 0);
            mLineMargin = mTypedArray.getDimensionPixelOffset(R.styleable.labels_view_lineMargin, 0);
            mWordMargin = mTypedArray.getDimensionPixelOffset(R.styleable.labels_view_wordMargin, 0);
            mTextColor = mTypedArray.getColorStateList(R.styleable.labels_view_labelTextColor);
            mTextSize = mTypedArray.getDimension(R.styleable.labels_view_labelTextSize,
                    sp2px(context, 14));
            mLabelBgResId = mTypedArray.getResourceId(R.styleable.labels_view_labelBackground, 0);
            mTypedArray.recycle();
        }
    }

    public void setFilterListView(FilterListView filterListView){
        this.filterListView = filterListView;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int thisLine = 1; //标签列表的行数
        int count = getChildCount();
        int maxWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();

        int contentHeight = 0; //记录内容的高度
        int lineWidth = 0; //记录行的宽度
        int maxLineWidth = 0; //记录最宽的行宽
        int maxItemHeight = 0; //记录一行中item高度最大的高度
        boolean begin = true; //是否是行的开头

        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);

            if (maxWidth < lineWidth + view.getMeasuredWidth()) {
                contentHeight += mLineMargin;
                contentHeight += maxItemHeight;
                maxItemHeight = 0;
                maxLineWidth = Math.max(maxLineWidth, lineWidth);
                lineWidth = 0;
                begin = true;
                thisLine++;
            }
            maxItemHeight = Math.max(maxItemHeight, view.getMeasuredHeight());
            if (!begin) {
                lineWidth += mWordMargin;
            } else {
                lineWidth += mWordMargin;
                begin = false;
            }
            lineWidth += view.getMeasuredWidth();
        }

        contentHeight += maxItemHeight;
        maxLineWidth = Math.max(maxLineWidth, lineWidth);

        setMeasuredDimension(measureWidth(widthMeasureSpec, maxLineWidth),
                measureHeight(heightMeasureSpec, contentHeight, thisLine));
    }

    private int measureWidth(int measureSpec, int contentWidth) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentWidth + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumWidth());
        return result;
    }

    private int measureHeight(int measureSpec, int contentHeight, int thisLine) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = contentHeight + getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        result = Math.max(result, getSuggestedMinimumHeight());
        //赋值标签的最大高度
        if(thisLine <= maxLine){
            labelHeight = result;
        }
        filterListView.setScrollViewHeight();
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int x = getPaddingLeft();
        int y = getPaddingTop();

        int contentWidth = right - left;
        int maxItemHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);

            if (contentWidth < x + view.getMeasuredWidth() + getPaddingRight()) {
                x = getPaddingLeft();
                y += mLineMargin;
                y += maxItemHeight;
                maxItemHeight = 0;
            }
            view.layout(x, y, x + view.getMeasuredWidth(), y + view.getMeasuredHeight());
            x += view.getMeasuredWidth();
            x += mWordMargin;
            maxItemHeight = Math.max(maxItemHeight, view.getMeasuredHeight());
        }
    }

    /**
     * 设置标签列表的最大行数
     */
    public void setMaxLine(int maxLine){
        this.maxLine = maxLine;
    }

    /**
     * 获取标签列表高度
     */
    public int getLabelHeight(){
        return labelHeight;
    }

    /**
     * 设置标签列表
     *
     * @param labels
     */
    public void setLabels(List<String> labels) {
        removeAllViews();
//        mLabels.clear();

        if (labels != null) {
//            mLabels.addAll(labels);
            for (int i = 0; i < labels.size(); i++) {
                addLabel(labels.get(i), i);
            }
        }
    }

    /**
     * 获取所有的标签数据
     * @return
     */
    public List<String> getLabels(){
        List<String> labels = new ArrayList<>();
        for(int i = 0; i < getChildCount(); i++){
            String label = ((TextView)getChildAt(i)).getText().toString();
            if(!TextUtils.isEmpty(label)){
                labels.add(label);
            }
        }
        return labels;
    }

    /**
     * 删除指定标签列表
     * @param updateTextList
     */
    public void removeLabels(List<String> updateTextList){
        List<View> deleteViews = new ArrayList<View>();
        if(getChildCount() > 0){
            for(int i = 0; i < getChildCount(); i++){
                String label = ((TextView)getChildAt(i)).getText().toString();
                for(String updateText : updateTextList){
                    if(TextUtils.equals(label,updateText)){
                        deleteViews.add(getChildAt(i));
                    }
                }
            }
            for(int i = 0; i < deleteViews.size(); i++){
                removeLabel(deleteViews.get(i));
            }
        }
    }

    /**
     * 删除指定标签
     * @param view
     */
    public void removeLabel(View view){
        removeView(view);
    }


    public void addLabel(String text, int position) {
        final TextView textView = new TextView(mContext);
        textView.setPadding(mTextPaddingLeft, mTextPaddingTop, mTextPaddingRight, mTextPaddingBottom);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        textView.setTextColor(mTextColor != null ? mTextColor : ColorStateList.valueOf(0xFF000000));
        textView.setText(text);
        Drawable rightDrawable = getResources().getDrawable(R.drawable.ic_clear_black_18dp);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, rightDrawable, null);
        if (mLabelBgResId != 0) {
            textView.setBackgroundResource(mLabelBgResId);
        }
        //label通过tag保存自己的位置(position)
        textView.setTag(position);
        textView.setOnClickListener(this);
        addView(textView);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            TextView label = (TextView) v;

            if (mLabelClickListener != null) {
                mLabelClickListener.onLabelClick(label, label.getText().toString(), (int) v.getTag());
            }
        }
    }

    /**
     * 设置标签背景
     *
     * @param resId
     */
    public void setLabelBackgroundResource(int resId) {
        if (mLabelBgResId != resId) {
            mLabelBgResId = resId;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                TextView label = (TextView) getChildAt(i);
                label.setBackgroundResource(mLabelBgResId);
            }
        }
    }

    /**
     * 设置标签内边距
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public void setLabelTextPadding(int left, int top, int right, int bottom) {
        if (mTextPaddingLeft != left || mTextPaddingTop != top
                || mTextPaddingRight != right || mTextPaddingBottom != bottom) {
            mTextPaddingLeft = left;
            mTextPaddingTop = top;
            mTextPaddingRight = right;
            mTextPaddingBottom = bottom;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                TextView label = (TextView) getChildAt(i);
                label.setPadding(left, top, right, bottom);
            }
        }
    }

    /**
     * 设置标签的文字大小（单位是px）
     *
     * @param size
     */
    public void setLabelTextSize(float size) {
        if (mTextSize != size) {
            mTextSize = size;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                TextView label = (TextView) getChildAt(i);
                label.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            }
        }
    }

    /**
     * 设置标签的文字颜色
     *
     * @param color
     */
    public void setLabelTextColor(int color) {
        setLabelTextColor(ColorStateList.valueOf(color));
    }

    /**
     * 设置标签的文字颜色
     *
     * @param color
     */
    public void setLabelTextColor(ColorStateList color) {
        mTextColor = color;
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            TextView label = (TextView) getChildAt(i);
            label.setTextColor(mTextColor != null ? mTextColor : ColorStateList.valueOf(0xFF000000));
        }
    }

    /**
     * 设置行间隔
     */
    public void setLineMargin(int margin) {
        if (mLineMargin != margin) {
            mLineMargin = margin;
            requestLayout();
        }
    }

    /**
     * 设置标签的间隔
     */
    public void setWordMargin(int margin) {
        if (mWordMargin != margin) {
            mWordMargin = margin;
            requestLayout();
        }
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置标签的点击监听
     *
     * @param l
     */
    public void setOnLabelClickListener(OnLabelClickListener l) {
        mLabelClickListener = l;
    }

    public interface OnLabelClickListener {
        void onLabelClick(View label, String labelText, int position);
    }

}
