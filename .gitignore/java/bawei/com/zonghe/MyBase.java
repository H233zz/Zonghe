package bawei.com.zonghe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 类描述：
 * 创建人：
 * 创建时间：2017/8/22  9:57
 */
public class MyBase extends BaseAdapter{
    private Context context;
    private List<Data.DataBean.TopicsBean> list;
  private DisplayImageOptions options;
    private ImageLoader imageLoader;
    public MyBase(Context context, List<Data.DataBean.TopicsBean> list) {
        this.context = context;
        this.list = list;
        //创建默认的ImageLoader配置参数
                ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                        .createDefault(context);

                //将configuration配置到imageloader中
                imageLoader=ImageLoader.getInstance();
                imageLoader.init(configuration);

                options=new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .bitmapConfig(Bitmap.Config.ARGB_8888)
                        .showImageOnLoading(R.mipmap.ic_launcher)
                        .showImageForEmptyUri(R.mipmap.ic_launcher)
                        .showImageOnFail(R.mipmap.ic_launcher)
                        .build();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=view.inflate(context,R.layout.item_fragment,null);
            holder.name=view.findViewById(R.id.name);
            holder.imageView=view.findViewById(R.id.image);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
       final Data.DataBean.TopicsBean bean=list.get(i);
        holder.name.setText(bean.getDescription());
        imageLoader.displayImage((String) bean.getCover_image_url(),holder.imageView,options);
        return view;
    }
    class ViewHolder{

        TextView name;

        ImageView imageView;
    }
}
