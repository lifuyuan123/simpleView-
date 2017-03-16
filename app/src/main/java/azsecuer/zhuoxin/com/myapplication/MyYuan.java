package azsecuer.zhuoxin.com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyYuan extends View {
    int reselt;
    private TextView mtext;
//    private View yuan,waiyuan;
//    private LayoutParams textlp,yuanlp,waiyuanlp;

    private float yuanXY,banjin;
    private RectF rectF;
    private String text;

    public MyYuan(Context context) {
        super(context,null);
    }

    public MyYuan(Context context, AttributeSet attrs) {
        super(context, attrs);
//        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.MyYuan);
//        int textColor = ta.getColor(R.styleable.MyYuan_textColor,0);
////        int yuanColor = ta.getColor(R.styleable.MyYuan_yuanColor,0);
////        int waiyuanColor = ta.getColor(R.styleable.MyYuan_waiyuanColor,0);
//        text = ta.getString(R.styleable.MyYuan_text);
//        float textSize = ta.getDimension(R.styleable.MyYuan_textSize, 10);
//        ta.recycle();
////
//        mtext= new TextView(context);
////        yuan=new View(context);
////        waiyuan=new View(context);
////
//        mtext.setText(text);
//        mtext.setTextColor(textColor);
//        mtext.setTextSize(textSize);
//        mtext.setGravity(Gravity.CENTER);
//
//        yuan.setBackgroundColor(yuanColor);
//        waiyuan.setBackgroundColor(waiyuanColor);
//
//        textlp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
//        textlp.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
//        addView(mtext,textlp);
//
//        yuanlp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
//        yuanlp.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
//        addView(yuan,yuanlp);
//
//        waiyuanlp=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
//        waiyuanlp.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
//        addView(waiyuan,waiyuanlp);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint1=new Paint();
        paint1.setColor(Color.YELLOW);
        Paint paint2=new Paint();
        paint2.setColor(Color.BLUE);
        Paint paint3=new Paint();
        paint3.setColor(Color.BLACK);
        yuanXY=reselt/2;
        banjin=reselt/4;
        rectF=new RectF((float)(reselt*0.1),(float)(reselt*0.1),
                (float)(reselt*0.9),(float)(reselt*0.9));
        //园
        canvas.drawCircle(yuanXY,yuanXY,banjin,paint1);
        //字
        canvas.drawText("1234",0,"1234".length(),yuanXY,yuanXY,paint2);
        //圆弧
        canvas.drawArc(rectF,270,180,false,paint3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(gethight(widthMeasureSpec),gethight(heightMeasureSpec));
    }

    private int gethight(int heightMeasureSpec) {
        reselt=0;
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
