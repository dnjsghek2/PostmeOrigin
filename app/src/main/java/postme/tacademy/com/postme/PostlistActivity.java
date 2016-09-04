package postme.tacademy.com.postme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import postme.tacademy.com.postme.adapter.History_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.data.History;

/**
 * Created by wonhochoi on 2016. 8. 30..
 */
public class PostlistActivity extends AppCompatActivity {
    private History_Rc_Adapter mAdapter;
    private ArrayList<History> mItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_postlist);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        mAdapter = new History_Rc_Adapter(mItems, itemTouchListener);

        recyclerView.setAdapter(mAdapter);
    }
}
