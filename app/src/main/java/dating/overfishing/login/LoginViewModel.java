package dating.overfishing.login;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginViewModel extends ViewModel {

    public final String TAG = getClass().getSimpleName();

    private FirebaseAuth mFirebaseAuth;

    private MutableLiveData<Boolean> mShouldFinishLogin = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> mIsCodeError = new MutableLiveData<>(false);
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    public LoginViewModel() {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public void signInWithPhoneNumber(String phoneNumber, PhoneLoginActivity activity) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                activity,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        // This callback will be invoked in two situations:
                        // 1 - Instant verification. In some cases the phone number can be instantly
                        //     verified without needing to send or enter a verification code.
                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
                        //     detect the incoming verification SMS and perform verification without
                        //     user action.
                        // Log.d(TAG, "onVerificationCompleted:" + credential);

                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        // This callback is invoked when an invalid request for verification is made,
                        // for instance if the the phone number format is not valid.
                        Log.w(TAG, "onVerificationFailed", e);

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            // ...
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // The SMS quota for the project has been exceeded
                            // ...
                        }

                        // Show a message and update the UI

                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {
                        // The SMS verification code has been sent to the provided phone number, we
                        // now need to ask the user to enter the code and then construct a credential
                        // by combining the code with a verification ID.
                        Log.d(TAG, "onCodeSent:" + verificationId);

                        // Save verification ID and resending token so we can use them later
                        mVerificationId = verificationId;
                        mResendToken = token;

                        // ...
                    }
                });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            task.getResult().getUser().getIdToken(true).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful())
                                    Log.e(TAG, task1.getResult().getToken());
                            });

                            // if (mPreferences.getBoolean("push_notifications", true)) {
                            //     generateNewFirebaseInstanceId();
                            // }
                            //
                            // if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                            //     mPreferences.edit().putBoolean("firstTime", true).apply();
                            //     startActivity(new Intent(PhoneLoginActivity.this, InitUserDataActivity.class));
                            //     return;
                            // }

                            mShouldFinishLogin.postValue(true);

                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                mIsCodeError.postValue(true);
                            }
                        }
                    }
                });
    }

    public void onVerificationCodeEntered(CharSequence code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code.toString());
        signInWithPhoneAuthCredential(credential);
    }

    public LiveData<Boolean> isCodeError() {
        return mIsCodeError;
    }

    public LiveData<Boolean> shouldFinish() {
        return mShouldFinishLogin;
    }

}
