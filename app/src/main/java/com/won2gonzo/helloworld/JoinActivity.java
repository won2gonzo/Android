package com.won2gonzo.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class JoinActivity extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        TextView name =(TextView)findViewById(R.id.name);

        Intent intent = getIntent();
        String value = intent.getStringExtra("name");
        name.setText(value);

        mAdapter = new ArrayAdapter<String>(this, R.layout.row_list_view,R.id.textView);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(mAdapter);


        String url = "http://180.224.218.200:3000/read";

        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0 ; i<response.length();i++) {
                                JSONObject obj = response.getJSONObject(i);
                                if(obj.has("content")){
                                    String content = obj.getString("content");
                                    if(content ==null){
                                        content = "";
                                    }
                                    mAdapter.add(content);
                              }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        error.printStackTrace();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);

    }
}
