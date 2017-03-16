package azsecuer.zhuoxin.com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class TopBar extends RelativeLayout {
    private Button mleftButton,mrightButton;
    private TextView mtext;
    private LayoutParams leftparams,rightparams,titleparams;
    private TopbarListener topbarListener;
    public TopBar(Context context) {
        super(context,null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        int leftColor = typedArray.getColor(R.styleable.TopBar_leftColor,0);
        int rightColor = typedArray.getColor(R.styleable.TopBar_rightColor,0);
        String lefText = typedArray.getString(R.styleable.TopBar_lefText);
        String rightText = typedArray.getString(R.styleable.TopBar_rightText);
        Drawable leftBackgrand = typedArray.getDrawable(R.styleable.TopBar_leftBackgrand);
        Drawable rightBackgrand = typedArray.getDrawable(R.styleable.TopBar_rightBackgrand);
        String title = typedArray.getString(R.styleable.TopBar_title);
        float titleSize = typedArray.getDimension(R.styleable.TopBar_titleSize, 10);
        int titleColor = typedArray.getColor(R.styleable.TopBar_titleColor, 0);
        typedArray.recycle();

        //初始化控件
        mleftButton=new Button(context);
        mrightButton=new Button(context);
        mtext=new TextView(context);

        mleftButton.setTextColor(leftColor);
        mleftButton.setText(lefText);
        mleftButton.setBackground(leftBackgrand);

        mrightButton.setTextColor(rightColor);
        mrightButton.setText(rightText);
        mrightButton.setBackground(rightBackgrand);

        mtext.setText(title);
        mtext.setTextColor(titleColor);
        mtext.setTextSize(titleSize);
        mtext.setGravity(Gravity.CENTER);

        //设置布局
        leftparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        leftparams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mleftButton,leftparams);

        rightparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mrightButton,rightparams);

        titleparams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleparams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mtext,titleparams);

        mleftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topbarListener!=null){
                    topbarListener.leftButtonClick(view);
                }
            }
        });
        mrightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topbarListener!=null){
                    topbarListener.rightButtonClick(view);
                }
            }
        });

    }
   //显示和隐藏的方法
    public void setButtonVisible(int id,boolean flag){
        if(flag){
            if(id==0){
              mleftButton.setVisibility(VISIBLE);  //0,true
            }else {
              mrightButton.setVisibility(VISIBLE); //!=0,true
            }
        }else {
           if(id==0){
               mleftButton.setVisibility(INVISIBLE);//0,fslse
           }else {
               mrightButton.setVisibility(INVISIBLE);//!=0,false
           }
        }
    }

    interface TopbarListener{
        void leftButtonClick(View view);
        void rightButtonClick(View view);
    }

    public void setTopbarListener(TopbarListener topbarListener) {
        this.topbarListener = topbarListener;
    }

}

