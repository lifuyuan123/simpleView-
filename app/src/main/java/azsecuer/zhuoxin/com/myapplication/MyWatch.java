package azsecuer.zhuoxin.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MyWatch extends View {
    int w,h;

    public MyWatch(Context context) {
        super(context);
    }

    public MyWatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        w=gethight(widthMeasureSpec);
        h=gethight(heightMeasureSpec);
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画圆-----------------------------------
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        //x,y,半径，画笔
        canvas.drawCircle(w/2,h/2,w/2-5,paint);
        //画圆-----------------------------------

        //画刻度-------------------------------------
        Paint paint1=new Paint();
        for (int i = 0; i <24 ; i++) {
            //区分整点与非整点
            if(i==0||i==6||i==12||i==18){
                paint1.setStrokeWidth(5);
                paint1.setTextSize(25);
                canvas.drawLine(w/2,5,w/2,45,paint1);
                //刻度值
                String s=String.valueOf(i);
                canvas.drawText(s,w/2-paint1.measureText(s)/2,75,paint1);
                //paint1.measureText(s) 指画笔画出的字的宽度
            }else {
                paint1.setStrokeWidth(3);
                paint1.setTextSize(15);
                canvas.drawLine(w/2,5,w/2,35,paint1);
                String s=String.valueOf(i);
                canvas.drawText(s,w/2-paint1.measureText(s)/2,65,paint1);
                //paint1.measureText(s) 指画笔画出的字的宽度
            }
            canvas.rotate(15,w/2,h/2);
        }
        //画刻度-------------------------------------

        //画指针----------------------------------------------------
        Paint paintHour=new Paint();//时针
        paintHour.setStrokeWidth(10);
        Paint paintMinute=new Paint();//分针
        paintMinute.setStrokeWidth(6);
        Paint paint3=new Paint();//园
        canvas.save();
        canvas.translate(w/2,h/2);//原点移到中心
        canvas.drawCircle(0,0,8,paint3);
        canvas.drawLine(-10,-10,60,60,paintHour);   //指针角度
        canvas.drawLine(-12,-20,60,100,paintMinute);
        canvas.restore();

        //画指针----------------------------------------------------

    }


    private int gethight(int heightMeasureSpec) {
        int reselt=0;
        //获取模式和大小
        // mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED,未指定尺寸
        // MeasureSpec.EXACTLY,精确
        // MeasureSpec.AT_MOST。wrap_content  最大
        int mode=MeasureSpec.getMode(heightMeasureSpec);
        int size=MeasureSpec.getSize(heightMeasureSpec);
        if(mode==MeasureSpec.EXACTLY){
            reselt=size;
        }else {
            reselt=200;
            if(mode==MeasureSpec.AT_MOST){
                reselt=Math.min(reselt,size);
            }
        }
        return reselt;
    }
}
