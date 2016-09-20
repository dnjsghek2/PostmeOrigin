package postme.tacademy.com.postme.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.adapter.History_Rc_Adapter;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.AbstractRequest;
import postme.tacademy.com.postme.request.HistoryRequest;
import postme.tacademy.com.postme.request.NetworkRequest;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class HistoryFragment extends Fragment {

    private int ITEMPERPAGE = 10;
    private int CURRENTPAGE = 0;
    private int TOTALPAGE = 0;

    History_Rc_Adapter mAdapter;
    ArrayList<History> mItems;
    ArrayList<History> imagemItems;
    //    Toolbar toolbar;
    ImageView imageView;
    EditText edittext01;
    EditText edittext02;
    public int position = 0;
    RelativeLayout relativeLayout_toolbar;
    RelativeLayout relativeLayout_radio;
    RecyclerView recyclerView;
    RadioGroup his_radioGroup;
    RadioButton his_radiobutton01;
    RadioButton his_radiobutton02;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onButton2Click(View view, int position) {
                Toast.makeText(getContext(), "Clicked Button2 in " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        };
        Log.d("확인", "onCreate");
        mAdapter = new History_Rc_Adapter(itemTouchListener, HistoryFragment.this, getContext());
//        onRequest();

    }

    //    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.history_toolbar) {
//            v.setVisibility(View.VISIBLE);
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
    }

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

        relativeLayout_toolbar = (RelativeLayout) view.findViewById(R.id.his_toolbar);
        relativeLayout_radio = (RelativeLayout) view.findViewById(R.id.his_radio);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setHasOptionsMenu(true);
        recyclerView.setAdapter(mAdapter);

        his_radioGroup = (RadioGroup) view.findViewById(R.id.history_radiogroup);
        his_radiobutton01 = (RadioButton) view.findViewById(R.id.history_radio01);
        his_radiobutton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRequest();
            }
        });
        his_radiobutton02 = (RadioButton) view.findViewById(R.id.history_radio02);
        his_radiobutton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRequest02();
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
                if (relativeLayout_toolbar.getVisibility() == View.GONE) {
                    item.setIcon(R.drawable.arrowhaciabajo);
                    relativeLayout_toolbar.setVisibility(View.VISIBLE);
                    relativeLayout_radio.setVisibility(View.GONE);
                } else {
                    item.setIcon(R.drawable.search_icon);
                    relativeLayout_toolbar.setVisibility(View.GONE);
                    relativeLayout_radio.setVisibility(View.VISIBLE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRequest() {
        HistoryRequest request = new HistoryRequest(getContext(), CURRENTPAGE, ITEMPERPAGE);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<HistoryList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<HistoryList>> request, NetworkResult<HistoryList> result) {
                History[] history = result.getResult().getHistory();
                if (mItems == null) {
                    mItems = new ArrayList<History>();
                }

                for (int i = 0; i < history.length; i++) {
                    mItems.add(history[i]);
                }

                mAdapter.CURRENTPAGE = CURRENTPAGE = result.getResult().getCurrentPage() + 1;
                mAdapter.TOTALPAGE = TOTALPAGE = result.getResult().getTotalPage();
                mAdapter.setList(mItems);
                recyclerView.setAdapter(mAdapter);
                recyclerView.getLayoutManager().scrollToPosition(position);
                onResume();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<HistoryList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    public void onRequest02() {

        HistoryRequest request = new HistoryRequest(getContext(), CURRENTPAGE, ITEMPERPAGE);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<HistoryList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<HistoryList>> request, NetworkResult<HistoryList> result) {
                History[] history = result.getResult().getHistory();

                if (imagemItems == null) {
                    imagemItems = new ArrayList<History>();
                }

                for (int i = 0; i < history.length; i++) {
                    imagemItems.add(history[i]);
                }
                int imageposition = 0;
                for (int i = 0; i < imagemItems.size(); i++) {
                    if (imagemItems.get(imageposition).getImage() == null) {
                        imagemItems.remove(imageposition);
                        imageposition-=1;
                    }
                    imageposition+=1;
                }

                mAdapter.CURRENTPAGE = CURRENTPAGE = result.getResult().getCurrentPage() + 1;
                mAdapter.TOTALPAGE = TOTALPAGE = result.getResult().getTotalPage();
                mAdapter.setList(imagemItems);
                recyclerView.setAdapter(mAdapter);
                Log.d("확인", "onRequest02");
                recyclerView.getLayoutManager().scrollToPosition(position);
                onResume();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<HistoryList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }
}