package dating.overfishing;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.AbstractViewProfileFragment;
import dating.overfishing.ui.main.NestedViewProfileFragment;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        UserProfile profile = (UserProfile) getIntent().getSerializableExtra(AbstractViewProfileFragment.PROFILE);

        if (profile != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NestedViewProfileFragment.newInstance(profile))
                    .commit();
        }
    }

}
