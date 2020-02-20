package com.example.practiceset;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listSet);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);
        Call<List<Item>> call = api.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                   List<Item> items = response.body();

                   String[] itemNames = new String[items.size()];
                   for (int i = 0; i<items.size(); i++){
                       itemNames[i] = items.get(i).getName();
                       /*itemNames[i] = items.get(i).getSalary();
                       itemNames[i] = items.get(i).getDesig();*/
                   }

                   listView.setAdapter(new ArrayAdapter<String>
                           (getApplicationContext(),
                                   android.R.layout.simple_list_item_1,
                                   itemNames
                           )
                   );
                   /*for (Item i: items){
                       Log.d("name",i.getName());
                       Log.d("salary",i.getSalary());
                       Log.d("desig",i.getDesig());
                   }
*/
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}
