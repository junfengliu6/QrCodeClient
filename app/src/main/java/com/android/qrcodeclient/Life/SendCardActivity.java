package com.android.qrcodeclient.Life;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.base.BaseAppCompatActivity;
import com.android.qrcodeclient.R;

import butterknife.Bind;

/**
 * Created by jisx on 2016/6/13.
 */
public class SendCardActivity extends BaseAppCompatActivity implements View.OnClickListener{

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_card);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        title.setText(R.string.send_card_title);
    }

    @Override
    public void setListener() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
