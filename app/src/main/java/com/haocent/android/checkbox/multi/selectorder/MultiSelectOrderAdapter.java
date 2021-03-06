package com.haocent.android.checkbox.multi.selectorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.haocent.android.checkbox.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tnno Wu on 2018/01/05.
 */

public class MultiSelectOrderAdapter extends RecyclerView.Adapter<MultiSelectOrderAdapter.MultiSelectOrderViewHolder> {

    private Context mContext;

    private List<String> mList = new ArrayList<>();

    private List<String> mSelectList = new ArrayList<>();

    private Map<Integer, Boolean> mMap = new HashMap<>();

    public MultiSelectOrderAdapter(Context context) {
        mContext = context;
    }

    public void setDataList(List<String> list) {
        mList = list;

        initMap();

        notifyDataSetChanged();
    }

    // 初始化集合，默认不选中
    private void initMap() {
        for (int i = 0; i < mList.size(); i++) {
            mMap.put(i, false);
        }
    }

    // 返回给 Activity 当前 CheckBox 选择的顺序的数据
    public List<String> getSelectList() {
        return mSelectList;
    }

    @Override
    public MultiSelectOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_multi_select_order, parent, false);
        return new MultiSelectOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultiSelectOrderViewHolder holder, final int position) {
        holder.tvName.setText(mList.get(position));

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mMap.put(position, isChecked);

                /* 将 CheckBox 选中的 item 按选择顺序添加到 List 里 */
                if (isChecked) {
                    // 将 position 对应的 item 存到 List 里
                    mSelectList.add(mList.get(position));
                } else {
                    // 将 position 对应的 item 从 List 里移除
                    mSelectList.remove(mList.get(position));
                }
            }
        });

        if (mMap.get(position) == null) {
            mMap.put(position, false);
        }

        // 根据 Map 来设置 CheckBox 的选中状况
        holder.checkBox.setChecked(mMap.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MultiSelectOrderViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        TextView tvName;

        public MultiSelectOrderViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.cb_multi);
            tvName = itemView.findViewById(R.id.tv_name_multi);
        }
    }
}
