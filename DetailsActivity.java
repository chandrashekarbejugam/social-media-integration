package com.codeinfinity.socialmedialogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    GoogleSignInOptions signInOptions;
    GoogleSignInClient signInClient;
    TextView name, mail;
    ImageView profile, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, signInOptions);
        name = findViewById(R.id.username);
        mail = findViewById(R.id.usermail);
        profile = findViewById(R.id.personl);
        logout = findViewById(R.id.logoutd);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            String username = account.getDisplayName();
            String usermail = account.getEmail();
            String image = String.valueOf(account.getPhotoUrl());
            Picasso.get()
                    .load(image)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(profile);
            name.setText(username);
            mail.setText(usermail);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signout();
                }
            });
        }

    }
    void signout(){
        signInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(DetailsActivity.this, MainActivity.class));
            }
        });
    }
}