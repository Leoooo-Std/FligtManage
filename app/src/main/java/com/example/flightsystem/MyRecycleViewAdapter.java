package com.example.flightsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyHolder>{
    private List<FlightData> mList;//数据源

    public MyRecycleViewAdapter(List<FlightData> list) {
        mList = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flight_item, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyHolder holder = new MyHolder(view);
        //返回这个MyHolder实体
        return holder;
    }

    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(MyHolder holder, int position){
        FlightData data = mList.get(position);
        holder.name.setText(data.getName());
        String info = data.getStartTime() + "-" + data.getEndTime() + "\n"
                    + data.getStartCity() + "-" + data.getEndCity();
        holder.info.setText(info);

    }

    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private Button button;
        private TextView name;
        private TextView info;

        public MyHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.item_name_text);
            info = itemView.findViewById(R.id.item_info_text);
            button = itemView.findViewById(R.id.item_submit_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyRecycleViewAdapter.this.onSubmitButtonClick(getAdapterPosition());
                }
            });
        }
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener  {
        void onSubmitButtonClick(int position);
    }

    public void onSubmitButtonClick(int position){
        mOnItemClickListener.onSubmitButtonClick(position);
    }


    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener  listener) {
        this.mOnItemClickListener  = listener;
    }

    private OnItemClickListener mOnItemClickListener;//声明自定义的接口
}
