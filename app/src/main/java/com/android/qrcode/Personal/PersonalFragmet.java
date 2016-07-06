package com.android.qrcode.Personal;

import android.os.Bundle;
import android.widget.TextView;

import com.android.base.BaseFragment;
import com.android.qrcode.Personal.Cell.CellActivity;
import com.android.qrcode.R;
import com.android.view.ExitHintDialog;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by liujunqin on 2016/5/31.
 */
public class PersonalFragmet extends BaseFragment {


    @Bind(R.id.cell)
    TextView cell;
    @Bind(R.id.entrance)
    TextView entrance;
    @Bind(R.id.apply)
    TextView apply;
    @Bind(R.id.micro_card)
    TextView microCard;
    @Bind(R.id.problem)
    TextView problem;
    @Bind(R.id.about)
    TextView about;
    @Bind(R.id.modify_pwd)
    TextView modifyPwd;
    @Bind(R.id.loginout)
    TextView loginout;

    ExitHintDialog exitHintDialog;

    Bundle bundle;

    public PersonalFragmet() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "我的");
        bundle.putInt("icon", R.drawable.selector_main_tab_home_icon);
        this.setArguments(bundle);
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        bundle = new Bundle();
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.cell, R.id.entrance, R.id.apply, R.id.micro_card, R.id.problem, R.id.about, R.id.modify_pwd, R.id.loginout})
    public void onClick(TextView view) {
        bundle.putString(getResources().getString(R.string.develop_title), view.getText().toString());
        switch (view.getId()) {
            case R.id.cell:
                goNext(CellActivity.class,bundle);
                break;
            case R.id.entrance:
                goNext(EntranceActivity.class,bundle);
                break;
            case R.id.apply:
                goNext(ApplyActivity.class,bundle);
                break;
            case R.id.micro_card:
                goNext(MicroCardActivity.class,bundle);
                break;
            case R.id.problem:
                goNext(FeedBackActivity.class,bundle);
                break;
            case R.id.about:
                goNext(AboutActivity.class,bundle);
                break;
            case R.id.modify_pwd:
                goNext(ModifyPwdActivity.class,bundle);
                break;
            case R.id.loginout:
                if(exitHintDialog == null){
                    exitHintDialog = new ExitHintDialog(getContext());
                }
                exitHintDialog.show();
                break;
        }
    }
}
