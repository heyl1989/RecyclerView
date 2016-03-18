package com.zhangli.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private Context mContext;
    private List<DataBean> mDatas;
    BitmapUtil bit=new BitmapUtil();

    public ListAdapter(Context context, List<DataBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //当ViewHolder创建时的回调
        View view = View.inflate(mContext, R.layout.list_layout, null);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        //当viewHolder与数据绑定时的回调
        DataBean bean = mDatas.get(position);

        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        //返回的是list数据的个数
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcn;
        private TextView tvName;

        public ListViewHolder(View itemView) {
            super(itemView);
            imgIcn = (ImageView) itemView.findViewById(R.id.list_img_id);
            tvName = (TextView) itemView.findViewById(R.id.list_tv_id);
        }

        public void setData(DataBean bean) {
            Log.e("tag", "data.icn:" + bean.icn);
//            imgIcn.setImageResource(bean.icn);

            Bitmap bitmap=getBitmap(bean.icn);

            //图片先加载出来再缩放  效率低
//            imgIcn.setImageDrawable(bit.resizeImage(bitmap,1000,640));

            //将bitmap缩小
//            imgIcn.setImageBitmap(ThumbnailUtils.extractThumbnail(bitmap, 200, 100));//2.2才加进来的新类，简单易用

            imgIcn.setImageBitmap(bitmap);

            tvName.setText(bean.name);
        }
    }

    public Bitmap getBitmap(int id){
        return bit.drawableToBitmap(mContext,id);
    }
}
