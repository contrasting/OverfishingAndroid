package dating.overfishing.ui.main;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import dating.overfishing.data.UserProfile;

public class MainViewModel extends ViewModel {

    private Stack<UserProfile> mUserProfiles = new Stack<>();

    public UserProfile getLatestUserProfile() {
        // TODO what if empty
        return new UserProfile(new ArrayList<>(
                Arrays.asList("https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-1/p200x200/67745945_10219547573225969_7196020350700748800_n.jpg?_nc_cat=102&_nc_oc=AQlLKJ5ZcqUfpLT34Ud7p6MyH3EYM_jyP9jLge40Ijcpun_Op1Q5F6V9gBcRGlm4JNeQ_bHKlKX-6io4csU0SmSF&_nc_ht=scontent-lht6-1.xx&oh=e502417b7c097acf25d0a68800c8a11d&oe=5E2630C3",
                        "https://scontent-lht6-1.xx.fbcdn.net/v/t1.15752-9/62473747_308112473430934_694946835436929024_n.jpg?_nc_cat=105&_nc_oc=AQlM0QzvSoxzWmGAeR5CTrn3xVyWO4-GhkutBSOhpWDJqBNVqfg5swCkwiv8Pm7Y9tmKXDKKi6NWyvGr9TiIxFmg&_nc_ht=scontent-lht6-1.xx&oh=9fae43f034c9aab53fa632e41b3a72db&oe=5E1901C2")),
                "Argha",
                25,
                23,
                "I only like Indian girls who are immigrants lololololol",
                "Warwick University");
    }
}
