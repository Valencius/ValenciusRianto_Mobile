package com.example.finaltest_praetorian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private Button btnProfile;
    private int currentFragment;
    private FragmentContainerView fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        currentFragment = 0;
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(R.id.fragment_view, imgFragment.class, null ).commit();

        fragmentContainer = findViewById(R.id.fragment_view);
        btnProfile = findViewById(R.id.btn_profile);

        btnProfile.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, Profile.class));
            finish();
        });

        fragmentContainer.setOnClickListener(view->{
            if(currentFragment == 0){
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_view, TextFragment.class, null).commit();
                currentFragment = 1;
            }else{
                getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_view, imgFragment.class, null ).commit();
                currentFragment = 0;
            }


        });

    }
}