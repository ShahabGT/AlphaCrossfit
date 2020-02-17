package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.classes.ConfirmInterface;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.dialogs.ConfirmDialog;
import ir.shahabazimi.alphacrossfit.models.GeneralResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceActivity extends AppCompatActivity {

    private TextInputEditText search,name,price,type,length;
    private MaterialButton reg;
    private Spinner lenghtType;
    private String cName = "";
    private String cNumber = "";
    private String cId = "";
    private String cWallet = "";
    private String cCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        init();
    }

    private void init(){
        search = findViewById(R.id.service_code);
        name = findViewById(R.id.service_name);
        price = findViewById(R.id.service_price);
        type = findViewById(R.id.service_type);
        length = findViewById(R.id.service_length);

        reg = findViewById(R.id.service_reg);

        lenghtType = findViewById(R.id.service_length_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.length, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lenghtType.setAdapter(adapter);

        onClicks();
    }

    private void onClicks(){

        reg.setOnClickListener(w->{
            Utils.hideKeyboard(ServiceActivity.this);
            String p = price.getText().toString().trim().replace(",","");
            String t = type.getText().toString().trim();
            String l = length.getText().toString().trim();

            if (p.isEmpty() || t.isEmpty() || l.isEmpty()) {

                Toast.makeText(this, "لطفا لیست خدمات را کامل کنید", Toast.LENGTH_SHORT).show();
            } else if (cId.isEmpty()) {
                Toast.makeText(this, "لطفا شماره مشتری را وارد کنید", Toast.LENGTH_SHORT).show();

            } else {

                ConfirmDialog dialog = new ConfirmDialog(ServiceActivity.this, cCode, cName, cWallet, p, new ConfirmInterface() {
                    @Override
                    public void onClick(String amount, String wallet, String pay) {
                       // buy(amount, wallet, pay);
                    }
                });
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                price.removeTextChangedListener(this);

                String value = price.getText().toString();


                if (!value.equals("")) {
                    if (value.startsWith("0") && !value.startsWith("0.")) {
                        price.setText("");
                    }


                    String str = price.getText().toString().replaceAll(",", "");
                    price.setText(Utils.moneySeparator(str));
                    price.setSelection(price.getText().toString().length());

                }
                price.addTextChangedListener(this);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 6) {
                    searchCode(s.toString());
                } else {
                    cName = "";
                    cNumber = "";
                    cId = "";
                    cWallet = "";
                    cCode = "";
                    name.setText("");

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 6) {
                    searchCode(s.toString());

                } else {
                    cName = "";
                    cNumber = "";
                    cId = "";
                    cWallet = "";
                    cCode = "";
                    name.setText("");

                }
            }
        });
    }

    private void searchCode(String code) {
        Utils.hideKeyboard(ServiceActivity.this);

        cName = "";
        cNumber = "";
        cId = "";
        cWallet = "";
        cCode = "";


        RetrofitClient.getInstance().getApi().search(code)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if(response.body().getMessage().equals("success")) {
                                cName = response.body().getName();
                                cId = response.body().getId();
                                cNumber = response.body().getNumber();
                                cWallet = response.body().getWallet();
                                cCode = code;
                                name.setText(cName);

                            }else if(response.body().getMessage().equals("empty")){
                                name.setText("کاربر وجود ندارد");

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {

                    }
                });
    }

}
