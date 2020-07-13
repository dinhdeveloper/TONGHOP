package qtc.project.pos.adapter.report.tonkho_vs_doanhthu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import b.laixuantam.myaarlibrary.helper.map.location.LocationHelper;
import qtc.project.pos.R;
import qtc.project.pos.model.Stock_Income_Model;

public class TonKho_Vs_DoanhThuAdapter extends RecyclerView.Adapter<TonKho_Vs_DoanhThuAdapter.ViewHolder> {

    List<Stock_Income_Model> modelList;
    Context context;

    public TonKho_Vs_DoanhThuAdapter(List<Stock_Income_Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_chart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Stock_Income_Model model = modelList.get(position);
            holder.thang.setText(model.getTitle());
            LinearLayout.LayoutParams paramsStock = (LinearLayout.LayoutParams) holder.layout_one.getLayoutParams();
            LinearLayout.LayoutParams paramsIncome = (LinearLayout.LayoutParams) holder.layout_two.getLayoutParams();

//            //stock
//            int minStock = Integer.parseInt(modelList.get(0).getValueStock());
//            int maxStock = Integer.parseInt(modelList.get(0).getValueStock());
//            for (int i = 0; i < modelList.size(); i++) {
//                int number = Integer.parseInt(modelList.get(i).getValueStock());
//                if (number < minStock) {
//                    minStock = number;
//                }
//                if (number > maxStock) {
//                    maxStock = number;
//                }
//            }
//
//            //min max income
//            int minIncome = Integer.parseInt(modelList.get(0).getValueIncome());
//            int maxIncome = Integer.parseInt(modelList.get(0).getValueIncome());
//            for (int i = 0; i < modelList.size(); i++) {
//                int number = Integer.parseInt(modelList.get(i).getValueIncome());
//                if (number < minIncome) {
//                    minIncome = number;
//                }
//                if (number > maxIncome) {
//                    maxIncome = number;
//                }
//            }
//            int maxAll = 0;
//            int maxHeight = 200;
//            if (maxIncome > maxStock) {
//                maxAll = maxIncome;
//            } else {
//                maxAll = maxStock;
//            }
//            for (int i = 0; i < modelList.size(); i++) {
//                int heightIncome = (Integer.parseInt(modelList.get(i).getValueIncome()) * maxHeight) / maxAll;
//                int heightStock = (Integer.parseInt(modelList.get(i).getValueStock()) * maxHeight) / maxAll;
//                model.setHeightIncome(String.valueOf(heightIncome));
//                model.setHeightIncome(String.valueOf(heightStock));
//            }

            paramsIncome.height = Integer.parseInt(model.getHeightIncome());
            paramsStock.height = Integer.parseInt(model.getHeightStock());
            holder.layout_one.setLayoutParams(paramsStock);
            holder.layout_two.setLayoutParams(paramsIncome);

        } catch (Exception e) {
            Log.e("Ex", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout_two;
        LinearLayout layout_one;
        TextView thang;
        TextView id_two;
        TextView id_one;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_one = itemView.findViewById(R.id.layout_one);
            layout_two = itemView.findViewById(R.id.layout_two);
            thang = itemView.findViewById(R.id.thang);
            id_two = itemView.findViewById(R.id.id_two);
            id_one = itemView.findViewById(R.id.id_one);
        }
    }
}