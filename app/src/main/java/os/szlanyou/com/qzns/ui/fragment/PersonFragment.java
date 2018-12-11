package os.szlanyou.com.qzns.ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import os.szlanyou.com.qzns.adapter.PersonAdapter;
import os.szlanyou.com.qzns.ui.widget.MyDecoration;
import os.szlanyou.com.qzns.R;

/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 个人主页界面
 */
public class PersonFragment extends Fragment {

    private View mView;
    private RecyclerView personRecyclerView;
    private Context mContext;


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

    //初始化控件
    private void initWidget() {
        personRecyclerView = (RecyclerView) mView.findViewById(R.id.rv_person);
        personRecyclerView.setLayoutManager(new LinearLayoutManager(mContext)); //布局管理器
        personRecyclerView.setAdapter(new PersonAdapter(mContext));
        MyDecoration myDecoration = new MyDecoration(mContext, MyDecoration.VERTICAL_LIST);
        //设置分割线长度、高和线条颜色
        myDecoration.setInset((int) getResources().getDimension(R.dimen.item_left_maigin_person));
        myDecoration.setDividerHeight((int) getResources().getDimension(R.dimen.item_decoration));
        myDecoration.setDivider(ContextCompat.getDrawable(mContext, R.drawable.drawable_decoration));
        personRecyclerView.addItemDecoration(myDecoration);
    }

}
