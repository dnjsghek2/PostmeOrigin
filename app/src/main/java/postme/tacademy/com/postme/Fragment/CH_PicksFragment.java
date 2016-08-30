package postme.tacademy.com.postme.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import postme.tacademy.com.postme.Adapter.CH_Pick_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CH_PicksFragment extends Fragment {
    private CH_Pick_Rc_Adapter mAdapter;
    private ArrayList<String> mItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rc, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        mAdapter = new CH_Pick_Rc_Adapter(mItems, itemTouchListener);

        recyclerView.setAdapter(mAdapter);

        return view; //완성된 VIEW return
    }
}