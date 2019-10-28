package dating.overfishing;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.AbstractViewProfileFragment;
import dating.overfishing.ui.main.NestedViewProfileFragment;
import dating.overfishing.ui.main.favourites.LikeViewProfileFragment;

public class ViewProfileActivity extends AppCompatActivity {

    public static final String SHOW_BUTTONS = "showButtons";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        UserProfile profile = (UserProfile) getIntent().getSerializableExtra(AbstractViewProfileFragment.PROFILE);
        boolean shouldShowButtons = getIntent().getBooleanExtra(SHOW_BUTTONS, false);

        if (profile != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,
                            shouldShowButtons ? LikeViewProfileFragment.newInstance(profile) :
                                    NestedViewProfileFragment.newInstance(profile))
                    .commit();
        }
    }

}
