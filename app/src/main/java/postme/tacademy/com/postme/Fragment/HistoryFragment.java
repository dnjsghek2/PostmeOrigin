package postme.tacademy.com.postme.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import gpslocation.wonho.example.com.postmetest.Adapter.History_Rc_Adapter;
import gpslocation.wonho.example.com.postmetest.Interface.OnItemTouchListener;
import gpslocation.wonho.example.com.postmetest.R;

/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class HistoryFragment extends Fragment {
    private History_Rc_Adapter mAdapter;
    private ArrayList<String> mItems;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rc, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);

        mItems = new ArrayList<>(30);
        for (int i = 0; i < 30; i++) {
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
        mAdapter = new History_Rc_Adapter(mItems, itemTouchListener);

        recyclerView.setAdapter(mAdapter);

        return view; //완성된 VIEW return
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_history, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
