package postme.tacademy.com.postme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.History;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class History_Rc_Adapter extends RecyclerView.Adapter<History_Rc_Adapter.ViewHolder>{
    private List<History> cards;
    private OnItemTouchListener onItemTouchListener;

    public History_Rc_Adapter(List<History> cards, OnItemTouchListener onItemTouchListener) {
        this.cards = cards;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_post_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        switch (cards.get(i).getFeeling()){
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

        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.ctime.setText(cards.get(i).getCtime());
        viewHolder.button2.setText(cards.get(i).getJjimcount());
        viewHolder.nickname.setText(cards.get(i).getUser_id());
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView feeling;
        private ImageView state;
        private TextView title;
        private Button button2;
        private TextView nickname;
        private TextView  ctime;
        public ViewHolder(View itemView) {
            super(itemView);
            feeling = (ImageView) itemView.findViewById(R.id.his_feeling);
            nickname = (TextView) itemView.findViewById(R.id.his_id);
            title = (TextView) itemView.findViewById(R.id.his_body);
            ctime = (TextView)itemView.findViewById(R.id.his_ctime);
            button2 = (Button) itemView.findViewById(R.id.card_view_button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onButton2Click(v, getLayoutPosition());
                }
            });
        }
    }
}