package dating.overfishing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.MainViewModel;
import dating.overfishing.ui.main.NoUsersFragment;
import dating.overfishing.ui.main.ViewProfileFragment;

public class MainActivity extends AppCompatActivity implements ViewProfileFragment.ProfileActionListener {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        displayCurrentProfile();
    }

    private void displayCurrentProfile() {
        displayDiscover(mViewModel.getLatestUserProfile());
    }

    private void displayNextProfile() {
        displayDiscover(mViewModel.nextUser());
    }

    private void displayDiscover(UserProfile profile) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.container, profile != null ? ViewProfileFragment.newInstance(profile) : NoUsersFragment.newInstance()
                )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();
    }

    @Override
    public void likeUser(String id) {
        mViewModel.likeUser(id);
        displayNextProfile();
    }

    @Override
    public void passUser(String id) {
        mViewModel.passUser(id);
        displayNextProfile();
    }

    @Override
    public void pinUser(String id) {
        mViewModel.pinUser(id);
        displayNextProfile();
    }
}
