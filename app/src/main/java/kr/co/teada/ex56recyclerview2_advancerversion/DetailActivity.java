package kr.co.teada.ex56recyclerview2_advancerversion;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv=findViewById(R.id.iv);

        //클릭했을 때 디테일샷 MyAdapter 에서 작업

        //인텐트 객체가 전달되어 왔어
        //이 객체가 데이터 갖고 있어
        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");
        int imgId=intent.getIntExtra("Img",-1);

        if(imgId!= -1){ //안 왔을 수도 있으니까
            iv.setImageResource(imgId);
            getSupportActionBar().setTitle(name);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // 이미지뷰에 Transition 의 Pair 를 위한 이름 부여 : 너가 쟤랑 연결되는 얘다
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                iv.setTransitionName("IMG");
            }
        }
    }
}
