package com.example.flightsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecycleView2Adapter extends RecyclerView.Adapter<MyRecycleView2Adapter.MyHolder>{
    private List<OrderData> mList;//数据源

    public MyRecycleView2Adapter(List<OrderData> list) {
        mList = list;
    }

    @Override
    public MyRecycleView2Adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyRecycleView2Adapter.MyHolder holder = new MyRecycleView2Adapter.MyHolder(view);
        //返回这个MyHolder实体
        return holder;
    }

    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(MyRecycleView2Adapter.MyHolder holder, int position){
        OrderData orderData = mList.get(position);
        holder.idxText.setText(String.valueOf(orderData.orderIdx));
        holder.infoText.setText(orderData.getTime() + "\n" + orderData.getName());

    }

    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {
        private TextView idxText;
        private TextView infoText;
        private Button siteBtn;
        private Button serviceBtn;

        public MyHolder(View itemView) {
            super(itemView);
            idxText = itemView.findViewById(R.id.item2_idx_text);
            infoText = itemView.findViewById(R.id.item2_info_text);
            siteBtn = itemView.findViewById(R.id.item2_site_btn);
            serviceBtn = itemView.findViewById(R.id.item2_service_btn);

            siteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyRecycleView2Adapter.this.onSiteButtonClick(getAdapterPosition());
                }
            });

            serviceBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyRecycleView2Adapter.this.onServiceButtonClick(getAdapterPosition());
                }
            });
        }
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener  {
        void onSiteButtonClick(int position);
        void onServiceButtonClick(int position);
    }

    public void onSiteButtonClick(int position){
        mOnItemClickListener.onSiteButtonClick(position);
    }

    public void onServiceButtonClick(int position){
        mOnItemClickListener.onServiceButtonClick(position);
    }

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(MyRecycleView2Adapter.OnItemClickListener listener) {
        this.mOnItemClickListener  = listener;
    }

    private MyRecycleView2Adapter.OnItemClickListener mOnItemClickListener;//声明自定义的接口
}
