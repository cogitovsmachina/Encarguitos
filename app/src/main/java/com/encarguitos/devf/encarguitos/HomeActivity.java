package com.encarguitos.devf.encarguitos;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends ActionBarActivity {

    private static final String URL = "http://requestb.in/1juzstn1";
    private EditText edText;
    private ProgressDialog progressDialog;

    protected JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edText = (EditText)findViewById(R.id.editText);

        edText.getText().toString();

    }

    public void sendToServer(View v) {
        progressDialog = ProgressDialog.show(this, "",
                "Sending data to server");

        try {
            jsonObject = new JSONObject();
            jsonObject.put("pedido", edText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener() {

                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(HomeActivity.this, "Respuesta: " + response.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeActivity.this, "El pedo fué: " + error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("pedido", "" + edText.getText().toString());
                return params;
            }
        };
        mRequestQueue.add(stringRequest);

    }

}
