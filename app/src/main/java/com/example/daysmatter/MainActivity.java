package com.example.daysmatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.daysmatter.app.MyApplication;
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

    private TextView txtBook, txtEvent;
//    private  Button addBook, addEvent, deleteBook, deleteEvent;

    private ViewPager mViewpager;
    private RadioGroup mRadioGroup;
    private RadioButton tab1,tab2,tab3,tab4;  //Tab按钮
    private List<View> mViews;   //存放视图

    /** phone列表 */
    private List<BookBean> mBookList;
    /** Company列表 */
    private List<EventBean> mEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        txtBook = findViewById(R.id.txt_book);
//        txtEvent = findViewById(R.id.txt_event);
//        addBook = (Button) findViewById(R.id.btn_add_book);
//        addEvent = (Button) findViewById(R.id.btn_add_event);
//        deleteBook = (Button) findViewById(R.id.btn_delete_book);
//        deleteEvent = (Button) findViewById(R.id.btn_delete_event);
//        addBook.setOnClickListener(this::onClick);
//        addEvent.setOnClickListener(this::onClick);
//        deleteBook.setOnClickListener(this::onClick);
//        deleteEvent.setOnClickListener(this::onClick);
        initView();
        setLinstener();
    }

    // 初始化
    private void initView() {
        mViewpager = findViewById(R.id.container);
        mRadioGroup = findViewById(R.id.rg_tab);
        tab1 = findViewById(R.id.rb_home);
        tab2 = findViewById(R.id.rb_books);
        tab3 = findViewById(R.id.rb_history);
        tab4 = findViewById(R.id.rb_my);

        mViews = new ArrayList<>(); // 加载，添加视图
        mViews.add(LayoutInflater.from(this).inflate(R.layout.home, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.books, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.history, null, false));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.my, null, false));

        mViewpager.setAdapter(new MyViewPagerAdapter()); // 设置适配器
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    /**
     * Tab按钮监听事件配置
     */
    private void setLinstener() {
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpager.setCurrentItem(0);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpager.setCurrentItem(1);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpager.setCurrentItem(2);
            }
        });
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpager.setCurrentItem(3);
            }
        });
    }

    /**
     * ViewPager适配器
     */
    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }


        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_add_book:
//                InsertBook(v);
//                break;
//            case R.id.btn_delete_book:
//                DeleteBook(v);
//                break;
//            case R.id.btn_add_event:
//                InsertEvent(v);
//                break;
//            case R.id.btn_delete_event:
//                DeleteEvent(v);
//                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 关闭数据库
        MyApplication.mDBMaster.closeDataBase();
    }


    /**
     * 点击插入PHONE按钮
     * @param view
     */
    public void InsertBook(View view){
        // 新实例化一个PHONE
        BookBean book = new BookBean();
        book.name = "在一起";
        book.icon = 1;
        book.cover = 2;
        // 插入数据库
        MyApplication.mDBMaster.mBookDBDao.insertData(book);
        updatePhoneTxt();
    }

    /**
     * 点击删除PHONE按钮
     * @param view
     */
    public void DeleteBook(View view){
        // 删除最老的一个数据
        if (null != mBookList && mBookList.size()>0){
            MyApplication.mDBMaster.mBookDBDao.deleteData(mBookList.get(0).id);
            updatePhoneTxt();
        }
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
        updateCompanyTxt();
    }

    /**
     * 点击删除COMPANY按钮
     * @param view
     */
    public void DeleteEvent(View view){
        // 删除最老的一个数据
        if (null != mEventList && mEventList.size()>0){
            MyApplication.mDBMaster.mEventDBDao.deleteData(mEventList.get(0).id);
            updateCompanyTxt();
        }
    }

    /**
     * 更新Phone列表
     */
    private void updatePhoneTxt(){
        // 查询数据库里的所有数据
        mBookList = MyApplication.mDBMaster.mBookDBDao.queryDataList();
        // 数据为空，也不能让列表为null
        if (null == mBookList) mBookList = new ArrayList<>();
        // 将数据转为字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mBookList.size(); i++) {
            builder.append(mBookList.get(i).toString()).append("\r\n");
        }
        // 显示数据
        txtBook.setText(builder.toString());
    }

    /**
     * 更新Company列表
     */
    private void updateCompanyTxt(){
        // 查询数据库里的所有数据
        mEventList = MyApplication.mDBMaster.mEventDBDao.queryDataList();
        // 数据为空，也不能让列表为null
        if (null == mEventList) mEventList = new ArrayList<>();
        // 将数据转为字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mEventList.size(); i++) {
            builder.append(mEventList.get(i).toString()).append("\r\n");
        }
        // 显示数据
        txtEvent.setText(builder.toString());
    }
}