package dating.overfishing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dating.overfishing.ui.main.ViewProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ViewProfileFragment.newInstance())
                    .commitNow();
        }
    }
}
