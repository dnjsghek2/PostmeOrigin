package postme.tacademy.com.postme.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;
import postme.tacademy.com.postme.data.NetworkResult;
import postme.tacademy.com.postme.data.NetworkResultTemp;
import postme.tacademy.com.postme.data.Post;
import postme.tacademy.com.postme.data.PostList;
import postme.tacademy.com.postme.manager.NetworkManager;
import postme.tacademy.com.postme.request.JjimRequest;
import postme.tacademy.com.postme.request.NetworkRequest;
import postme.tacademy.com.postme.request.PostlistRequest;


/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public class Post_Rc_Adapter extends RecyclerView.Adapter<Post_Rc_Adapter.ViewHolder> {
    private List<Post> cards;
    private OnItemTouchListener onItemTouchListener;
    Context context;

    public Post_Rc_Adapter(List<Post> cards, OnItemTouchListener onItemTouchListener, Context context) {
        this.cards = cards;
        this.onItemTouchListener = onItemTouchListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_post_card, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.nickname.setText(cards.get(i).getNick());
        viewHolder.ctime.setText(cards.get(i).getCtime());

        if (cards.get(i).getImage() != null && !cards.get(i).getImage().equals("null")) {
                Glide.with(context).load(cards.get(i).getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(viewHolder.serverimage);
        }
        Glide.with(context).load(cards.get(i).getMap())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.localview);
        viewHolder.title.setText(cards.get(i).getBody());
        viewHolder.ctime.setText(cards.get(i).getCtime());

        viewHolder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostRequest(cards.get(i).getPost_id());
            }
        });
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
        private TextView nickname;
        private TextView ctime;
        private ImageView serverimage;
        private ImageView localview;
        private Button locationbtn;

        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.his_id);
            feeling = (ImageView) itemView.findViewById(R.id.his_feeling);
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

    void PostRequest(String Post_id) {
        JjimRequest request = new JjimRequest(context, Post_id);
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