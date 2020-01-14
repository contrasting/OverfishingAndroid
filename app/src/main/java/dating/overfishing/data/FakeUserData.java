package dating.overfishing.data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
                getArgha()
        );

        mUserProfiles.add(
                getLegoi()
        );
        mCurrentProfile = mUserProfiles.poll();
    }

    public static UserProfile getLegoi() {
        return new UserProfile(new ArrayList<>(
                Arrays.asList("https://www.nps.gov/miss/learn/nature/images/20150427-0409.jpg?maxwidth=650&autorotate=false", "https://a.wattpad.com/cover/60596436-352-k319779.jpg")),
                null,
                "Olivia",
                23,
                6,
                null,
                "Is it time for me yet?",
                "University of Kent",
                "Professional Stalker");
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
        return getFred();
    }

    @Override
    public void getMoreUsers(Filters filters) {
        addFakeUsers();
        mListener.onMoreUsersFound(mCurrentProfile);
    }

    @Override
    public UserProfile getPinnedUser() {
        return getOwnProfile();
    }

    @Override
    public List<UserProfile> getLikedUsers() {
        List<UserProfile> tempList = new ArrayList<>();
        tempList.add(getFred());
        tempList.add(getArgha());
        tempList.add(getLegoi());
        return tempList;
    }

    @Override
    public void getProfileFromId(String otherID) {
        mListener.onUserWithIdFound(getArgha());
    }

    public static UserProfile getArgha() {
        return new UserProfile(new ArrayList<>(
                Arrays.asList("https://scontent-lht6-1.xx.fbcdn.net/v/t1.0-1/p200x200/67745945_10219547573225969_7196020350700748800_n.jpg?_nc_cat=102&_nc_oc=AQlLKJ5ZcqUfpLT34Ud7p6MyH3EYM_jyP9jLge40Ijcpun_Op1Q5F6V9gBcRGlm4JNeQ_bHKlKX-6io4csU0SmSF&_nc_ht=scontent-lht6-1.xx&oh=e502417b7c097acf25d0a68800c8a11d&oe=5E2630C3",
                        "https://scontent-lht6-1.xx.fbcdn.net/v/t1.15752-9/62473747_308112473430934_694946835436929024_n.jpg?_nc_cat=105&_nc_oc=AQlM0QzvSoxzWmGAeR5CTrn3xVyWO4-GhkutBSOhpWDJqBNVqfg5swCkwiv8Pm7Y9tmKXDKKi6NWyvGr9TiIxFmg&_nc_ht=scontent-lht6-1.xx&oh=9fae43f034c9aab53fa632e41b3a72db&oe=5E1901C2")),
                null,
                "Argha",
                25,
                23,
                null,
                "Technology entrepreneur. \n\nGamer and traveller. 13 countries and counting \n\nThird culture kid and trilingual",
                "Warwick University",
                null);
    }

    public static UserProfile getFred() {
        return new UserProfile(new ArrayList<>(
                Arrays.asList("https://scontent.flis5-1.fna.fbcdn.net/v/t1.0-9/48995983_10161446075920314_7922672724911063040_o.jpg?_nc_cat=105&_nc_oc=AQlyq3C81_eXEzEsR69GWcV_H409YYn6sX41CswXP6tNmRHOVPW2qWdrnUYlrEw5prkYyyXmnjAMjyLfU426eHKH&_nc_ht=scontent.flis5-1.fna&oh=e55658c66cfc21edcfa3744d0bd3e285&oe=5E62E64D",
                        "https://scontent.flis5-1.fna.fbcdn.net/v/t31.0-8/26197928_10160001433390314_322605889162436410_o.jpg?_nc_cat=108&_nc_oc=AQlzGdq6vLtRSzpNhb6xDZLoN7_lv5tefKzDPmg1dLG5zv6UuTiE1m2-Nf2mF1_2rH7qLP2VEFseYRAh-yzuGo0H&_nc_ht=scontent.flis5-1.fna&oh=33ca43624e6c442d1a8ab0fa9b004d2a&oe=5E4E4128")),
                null,
                "Fred",
                25,
                5,
                null,
                "Hi it's me I made this app",
                "University of Cambridge",
                null);
    }

}
