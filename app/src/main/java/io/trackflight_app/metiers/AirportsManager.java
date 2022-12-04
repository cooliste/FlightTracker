//package io.trackflight_app.metiers;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//import org.jetbrains.annotations.NotNull;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import io.trackflight_app.MainActivity;
//import io.trackflight_app.R;
//import io.trackflight_app.activities.DashboardActivity;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//class AirportsManger extends AppCompatActivity {
//
//    String myResponse;
//    ListView lv;
//    ArrayList<HashMap<String,String>> arrayList;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        arrayList=new ArrayList<>();
//        lv = (ListView)findViewById(R.id.airport_list);
//        OkHttpClient client = new OkHttpClient();
//        String url = "https://gist.github.com/tdreyno/4278655";
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                e.printStackTrace();
//            }
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if(response.isSuccessful())
//                {
//                    myResponse = response.body().string();
//                    DashboardActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                JSONObject reader = new JSONObject(myResponse);
//                                JSONArray airport_list = reader.getJSONArray("airports"); // get the whole json array list
//                                System.out.println("json size is : "+airport_list.length());
//                                for(int i = 0;i<airport_list.length();i++)
//                                {
//                                    JSONObject airport = airport_list.getJSONObject(i);
//                                    String code = airport.getString("code");
//                                    String  name= airport.getString("name");
//                                    String  city= airport.getString("city");
//                                    System.out.println(i+" Code: "+code +" Name : "+name+" City : "+city);
//                                    HashMap<String,String> data = new HashMap<>();
//                                    data.put("name",name);
//                                    data.put("code",code);
//                                  //  data.put("city",city);
//                                    arrayList.add(data);
//                                    ListAdapter adapter = new SimpleAdapter(DashboardActivity.this,arrayList,R.layout.airport_item
//                                            ,new String[]{"name","code"},new int[]{R.id.name,R.id.code});
//                                    lv.setAdapter(adapter);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//}