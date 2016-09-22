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

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.adapter.History_Rc_Adapter;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.Message;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.AbstractRequest;
import postme.tacademy.com.postme.request.HistoryImageRequest;
import postme.tacademy.com.postme.request.HistoryRequest;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.PostdeleteRequest;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class HistoryFragment extends Fragment {

    private int ITEMPERPAGE = 10;
    private int CURRENTPAGE = 0;
    private int TOTALPAGE = 0;
    private int CURRENTPAGEIMG = 0;

    History_Rc_Adapter mAdapter;
    ArrayList<History> mItems;
    ArrayList<History> imagemItems;
    ArrayList<History> items;

    //    Toolbar toolbar;
    ImageView imageView;
    EditText edittext01;
    EditText edittext02;
    public int position = 0;
    public boolean IMAGECHECK = false;
    RelativeLayout relativeLayout_toolbar;
    RelativeLayout relativeLayout_radio;
    RecyclerView recyclerView;
    RadioGroup his_radioGroup;
    RadioButton his_radiobutton01;
    RadioButton his_radiobutton02;
    SwipeableRecyclerViewTouchListener swipeTouchListener;

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
    }

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
        his_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.history_radio01 : onRequest(); break;
                    case R.id.history_radio02 : onRequest02(); break;
                }

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
                IMAGECHECK = false;
                History[] history = result.getResult().getHistory();

                mAdapter.CURRENTPAGE = CURRENTPAGE = +1;
                mAdapter.TOTALPAGE = TOTALPAGE = result.getResult().getTotalPage();
                postdelete(history, true);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<HistoryList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
        recyclerView.getLayoutManager().scrollToPosition(position);
    }

    public void onRequest02() {
        HistoryImageRequest request = new HistoryImageRequest(getContext(), CURRENTPAGEIMG, ITEMPERPAGE);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<HistoryList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<HistoryList>> request, NetworkResult<HistoryList> result) {
                IMAGECHECK = true;
                History[] history = result.getResult().getHistory();

                mAdapter.CURRENTPAGE = CURRENTPAGEIMG = +1;
                mAdapter.TOTALPAGE = TOTALPAGE = result.getResult().getTotalPage();
                postdelete(history, false);
                Log.d("확인", "onRequest02");
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<HistoryList>> request, int errorCode, String errorMessage, Throwable e) {

            }

        });
        recyclerView.getLayoutManager().scrollToPosition(position);
    }

    public void postdelete(History[] mItems, boolean check) {
        items = addList(mItems, check);
        mAdapter.setList(items);
        recyclerView.setAdapter(mAdapter);
        swipeTouchListener = null;
        swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return false;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    postdeleteRequset(items.get(position).getPost_id(), reverseSortedPositions);
                                    Log.d("스와이프", "실행");
                                }
                            }
                        });
        recyclerView.addOnItemTouchListener(swipeTouchListener);
    }

    ArrayList<History> addList(History[] history, boolean check) {
        ArrayList<History> items;
        if (check) {
            if (mItems == null) {
                mItems = new ArrayList<History>();
            }

            for (int i = 0; i < history.length; i++) {
                mItems.add(history[i]);
            }
            items = mItems;
        } else {
            if (imagemItems == null) {
                imagemItems = new ArrayList<History>();
            }
            for (int i = 0; i < history.length; i++) {
                imagemItems.add(history[i]);
            }
            items = imagemItems;
        }
        return items;
    }

    void postdeleteRequset(String postid, final int[] reverseSortedPositions) {
        Log.d("postdeleteRequset", "살행");
        PostdeleteRequest request = new PostdeleteRequest(getContext(), postid);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<Message>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Message>> request, NetworkResult<Message> result) {
                for (int position : reverseSortedPositions) {
                    items.remove(position);
                    mAdapter.notifyItemRemoved(position);
                    mAdapter.setList(items);
                    recyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Message>> request, int errorCode, String errorMessage, Throwable e) {

            }

        });
    }
}
