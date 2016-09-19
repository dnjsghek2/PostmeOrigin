package postme.tacademy.com.postme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.Jjim;
import postme.tacademy.com.postme.request.ImageRequest;


/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CH_Pick_Rc_Adapter extends RecyclerView.Adapter<CH_Pick_Rc_Adapter.ViewHolder> {
    private List<Jjim> cards;
    private OnItemTouchListener onItemTouchListener;
    private Context context;

    public CH_Pick_Rc_Adapter(Context context, List<Jjim> cards, OnItemTouchListener onItemTouchListener) {
        this.cards = cards;
        this.onItemTouchListener = onItemTouchListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_post_card, viewGroup, false);
        return new CH_Pick_Rc_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CH_Pick_Rc_Adapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.id.setText(cards.get(i).getNickname());
        viewHolder.ctime.setText(cards.get(i).getCtime());

        switch (cards.get(i).getFeeling()) {
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

        Glide.with(context).load(cards.get(i).getMap())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.localview);

        Glide.with(context).load(cards.get(i).getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.serverimage);
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
        private TextView id;
        private ImageView serverimage;
        private ImageView localview;
        private Button locationbtn;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.his_id);
            feeling = (ImageView) itemView.findViewById(R.id.his_feeling);
            state = (ImageView) itemView.findViewById(R.id.his_state);
            title = (TextView) itemView.findViewById(R.id.his_body);
            ctime = (TextView) itemView.findViewById(R.id.his_ctime);
            button2 = (TextView) itemView.findViewById(R.id.card_view_button2);
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

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onButton2Click(v, getLayoutPosition());
                }
            });
        }
    }
}