package com.android.qrcode.Life;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.adapter.LifeAdapter;
import com.android.base.BaseFragment;
import com.android.model.LifeItemBean;
import com.android.qrcode.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by liujunqin on 2016/5/31.
 */
public class LifeFragmet extends BaseFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.grid_life)
    GridView gridLife;

    List<LifeItemBean> list;

    public LifeFragmet() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "生活");
        bundle.putInt("icon", R.drawable.selector_main_tab_person_icon);
        this.setArguments(bundle);
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_life;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        list.add(new LifeItemBean("给亲友微卡", R.mipmap.fasongweika, SendCardActivity.class));
        list.add(new LifeItemBean("往来日志", R.mipmap.rizhi, LogActivity.class));
        list.add(new LifeItemBean("我的消息", R.mipmap.message, MessageActivity.class));
        list.add(new LifeItemBean("百度", R.mipmap.baidu2, DevelopingActivity.class));
        list.add(new LifeItemBean("票务", R.mipmap.ticket, DevelopingActivity.class));
        list.add(new LifeItemBean("百度外卖", R.mipmap.baiduwaimai11, DevelopingActivity.class));
        list.add(new LifeItemBean("找爱车", R.mipmap.zhaoaiche, DevelopingActivity.class));
        list.add(new LifeItemBean("滴滴打车", R.mipmap.dididache, DevelopingActivity.class));
        list.add(new LifeItemBean("游戏   ", R.mipmap.youxi, DevelopingActivity.class));
        gridLife.setAdapter(new LifeAdapter(getContext(), list));
    }

    @Override
    public void setListener() {
        gridLife.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class cls = list.get(position).getCls();
        if (cls != null) {
            Bundle bundle = new Bundle();
            bundle.putString(getResources().getString(R.string.develop_title), list.get(position).getItemName());
            goNext(cls, bundle);
        } else {
            showToast("暂未开放");
        }
    }
}
