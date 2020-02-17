package ir.shahabazimi.alphacrossfit.activities;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.classes.Utils;
import ir.shahabazimi.alphacrossfit.data.RetrofitClient;
import ir.shahabazimi.alphacrossfit.models.GeneralResponse;
import ir.shahabazimi.alphacrossfit.models.PointsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {

    private TextInputEditText userName,points,points2, userPoints, userNewPoints, code, wallet;
    private MaterialButton pointsSave, userPointsSave;
    private String cName = "";
    private String cNumber = "";
    private String cId = "";
    private String cPoints = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }

    private void init() {
        points = findViewById(R.id.setting_points);
        points2 = findViewById(R.id.setting_points2);
        wallet = findViewById(R.id.setting_wallet);
        pointsSave = findViewById(R.id.points_check);

        code = findViewById(R.id.setting_usercode);
        userPoints = findViewById(R.id.setting_userpoints);
        userNewPoints = findViewById(R.id.setting_userpoints_new);
        userPointsSave = findViewById(R.id.userpoints_check);
        userName = findViewById(R.id.setting_usercode_name);

        userName.setVisibility(View.GONE);
        userPointsSave.setVisibility(View.GONE);
        userPoints.setVisibility(View.GONE);
        userNewPoints.setVisibility(View.GONE);

        getPoints();
        onClicks();
    }

    private void onClicks() {

        code.addTextChangedListener(new TextWatcher() {
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
                    cPoints = "";
                    userName.setVisibility(View.GONE);
                    userPointsSave.setVisibility(View.GONE);
                    userPoints.setVisibility(View.GONE);
                    userNewPoints.setVisibility(View.GONE);

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
                    cPoints = "";
                    userName.setVisibility(View.GONE);
                    userPointsSave.setVisibility(View.GONE);
                    userPoints.setVisibility(View.GONE);
                    userNewPoints.setVisibility(View.GONE);

                }
            }
        });


        pointsSave.setOnClickListener(z -> {
            Utils.hideKeyboard(SettingActivity.this);
            String p = points.getText().toString().trim().replace(" ","");
            String p2 = points2.getText().toString().trim().replace(" ","");
            String w = wallet.getText().toString().trim().replace(" ","");
            if (p.isEmpty() || w.isEmpty() || p2.isEmpty() ) {
                Toast.makeText(this, "لطفا مقادیر امتیاز و کیف پول را بررسی کنید", Toast.LENGTH_SHORT).show();
            } else {
                if (Utils.checkInternet(SettingActivity.this))
                    sendPoints(p, w,p2);
                else
                    Toast.makeText(SettingActivity.this, "لطفا دسترسی به اینترنت را بررسی کنید!", Toast.LENGTH_SHORT).show();

            }


        });

        userPointsSave.setOnClickListener(z -> {
            Utils.hideKeyboard(SettingActivity.this);
            String p = userNewPoints.getText().toString().trim().replace(" ","");

            if (p.isEmpty() || cId.isEmpty()) {
                Toast.makeText(this, "لطفا مقدار امتیاز را بررسی کنید", Toast.LENGTH_SHORT).show();
            } else {
                if (Utils.checkInternet(SettingActivity.this))
                    sendUserPoints(cId, p);
                else
                    Toast.makeText(SettingActivity.this, "لطفا دسترسی به اینترنت را بررسی کنید!", Toast.LENGTH_SHORT).show();

            }


        });


    }

    private void getPoints() {
        RetrofitClient.getInstance().getApi()
                .getpoints()
                .enqueue(new Callback<PointsResponse>() {
                    @Override
                    public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().getData().size() > 0) {
                            points.setText(response.body().getData().get(0).getAmount());
                            wallet.setText(response.body().getData().get(1).getAmount());
                            points2.setText(response.body().getData().get(2).getAmount());

                        } else {
                            Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        }
                    }

                    @Override
                    public void onFailure(Call<PointsResponse> call, Throwable t) {
                        Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                });
    }

    private void sendPoints(String point, String wallet,String point2) {
        RetrofitClient.getInstance().getApi()
                .setpoints(point, wallet,point2)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful() && response.body()!=null) {
                            if(response.body().getMessage().equals("success"))
                                Toast.makeText(SettingActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();


                        } else
                            Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void sendUserPoints(String userId, String points) {
        RetrofitClient.getInstance().getApi()
                .setuserpoints(userId, points)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful() && response.body()!=null) {
                            if(response.body().getMessage().equals("success"))
                                Toast.makeText(SettingActivity.this, "با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();


                        } else
                            Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Toast.makeText(SettingActivity.this, "خطا! لطفا دوباره امتحان کنید", Toast.LENGTH_SHORT).show();

                    }
                });

    }


    private void searchCode(String code) {
        Utils.hideKeyboard(SettingActivity.this);

        cName = "";
        cNumber = "";
        cId = "";
        cPoints = "";
        userName.setVisibility(View.GONE);
        userPointsSave.setVisibility(View.GONE);
        userPoints.setVisibility(View.GONE);
        userNewPoints.setVisibility(View.GONE);


        RetrofitClient.getInstance().getApi().getuserpoints(code)
                .enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if(response.body().getMessage().equals("success")) {
                                userName.setVisibility(View.VISIBLE);
                                userPointsSave.setVisibility(View.VISIBLE);
                                userPoints.setVisibility(View.VISIBLE);
                                userNewPoints.setVisibility(View.VISIBLE);
                                cName = response.body().getName();
                                cId = response.body().getId();
                                cNumber = response.body().getNumber();
                                cPoints = response.body().getPoints();
                                userName.setText(cName + " " + cNumber);
                                userPoints.setText(cPoints);
                            }else if(response.body().getMessage().equals("empty")){
                                userName.setVisibility(View.VISIBLE);
                                userName.setText("کاربر وجود ندارد");
                                userPointsSave.setVisibility(View.GONE);
                                userPoints.setVisibility(View.GONE);
                                userNewPoints.setVisibility(View.GONE);
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {

                    }
                });
    }
}
