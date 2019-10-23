package dating.overfishing;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.MainViewModel;
import dating.overfishing.ui.main.NoUsersFragment;
import dating.overfishing.ui.main.OwnProfileFragment;
import dating.overfishing.ui.main.ViewProfileFragment;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getCurrentProfile().observe(this, this::displayDiscover);

        ((BottomNavigationView) findViewById(R.id.bottom_nav_view)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_discover:
                    displayDiscover(mViewModel.getCurrentProfile().getValue());
                    break;
                case R.id.action_profile:
                    displayFragment(OwnProfileFragment.newInstance());
                    break;
                default:
            }
            return true;
        });
    }

    private void displayDiscover(UserProfile profile) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.container, profile != null ? ViewProfileFragment.newInstance() : NoUsersFragment.newInstance()
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
