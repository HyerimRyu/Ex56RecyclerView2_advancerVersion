package kr.co.teada.ex56recyclerview2_advancerversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //1. 대량의 데이터 참조변수
    ArrayList<Item> items=new ArrayList<>();

    RecyclerView recyclerView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. 데이터 추가작업
        items.add(new Item("루피", "해적단 선장", R.drawable.ch_luffy, R.drawable.img01));
        items.add(new Item("조로", "해적단 검사", R.drawable.ch_zoro, R.drawable.img02));
        items.add(new Item("나미", "해적단 항해사", R.drawable.ch_nami, R.drawable.img03));
        items.add(new Item("우솝", "해적단 저격수", R.drawable.ch_usoup, R.drawable.img04));
        items.add(new Item("상디", "해적단 요리사", R.drawable.ch_sandi, R.drawable.img06));
        items.add(new Item("쵸파", "해적단 의사", R.drawable.ch_chopa, R.drawable.img07));


        //3. 리사이클러뷰에 아답터 연결
        recyclerView=findViewById(R.id.recycler);
        adapter=new MyAdapter(items,this);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰의 아이템클릭리스너 없어!!!
        //항목 하나 ItemView 에게 직접 클릭리스터를 추가해야해
        //이 작업은 MyAdapter.java 에서 할 수 밖에 없어

        //리사이클러 및 아이템뷰 꾸미기_Customizing
        MyDecoration myDecoration=new MyDecoration();
        recyclerView.addItemDecoration(myDecoration);


    }

    // 원래는 핸드폰 갤러리에서 사진 선택
    public void clickAdd(View view) {
        items.add(0, new Item("New", "message", R.drawable.img01, R.drawable.img10));
        //어댑터에게 데이터가 갱신되었다고 공지해야만 화면 변경돼
        adapter.notifyItemInserted(0);
        //리사이클러뷰의 커서 위치 변경
        recyclerView.scrollToPosition(0);
    }

    public void clickDelete(View view) {
        if(items.size()==0)return;

        items.remove(0);
        adapter.notifyItemRemoved(0);
    }

    public void clickGrid(View view) {
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clickLinear(View view) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }
}
