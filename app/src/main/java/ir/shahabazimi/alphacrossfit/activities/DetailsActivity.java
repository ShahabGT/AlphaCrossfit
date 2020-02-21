package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.adapters.DetailsAdapter;
import ir.shahabazimi.alphacrossfit.adapters.MonthAdapter;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.models.DetailsResponse;
import ir.shahabazimi.alphacrossfit.models.StatResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String month,year,eyear;
    private DetailsAdapter adapter;
    private boolean allUsers =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();

    }

    private void init(){
        recyclerView = findViewById(R.id.details_recycler);

        Bundle b = getIntent().getExtras();
        if(b==null){
            Toast.makeText(this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }else{
            month = b.getString("month","");
            year = b.getString("year","");
            eyear = b.getString("eyear","");

            if(month.equals("14"))
                allUsers=true;
        }

        getData();
    }

    private void getData(){

        RetrofitClient.getInstance().getApi()
                .getdetails(eyear,month)
                .enqueue(new Callback<DetailsResponse>() {
                    @Override
                    public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                        if(response.isSuccessful()&& response.body()!=null){

                            adapter = new DetailsAdapter(response.body().getData(), allUsers);
                            recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                            recyclerView.setAdapter(adapter);
                        }else{
                            Toast.makeText(DetailsActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailsResponse> call, Throwable t) {
                        Toast.makeText(DetailsActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
    }
}
