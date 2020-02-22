package ir.shahabazimi.alphacrossfit.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.activities.DetailsActivity;
import ir.shahabazimi.alphacrossfit.activities.MonthActivity;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.models.DataModel;


public class YearAdapter extends RecyclerView.Adapter<YearAdapter.ViewHolder> {

    private List<DataModel> data;
    private Context context;
    private String year;
    private String eyear;

    public YearAdapter(Context context,List<DataModel> data,String year){
        this.data=data;
        this.context=context;
        this.year=year;
        this.eyear = String.valueOf(Integer.valueOf(year)+621);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_year,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {

        DataModel model = data.get(position);

        h.count.setText("تعداد: "+model.getCount());
        h.price.setText("مبلغ: "+ Utils.moneySeparator(model.getSum()));

        switch (model.getId()){
            case 1:
                h.title.setText("فروردین");
                break;
            case 2:
                h.title.setText("اردیبهشت");
                break;
            case 3:
                h.title.setText("خرداد");
                break;
            case 4:
                h.title.setText("تیر");
                break;
            case 5:
                h.title.setText("مرداد");
                break;
            case 6:
                h.title.setText("شهریور");
                break;
            case 7:
                h.title.setText("مهر");
                break;
            case 8:
                h.title.setText("آبان");
                break;
            case 9:
                h.title.setText("آذر");
                break;
            case 10:
                h.title.setText("دی");
                break;
            case 11:
                h.title.setText("بهمن");
                break;
            case 12:
                h.title.setText("اسفند");
                break;
            case 13:
                h.title.setText("امسال");
                break;

            case 14:
                h.title.setText("کل");
                break;
        }

        h.itemView.setOnClickListener(w->{
            if(Integer.parseInt(model.getCount())>0) {
                if(model.getId()==14){
                    Intent in = new Intent(context, DetailsActivity.class);
                    in.putExtra("month",model.getId()+"");
                    in.putExtra("year",year);
                    in.putExtra("eyear",eyear);
                    in.putExtra("name","all");
                    context.startActivity(in);
                }else{
                    Intent in = new Intent(context, MonthActivity.class);
                    in.putExtra("month",model.getId()+"");
                    in.putExtra("year",year);
                    in.putExtra("eyear",eyear);
                    context.startActivity(in);
                }

            }

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView count,price,title;


        ViewHolder(@NonNull View v) {
            super(v);
            count = v.findViewById(R.id.year_count);
            price = v.findViewById(R.id.year_price);
            title = v.findViewById(R.id.year_title);
        }
    }
}
