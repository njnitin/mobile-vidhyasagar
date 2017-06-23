package com.jain.vidhyasagarsant.ui.contactUs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jain.vidhyasagarsant.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.iamBedant.starter.utils.GoogleAnalyticsManager;

import com.jain.vidhyasagarsant.R;

public class ContactUsActivity extends BaseActivity implements ContactUsMvpView {

    @BindView(R.id.cv_phone)
    CardView mCardViewPhone;

    @BindView(R.id.cv_email)
    CardView mCardViewEmail;

    @BindView(R.id.cv_address)
    CardView mCardViewAddress;

    @BindView(R.id.tv_phone_number)
    TextView mTextViewPhoneNumber;

    @BindView(R.id.tv_email)
    TextView mTextViewEmail;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private String mHeading = "Contact Us";

    @Inject
    ContactUsMvpPresenter<ContactUsMvpView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CONTACT_US_VISITED);
        setUp();
    }

    @Override
    protected void setUp() {
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ContactUsActivity.this);

        mToolbar.setTitle(mHeading);
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({R.id.cv_phone , R.id.cv_address})
    public void PhoneClicked(){
        mPresenter.phoneClicked(mTextViewPhoneNumber.getText().toString().trim());
    }

    @Override
    public void call(Intent intent) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    123);
        }
        else {
            startActivity(intent);
            GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CONTACT_CALL_CLICKED);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    mPresenter.phoneClicked(mTextViewPhoneNumber.getText().toString().trim());
                } else {
                    Toast.makeText(this, "You must provide permission to call the given number", Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void sendEmail(Intent emailIntent) {
        try {
            startActivity(emailIntent);
            GoogleAnalyticsManager.trackGoogleAnalyticsEvent(GoogleAnalyticsManager.CONTACT_EMAIL_CLICKED);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.no_email_apps_error_message, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cv_email)
    public void EmailClicked(){
        mPresenter.emailClicked(mTextViewEmail.getText().toString().trim());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
