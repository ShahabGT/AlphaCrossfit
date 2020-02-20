package ir.shahabazimi.alphacrossfit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.models.DataModel;

public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {

    private Context context;
    private List<DataModel> data;

    public MonthAdapter(Context context, List<DataModel> data){

        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        DataModel model = data.get(position);
        h.type.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView type;

        ViewHolder(@NonNull View v) {
            super(v);
            type = v.findViewById(R.id.month_type);

        }
    }
}
