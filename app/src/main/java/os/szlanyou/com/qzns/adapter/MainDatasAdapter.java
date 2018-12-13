package os.szlanyou.com.qzns.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import os.szlanyou.com.qzns.model.bean.WriteData;
import os.szlanyou.com.qzns.ui.activity.MainActivity;
import os.szlanyou.com.qzns.ui.activity.WriteActivity;
import os.szlanyou.com.qzns.ui.widget.TimeView;
import os.szlanyou.com.qzns.util.TextUtils;


/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 主界面RecyclerView的adapter
 */
public class MainDatasAdapter extends RecyclerView.Adapter<MainDatasAdapter.ViewHodler> {

    private List<WriteData> datas;
    private Context mContext;

    //构建，赋予person界面静态数据
    public MainDatasAdapter(Context context, List<WriteData> datas) {
        mContext = context;
        this.datas = datas;
    }

    static class ViewHodler extends RecyclerView.ViewHolder {
        View mView;
        TextView titleTV;
        TextView contentTV;
        TimeView mTimeView;

        public ViewHodler(View view) {
            super(view);
            mView = view;
            mTimeView = (TimeView) view.findViewById(R.id.time_v);
            titleTV = (TextView) view.findViewById(R.id.item_title_tv);
            contentTV = (TextView) view.findViewById(R.id.item_content_tv);
        }
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_list, viewGroup, false);
        final ViewHodler hodler = new ViewHodler(view);
        hodler.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动编辑界面
                int position = hodler.getAdapterPosition();
                WriteActivity.actionStartForResult(mContext, datas.get(position).getSaveTime());
            }
        });
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler hodler, int position) {

        String content = datas.get(position).getContent();
        String mTitle = "";
        String mContent = "";

        //通过换行符来粗略划分标题和内容
        String[] contentArray = content.split("\n");

        //避免前面存在空行，一行行扫描过去，取前非空前两行作为title和content显示
        int cur;
        for (cur = 0; cur < contentArray.length; cur++) {
            if (!TextUtils.isEmpty(contentArray[cur])) {
                mTitle = contentArray[cur];
                break;
            }
        }

        for (; cur < contentArray.length - 1; cur++) {
            if (!TextUtils.isEmpty(contentArray[cur + 1])) {
                mContent = contentArray[cur + 1];
                break;
            }
        }

        //通过一行的字数限制粗略划分标题和内容
        if (mTitle.length() > Contants.NUM_ITEM_TITLE_LENGTH) {
            mTitle = content.substring(0, Contants.NUM_ITEM_TITLE_LENGTH);
            mContent = content.substring(Contants.NUM_ITEM_TITLE_LENGTH);
        }

        if (TextUtils.isEmpty(mContent)) {
            hodler.contentTV.setVisibility(View.GONE);
        } else {
            hodler.contentTV.setVisibility(View.VISIBLE);
            hodler.contentTV.setText(mContent);
        }
        hodler.titleTV.setText(mTitle);
        hodler.mTimeView.setTime(datas.get(position).getSaveTime());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
