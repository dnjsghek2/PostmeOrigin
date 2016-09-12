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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.History;
import postme.tacademy.com.postme.fragment.HistoryFragment;
import postme.tacademy.com.postme.request.ImageRequest;


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
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if (i == cards.size() - 1) {
            if (CURRENTPAGE < TOTALPAGE+1) {
                Log.d("page", "TOTAL : " + TOTALPAGE + " CURRUNT :  " + CURRENTPAGE);
                historyFragment.position = cards.size() - 1;
                historyFragment.onRequest();
            }
        }
        switch (cards.get(i).getFeeling()) {
            case "1":
                viewHolder.feeling.setImageResource(R.drawable.radio);
                break;
            case "2":
                viewHolder.feeling.setImageResource(R.drawable.radio1);
                break;
            case "3":
                viewHolder.feeling.setImageResource(R.drawable.radio2);
                break;
            case "4":
                viewHolder.feeling.setImageResource(R.drawable.radio3);
                break;
        }

        switch (cards.get(i).getState()) {
            case "2":
                viewHolder.state.setImageResource(R.drawable.womanon);
        }

        if (cards.get(i).getImage() != null && !cards.get(i).getImage().equals("null")) {
            if (imageset.get(cards.get(i).getPost_id()) == null) {
                Glide.with(historyFragment.getActivity()).load(cards.get(i).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.serverimage);
            }
        }
        Glide.with(historyFragment.getActivity()).load(cards.get(i).getMap())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.localview);
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.ctime.setText(cards.get(i).getCtime());
        viewHolder.button2.setText(cards.get(i).getJjimcount());
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
        public ViewHolder(View itemView) {
            super(itemView);
            feeling = (ImageView) itemView.findViewById(R.id.his_feeling);
            title = (TextView) itemView.findViewById(R.id.his_body);
            ctime = (TextView) itemView.findViewById(R.id.his_ctime);
            button2 = (TextView) itemView.findViewById(R.id.card_view_button2);
            serverimage = (ImageView) itemView.findViewById(R.id.server_image);
            localview = (ImageView)itemView.findViewById(R.id.local_view);
            locationbtn = (Button)itemView.findViewById(R.id.local_btn);
            locationbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (localview.getVisibility() == View.VISIBLE){
                        localview.setVisibility(View.GONE);
                        locationbtn.setBackgroundResource(R.drawable.location_bottom);
                    }else{
                        localview.setVisibility(View.VISIBLE);
                        locationbtn.setBackgroundResource(R.drawable.location_top);
                    }
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onButton2Click(v, getLayoutPosition());
                }
            });
        }
    }
}