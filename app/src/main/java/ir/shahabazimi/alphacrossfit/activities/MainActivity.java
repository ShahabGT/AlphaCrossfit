package ir.shahabazimi.alphacrossfit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Objects;

import ir.shahabazimi.alphacrossfit.R;
import ir.shahabazimi.alphacrossfit.dialogs.RegisterDialog;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.main_reg).setOnClickListener(w->{
            RegisterDialog dialog = new RegisterDialog(MainActivity.this);
            dialog.setCanceledOnTouchOutside(true);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        });

        findViewById(R.id.main_service).setOnClickListener(w->{
            startActivity(new Intent(MainActivity.this,ServiceActivity.class));
            overridePendingTransition(R.anim.fadein,R.anim.fadeout);

        });

        findViewById(R.id.main_message).setOnClickListener(w->{
            startActivity(new Intent(MainActivity.this,MessageActivity.class));
            overridePendingTransition(R.anim.fadein,R.anim.fadeout);

        });


    }
}
