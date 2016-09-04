package postme.tacademy.com.postme.fragment;

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

import postme.tacademy.com.postme.PostMeApplication;
import postme.tacademy.com.postme.Adapter.CH_Push_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.database.DBHelper;
import postme.tacademy.com.postme.database.DBinfo;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CH_PushFragment extends Fragment {
    private CH_Push_Rc_Adapter mAdapter;
    /*private ArrayList<String> mItems;*/
    private ArrayList<DBinfo> mItems;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rc_cu, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final DBHelper dbHelper = new DBHelper(PostMeApplication.getContext(),"POSTMEPUSH.db", null, 1);

        //********************실행시마다 5개씩 생성***********************
//        dbHelper.forinsert();
        /*mItems = new ArrayList<>();*/
        mItems = dbHelper.getResult();

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
                mItems.remove(position);
                mAdapter.notifyItemRemoved(position);
                Toast.makeText(getContext(), "삭제" + mItems.get(position), Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
            }
        };
        mAdapter = new CH_Push_Rc_Adapter(mItems, itemTouchListener);
        recyclerView.setAdapter(mAdapter);
        return view; //완성된 VIEW return
    }
}