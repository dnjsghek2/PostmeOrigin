package postme.tacademy.com.postme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.Post;
import postme.tacademy.com.postme.data.PostList;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.adapter.Post_Rc_Adapter;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.PostlistRequest;

/**
 * Created by wonhochoi on 2016. 8. 30..
 */
public class PostlistActivity extends AppCompatActivity {
    private Post_Rc_Adapter mAdapter;
    private ArrayList<Post> mItems;
    static int CURRENTPAGE = 1;
    static int ITEMPERPAGE = 10;
    Intent intent;
    int Cok_id = -1;
    String TAG_D = "PostlistActivity";
    Post[] posts;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_postlist);
        intent = getIntent();
        Cok_id = intent.getIntExtra("COK_INFO", -1);
        Log.d(TAG_D, "" + Cok_id);
        PostRequest();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
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
                    public void onCardViewTap(View view, int position) {
                    }

                    @Override
                    public void onButton1Click(View view, int position) {
                    }

                    @Override
                    public void onButton2Click(View view, int position) {
                    }
                };
                mAdapter = new Post_Rc_Adapter(mItems, itemTouchListener);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<PostList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

}