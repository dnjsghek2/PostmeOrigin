package postme.tacademy.com.postme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import postme.tacademy.com.postme.Interface.OnItemTouchListener;
import postme.tacademy.com.postme.R;

/**
 * Created by wonhochoi on 2016. 8. 24..
 */
public class CH_Push_Rc_Adapter extends RecyclerView.Adapter<CH_Push_Rc_Adapter.ViewHolder> {
    private List<String> cards;
    private OnItemTouchListener onItemTouchListener;

    public CH_Push_Rc_Adapter(List<String> cards, OnItemTouchListener onItemTouchListener) {
        this.cards = cards;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v_push_card, viewGroup, false);
        return new CH_Push_Rc_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CH_Push_Rc_Adapter.ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return cards == null ? 0 : cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button Pushdelete;

        public ViewHolder(View itemView) {
            super(itemView);
            Pushdelete = (Button) itemView.findViewById(R.id.Push_delete);

            Pushdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onButton2Click(v, getLayoutPosition());
                }
            });
        }
    }
}