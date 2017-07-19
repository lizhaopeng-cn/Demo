package com.example.ringview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by lzp on 2017/7/1.
 */

public class RingView extends View{
    public static final int DEFAULT_SIZE = 200;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 3;
//    private int mColor;
    private int mRingBackgroundColor; //圆环背景色
    private int mRingColor1; //圆环颜色，渐变颜色一
    private int mRingColor2; //渐变颜色二
    private int mRingColor3; //渐变颜色三
    private float mInnerRadius; //内环半径
    private float mThickness; //圆环厚度
    private boolean mClockwise; //是否为顺时针
    private float mProgress; //圆环进度
    private int mDirection; //圆环起始方向
    private Paint mRingBackgroundPaint = new Paint(); //圆环背景笔
    private Paint mRingPaint = new Paint(); //圆环笔
    private float density = getResources().getDisplayMetrics().density;;
    private static float currentValue = 0f;



    private int paddingLeft = getPaddingLeft();
    private int paddingRight = getPaddingRight();
    private int paddingTop = getPaddingTop();
    private int paddingBottom = getPaddingBottom();
    private int width = getWidth() - paddingLeft - paddingRight;
    private int height = getHeight() - paddingTop - paddingBottom;

    private RectF rectF = new RectF(paddingLeft - paddingRight + mThickness / 2, paddingTop - paddingBottom + mThickness / 2, paddingLeft - paddingRight + mInnerRadius * 2 + mThickness / 2, paddingTop - paddingBottom + mInnerRadius * 2 + mThickness / 2);

    private int[] colors;
    private SweepGradient sweepGradient;

    public RingView(Context context) {
        super(context);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.RingView);
        mRingBackgroundColor = a.getColor(R.styleable.RingView_ringBackgroundColor,context.getResources().getColor(R.color.colorPrimary));
        mRingColor1 = a.getColor(R.styleable.RingView_ringColor1, context.getResources().getColor(R.color.colorAccent));
        mRingColor2 = a.getColor(R.styleable.RingView_ringColor2, context.getResources().getColor(R.color.colorAccent));
        mRingColor3 = a.getColor(R.styleable.RingView_ringColor3, context.getResources().getColor(R.color.colorAccent));
        mThickness = a.getFloat(R.styleable.RingView_ringThickness, DEFAULT_SIZE / 8) * density;
        mInnerRadius = (a.getFloat(R.styleable.RingView_ringInnerRadius, (DEFAULT_SIZE / 2 - mThickness/density) ) + mThickness / density / 2) * density ;
        mClockwise = a.getBoolean(R.styleable.RingView_clockwise, true);
        mProgress = a.getFloat(R.styleable.RingView_progress, 0f);
        mDirection = a.getInt(R.styleable.RingView_direction, 1);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int) (DEFAULT_SIZE * density),(int) (DEFAULT_SIZE * density));
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int) (DEFAULT_SIZE * density),heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,(int) (DEFAULT_SIZE * density));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawTextView(canvas);

        drawBackgroundRing(canvas);

        drawRing(canvas);


    }

    private void drawTextView(Canvas canvas) {
        Rect rect = new Rect();
        String text = "三";
        Paint paintText = new Paint();
        paintText.setStrokeWidth(10*density);
        paintText.setTextSize(50*density);
//        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.getTextBounds(text,0,text.length(),rect);
        Paint.FontMetricsInt fontMetrics = paintText.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(text,100*density - rect.width()/2,100*density+rect.height()/2,paintText);
    }

    private void drawBackgroundRing(Canvas canvas) {
        mRingBackgroundPaint.setColor(mRingBackgroundColor);
        mRingBackgroundPaint.setStrokeWidth(mThickness);
        mRingBackgroundPaint.setStyle(Paint.Style.STROKE);
        mRingBackgroundPaint.setAntiAlias(true);

        canvas.drawArc(rectF,0,360,false,mRingBackgroundPaint);
    }

    private void drawRing(Canvas canvas) {
        mRingPaint.setColor(mRingColor1);
        mRingPaint.setStrokeWidth(mThickness);
//        paint.setStrokeCap(Paint.Cap.ROUND);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setAntiAlias(true);
        if(mClockwise){
            colors = new int[]{mRingColor1,mRingColor2,mRingColor3};
        }else{
            colors = new int[]{mRingColor3,mRingColor2,mRingColor1};
        }
        sweepGradient = new SweepGradient(paddingLeft - paddingRight + mInnerRadius + mThickness / 2, paddingTop - paddingBottom + mInnerRadius + mThickness / 2, colors, null);
        mRingPaint.setShader(sweepGradient);

        canvas.save();
        switch (mDirection){

            case LEFT:
                canvas.rotate(-180, paddingLeft - paddingRight + mInnerRadius + mThickness / 2 ,paddingTop - paddingBottom + mInnerRadius + mThickness / 2);
                break;

            case TOP:
                canvas.rotate(-90, paddingLeft - paddingRight + mInnerRadius + mThickness / 2 ,paddingTop - paddingBottom + mInnerRadius + mThickness / 2);
                break;

            case RIGHT:
                canvas.rotate(0, paddingLeft - paddingRight + mInnerRadius + mThickness / 2 ,paddingTop - paddingBottom + mInnerRadius + mThickness / 2);
                break;

            case BOTTOM:
                canvas.rotate(90, paddingLeft - paddingRight + mInnerRadius + mThickness / 2 ,paddingTop - paddingBottom + mInnerRadius + mThickness / 2);
                break;

            default:
                canvas.rotate(180, paddingLeft - paddingRight + mInnerRadius + mThickness / 2 ,paddingTop - paddingBottom + mInnerRadius + mThickness / 2);
                break;

        }

        if(mClockwise){
            canvas.drawArc(rectF,0,currentValue,false,mRingPaint);
        }else{
            canvas.drawArc(rectF,0,-currentValue,false,mRingPaint);
        }

        canvas.restore();

    }


    public void setCircleColor(int mRingColor1){
        this.mRingColor1 = mRingColor1;
    }

    public void setWidth(int width){

    }

    public void setAnimator(){
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,mProgress * 360);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                invalidate();
//                Log.i("test",currentValue+"");
            }
        });
//        valueAnimator.setRepeatCount(3);
//        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setStartDelay(3000);
        valueAnimator.setDuration(3000);
//        valueAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.start();
    }
}
