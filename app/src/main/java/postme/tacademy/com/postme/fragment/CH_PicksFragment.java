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

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.adapter.CH_Pick_Rc_Adapter;
import postme.tacademy.com.postme.adapter.History_Rc_Adapter;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.data.HistoryList;
import postme.tacademy.com.postme.data.Jjim;
import postme.tacademy.com.postme.data.JjimList;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.HistoryRequest;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.PickRequest;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CH_PicksFragment extends Fragment {
    private CH_Pick_Rc_Adapter mAdapter;
    private ArrayList<Jjim> mItems;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_rc_cu, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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

        PickRequest request = new PickRequest(getContext(), 0, 10);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<JjimList>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<JjimList>> request, NetworkResult<JjimList> result) {
                Jjim[] Jjim = result.getResult().getJjim();
                mItems = new ArrayList<Jjim>(Jjim.length);
                for (int i=0; i < Jjim.length; i++)
                    mItems.add(Jjim[i]);

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


            }


            @Override
            public void onFail(NetworkRequest<NetworkResult<JjimList>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });


        return view; //완성된 VIEW return
    }
}