package postme.tacademy.com.postme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.Post;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class Post_Rc_Adapter extends RecyclerView.Adapter<Post_Rc_Adapter.ViewHolder>{
    private List<Post> cards;
    private OnItemTouchListener onItemTouchListener;

    public Post_Rc_Adapter(List<Post> cards, OnItemTouchListener onItemTouchListener) {
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
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.nickname.setText(cards.get(i).getNickname());
        viewHolder.ctime.setText(cards.get(i).getCtime());
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private Button button2;
        private TextView nickname;
        private TextView  ctime;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.his_body);
            nickname = (TextView)itemView.findViewById(R.id.nickname);
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