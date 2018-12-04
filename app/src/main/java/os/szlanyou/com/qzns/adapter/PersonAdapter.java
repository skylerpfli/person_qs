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
 * Description: person界面RecyclerView的adapter
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHodler> {

    private List<String> dataList;

    //构建，赋予person界面静态数据
    public PersonAdapter() {
        dataList = new ArrayList<String>(Arrays.asList(Contants.PERSON_LIST_DATA));
    }

    static class ViewHodler extends RecyclerView.ViewHolder {
        TextView itemTv;
        ImageView passwordSwitch;

        public ViewHodler(View view) {
            super(view);
            itemTv = (TextView) view.findViewById(R.id.item_tv);
            passwordSwitch = (ImageView) view.findViewById(R.id.password_switch);
        }
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_person_list, viewGroup, false);
        ViewHodler hodler = new ViewHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler hodler, int position) {
        String itemStr = dataList.get(position);
        hodler.itemTv.setText(itemStr);

        if (itemStr.equals(Contants.PERSON_LIST_DATA[1])) {
            //第二个item存在密码锁开关，进行显示

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
