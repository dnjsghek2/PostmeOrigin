package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.adapter.Post_Rc_Adapter;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.Post;
import postme.tacademy.com.postme.data.PostList;
import postme.tacademy.com.postme.data.Statistics;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.PostlistRequest;
import postme.tacademy.com.postme.request.StatisticsRequest;

/**
 * Created by wonhochoi on 2016. 8. 30..
 */
public class PostlistActivity extends AppCompatActivity {
    private Post_Rc_Adapter mAdapter;
    private ArrayList<Post> mItems;
    static int CURRENTPAGE = 0;
    static int ITEMPERPAGE = 10;
    Intent intent;
    int Cok_id = -1;
    String Cok_name;
    String TAG_D = "PostlistActivity";
    Post[] posts;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ImageView imageView;
    RelativeLayout stasticslayout;
    TextView age10_num, age20_num, age30_num, age40_num, age50_num, man_stats_num, woman_stats_num;
    ImageView age10_img, age20_img, age30_img, age40_img, age50_img, man_stats, woman_stats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_postlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.postlist_toolbar);
        intent = getIntent();
        Cok_id = intent.getIntExtra("COK_INFO", -1);
        Cok_name = intent.getStringExtra("COK_NAME");
        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(Cok_name);
        PostRequest();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        age10_img = (ImageView) findViewById(R.id.age10_status_img);
        age20_img = (ImageView) findViewById(R.id.age20_status_img);
        age30_img = (ImageView) findViewById(R.id.age30_status_img);
        age40_img = (ImageView) findViewById(R.id.age40_status_img);
        age50_img = (ImageView) findViewById(R.id.age50_status_img);
        man_stats = (ImageView) findViewById(R.id.man_stats);
        woman_stats = (ImageView) findViewById(R.id.woman_stats);

        age10_num = (TextView)findViewById(R.id.age10_num);
        age20_num = (TextView)findViewById(R.id.age20_num);
        age30_num = (TextView)findViewById(R.id.age30_num);
        age40_num = (TextView)findViewById(R.id.age40_num);
        age50_num = (TextView)findViewById(R.id.age50_num);
        man_stats_num = (TextView)findViewById(R.id.man_stats_num);
        woman_stats_num = (TextView)findViewById(R.id.woman_stats_num);

        imageView = (ImageView) findViewById(R.id.a_postlist_arrow);
        stasticslayout = (RelativeLayout) findViewById(R.id.a_postlist_stastics);
        relativeLayout = (RelativeLayout) findViewById(R.id.a_postlist_rl);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stasticslayout.getVisibility() == View.GONE) {
                    imageView.setImageResource(R.drawable.arrowhaciabajo);
                    stasticslayout.setVisibility(View.VISIBLE);
                    Statisticss();
                } else {
                    imageView.setImageResource(R.drawable.arrowhaciaarriba);
                    stasticslayout.setVisibility(View.GONE);
                }
            }
        });
    }

    void PostRequest() {
        PostlistRequest request = new PostlistRequest(PostlistActivity.this, Cok_id, CURRENTPAGE, ITEMPERPAGE);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<PostList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<PostList>> request, NetworkResult<PostList> result) {

                recyclerView.setLayoutManager(new LinearLayoutManager(PostlistActivity.this));
                posts = result.getResult().getPost();
                mItems = new ArrayList<Post>(posts.length);
                for (Post post : posts) {
                    mItems.add(post);
                }
                OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
                    @Override
                    public void onButton2Click(View view, int position) {
                    }
                };
                mAdapter = new Post_Rc_Adapter(mItems, itemTouchListener, PostlistActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PostList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    void Statisticss() {
        StatisticsRequest request = new StatisticsRequest(this, Cok_id);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Statistics>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Statistics>> request, NetworkResult<Statistics> result) {
                int age10 = Integer.valueOf(result.getResult().getAge().getAges_10());
                int age20 = Integer.valueOf(result.getResult().getAge().getAges_20());
                int age30 = Integer.valueOf(result.getResult().getAge().getAges_30());
                int age40 = Integer.valueOf(result.getResult().getAge().getAges_40());
                int age50 = Integer.valueOf(result.getResult().getAge().getAges_50());




                stastics(age10, age20, age30, age40, age50, 1, 1);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Statistics>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(PostlistActivity.this, "error : " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("TAG_E", errorMessage);
            }
        });
    }

    void stastics(int age10, int age20, int age30, int age40, int age50, int man, int woman) {

        age10_num.setText(""+age10+"%");
        age20_num.setText(""+age20+"%");
        age30_num.setText(""+age30+"%");
        age40_num.setText(""+age40+"%");
        age50_num.setText(""+age50+"%");
        man_stats_num.setText(""+man+"%");
        woman_stats_num.setText(""+woman+"%");

        switch (age10 / 10) {
            case 0: break;
            case 1: age10_img.setImageResource(R.drawable.age_10_10); break;
            case 2: age10_img.setImageResource(R.drawable.age_10_20); break;
            case 3: age10_img.setImageResource(R.drawable.age_10_30); break;
            case 4: age10_img.setImageResource(R.drawable.age_10_40); break;
            case 5: age10_img.setImageResource(R.drawable.age_10_50); break;
            case 6: age10_img.setImageResource(R.drawable.age_10_60); break;
            case 7: age10_img.setImageResource(R.drawable.age_10_70); break;
            case 8: age10_img.setImageResource(R.drawable.age_10_80); break;
            case 9: age10_img.setImageResource(R.drawable.age_10_90); break;
            case 10: age10_img.setImageResource(R.drawable.age_10_100); break;
        }

        switch (age20 / 10) {
            case 0: break;
            case 1: age20_img.setImageResource(R.drawable.age_20_10); break;
            case 2: age20_img.setImageResource(R.drawable.age_20_20); break;
            case 3: age20_img.setImageResource(R.drawable.age_20_30); break;
            case 4: age20_img.setImageResource(R.drawable.age_20_40); break;
            case 5: age20_img.setImageResource(R.drawable.age_20_50); break;
            case 6: age20_img.setImageResource(R.drawable.age_20_60); break;
            case 7: age20_img.setImageResource(R.drawable.age_20_70); break;
            case 8: age20_img.setImageResource(R.drawable.age_20_80); break;
            case 9: age20_img.setImageResource(R.drawable.age_20_90); break;
            case 10: age20_img.setImageResource(R.drawable.age_20_100); break;
        }

        switch (age30 / 10) {
            case 0: break;
            case 1: age30_img.setImageResource(R.drawable.age_30_10); break;
            case 2: age30_img.setImageResource(R.drawable.age_30_20); break;
            case 3: age30_img.setImageResource(R.drawable.age_30_30); break;
            case 4: age30_img.setImageResource(R.drawable.age_30_40); break;
            case 5: age30_img.setImageResource(R.drawable.age_30_50); break;
            case 6: age30_img.setImageResource(R.drawable.age_30_60); break;
            case 7: age30_img.setImageResource(R.drawable.age_30_70); break;
            case 8: age30_img.setImageResource(R.drawable.age_30_80); break;
            case 9: age30_img.setImageResource(R.drawable.age_30_90); break;
            case 10: age30_img.setImageResource(R.drawable.age_30_100); break;
        }

        switch (age40 / 10) {
            case 0: break;
            case 1: age40_img.setImageResource(R.drawable.age_40_10); break;
            case 2: age40_img.setImageResource(R.drawable.age_40_20); break;
            case 3: age40_img.setImageResource(R.drawable.age_40_30); break;
            case 4: age40_img.setImageResource(R.drawable.age_40_40); break;
            case 5: age40_img.setImageResource(R.drawable.age_40_50); break;
            case 6: age40_img.setImageResource(R.drawable.age_40_60); break;
            case 7: age40_img.setImageResource(R.drawable.age_40_70); break;
            case 8: age40_img.setImageResource(R.drawable.age_40_80); break;
            case 9: age40_img.setImageResource(R.drawable.age_40_90); break;
            case 10: age40_img.setImageResource(R.drawable.age_40_100); break;
        }

        switch (age50 / 10) {
            case 0: break;
            case 1: age50_img.setImageResource(R.drawable.age_50_10); break;
            case 2: age50_img.setImageResource(R.drawable.age_50_20); break;
            case 3: age50_img.setImageResource(R.drawable.age_50_30); break;
            case 4: age50_img.setImageResource(R.drawable.age_50_40); break;
            case 5: age50_img.setImageResource(R.drawable.age_50_50); break;
            case 6: age50_img.setImageResource(R.drawable.age_50_60); break;
            case 7: age50_img.setImageResource(R.drawable.age_50_70); break;
            case 8: age50_img.setImageResource(R.drawable.age_50_80); break;
            case 9: age50_img.setImageResource(R.drawable.age_50_90); break;
            case 10: age50_img.setImageResource(R.drawable.age_50_100); break;
        }

/*        switch (man / 10){
            case 0: break;
            case 1: man_stats.setImageResource(R.drawable.graph_man_10);
            case 2: man_stats.setImageResource(R.drawable.graph_man_20);
            case 3: man_stats.setImageResource(R.drawable.graph_man_30);
            case 4: man_stats.setImageResource(R.drawable.graph_man_40);
            case 5: man_stats.setImageResource(R.drawable.graph_man_50);
            case 6: man_stats.setImageResource(R.drawable.graph_man_60);
            case 7: man_stats.setImageResource(R.drawable.graph_man_70);
            case 8: man_stats.setImageResource(R.drawable.graph_man_80);
            case 9: man_stats.setImageResource(R.drawable.graph_man_90);
            case 10: man_stats.setImageResource(R.drawable.graph_man_100);
        }

        switch (woman / 10){
            case 0: break;
            case 1: woman_stats.setImageResource(R.drawable.graph_woman_10);
            case 2: woman_stats.setImageResource(R.drawable.graph_woman_20);
            case 3: woman_stats.setImageResource(R.drawable.graph_woman_30);
            case 4: woman_stats.setImageResource(R.drawable.graph_woman_40);
            case 5: woman_stats.setImageResource(R.drawable.graph_woman_50);
            case 6: woman_stats.setImageResource(R.drawable.graph_woman_60);
            case 7: woman_stats.setImageResource(R.drawable.graph_woman_70);
            case 8: woman_stats.setImageResource(R.drawable.graph_woman_80);
            case 9: woman_stats.setImageResource(R.drawable.graph_woman_90);
            case 10: woman_stats.setImageResource(R.drawable.graph_woman_100);
        }*/
    }
}