package os.szlanyou.com.qzns.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import os.szlanyou.com.qzns.R;


/**
 * Author: qzns木雨
 * Date:2018/12/4
 * Description: 标记便签界面
 */
public class TagFragment extends Fragment {

    private View mView;
    private TextView titleTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_tag, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titleTV = (TextView) mView.findViewById(R.id.title_tv);
        titleTV.setText(R.string.title_tag);
    }
}
