package dating.overfishing;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dating.overfishing.login.PhoneLoginActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_phone_button).setOnClickListener(v -> {
            startActivity(new Intent(this, PhoneLoginActivity.class));
        });
        findViewById(R.id.login_facebook_button).setOnClickListener(v -> gotoMain());

        setAnimatedBackground();
    }

    private void setAnimatedBackground() {
        AnimationDrawable animationDrawable = (AnimationDrawable) findViewById(R.id.login_layout).getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }

    private void gotoMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
