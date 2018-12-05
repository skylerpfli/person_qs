package os.szlanyou.com.qzns.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.adapter.MainDatasAdapter;
import os.szlanyou.com.qzns.ui.widget.MyDecoration;

/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 主界面
 */
public class MainFragment extends Fragment {

    private View mView;
    private Context mContext;

    private TextView titleTV;
    private RecyclerView mainRecycerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
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
        titleTV = (TextView) mView.findViewById(R.id.title_tv);
        titleTV.setText(R.string.title_main);

        mainRecycerView = (RecyclerView) mView.findViewById(R.id.rv_main);
        mainRecycerView.setLayoutManager(new LinearLayoutManager(mContext));
        mainRecycerView.setAdapter(new MainDatasAdapter());
        MyDecoration myDecoration = new MyDecoration(mContext, MyDecoration.VERTICAL_LIST);

        //设置分割线长度、高和线条颜色
        myDecoration.setInset((int) getResources().getDimension(R.dimen.item_left_maigin_person));
        myDecoration.setDividerHeight((int) getResources().getDimension(R.dimen.item_decoration));
        myDecoration.setDivider(ContextCompat.getDrawable(mContext, R.drawable.drawable_decoration));
        mainRecycerView.addItemDecoration(myDecoration);
    }

}
