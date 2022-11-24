package com.example.daysmatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.daysmatter.app.MyApplication;
import com.example.daysmatter.fragment.MyFragmentPagerAdapter;
import com.example.daysmatter.model.BookBean;
import com.example.daysmatter.model.EventBean;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewpager;
    private RadioGroup mRadioGroup;
    private RadioButton tab1,tab2,tab3,tab4;  //Tab按钮
    private List<View> mViews;   //存放视图
    private MyFragmentPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        initView();
    }

    //代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    // 初始化
    private void initView() {
        mViewpager = findViewById(R.id.container);
        mRadioGroup = findViewById(R.id.rg_tab);
        tab1 = findViewById(R.id.rb_home);
        tab2 = findViewById(R.id.rb_books);
        tab3 = findViewById(R.id.rb_history);
        tab4 = findViewById(R.id.rb_my);
        tab1.setOnClickListener(this::onClick);
        tab2.setOnClickListener(this::onClick);
        tab3.setOnClickListener(this::onClick);
        tab4.setOnClickListener(this::onClick);

        mViews = new ArrayList<>(); // 加载，添加视图
        mViews.add(LayoutInflater.from(this).inflate(R.layout.home, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.books, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.history, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.my, null, false));

        mViewpager.setAdapter(mAdapter); // 设置适配器
        mViewpager.setCurrentItem(0);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override   //让viewpager滑动的时候，下面的图标跟着变动
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab1.setChecked(true);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 1:
                        tab1.setChecked(false);
                        tab2.setChecked(true);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 2:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(true);
                        tab4.setChecked(false);
                        break;
                    case 3:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                mViewpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_books:
                mViewpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_history:
                mViewpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_my:
                mViewpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭数据库
        MyApplication.mDBMaster.closeDataBase();
    }


    /**
     * 点击插入Book按钮
     * @param view
     */
    public void InsertBook(View view){
        // 新实例化一个Book
        BookBean book = new BookBean();
        book.name = "在一起";
        book.icon = 1;
        book.cover = 2;
        // 插入数据库
        MyApplication.mDBMaster.mBookDBDao.insertData(book);
    }

    /**
     * 点击插入COMPANY按钮
     * @param view
     */
    public void InsertEvent(View view){
        // 新实例化一个COMPANY
        EventBean event = new EventBean();
        event.name = "谷歌";
        event.data = "2022-11-18";
        event.book_id = 1;
        event.home_show = 1;
        event.home_first = 1;
        event.repeat = "不重复";
        // 插入数据库
        MyApplication.mDBMaster.mEventDBDao.insertData(event);
    }
//
//    /**
//     * 点击删除COMPANY按钮
//     * @param view
//     */
//    public void DeleteEvent(View view){
//        // 删除最老的一个数据
//        if (null != mEventList && mEventList.size()>0){
//            MyApplication.mDBMaster.mEventDBDao.deleteData(mEventList.get(0).id);
//        }
//    }




}