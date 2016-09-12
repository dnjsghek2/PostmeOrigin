package postme.tacademy.com.postme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    public CH_Pick_Rc_Adapter(List<Jjim> cards, OnItemTouchListener onItemTouchListener) {
        this.cards = cards;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_post_card, viewGroup, false);
        return new CH_Pick_Rc_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CH_Pick_Rc_Adapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.nickname.setText(cards.get(i).getNickname());
        viewHolder.ctime.setText(cards.get(i).getCtime());

        if (cards.get(i).getImage() != null && !cards.get(i).getImage().equals("null")){
            ImageRequest imageRequest = new ImageRequest();
            viewHolder.serverimage.setImageBitmap(imageRequest.getBitmapImg(cards.get(i).getImage()));
        }
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView button2;
        private Button local_btn;
        private ImageView local_view;
        private TextView title;
        private TextView nickname;
        private TextView  ctime;
        private ImageView serverimage;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.his_body);
            nickname = (TextView)itemView.findViewById(R.id.his_id);
            ctime = (TextView)itemView.findViewById(R.id.his_ctime);
            button2 = (TextView) itemView.findViewById(R.id.card_view_button2);
            local_view = (ImageView) itemView.findViewById(R.id.local_view);
            serverimage = (ImageView)itemView.findViewById(R.id.server_image);
            local_btn = (Button) itemView.findViewById(R.id.local_btn);
            local_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    local_view.setVisibility(View.VISIBLE);
                    local_view.setVisibility(View.GONE);
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
