package dating.overfishing.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import dating.overfishing.R;

public class PhoneCodeDialog extends DialogFragment {

    private TextView mInvalidCode;
    private ProgressBar mProgressBar;
    private LoginViewModel mLoginViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        mLoginViewModel.isCodeError().observe(this, isError -> {
            if (isError) {
                mProgressBar.setVisibility(View.GONE);
                mInvalidCode.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_phone_code, container, false);

        PinEntryEditText pinEntry = v.findViewById(R.id.phone_code_entry);
        pinEntry.requestFocus();

        mInvalidCode = v.findViewById(R.id.phone_code_invalid);
        mProgressBar = v.findViewById(R.id.code_progress_bar);

        // this only gets called when the full pin is entered
        pinEntry.setOnPinEnteredListener(code -> {
            mProgressBar.setVisibility(View.VISIBLE);
            mLoginViewModel.onVerificationCodeEntered(code);
        });
        return v;
    }

}
