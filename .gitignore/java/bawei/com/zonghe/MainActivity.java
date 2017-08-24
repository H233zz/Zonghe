package bawei.com.zonghe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
        private TabLayout tabLayout;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //初始化控件
            initview();
            boolean b = isNetworkAvailable(this);
            if(b) {
                Toast.makeText(this,"有网",Toast.LENGTH_SHORT).show();

                //初始化viewpager
                initViewPager();
            }else{
                Toast.makeText(this,"没有网",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("是否设置网络？");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
                        startActivity(intent);
                    }
                });
                builder.show();

            }
        }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
         private void initViewPager() {
            //创建一个集合,装fragment
            ArrayList<Fragment> fragments = new ArrayList<>();
            //把VR全景图和视频的fragment对象装入集合
  //          Fragment01 fragment01 = new Fragment01();
//            fragment01.setUrlPath(urlPath);
            fragments.add(new Fragment01());
            fragments.add(new Fragment01());
            fragments.add(new Fragment01());
            fragments.add(new Fragment01());
            fragments.add(new Fragment01());
            //创建viewpager适配器
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
            myPagerAdapter.setFragments(fragments);
            //给viewpager设置适配器,
            viewPager.setAdapter(myPagerAdapter);
            //tablayout指示器有几个就创建几个
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
            tabLayout.addTab(tabLayout.newTab());
          //  tabLayout.addTab(tabLayout.newTab());
            //使用tablayout和viewpager相关联
            tabLayout.setupWithViewPager(viewPager);
            //给tablayout指示器添加文本
            tabLayout.getTabAt(0).setText("新闻");
            tabLayout.getTabAt(1).setText("每天");
            tabLayout.getTabAt(2).setText("新闻");
            tabLayout.getTabAt(3).setText("每天");
            tabLayout.getTabAt(4).setText("科技");
           // tabLayout.getTabAt(2).setText("更多");

        }



        /**
         * 初始化控件
         */
        private void initview() {

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            tabLayout = (TabLayout) findViewById(R.id.tablayout);

        }


    }

