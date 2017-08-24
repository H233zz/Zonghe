package bawei.com.zonghe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bawei.com.zonghe.utills.Dao;
import me.maxwin.view.XListView;

/**
 * 类描述：
 * 创建人：
 * 创建时间：2017/8/22  9:29
 */
public class Fragment01 extends Fragment implements XListView.IXListViewListener{
    private List<Data.DataBean.TopicsBean> list=new ArrayList<>();
    private MyBase adapter;
    private String urlPath="http://api.kkmh.com/v1/topic_new/discovery_module_list/210?offset=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3OTE2MTcwNjE5LCJwcm9wZXJ0aWVzIjp7IiRvc192ZXJzaW9uIjoiNC4yLjIiLCJHZW5kZXJUeXBlIjoi5peg5rOV6I635Y-WIiwiVHJpZ2dlckl0ZW0iOjE3LCJWaXNpdFBhZ2VOYW1lIjoi5paw5L2c5LiK5p62IiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6InNhbXN1bmciLCJUcmlnZ2VySXRlbU5hbWUiOiLmlrDkvZzkuIrmnrYiLCIkc2NyZWVuX2hlaWdodCI6NTc2LCJMaXN0VHlwZSI6IuaXp-eJiCIsIlByb3BlcnR5RXZlbnQiOiJSZWFkTGlzdCIsIlRyaWdnZXJPcmRlck51bWJlciI6MCwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjQ2LCIkc2NyZWVuX3dpZHRoIjoxMDI0LCIkb3MiOiJBbmRyb2lkIiwiVHJpZ2dlckl0ZW1UeXBlIjowLCJUcmlnZ2VyUGFnZSI6IkZpbmRQYWdlIiwiJGNhcnJpZXIiOiJDTUNDIiwiJG1vZGVsIjoiR1QtUDUyMTAiLCIkYXBwX3ZlcnNpb24iOiIzLjguMSJ9LCJ0eXBlIjoidHJhY2siLCJkaXN0aW5jdF9pZCI6IkE6OTA1MTA0Mjc2Mzc1NTEwOSIsIm9yaWdpbmFsX2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5IiwiZXZlbnQiOiJSZWFkTGlzdCJ9&style=2&limit=";
    private int limit=3;
    private XListView xListView;
     private Dao dao;

//    public void setUrlPath(String urlPath) {
//        this.urlPath = urlPath;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        xListView = view.findViewById(R.id.xlistview);
        dao=new Dao(getActivity());
        xListView.setPullRefreshEnable(true);
        xListView.setXListViewListener(this);
        adapter=new MyBase(getActivity(),list);
        xListView.setAdapter(adapter);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences preferences=getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("name",list.get(i-1).getCover_image_url()+"");
                editor.commit();
                Intent intent=new Intent(getActivity(),ImageActivity.class);
                startActivity(intent);
            }
        });
        loadData();
        return view;
    }




    private void loadData() {
        RequestParams pa=new RequestParams(urlPath+limit);
        x.http().get(pa, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
               Data data=new Gson().fromJson(result,Data.class);
                System.out.println(result);
                list.addAll(data.getData().getTopics());
                //数据存入数据库
                for (Data.DataBean.TopicsBean bean:list){
                    dao.insert(bean.getDescription(), (String) bean.getCover_image_url());
                }
                System.out.println(list.size());

                StopXlist();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onRefresh() {
        limit=3;
        list.clear();
        loadData();
    }

    @Override
    public void onLoadMore() {
      limit++;
        limit=list.size();
        loadData();
    }
    public void StopXlist(){
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime("刚刚");
    }
}
