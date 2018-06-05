package com.example.canvasclip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lzp48947 on 2018/5/17.
 */

public class MyView extends View {
    private int topColor;
    private int bottomColor;

    private Paint mPaint;
    private Path mPath;
    private float W = getWidth();
    private float H = getHeight();

    public MyView(Context context) {
        super(context);

    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);

        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100, 30, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //        MyView
//        canvas.clipRect(0,0,getWidth(),getHeight());
//
//        Path pathLeft = new Path();
//        Path pathRight = new Path();
//        pathLeft.addCircle(0,getHeight()/2,getHeight()/2,Path.Direction.CCW);
//        pathRight.addCircle(getWidth(),getHeight()/2,getHeight()/2,Path.Direction.CCW);
//        canvas.clipPath(pathLeft,Region.Op.DIFFERENCE);
//        canvas.clipPath(pathRight,Region.Op.DIFFERENCE);
//
//        Paint paintTop = new Paint();
//        if(topColor != 0){
//            paintTop.setColor(topColor);
//        }
//        canvas.drawRect(new RectF(0,0,getWidth(),getHeight()/2),paintTop);
//
//        Paint paintBottom = new Paint();
//        if(bottomColor != 0){
//            paintBottom.setColor(bottomColor);
//        }
//        canvas.drawRect(new RectF(0,getHeight()/2,getWidth(),getHeight()),paintBottom);





//        Paint paintTop = new Paint();
//        if(topColor != 0){
//            paintTop.setColor(topColor);
//        }
//        canvas.drawRect(new RectF(0, 0, getWidth(), getHeight()/2), paintTop);
//
//        Paint paintBottom = new Paint();
//        if(bottomColor != 0){
//            paintBottom.setColor(bottomColor);
//        }
//        canvas.drawRect(new RectF(0, getHeight()/2, getWidth(), getHeight()), paintBottom);
//
//        Paint paintLeft = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintLeft.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        Paint paintRight = new Paint();
//        paintRight.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//        paintRight.setAntiAlias(true);
//        canvas.drawCircle(0, getHeight()/2, getHeight()/2, paintLeft);
//        canvas.drawCircle(getWidth(), getHeight()/2, getHeight()/2, paintRight);
//        paintLeft.setXfermode(null);
//        paintRight.setXfermode(null);


        Paint paint = new Paint();
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int r = canvasWidth / 3;

        //设置背景色
        canvas.drawARGB(255, 139, 197, 186);
        int layerId = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
        //正常绘制黄色的圆形
        paint.setColor(0xFFFFCC44);
        canvas.drawCircle(r, r, r, paint);
        //使用CLEAR作为PorterDuffXfermode绘制蓝色的矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setColor(0xFF66AAFF);
        canvas.drawRect(r, r, r * 2.7f, r * 2.7f, paint);
        //最后将画笔去除Xfermode
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);




//        canvas.save();
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        canvas.drawCircle(250,200,100,paint);
//        canvas.restore();
//
//        canvas.save();
//        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint1.setColor(Color.RED);
//        canvas.drawCircle(250,400,100,paint1);
//        canvas.restore();
//
//        canvas.save();
//        Paint paint2 = new Paint();
//        paint2.setAntiAlias(true);
//        paint2.setColor(Color.RED);
//        canvas.drawCircle(250,600,100,paint2);
//        canvas.restore();
//
//        canvas.save();
//        Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint3.setAntiAlias(true);
//        paint3.setColor(Color.RED);
//        canvas.drawCircle(250,800,100,paint3);
//        canvas.restore();







//        Region.Op
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
//        mPaint.setStrokeWidth(6);
//        mPaint.setTextSize(16);
//        mPaint.setTextAlign(Paint.Align.RIGHT);
//
//        mPath = new Path();
//
//        canvas.drawColor(Color.GRAY);
//
//        canvas.save();
//        canvas.translate(10, 10);
//        drawScene(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(160, 10);
//        canvas.clipRect(10, 10, 90, 90);
//        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);// 扣一块区域
//        drawScene(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(10, 160);
////        mPath.reset();
////        canvas.clipPath(mPath); // makes the clip empty
//        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
//        canvas.clipPath(mPath, Region.Op.REPLACE);
//        drawScene(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(160, 160);
//        canvas.clipRect(0, 0, 60, 60);
//        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
//        drawScene(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(10, 310);
//        canvas.clipRect(0, 0, 60, 60);
//        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
//        drawScene(canvas);
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(160, 310);
//        canvas.clipRect(0, 0, 60, 60);
//        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);
//        drawScene(canvas);
//        canvas.restore();
    }
    public void setViewColor(int topColor,int bottomColor){
        this.topColor = topColor;
        this.bottomColor = bottomColor;
    }

}
