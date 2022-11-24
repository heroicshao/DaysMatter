package com.example.daysmatter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.daysmatter.R;
import com.example.daysmatter.app.MyApplication;
import com.example.daysmatter.model.BookBean;

import java.util.ArrayList;
import java.util.List;

public class BooksFragment extends Fragment {
    /** book列表 */
    private List<BookBean> mBookList;
    private TextView textView;
    @Nullable
    @Override

    //类似于Activity里面的setContentView();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.books,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView=view.findViewById(R.id.book);
        updateBookTxt();
    }

    /**
     * 更新Book列表
     */
    private void updateBookTxt(){
        // 查询数据库里的所有数据
        mBookList = MyApplication.mDBMaster.mBookDBDao.queryDataList();
        // 数据为空，也不能让列表为null
        if (null == mBookList) mBookList = new ArrayList<>();
    }
}


