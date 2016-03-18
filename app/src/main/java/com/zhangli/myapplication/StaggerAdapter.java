package com.zhangli.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.StaggerViewHolder> {

    private Context mContext;
    private List<DataBean> mDatas;
    BitmapUtil bit=new BitmapUtil();


    public StaggerAdapter(Context context, List<DataBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public StaggerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //当ViewHolder创建时的回调
        View view = View.inflate(mContext, R.layout.stagger_layout, null);

        return new StaggerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StaggerViewHolder holder, int position) {
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

    public class StaggerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgIcn;
        private TextView tvName;

        public StaggerViewHolder(View itemView) {
            super(itemView);
            imgIcn = (ImageView) itemView.findViewById(R.id.list_img_id);
            tvName = (TextView) itemView.findViewById(R.id.list_tv_id);
        }

        public void setData(DataBean bean) {
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
