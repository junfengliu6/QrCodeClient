package com.android.qrcode;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adapter.MainActivityAdapter;
import com.android.base.BaseAppCompatActivity;
import com.android.qrcode.Card.CardFragmet;
import com.android.qrcode.Life.LifeFragmet;
import com.android.qrcode.Personal.PersonalFragmet;
import com.android.utils.Page;
import com.android.utils.Utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseAppCompatActivity implements
        TabLayout.OnTabSelectedListener {


    private SearchView searchView;
    private MainActivityAdapter mainActivityAdapter;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private Page flag = Page.CARD;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.toolbar)
    Toolbar toolBar;

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void initView() {

        setSupportActionBar(toolBar);

        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(MainActivity.this, R.string.menu_search, Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });


    }

    @Override
    public void initData() {
        //把页面加入到集合中
        fragmentList.add(new LifeFragmet());
        fragmentList.add(new CardFragmet());
        fragmentList.add(new PersonalFragmet());

        //实例化适配器
        mainActivityAdapter = new MainActivityAdapter(
        getSupportFragmentManager(),
                fragmentList
        );

        //如果不设置，默认值为1，那么切换第四个页面的时候，第一个页面在下次展现的时候，会重新加载。
        viewPager.setOffscreenPageLimit(3);
        //绑定适配器
        viewPager.setAdapter(mainActivityAdapter);

        //tab与viewPage关联
        tabLayout.setupWithViewPager(viewPager);
        initTab();
        //默认微卡被选中
        onTabSelected(tabLayout.getTabAt(1));
    }

    @Override
    public void setListener() {

        //设置页面切换监听
        tabLayout.setOnTabSelectedListener(this);


    }


    /**
     * 设置所有tab的图标和初始化第一个界面
     */
    private void initTab() {
        for (int i = 0; i < fragmentList.size(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            setTabIcon(fragmentList.get(i), tab);
            ViewGroup layout = (ViewGroup) tabLayout.getChildAt(0);
            setIconSpacing((ViewGroup) layout.getChildAt(i));

        }
    }

    /**
     * 添加具有切换效果的图标
     *
     * @param fragment
     * @param tab
     */
    private void setTabIcon(Fragment fragment, TabLayout.Tab tab) {
        Bundle bundle = fragment.getArguments();
        if (bundle != null) {
            int drawableId = bundle.getInt("icon");
            tab.setIcon(getResources().getDrawable(drawableId));
        }
    }

    /**
     * 设置icon和文字的距离
     *
     * @param viewGroup
     */
    private void setIconSpacing(ViewGroup viewGroup) {
        ImageView iconView = (ImageView) viewGroup.getChildAt(0);
        if (iconView != null) {
            ViewGroup.MarginLayoutParams lp = ((ViewGroup.MarginLayoutParams) iconView.getLayoutParams());
            int bottomMargin = Utils.dpToPx(this, 4);
            if (bottomMargin != lp.bottomMargin) {
                lp.bottomMargin = bottomMargin;
                iconView.requestLayout();
            }
        }
    }


    /**
     * 加载菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

        switch (tab.getPosition()) {

            case 0:
                title.setText(R.string.life_title);
                flag = Page.LIFE;
                break;
            case 1:
                title.setText(R.string.card_title);
                flag = Page.CARD;
                break;
            case 2:
                title.setText(R.string.personal_title);
                flag = Page.PERSONAL;
                break;
            default:
                break;

        }
        //上面是给切换的页面赋值标示，但是因为每次点击一个Menu的时候，它就改变一次，点击tablayout不起作用，所以加了下面的方法
        invalidateOptionsMenu();


    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //为了在溢出菜单里添加icon
        setOverflowIconVisible(menu);
        toolBar.setTitle("");
        // 显示和隐藏menu
        switch (flag) {

            case LIFE:
                toolBar.setNavigationIcon(null);
                isShowMenu(false, menu);
                break;
            case CARD:
                toolBar.setNavigationIcon(android.R.drawable.ic_menu_mylocation);
                isShowMenu(true, menu);
                break;
            case PERSONAL:
                toolBar.setNavigationIcon(null);
                isShowMenu(false, menu);
                break;
            default:
                break;
        }


        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 利用反射让隐藏在Overflow中的MenuItem显示Icon图标
     *
     * @param menu onMenuOpened方法中调用
     */
    public static void setOverflowIconVisible(Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
    }

    public void isShowMenu(boolean isShow, Menu menu) {

        if (isShow) {

            menu.findItem(R.id.action_search).setVisible(true);

        } else {

            menu.findItem(R.id.action_search).setVisible(false);
        }
    }
}
