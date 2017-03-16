package azsecuer.zhuoxin.com.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.KeyEvent;
import android.view.Window;

public class Main3Activity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在setContentView之前

        // 允许使用transitions
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        String transition = getIntent().getStringExtra("transition");
        if(transition!=null){
            switch (transition){
                case "explode" :
                    getWindow().setEnterTransition(new Explode().setDuration(1000));
                    break;
                case "slide" :
                    getWindow().setEnterTransition(new Slide().setDuration(1000));
                    break;
                case "fade" :
                    getWindow().setEnterTransition(new Fade().setDuration(1000));
                    break;
                case "shar" :
//                getWindow().setSharedElementEnterTransition(new Slide());
                    break;

            }
        }

        setContentView(R.layout.activity_main3);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.anim.out_to_right,R.anim.in_from_left);
        }
        return super.onKeyDown(keyCode, event);
    }
}
