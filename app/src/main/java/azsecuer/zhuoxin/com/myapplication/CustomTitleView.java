package azsecuer.zhuoxin.com.myapplication;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class CustomTitleView extends View
{
	//文本
	private String mTitleText;
	//文字颜色
	private int mTitleTextColor;
	//文字大小
	private int mTitleTextSize;

	//绘制时控制文本绘制的范围
	private Rect mBound;
	private Paint mPaint;

	public CustomTitleView(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public CustomTitleView(Context context)
	{
		this(context, null);
	}

	//获得我自定义的样式属性
	public CustomTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		//获得我们所定义的自定义样式属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTitleView_titleText:
				mTitleText = a.getString(attr);
				break;
			case R.styleable.CustomTitleView_titleTextColor:
				// 默认颜色设置为黑色
				mTitleTextColor = a.getColor(attr, Color.BLACK);
				break;
			case R.styleable.CustomTitleView_titleTextSize:
				// 默认设置为16sp，TypeValue也可以把sp转化为px
				mTitleTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;

			}

		}
		a.recycle();

//		获得绘制文本的宽和高
		mPaint = new Paint();
		mPaint.setTextSize(mTitleTextSize);
		// mPaint.setColor(mTitleTextColor);
		mBound = new Rect();
		mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

		this.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				mTitleText = randomText();
				//invalidate()和postInvalidate() 都是刷新界面   invalidate是ui线程中才能用
				postInvalidate();
			}

		});

	}
	private String randomText()
	{
		Random random = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < 4)
		{
			int randomInt = random.nextInt(10);
			set.add(randomInt);
		}
		StringBuffer sb = new StringBuffer();
		for (Integer i : set)
		{
			sb.append("" + i);
		}

		return sb.toString();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //onMeasure传入的widthMeasureSpec和heightMeasureSpec不是一般的尺寸数值，
       // 而是将模式和尺寸组合在一起的数值。我们需要通过int mode = MeasureSpec.getMode(widthMeasureSpec)得到模式，
       // 用int size = MeasureSpec.getSize(widthMeasureSpec)得到尺寸。
		//mode共有三种情况，取值分别为MeasureSpec.UNSPECIFIED,未指定尺寸
		// MeasureSpec.EXACTLY,精确
		// MeasureSpec.AT_MOST。最大
		int width = 0;
		int height = 0;

//		设置宽度
		int specMode = MeasureSpec.getMode(widthMeasureSpec);//得到模式
		int specSize = MeasureSpec.getSize(widthMeasureSpec);//得到尺寸
		switch (specMode) {
		case MeasureSpec.EXACTLY:// 明确指定了
			width = getPaddingLeft() + getPaddingRight() + specSize;
			break;
		case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
			width = getPaddingLeft() + getPaddingRight() + mBound.width();
			break;
		}

//		设置高度
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		switch (specMode) {
		case MeasureSpec.EXACTLY:// 明确指定了
			height = getPaddingTop() + getPaddingBottom() + specSize;
			break;
		case MeasureSpec.AT_MOST:// 一般为WARP_CONTENT
			height = getPaddingTop() + getPaddingBottom() + mBound.height();
			break;
		}

		setMeasuredDimension(width, height);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		mPaint.setColor(Color.YELLOW);
//		getMeasuredHeight()指获取全部的高度  包括隐藏、未显示的部分，   getHeight获取显示的部分
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		mPaint.setColor(mTitleTextColor);
		float baseline = (getMeasuredHeight() - mPaint.getFontMetricsInt().bottom + mPaint.getFontMetricsInt().top) / 2 - mPaint.getFontMetricsInt().top;
		canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, baseline, mPaint);
	}
}
