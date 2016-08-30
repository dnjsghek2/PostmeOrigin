package postme.tacademy.com.postme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import postme.tacademy.com.postme.Adapter.Post_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;

/**
 * Created by wonhochoi on 2016. 8. 30..
 */
public class PostlistActivity extends AppCompatActivity {
    private Post_Rc_Adapter mAdapter;
    private ArrayList<String> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_postlist);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mItems = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            mItems.add(String.format("Card number %02d", i));
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
}
