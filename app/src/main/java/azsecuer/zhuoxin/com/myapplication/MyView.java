package azsecuer.zhuoxin.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(gethight(widthMeasureSpec),gethight(heightMeasureSpec));
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap,getWidth()/2,getHeight()/2,paint);
    }
}
