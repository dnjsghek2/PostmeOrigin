package postme.tacademy.com.postme.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import postme.tacademy.com.postme.adapter.History_Rc_Adapter;
import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.HistoryRequest;
import postme.tacademy.com.postme.request.NetworkRequest;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class HistoryFragment extends Fragment {
    private History_Rc_Adapter mAdapter;
    private ArrayList<History> mItems;
//    Toolbar toolbar;
    ImageView imageView;
    EditText edittext01;
    EditText edittext02;

    RelativeLayout relativeLayout_toolbar;
    RelativeLayout relativeLayout_radio;
    RecyclerView recyclerView;
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




        imageView = (ImageView) view.findViewById(R.id.history_toolbar);
        edittext01 = (EditText) view.findViewById(R.id.his_toolbar_edit01);
        edittext02 = (EditText) view.findViewById(R.id.his_toolbar_edit02);

        relativeLayout_toolbar = (RelativeLayout)view.findViewById(R.id.his_toolbar);
        relativeLayout_radio = (RelativeLayout)view.findViewById(R.id.his_radio);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setHasOptionsMenu(true);


        HistoryRequest request = new HistoryRequest(getContext(), 0, 10);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<HistoryList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<HistoryList>> request, NetworkResult<HistoryList> result) {
                History[] history = result.getResult().getHistory();
                mItems = new ArrayList<History>(history.length);
                for (int i=0; i < history.length; i++)
                mItems.add(history[i]);

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


            }


            @Override
            public void onFail(NetworkRequest<NetworkResult<HistoryList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });

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
                if (relativeLayout_toolbar.getVisibility() == View.GONE){
                    item.setIcon(R.drawable.arrowhaciabajo);
                    relativeLayout_toolbar.setVisibility(View.VISIBLE);
                    relativeLayout_radio.setVisibility(View.GONE);
                }else{
                    item.setIcon(R.drawable.test);
                    relativeLayout_toolbar.setVisibility(View.GONE);
                    relativeLayout_radio.setVisibility(View.VISIBLE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

