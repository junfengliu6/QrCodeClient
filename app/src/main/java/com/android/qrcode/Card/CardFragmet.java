package com.android.qrcode.Card;

import android.os.Bundle;

import com.android.base.BaseFragment;
import com.android.model.BannerItemBean;
import com.android.qrcode.R;
import com.android.utils.Utils;
import com.android.view.SimpleImageBanner;
import com.android.view.SquareImageView;
import com.flyco.banner.anim.select.RotateEnter;
import com.flyco.banner.anim.unselect.NoAnimExist;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by liujunqin on 2016/5/31.
 */
public class CardFragmet extends BaseFragment {


    SimpleImageBanner advert;

    @Bind(R.id.binaryCode)
    SquareImageView binaryCode;


    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };
    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160323071011277.jpg",//伪装者:胡歌演绎"痞子特工"
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144158380433341332.jpg",//无心法师:生死离别!月牙遭虐杀
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144160286644953923.jpg",//花千骨:尊上沦为花千骨
            "http://photocdn.sohu.com/tvmobilemvms/20150902/144115156939164801.jpg",//综艺饭:胖轩偷看夏天洗澡掀波澜
            "http://photocdn.sohu.com/tvmobilemvms/20150907/144159406950245847.jpg",//碟中谍4:阿汤哥高塔命悬一线,超越不可能
    };

    public CardFragmet() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "微卡");
        bundle.putInt("icon", R.drawable.selector_main_tab_message_icon);
        this.setArguments(bundle);
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_card;
    }

    @Override
    public void initView() {
        advert = (SimpleImageBanner) getView().findViewById(R.id.advert);
    }

    @Override
    public void initData() {
        advert.setSelectAnimClass(RotateEnter.class)
                .setUnselectAnimClass(NoAnimExist.class)
                .setSource(getList())
                .startScroll();
        binaryCode.setImageBitmap(Utils.createQRImage(getContext(),"test",500,500));
    }

    @Override
    public void setListener() {

    }

    public static ArrayList<BannerItemBean> getList() {
        ArrayList<BannerItemBean> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItemBean item = new BannerItemBean();
            item.imgUrl = urls[i];
            item.title = titles[i];
            list.add(item);
        }

        return list;
    }

    @OnClick(R.id.binaryCode)
    public void onClick() {

    }
}
