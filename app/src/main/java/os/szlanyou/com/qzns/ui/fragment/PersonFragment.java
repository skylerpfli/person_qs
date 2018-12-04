package os.szlanyou.com.qzns.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.adapter.PersonAdapter;
import os.szlanyou.com.qzns.ui.widget.MyDecoration;


/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 个人主页界面
 */
public class PersonFragment extends Fragment {

    View mView;
    RecyclerView mRecyclerView;
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_person, container, false);
        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        initWidget();
    }

    private void initWidget() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_person);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new PersonAdapter());
        MyDecoration myDecoration = new MyDecoration(mContext, MyDecoration.VERTICAL_LIST);
        myDecoration.setInset(100);
        myDecoration.setDividerHeight(1);
        mRecyclerView.addItemDecoration(myDecoration);
    }

}
