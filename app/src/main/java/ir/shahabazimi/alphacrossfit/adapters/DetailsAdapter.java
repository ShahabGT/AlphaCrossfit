package ir.shahabazimi.alphacrossfit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.classes.DateConverter;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.models.DetailsModel;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private List<DetailsModel> data;
    private boolean allUsers;

    public DetailsAdapter(List<DetailsModel> data, boolean allUsers) {
        this.data = data;
        this.allUsers = allUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        DetailsModel model = data.get(position);
        h.name.setText("نام: " + model.getUserName());
        h.number.setText("شماره تلفن: " + model.getUserNumber());
        h.code.setText("کد مشتری: " + model.getUserCode());
        h.price.setText("مبلغ دوره: " + Utils.moneySeparator(model.getPrice()));
        h.wallet.setText("مبلغ استفاده شده از کیف پول: " + Utils.moneySeparator(model.getWallet()));
        h.pay.setText("مبلغ پرداختی: " + Utils.moneySeparator(model.getPay()));
        h.length.setText("طول دوره: " + model.getLength() + " " + model.getLenghtType());
        String date = model.getDate();
        DateConverter dateConverter = new DateConverter();

        dateConverter.gregorianToPersian(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7)),Integer.parseInt(date.substring(8,10)));
        h.date.setText("تایخ ثبت: "+dateConverter.getYear()+"/"+dateConverter.getMonth()+"/"+dateConverter.getDay()+" "+date.substring(11,16));

        if(model.getUser_bday()!=null && !model.getUser_bday().isEmpty()){
            h.bday.setText("تاریخ تولد: " + model.getUser_bday());

        }else{
            h.bday.setVisibility(View.GONE);
        }
        if (allUsers) {
           h.type.setText("نام دوره: "+model.getName());
        } else {
           h.type.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, number, code, bday, price, wallet, pay, length,type, date;

        ViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.detail_name);
            number = v.findViewById(R.id.detail_number);
            code = v.findViewById(R.id.detail_code);
            bday = v.findViewById(R.id.detail_bday);
            price = v.findViewById(R.id.detail_price);
            wallet = v.findViewById(R.id.detail_wallet);
            pay = v.findViewById(R.id.detail_pay);
            length = v.findViewById(R.id.detail_length);
            type = v.findViewById(R.id.detail_type);
            date = v.findViewById(R.id.detail_date);
        }
    }
}
