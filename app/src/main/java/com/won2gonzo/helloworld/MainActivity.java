package com.won2gonzo.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Volley v;
        setContentView(R.layout.activity_main);
        Button btnOK = (Button)findViewById(R.id.login_button);
        Button btnCANCEL = (Button)findViewById(R.id.cancel_button);
        final EditText id = (EditText)findViewById(R.id.getId);


        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String value = id.getText().toString()+"님";
                Intent intent = new Intent(MainActivity.this , JoinActivity.class);
                intent.putExtra("name",value);
                startActivity(intent);
            }
        };
        btnOK.setOnClickListener(listener);
    }
}
