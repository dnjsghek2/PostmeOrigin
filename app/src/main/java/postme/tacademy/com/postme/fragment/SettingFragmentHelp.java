package postme.tacademy.com.postme.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import postme.tacademy.com.postme.R;

/**
 * Created by Monkey on 2016. 9. 2..
 */
public class SettingFragmentHelp extends Activity {

    private ArrayList<String> mGroupList = null;
    private ArrayList<ArrayList<String>> mChildList = null;
    private ArrayList<String> mChildListContent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_setting_help_listview);

        setLayout();

        mGroupList = new ArrayList<String>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();

        mGroupList.add("가위");
        mGroupList.add("바위");
        mGroupList.add("보");

        mChildListContent.add("1");
        mChildListContent.add("2");
        mChildListContent.add("3");

        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);

        mListView.setAdapter(new SettingFragmentHelpAdapter(this, mGroupList, mChildList));

        // 그룹 클릭 했을 경우 이벤트
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getApplicationContext(), "g click = " + groupPosition,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 차일드 클릭 했을 경우 이벤트
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), "c click = " + childPosition,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 그룹이 닫힐 경우 이벤트
        mListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), "g Collapse = " + groupPosition,
                        Toast.LENGTH_SHORT).show();
            }
        });

        // 그룹이 열릴 경우 이벤트
        mListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), "g Expand = " + groupPosition,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * Layout
     */
    private ExpandableListView mListView;

    private void setLayout(){
        mListView = (ExpandableListView) findViewById(R.id.elv_list);
    }
}








//
//    RelativeLayout relativeLayout0101;
//    RelativeLayout relativeLayout0201;
//    RelativeLayout relativeLayout0301;
//    RelativeLayout relativeLayout0401;
//    TextView textView01;
//    TextView textView02;
//    TextView textView03;
//    TextView textView04;
//    Button button01;
//    Button button02;
//    Button button03;
//    Button button04;
//    RelativeLayout relativeLayout0102;
//    RelativeLayout relativeLayout0202;
//    RelativeLayout relativeLayout0302;
//    RelativeLayout relativeLayout0402;
//    ImageView imageView01;
//    ImageView imageView02;
//    ImageView imageView03;
//    ImageView imageView04;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.f_setting_help);
//
//        relativeLayout0102 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0102);
//        button01 = (Button) findViewById(R.id.f_setting_help_btn01);
//        button01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(relativeLayout0102.getVisibility() == View.GONE) {
//                    relativeLayout0102.setVisibility(View.VISIBLE);
//                } else {
//                    relativeLayout0102.setVisibility(View.GONE);
//                }
//            }
//        });
//        relativeLayout0202 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0202);
//        button02 = (Button) findViewById(R.id.f_setting_help_btn02);
//        button02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(relativeLayout0202.getVisibility() == View.GONE) {
//                    relativeLayout0202.setVisibility(View.VISIBLE);
//                } else {
//                    relativeLayout0202.setVisibility(View.GONE);
//                }
//            }
//        });
//        relativeLayout0302 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0302);
//        button03 = (Button) findViewById(R.id.f_setting_help_btn03);
//        button03.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(relativeLayout0302.getVisibility() == View.GONE) {
//                    relativeLayout0302.setVisibility(View.VISIBLE);
//                } else {
//                    relativeLayout0302.setVisibility(View.GONE);
//                }
//            }
//        });
//        relativeLayout0402 = (RelativeLayout) findViewById(R.id.f_setting_help_relativelayout0402);
//        button04 = (Button) findViewById(R.id.f_setting_help_btn04);
//        button04.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(relativeLayout0402.getVisibility() == View.GONE) {
//                    relativeLayout0402.setVisibility(View.VISIBLE);
//                } else {
//                    relativeLayout0402.setVisibility(View.GONE);
//                }
//            }
//        });
//
//
//    }