package dating.overfishing.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import net.rimoto.intlphoneinput.IntlPhoneInput;

import dating.overfishing.MainActivity;
import dating.overfishing.R;

public class PhoneLoginActivity extends AppCompatActivity {

    public static final String TAG = "PhoneLoginActivity";

    private LoginViewModel mLoginViewModel;

    private IntlPhoneInput mPhoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        Toolbar toolbar = findViewById(R.id.phone_login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mPhoneInput = findViewById(R.id.phone_input);

        findViewById(R.id.phone_login_button).setOnClickListener(view -> {
            if (!mPhoneInput.isValid()) {
                Toast.makeText(PhoneLoginActivity.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            signInWithPhoneNumber();
        });

        mLoginViewModel.shouldFinish().observe(this, shouldFinish -> {
            if (shouldFinish) {
                finishAffinity();
                startActivity(new Intent(PhoneLoginActivity.this, MainActivity.class));
            }
        });
    }

    private void signInWithPhoneNumber() {
        mLoginViewModel.signInWithPhoneNumber(mPhoneInput.getNumber(), this);
        new PhoneCodeDialog().show(getSupportFragmentManager(), "phoneCode");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return false;
    }
}
