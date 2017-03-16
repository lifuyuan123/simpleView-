package azsecuer.zhuoxin.com.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyText extends TextView {
    int with=0,hight=0,mtran=0;
    Paint paint3;
    LinearGradient linearGradient;
    Matrix matrix;
    public MyText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyText(Context context) {
        super(context);
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        Paint paint1=new Paint();
//        Paint paint2=new Paint();
//        paint1.setColor(Color.BLUE);
//        paint2.setColor(Color.YELLOW);
//        paint1.setStyle(Paint.Style.FILL);
//        paint2.setStyle(Paint.Style.FILL);
//        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint1);
//        canvas.drawRect(30,30,getMeasuredWidth(),getMeasuredHeight(),paint2);
//        canvas.save();
//        //文字平移20像素
//        canvas.translate(20,0);
//        super.onDraw(canvas);
//        canvas.restore();
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix!=null){
          mtran+=with/5;
            if(mtran>with*2){
                mtran=-with;
            }
            matrix.setTranslate(mtran,0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        with=MeasureSpec.getSize(widthMeasureSpec);
        hight=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(with,hight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(with==0){
          with=getMeasuredWidth();
            if(with>0){
                paint3=getPaint();
                linearGradient=new LinearGradient(0,0,with,0,new int[]{Color.BLUE,0xffffff,Color.BLUE}
                        ,null, Shader.TileMode.CLAMP);
                paint3.setShader(linearGradient);
                matrix=new Matrix();
            }
        }
    }
}
