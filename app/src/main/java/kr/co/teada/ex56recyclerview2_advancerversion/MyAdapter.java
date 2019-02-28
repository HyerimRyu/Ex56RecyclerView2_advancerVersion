package kr.co.teada.ex56recyclerview2_advancerversion;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
// * 상속 받을 때 RecyclerView 아니야! Adapter 야
public class MyAdapter extends RecyclerView.Adapter {

    //1. 대량의 데이터 참조변수 new 아냐!! 이미 있는애 데려오는거라
    ArrayList<Item> items;
    Context context;

    //2. 생성자 alt + insert


    public MyAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //3. recycler_item 을 View 객체로 만드는 작업
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item, viewGroup,false);

        //4. 뷰홀더(VH) 객체 생성(저~ 밑에 innerclass)
        VH holder=new VH(itemView);
        return holder;  //홀더 객체 리턴: 홀더가 갖고 있는거 리사이클러뷰에서 보여줄꺼야.
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        //6. 포지션 번째 데이터
        Item item =items.get(position);

        //5. 바인딩 작업 : 바인딩 안하면 똑같은 사진만 계속 반복_ 매개변수 뷰홀더, 포지션
        //도그인데 애니멀인지 알고 있어 너 도그 맞아!!! (VH)
        VH holder=(VH)viewHolder;
        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.msg);
        holder.civ.setImageResource(item.icon);
        holder.iv.setImageResource(item.img);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //inner class
    class VH extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMsg;
        CircleImageView civ;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            civ=itemView.findViewById(R.id.iv_icon);
            iv=itemView.findViewById(R.id.iv);

            //항목 클릭 리스너 추가
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();

                    //DetailActivity.java 사진 눌렀을 때 상세샷
                    //보낼 데이터
                    String name=items.get( getLayoutPosition() ).name;
                    int imgId=items.get( getLayoutPosition() ).img;

                    //디테일 액티비티
                    Intent intent=new Intent(context,DetailActivity.class);
                    //추가 데이터 전달 요청
                    intent.putExtra("Name", name);
                    intent.putExtra("Img", imgId);

                    //전환효과...롤리팝 이상에서만 가능
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

                        //context(Activity)로 형변환한거야
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context, new Pair<View, String>(civ, "IMG"));
                        context.startActivity(intent, options.toBundle());

                    }else{
                        context.startActivity(intent);
                    }

                }
            });
        }
    }

}
