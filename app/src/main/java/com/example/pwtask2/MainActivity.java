package com.example.pwtask2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pwtask2.ui.main.MainFragment;
import com.example.pwtask2.ui.main.SecondFragment;

public class MainActivity extends AppCompatActivity {
private MainFragment mainFragment;
private SecondFragment secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("List Items");
        setContentView(R.layout.main_activity);
        mainFragment = new MainFragment();
        secondFragment = new SecondFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame_1, mainFragment)
                    .replace(R.id.main_frame_2, secondFragment)
                    .commitNow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_button:
                showDiaglogBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void addItem(String item){
        mainFragment.addItem(item);
    }


    public void deleteItems() {
        mainFragment.deleteItems();
    }


    private void showDiaglogBox() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.about_me_layout,null);
        TextView call,email,github,resume;
        call = mView.findViewById(R.id.about_me_call);
        email = mView.findViewById(R.id.about_me_email);
        github = mView.findViewById(R.id.about_me_github);
        resume = mView.findViewById(R.id.about_me_resume);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);

        call.setOnClickListener(view -> {
            String uri = "tel:8375983710" ;
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(uri));
            startActivity(intent);
        });
        email.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:ankitv397@gmail.com"));
            startActivity(Intent.createChooser(intent, "Send Email"));
        });
        github.setOnClickListener(view -> {
            String url = "https://github.com/varshneyankit/";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        resume.setOnClickListener(view -> {
            String url = "https://drive.google.com/file/d/1Bw39pvqG-ModIvUYLCqPyZZe-lMA6eVm/view?usp=sharing";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
        alertDialog.show();
    }
    }
