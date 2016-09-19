package postme.tacademy.com.postme.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.fragment.HistoryFragment;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.ImageRequest;
import postme.tacademy.com.postme.request.JjimRequest;
import postme.tacademy.com.postme.request.NetworkRequest;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class History_Rc_Adapter extends RecyclerView.Adapter<History_Rc_Adapter.ViewHolder> {
    private List<History> cards;
    private OnItemTouchListener onItemTouchListener;
    HistoryFragment historyFragment;
    Context context;
    HashMap<String, Bitmap> imageset = new HashMap<String, Bitmap>();
    public int TOTALPAGE = 0;
    public int CURRENTPAGE = 0;

    public History_Rc_Adapter(OnItemTouchListener onItemTouchListener, HistoryFragment historyFragment, Context context) {
        this.onItemTouchListener = onItemTouchListener;
        this.historyFragment = historyFragment;
        this.context = context;
        Log.d("확인", "History_Rc_Adapter");
    }

    public void setList(List<History> cards) {
        this.cards = cards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_post_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        if (i == cards.size() - 1) {
            if (CURRENTPAGE < TOTALPAGE + 1) {
                Log.d("page", "TOTAL : " + TOTALPAGE + " CURRUNT :  " + CURRENTPAGE);
                historyFragment.position = cards.size() - 1;
                historyFragment.onRequest();
            }
        }

        viewHolder.jjimcount.setText("" + cards.get(i).getJjimcount());
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.ctime.setText(cards.get(i).getCtime());


        switch (cards.get(i).getFeeling()) { //감정
            case "1":
                viewHolder.feeling.setImageResource(R.drawable.joy);
                break;
            case "2":
                viewHolder.feeling.setImageResource(R.drawable.anger);
                break;
            case "3":
                viewHolder.feeling.setImageResource(R.drawable.sorrow);
                break;
            case "4":
                viewHolder.feeling.setImageResource(R.drawable.pleasure);
                break;
        }

        switch (cards.get(i).getState()) { //상태
            case "1":
                viewHolder.state.setImageResource(R.drawable.spring_on);
                break;
            case "2":
                viewHolder.state.setImageResource(R.drawable.summer_on);
                break;
            case "3":
                viewHolder.state.setImageResource(R.drawable.autumn_on);
                break;
            case "4":
                viewHolder.state.setImageResource(R.drawable.winter_on);
                break;
            case "5":
                viewHolder.state.setImageResource(R.drawable.picture_of_good_on);
                break;
            case "6":
                viewHolder.state.setImageResource(R.drawable.meal_good_on);
                break;
            case "7":
                viewHolder.state.setImageResource(R.drawable.night_view_on);
                break;
            case "8":
                viewHolder.state.setImageResource(R.drawable.natural_on);
                break;
        }

        /*if (cards.get(i).getImage() != null && !cards.get(i).getImage().equals("null")) { //이미지
         */
        Glide.with(historyFragment.getActivity()).load(cards.get(i).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.serverimage);
        /*} else {
            viewHolder.serverimage.setVisibility(View.GONE);
        }*/

        Glide.with(historyFragment.getActivity()).load(cards.get(i).getMap()) //맵
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.localview);

        if (cards.get(i).getJjim() == 1) {           //찜
            viewHolder.button2.setBackgroundResource(R.drawable.jjim_click);
            viewHolder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostRequest(cards.get(i).getPost_id(), false);
                    /*int count = Integer.valueOf(cards.get(i).getJjimcount());
                    count = count - 1;
                    cards.get(i).setJjim(String.valueOf(count));*/
                }
            });
        } else {
            viewHolder.button2.setBackgroundResource(R.drawable.jjimbtn);
            viewHolder.button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostRequest(cards.get(i).getPost_id(), true);
/*
                    int count = Integer.valueOf(cards.get(i).getJjimcount());
                    count = count + 1;
                    cards.get(i).setJjim(String.valueOf(count));
*/
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView feeling;
        private ImageView state;
        private TextView title;
        private TextView button2;
        private TextView ctime;
        private ImageView serverimage;
        private ImageView localview;
        private Button locationbtn;
        private TextView jjimcount;

        public ViewHolder(View itemView) {
            super(itemView);
            feeling = (ImageView) itemView.findViewById(R.id.his_feeling);
            state = (ImageView) itemView.findViewById(R.id.his_state);
            title = (TextView) itemView.findViewById(R.id.his_body);
            ctime = (TextView) itemView.findViewById(R.id.his_ctime);
            button2 = (TextView) itemView.findViewById(R.id.card_view_button2);
            jjimcount = (TextView) itemView.findViewById(R.id.jjim_count);
            serverimage = (ImageView) itemView.findViewById(R.id.server_image);
            localview = (ImageView) itemView.findViewById(R.id.local_view);
            locationbtn = (Button) itemView.findViewById(R.id.local_btn);
            locationbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (localview.getVisibility() == View.VISIBLE) {
                        localview.setVisibility(View.GONE);
                        locationbtn.setBackgroundResource(R.drawable.location_bottom);
                    } else {
                        localview.setVisibility(View.VISIBLE);
                        locationbtn.setBackgroundResource(R.drawable.location_top);
                    }
                }
            });
        }
    }

    void PostRequest(String Post_id, boolean check) {
        JjimRequest request = new JjimRequest(context, Post_id, check);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<NetworkResultTemp>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<NetworkResultTemp>> request, NetworkResult<NetworkResultTemp> result) {
                Toast.makeText(context, "찜 완료", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<NetworkResultTemp>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(context, "찜 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

}