package qtc.project.pos.adapter.levelcustomer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import b.laixuantam.myaarlibrary.widgets.superadapter.SuperAdapter;
import b.laixuantam.myaarlibrary.widgets.superadapter.SuperViewHolder;
import qtc.project.pos.R;
import qtc.project.pos.model.LevelCustomerModel;
import qtc.project.pos.model.PackageInfoModel;

public class LevelCustomerChooseAdapter extends RecyclerView.Adapter<LevelCustomerChooseAdapter.ViewHolder> {

    LevelCustomerChooseAdapterListener listener;

    Context context;
    List<LevelCustomerModel> list;

    public LevelCustomerChooseAdapter(Context context, List<LevelCustomerModel> list) {
        this.context = context;
        this.list = list;
    }

    private onRecyclerViewItemClickListener mItemClickListener;

    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;

    public interface LevelCustomerChooseAdapterListener{
        void onItemClick(LevelCustomerModel model);
    }

    public void setListener(LevelCustomerChooseAdapterListener listener) {
        this.listener = listener;
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(LevelCustomerModel model);
    }

    @NonNull
    @Override
    public LevelCustomerChooseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_choose_level_customer,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelCustomerChooseAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout_item;
        TextView name_level_customer;
        ImageView item_choose;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             layout_item = itemView.findViewById(R.id.layout_item);
             name_level_customer = itemView.findViewById(R.id.name_level_customer);
             item_choose = itemView.findViewById(R.id.item_choose);

        }

        void bind(final LevelCustomerModel item){
            try{
                if (checkedPosition == -1) {
                    item_choose.setVisibility(View.GONE);
                } else {
                    if (checkedPosition == getAdapterPosition()) {
                        item_choose.setVisibility(View.VISIBLE);

                    } else {
                        item_choose.setVisibility(View.GONE);
                    }
                }
                name_level_customer.setText(item.getName());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item_choose.setVisibility(View.VISIBLE);
                        if (checkedPosition != getAdapterPosition()) {
                            notifyItemChanged(checkedPosition);
                            checkedPosition = getAdapterPosition();
                            if (mItemClickListener!=null)
                                mItemClickListener.onItemClickListener(item);
                        }
                    }
                });
            }catch (Exception e){
                Log.e("Exception",e.getMessage());
            }
        }
    }
}
