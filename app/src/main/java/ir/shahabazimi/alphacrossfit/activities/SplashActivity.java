package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ir.shahabazimi.alphacrossfit.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(()->{
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            SplashActivity.this.finish();


        },2500);
    }
}
