package azsecuer.zhuoxin.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private TopBar topBar;
    private MyWatch myWatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topBar= (TopBar) findViewById(R.id.topbar);
        myWatch= (MyWatch) findViewById(R.id.watch);
        topBar.setTopbarListener(new TopBar.TopbarListener() {
            @Override
            public void leftButtonClick(View view) {
                Toast.makeText(MainActivity.this, "左", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightButtonClick(View view) {
                Toast.makeText(MainActivity.this, "右", Toast.LENGTH_SHORT).show();

            }
        });
//        topBar.setButtonVisible(0,false);//右边的button隐藏
//        topBar.setButtonVisible(1,false);//左边的隐藏
        myWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

    }
}
