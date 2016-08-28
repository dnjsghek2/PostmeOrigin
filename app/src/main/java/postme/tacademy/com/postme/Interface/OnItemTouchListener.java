package postme.tacademy.com.postme.Interface;

import android.view.View;

/**
 * Created by wonhochoi on 2016. 8. 23..
 */
public interface OnItemTouchListener {
    /**
     * Callback invoked when the user Taps one of the RecyclerView items
     *
     * @param view     the CardView touched
     * @param position the index of the item touched in the RecyclerView
     */
    void onCardViewTap(View view, int position);

    /**
     * Callback invoked when the Button1 of an item is touched
     *
     * @param view     the Button touched
     * @param position the index of the item touched in the RecyclerView
     */
    void onButton1Click(View view, int position);

    /**
     * Callback invoked when the Button2 of an item is touched
     *
     * @param view     the Button touched
     * @param position the index of the item touched in the RecyclerView
     */
    void onButton2Click(View view, int position);
}

