package postme.tacademy.com.postme.indicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import postme.tacademy.com.postme.R;


public class SamplePagerAdapter extends PagerAdapter {

    //시작
    int index;
    //끝


    private final Random random = new Random();
    private int mSize;

    public SamplePagerAdapter() {
        mSize = 3;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    //fragment에 이미지추가
    int[] res = {
            R.drawable.eunhafirst,
            R.drawable.eunhasecond,
            R.drawable.eunhathird
    };

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        TextView textView = new TextView(view.getContext());
//        textView.setText(String.valueOf(position + 1));
//        textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.WHITE);
//        textView.setTextSize(48);
//        view.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        return textView;
//        위에는 반복적인 텍스트

//        ImageView imageView = new ImageView(view.getContext());
//        imageView.setImageResource(res[position]);
//        view.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//
//        return imageView;

//        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.layout_one, null);
//        container.addView(view);
//        inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.layout_two, null);
//        container.addView(view);
//        inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.layout_three, null);
//        container.addView(view);

        View view = container;
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (position == 0) {
            view = inflater.inflate(R.layout.layout_one, null);
            container.addView(view);
        }
        if (position == 1) {
            view = inflater.inflate(R.layout.layout_two, null);
            container.addView(view);
        }
        if (position == 2) {
            view = inflater.inflate(R.layout.activity_main, null);
            container.addView(view);
        }


        return view;
    }
}