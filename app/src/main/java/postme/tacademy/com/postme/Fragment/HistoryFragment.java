package postme.tacademy.com.postme.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import postme.tacademy.com.postme.adapter.Post_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class HistoryFragment extends Fragment {
    private Post_Rc_Adapter mAdapter;
    private ArrayList<String> mItems;
    Toolbar toolbar;

    public HistoryFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.history_toolbar) {
//            v.setVisibility(View.VISIBLE);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rc, container, false);

//        //Search
//        Button btn_history = (Button) view.findViewById(R.id.miCompose);
//        btn_history.setOnClickListener(this);

        toolbar = (Toolbar) view.findViewById(R.id.history_toolbar);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);

        mItems = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            mItems.add(String.format("Card number %02d", i));
        }
        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                Toast.makeText(getContext(), "Tapped " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButton1Click(View view, int position) {
                Toast.makeText(getContext(), "Clicked Button1 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onButton2Click(View view, int position) {
                Toast.makeText(getContext(), "Clicked Button2 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        };
        mAdapter = new Post_Rc_Adapter(mItems, itemTouchListener);

        recyclerView.setAdapter(mAdapter);

        return view; //완성된 VIEW return
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_history, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCompose:
                if (toolbar.getVisibility() == View.GONE){
                toolbar.setVisibility(View.VISIBLE);
                }else{
                    toolbar.setVisibility(View.GONE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

