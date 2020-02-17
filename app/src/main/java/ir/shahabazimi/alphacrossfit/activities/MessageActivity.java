package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.models.GeneralResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends AppCompatActivity {

    private MaterialButton send;
    private TextInputEditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();
    }

    private void init(){
        text = findViewById(R.id.sms_text);
        send = findViewById(R.id.sms_send);
        onClicks();
    }

    private void onClicks() {


        send.setOnClickListener(w->{
            String t= text.getText().toString().trim();

            if(t.isEmpty()){
                Toast.makeText(MessageActivity.this, "لطفا متن پیام را وارد کنید", Toast.LENGTH_SHORT).show();
            }else{
                if(Utils.checkInternet(MessageActivity.this))
                    sendSms(t);
                else
                    Toast.makeText(MessageActivity.this, "لطفا دسترسی به اینترنت را بررسی کنید!", Toast.LENGTH_SHORT).show();

            }


        });

    }

    private void sendSms(String text){
        RetrofitClient.getInstance().getApi()
                .sendsms(text)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful() && response.body()!=null){
                            if(response.body().getMessage().equals("success")) {
                                Toast.makeText(MessageActivity.this, "با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else{
                                Toast.makeText(MessageActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(MessageActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(MessageActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
