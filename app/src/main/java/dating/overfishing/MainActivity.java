package dating.overfishing;

import android.os.Bundle;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.transition.Fade;
import androidx.transition.Slide;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.ChatsFragment;
import dating.overfishing.ui.main.FavouritesFragment;
import dating.overfishing.ui.main.FiltersFragment;
import dating.overfishing.ui.main.MainViewModel;
import dating.overfishing.ui.main.NoUsersFragment;
import dating.overfishing.ui.main.OwnProfileFragment;
import dating.overfishing.ui.main.ViewProfileFragment;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private Fragment mActiveFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getCurrentProfile().observe(this, this::displayDiscover);

        ((BottomNavigationView) findViewById(R.id.bottom_nav_view)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_discover:
                    if (mActiveFragment.getClass() != ViewProfileFragment.class) {
                        // TODO track whether filters have changed, if so refresh
                        displayDiscover(mViewModel.getCurrentProfile().getValue());
                    }
                    break;
                case R.id.action_profile:
                    if (mActiveFragment.getClass() != OwnProfileFragment.class) {
                        mActiveFragment = OwnProfileFragment.newInstance();
                        displayActiveFragment();
                    }
                    break;
                case R.id.action_filters:
                    if (mActiveFragment.getClass() != FiltersFragment.class) {
                        mActiveFragment = FiltersFragment.newInstance();
                        displayActiveFragment();
                    }
                    break;
                case R.id.action_chats:
                    if (mActiveFragment.getClass() != ChatsFragment.class) {
                        mActiveFragment = ChatsFragment.newInstance();
                        displayActiveFragment();
                    }
                    break;
                case R.id.action_favourites:
                    if (mActiveFragment.getClass() != FavouritesFragment.class) {
                        mActiveFragment = FavouritesFragment.newInstance();
                        displayActiveFragment();
                    }
                    break;
                default:
            }
            return true;
        });
    }

    private void displayDiscover(UserProfile profile) {

        if (profile == null) {
            mActiveFragment = NoUsersFragment.newInstance();
            displayActiveFragment();
        } else {
            Fade fade = new Fade();
            fade.setDuration(200);
            Slide slide = new Slide(Gravity.BOTTOM);
            slide.setStartDelay(400);
            slide.setDuration(300);
            Fragment fragment = ViewProfileFragment.newInstance();
            displayFragmentWithTransition(fragment, fade, slide);
        }
    }

    private void displayActiveFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mActiveFragment)
                .commit();
    }

    // https://medium.com/bynder-tech/how-to-use-material-transitions-in-fragment-transactions-5a62b9d0b26b

    private void displayFragmentWithTransition(Fragment fragment, Object exitTransition, Object enterTransition) {

        // exit transition first
        if (mActiveFragment!= null && exitTransition != null) mActiveFragment.setExitTransition(exitTransition);
        if (enterTransition != null) fragment.setEnterTransition(enterTransition);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        mActiveFragment = fragment;
    }
}
