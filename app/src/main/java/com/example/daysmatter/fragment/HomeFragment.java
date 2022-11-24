package com.example.daysmatter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.daysmatter.MainActivity;
import com.example.daysmatter.R;
import com.example.daysmatter.app.MyApplication;
import com.example.daysmatter.model.EventBean;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    /** Event列表 */
    private List<EventBean> mEventList;

    private TextView textView;
    @Nullable
    @Override

    //类似于Activity里面的setContentView();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String today = LocalDate.now().toString();
        textView=view.findViewById(R.id.home);
        textView.setText("这是home页");
    }

    /**
     * 更新Event列表
     */
    private void updateEvent(){
        // 查询数据库里的所有数据
        mEventList = MyApplication.mDBMaster.mEventDBDao.queryDataList();
        // 数据为空，也不能让列表为null
        if (null == mEventList) mEventList = new ArrayList<>();
        // 将数据转为字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mEventList.size(); i++) {
            builder.append(mEventList.get(i).toString()).append("\r\n");
        }
    }
}


