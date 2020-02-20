package ir.shahabazimi.alphacrossfit.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.adapters.YearAdapter;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.models.StatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatActivity extends AppCompatActivity {

    private RecyclerView yearRecycler;
    private YearAdapter yearAdapter;
    private TextInputEditText year;
    private MaterialButton check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        init();



    }


    private void init(){

        year = findViewById(R.id.stats_search_year);
        check = findViewById(R.id.stats_check);

        yearRecycler = findViewById(R.id.stats_year_recycler);
        yearRecycler.setLayoutManager(new LinearLayoutManager(this));

        onClicks();

    }

    private void onClicks(){


        check.setOnClickListener(w->{
            if(year.getText().toString().isEmpty() || year.getText().toString().length()<4 ||
                    !year.getText().toString().startsWith("139") || year.getText().toString().startsWith("140")){
                Toast.makeText(this, "لطفا مقدار سال را وارد کنید", Toast.LENGTH_SHORT).show();
                return;
            }
            if(Utils.checkInternet(this)) {
                Utils.hideKeyboard(StatActivity.this);
                getYearStats(year.getText().toString());
            }else{
                Toast.makeText(this, "لطفا اتصال به اینترنت را بررسی کنید", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void getYearStats(String year){
        String eYear = String.valueOf(Integer.valueOf(year)+621);
        RetrofitClient.getInstance().getApi()
                .getstats(eYear)
                .enqueue(new Callback<StatResponse>() {
                    @Override
                    public void onResponse(Call<StatResponse> call, Response<StatResponse> response) {
                        if(response.isSuccessful() && response.body()!=null){
                            yearAdapter = new YearAdapter(StatActivity.this,response.body().getData(),year);
                            yearRecycler.setAdapter(yearAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<StatResponse> call, Throwable t) {
                        Toast.makeText(StatActivity.this, "خط! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
