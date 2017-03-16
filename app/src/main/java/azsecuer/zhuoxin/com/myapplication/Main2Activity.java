package azsecuer.zhuoxin.com.myapplication;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button button1,button2,button3,button4,button9;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button1= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        button4= (Button) findViewById(R.id.button4);
        imageView= (ImageView) findViewById(R.id.imag);
        button9= (Button) findViewById(R.id.button9);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button9.setOnClickListener(this);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,Main3Activity.class);
        switch (view.getId()){
            case R.id.button1:
                intent.putExtra("transition","explode");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Main2Activity.this).toBundle());
                break;
            case R.id.button2:
                intent.putExtra("transition","slide");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Main2Activity.this).toBundle());
                break;
            case R.id.button3:
                intent.putExtra("transition","fade");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Main2Activity.this).toBundle());
                break;
            case R.id.button4:
                //不要忘记在此xml中添加android:transitionName="shartransition"属性，目标xml中也需要添加此属性
                intent.putExtra("transition","shar");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                        (Main2Activity.this,imageView,"shartransition").toBundle());

//                如果有多个共享元素：
//                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
//                        Pair.create(view1, "agreedName1"),
//                        Pair.create(view2, "agreedName2"));
                break;
            case R.id.button9:
                startActivity(intent);
                overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
                break;

        }
    }
}
