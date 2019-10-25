package dating.overfishing.data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class FakeUserData implements UserDataProvider {

    private UserProfile mCurrentProfile;
    private Deque<UserProfile> mUserProfiles = new ArrayDeque<>();
    private Listener mListener;

    public FakeUserData(Listener listener) {
        mListener = listener;
        addFakeUsers();
    }

    private void addFakeUsers() {
        mUserProfiles.add(
                new UserProfile(new ArrayList<>(
                        Arrays.asList("https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-1/p200x200/67745945_10219547573225969_7196020350700748800_n.jpg?_nc_cat=102&_nc_oc=AQlLKJ5ZcqUfpLT34Ud7p6MyH3EYM_jyP9jLge40Ijcpun_Op1Q5F6V9gBcRGlm4JNeQ_bHKlKX-6io4csU0SmSF&_nc_ht=scontent-lht6-1.xx&oh=e502417b7c097acf25d0a68800c8a11d&oe=5E2630C3",
                                "https://scontent-lht6-1.xx.fbcdn.net/v/t1.15752-9/62473747_308112473430934_694946835436929024_n.jpg?_nc_cat=105&_nc_oc=AQlM0QzvSoxzWmGAeR5CTrn3xVyWO4-GhkutBSOhpWDJqBNVqfg5swCkwiv8Pm7Y9tmKXDKKi6NWyvGr9TiIxFmg&_nc_ht=scontent-lht6-1.xx&oh=9fae43f034c9aab53fa632e41b3a72db&oe=5E1901C2")),
                        "Argha",
                        25,
                        23,
                        "Technology entrepreneur. \n\nGamer and traveller. 13 countries and counting \nThird culture kid and trilingual",
                        "Warwick University")
        );

        mUserProfiles.add(
                new UserProfile(new ArrayList<>(
                        Collections.singleton("https://t5.rbxcdn.com/1eed2d39e12b7c67baedb0f643a7a839")),
                        "Legoi",
                        16,
                        3,
                        "Oh my god this is so ah wtefa; sldkfj!!!",
                        "University of Wfoslefj"
                )
        );
        mCurrentProfile = mUserProfiles.poll();
    }

    @Override
    public synchronized UserProfile moveOnToNext() {
        mCurrentProfile = mUserProfiles.poll();
        return mCurrentProfile;
    }

    @Override
    public synchronized UserProfile getLast() {
        return mCurrentProfile;
    }

    @Override
    public void passUserWithId(String id) {

    }

    @Override
    public void likeUserWithId(String id, String message) {

    }

    @Override
    public void pinUserWithId(String id) {

    }

    @Override
    public UserProfile getOwnProfile() {
        return new UserProfile(new ArrayList<>(
                Arrays.asList("https://petapixel.com/assets/uploads/2019/02/mooncompositemain-800x800.jpg",
                        "https://i5.walmartimages.com/asr/49d44f88-30e4-4bdd-a89d-74f795d3efdc_1.4739ce0b7de1063bc98c6091137e7ad3.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF")),
                "Fred",
                25,
                5,
                "Hi it's me I made this app",
                "University of Cambridge"
        );
    }

    @Override
    public void getMoreUsers(Filters filters) {
        addFakeUsers();
        mListener.onMoreUsersFound(mCurrentProfile);
    }

}
