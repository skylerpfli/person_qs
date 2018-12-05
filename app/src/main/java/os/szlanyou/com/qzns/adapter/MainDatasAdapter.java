package os.szlanyou.com.qzns.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import os.szlanyou.com.qzns.R;
import os.szlanyou.com.qzns.model.Contants;


/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 主界面RecyclerView的adapter
 */
public class MainDatasAdapter extends RecyclerView.Adapter<MainDatasAdapter.ViewHodler> {

    private List<String> dataList;

    //构建，赋予person界面静态数据
    public MainDatasAdapter() {
        dataList = new ArrayList<String>();
        String testStr = "调试数据";
        for (int i = 1; i <= 10; i++) {
            dataList.add(testStr + i);
        }
    }

    static class ViewHodler extends RecyclerView.ViewHolder {
        View mView;
        TextView dataTV;

        public ViewHodler(View view) {
            super(view);
            mView = view;
            dataTV = (TextView) view.findViewById(R.id.item_tv);
        }
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_list, viewGroup, false);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler hodler, int position) {
        hodler.dataTV.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
