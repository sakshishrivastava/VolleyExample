package com.techpalle.volleyexample;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.*;

public class MainActivity extends AppCompatActivity {
    private List<Detail> detail_list = new ArrayList<>();
    private RecyclerView recyclerview;
    private Details_Adapter madapter;
    private Context context;

    String tag_string_req = "string_req";
    String tag_json_obj = "json_obj_req";
    String tag_json_arry = "json_array_req";
    String obj_url = "https://api.androidhive.info/volley/person_object.json";
    String arr_url = "https://api.androidhive.info/volley/person_array.json";
    String str_url="https://api.androidhive.info/volley/string_response.html";
    TextView tv2,tv3,tv4,tv5,tv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        tv2= (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);



        urlStrRequest();
        urlObjRequest();
        urlArrRequest();

    }
    private void urlStrRequest(){
        StringRequest strReq = new StringRequest(Request.Method.GET,
                str_url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d(TAG, response.toString());
                // pDialog.hide();
                tv6.setText(Html.fromHtml(response));

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());
                // pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
    private void urlObjRequest(){
        StringRequest jsonObjReq = new StringRequest(Request.Method.GET,
                obj_url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonabj = new JSONObject(response);
                            String name = jsonabj.optString("name");
                            String email = jsonabj.optString("email");
                            JSONObject jsonObject = jsonabj.getJSONObject("phone");

                            String home=jsonObject.optString("home");
                            String mobile = jsonObject.optString("mobile");

                            tv4.setText(name);tv5.setText(email);tv3.setText(home);tv2.setText(mobile);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       // pdialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);




    }
    private void urlArrRequest(){
        JsonArrayRequest req = new JsonArrayRequest(arr_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, response.toString());



                        Detail d = null;
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject j1 = null;
                            try {
                                j1 = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String name = j1.optString("name");
                            String email = j1.optString("email");
                            JSONObject j2 = null;
                            try {
                                j2 = j1.getJSONObject("phone");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String home = j2.optString("home");
                             String mobile = j2.optString("mobile");
                            d = new Detail(name, email,home,mobile);
                            // d.setName(name);d.setEmail(email);d.setHome(home);d.setMobile(mobile);
                            detail_list.add(d);

                        }
                        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        // madapter.notifyDataSetChanged();
                        recyclerview.setAdapter(new Details_Adapter(context,detail_list));


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);



    }
}
