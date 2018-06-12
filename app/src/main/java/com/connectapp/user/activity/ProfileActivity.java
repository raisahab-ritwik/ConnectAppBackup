package com.connectapp.user.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.connectapp.user.R;
import com.connectapp.user.util.Util;
import com.connectapp.user.volley.ServerResponseCallback;
import com.connectapp.user.volley.VolleyTaskManager;

import org.json.JSONObject;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements ServerResponseCallback {

    private Context mContext;
    private TextView tv_name, tv_phone, tv_email;
    private VolleyTaskManager volleyTaskManager;
    private boolean isFetchProfileService = false, isUpdateProfileService = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mContext = ProfileActivity.this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("  Edit Profile");

        tv_name= findViewById(R.id.tv_name);
        tv_phone= findViewById(R.id.tv_phone);
        tv_email= findViewById(R.id.tv_email);

        volleyTaskManager = new VolleyTaskManager(mContext);

        fetchUserProfile();

    }

    private void fetchUserProfile() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userID", "" + Util.fetchUserClass(mContext).getUserId());
        volleyTaskManager.doFetchUserProfile(hashMap, true);
        isFetchProfileService = true;
    }

    public void onEditPhoneClick(View view) {

    }

    public void onEditEmailClick(View view) {

    }

    @Override
    public void onSuccess(JSONObject resultJsonObject) {
        Log.e("Response", "Response: " + resultJsonObject);
        if (isFetchProfileService) {
            isFetchProfileService = false;
            if (resultJsonObject.optString("code").equalsIgnoreCase("200")) {
                JSONObject data = resultJsonObject.optJSONObject("data");
                String name = data.optString("userName");
                String phone = data.optString("Phone");
                String email = data.optString("email");

                updateView(name, phone, email);


            } else if (resultJsonObject.optString("code").equalsIgnoreCase("404")) {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            } else if (resultJsonObject.optString("code").equalsIgnoreCase("400")) {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            } else {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            }

        } else if (isUpdateProfileService) {
            isUpdateProfileService = false;
            if (resultJsonObject.optString("code").equalsIgnoreCase("200")) {

            } else if (resultJsonObject.optString("code").equalsIgnoreCase("404")) {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            } else if (resultJsonObject.optString("code").equalsIgnoreCase("400")) {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            } else {
                Util.showMessageWithOk(ProfileActivity.this, "Something went wrong! Please try again.");
            }
        }
    }

    private void updateView(String name, String phone, String email) {

        tv_name.setText("" + name);
        tv_phone.setText("+91 " + phone);
        tv_email.setText("" + email);
    }

    @Override
    public void onError() {

    }
}
