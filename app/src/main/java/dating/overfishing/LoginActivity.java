package dating.overfishing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_phone_button).setOnClickListener(v -> gotoMain());

        findViewById(R.id.login_facebook_button).setOnClickListener(v -> gotoMain());
    }

    private void gotoMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
