package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.adapters.MonthAdapter;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.models.StatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonthActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView recyclerView;
    private MonthAdapter adapter;

    private String month,year,eyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        init();
    }

    private void init(){
        title = findViewById(R.id.month_title);
        recyclerView = findViewById(R.id.month_recycler);

        Bundle b = getIntent().getExtras();
        if(b==null){
            Toast.makeText(this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else{
            month = b.getString("month","");
            year = b.getString("year","");
            eyear = b.getString("eyear","");
        }

        String monthTitle="";

        switch (Integer.parseInt(month)){
            case 1:
                monthTitle="فروردین";
                break;
            case 2:
                monthTitle="اردیبهشت";
                break;
            case 3:
                monthTitle="خرداد";
                break;
            case 4:
                monthTitle="تیر";
                break;
            case 5:
                monthTitle="مرداد";
                break;
            case 6:
                monthTitle="شهریور";
                break;
            case 7:
                monthTitle="مهر";
                break;
            case 8:
                monthTitle="آبان";
                break;
            case 9:
                monthTitle="آذر";
                break;
            case 10:
                monthTitle="دی";
                break;
            case 11:
                monthTitle="بهمن";
                break;
            case 12:
                monthTitle="اسفند";
                break;
            case 13:
                monthTitle="امسال";
                break;
            case 14:
                monthTitle="کل";
                break;
        }

        if(monthTitle.equals("امسال"))
        title.setText("اطلاعات سال "+year);
        else if(monthTitle.equals("کل"))
            title.setText("اطلاعات کل");
        else
            title.setText("اطلاعات "+monthTitle+" ماه سال "+year);


        getData();
    }

    private void getData(){
        RetrofitClient.getInstance().getApi()
                .getmonth(eyear,month)
                .enqueue(new Callback<StatResponse>() {
                    @Override
                    public void onResponse(Call<StatResponse> call, Response<StatResponse> response) {
                        if(response.isSuccessful()&& response.body()!=null){
                            adapter = new MonthAdapter(MonthActivity.this,response.body().getData(),year,month);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MonthActivity.this));
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(MonthActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatResponse> call, Throwable t) {
                        Toast.makeText(MonthActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
    }
}
