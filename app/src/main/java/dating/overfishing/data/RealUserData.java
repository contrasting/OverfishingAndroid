package dating.overfishing.data;

import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RealUserData implements UserDataProvider {

    public static final String TAG = RealUserData.class.getSimpleName();

    public static final String SERVICE_URL_DEV = "http://54.77.77.247:8124/";
    public static final String ARGHA_ID = "5dbb9610f7e56679a70a6be2";

    private Queue<UserProfile> mUserProfiles = new ArrayDeque<>();

    private Listener mListener;

    public RealUserData(Listener listener) {
        mListener = listener;
    }

    @Override
    public UserProfile moveOnToNext() {
        return null;
    }

    @Override
    public UserProfile getLast() {
        return null;
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
        return null;
    }

    @Override
    public void getMoreUsers(Filters filters) {

    }

    @Override
    public UserProfile getPinnedUser() {
        return null;
    }

    @Override
    public List<UserProfile> getLikedUsers() {
        return null;
    }

    @Override
    public void getProfileFromId(String otherID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL_DEV)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        UserDataService service = retrofit.create(UserDataService.class);
        service.retrieveUserWithId(otherID).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                Log.e(TAG, response.message());
                mListener.onUserWithIdFound(response.body());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public interface UserDataService {
        @GET("users/{user}")
        Call<UserProfile> retrieveUserWithId(@Path("user") String userId);
    }
}
