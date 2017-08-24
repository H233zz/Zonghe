package bawei.com.zonghe;

import android.app.Application;

import org.xutils.x;


/**
 * 类描述：
 * 创建人：
 * 创建时间：2017/8/22  9:48
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
