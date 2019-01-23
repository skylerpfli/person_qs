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
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.LitePal;
import java.util.List;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.adapter.MainDatasAdapter;
import os.szlanyou.com.qzns.model.bean.WriteData;
import os.szlanyou.com.qzns.ui.activity.WriteActivity;
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
    private MainDatasAdapter mDatasAdapter;
    private ImageView actionButton;

    private List<WriteData> datas;

    private final static String TAG = "MainFragment";


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
        datas = LitePal.order("saveTime desc").find(WriteData.class);

        mDatasAdapter = new MainDatasAdapter(mContext, datas);
        mainRecycerView.setAdapter(mDatasAdapter);
        MyDecoration myDecoration = new MyDecoration(mContext, MyDecoration.VERTICAL_LIST);

        //设置分割线长度、高和线条颜色
        myDecoration.setInset((int) getResources().getDimension(R.dimen.item_height_main));
        myDecoration.setDividerHeight((int) getResources().getDimension(R.dimen.item_decoration));
        myDecoration.setDivider(ContextCompat.getDrawable(mContext, R.drawable.drawable_decoration));
        mainRecycerView.addItemDecoration(myDecoration);

        actionButton = (ImageView) mView.findViewById(R.id.write_bt);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteActivity.actionStartForResult(mContext);
            }
        });
    }

    //刷新数据
    public void refreshData() {
        datas.clear();
        datas.addAll(LitePal.order("saveTime desc").find(WriteData.class));
        mDatasAdapter.notifyDataSetChanged();
    }
}
