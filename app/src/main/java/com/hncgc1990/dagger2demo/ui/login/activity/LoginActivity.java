package com.hncgc1990.dagger2demo.ui.login.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hncgc1990.dagger2demo.DemoApplication;
import com.hncgc1990.dagger2demo.R;
import com.hncgc1990.dagger2demo.injection.component.DaggerLoginActivityComponent;
import com.hncgc1990.dagger2demo.ui.login.contract.LoginContract;
import com.hncgc1990.dagger2demo.ui.login.presenter.LoginPresent;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewEditorActionEvent;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 修改的AS自带的登录界面模板
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.login_progress)
    ProgressBar mProgressView;

    @BindView(R.id.email)
    EditText mEmailView;


    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;

    @BindView(R.id.email_login_form)
    LinearLayout mLoginFormView;


    @BindView(R.id.login_form)
    ScrollView loginForm;




    @Inject
    LoginPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginActivityComponent.builder()
                .applicationComponent(((DemoApplication)getApplication()).getApplicationComponent())
//                .loginActivityModule(new LoginActivityModule(this))
                .build()
                .inject(this);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mPresent.attachView(this);

        RxTextView.editorActionEvents(mPasswordView).subscribe(new Consumer<TextViewEditorActionEvent>() {
            @Override
            public void accept(TextViewEditorActionEvent textViewEditorActionEvent) throws Exception {
                int id = textViewEditorActionEvent.actionId();
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    mPresent.login();
                }
            }
        });

        RxView.clicks(mEmailSignInButton)
                .debounce(500, TimeUnit.MICROSECONDS)
                .subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mPresent.login();
            }
        });

    }


    @Override
    public void setPresenter(LoginPresent presenter) {
        mPresent = presenter;
    }

    @Override
    public String getUserName() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPass() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void resetError() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void showLoginSuccess() {
        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFail() {
        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
    }


    @Override
    public void showLoading(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    @Override
    public void showUserNameError(String error) {
        mEmailView.setError(error);
        mEmailView.requestFocus();
    }

    @Override
    public void showPassError(String error) {
        mPasswordView.setError(error);
        mPasswordView.requestFocus();
    }


}

