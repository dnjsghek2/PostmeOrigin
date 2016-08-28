package postme.tacademy.com.postme.indicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import postme.tacademy.com.postme.R;


/**
 * Created by Monkey on 2016. 8. 24..
 */
public class SampleListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return new RecyclerView(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SampleListAdapter adapter = new SampleListAdapter();

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.add(new SampleInfo("Custom Animation", CustomAnimationFragment.class.getName()));
    }

    private class SampleListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        private final List<SampleInfo> mList = new ArrayList<>();

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ItemViewHolder.create(parent);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, int position) {
            SampleInfo sample = mList.get(position);
            holder.bindView(sample.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToFragment(mList.get(holder.getAdapterPosition()).fragmentName);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public boolean add(SampleInfo object) {
            int lastIndex = mList.size();
            if (mList.add(object)) {
                notifyItemInserted(lastIndex);
                return true;
            } else {
                return false;
            }
        }
    }

    private void navigateToFragment(String fragmentName) {
        Fragment fragment = Fragment.instantiate(getContext(), fragmentName);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(String title) {
            ((TextView) itemView).setText(title);
        }

        public static ItemViewHolder create(ViewGroup viewGroup) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_view, viewGroup, false));
        }
    }
}