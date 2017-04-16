package interviewmaster.admin.interview.com.mallapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    MyAdapte myAdapte;
    Context context;
    Communicator communicator;
    private Gson gson;
    DBhandler dBhandler=new DBhandler(this);
    List<Example> employeelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //   employeelist = new ArrayList<>();
        listView = (RecyclerView)findViewById(R.id.list_View);
        listView.hasFixedSize();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(layoutManager);
        getting();
       // withoutnet();
      /*if(isConnectingToInternet()) {
          getting();
      }
        else{
          withoutnet();
      }*/

    }

    public void getting(){
        ApiService api = RetrofitClient.getApiService();
        Call<List<Example>> call = api.getMyJSON();
        Log.d("called","called");
        //Employ <Employee>employee=new ArrayList<Employee>();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                //Dismiss Dialog
                Log.d("called","seccalled");
                // Log.d("resspons", response.toString());
                if(response.isSuccessful()) {
                    employeelist=response.body();
                    Log.d("Sizeone", String.valueOf(employeelist.size()));
                    for(Example e:employeelist){
                        Log.d("ans", String.valueOf(employeelist.get(0).getEmployee()));
                        Log.d("Size", String.valueOf(employeelist.size()));
                    }
                   // dBhandler.addFoodCountryName(employeelist);
                    Log.d("added","successfully");
                  //  withoutnet();
                    myAdapte = new MyAdapte(getApplicationContext(), employeelist);
                    listView.setAdapter(myAdapte);
                    }
                else{
                    Log.d("failed","failure");
                }

            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                Log.d("failed",t.toString());
            }
        });
    }




    public void setCommunicator(Communicator comm) {
        this.communicator = comm;

    }
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
    public  void withoutnet(){
        employeelist=dBhandler.getAllFoodCountryName();
        if(employeelist!=null){
        myAdapte = new MyAdapte(getApplicationContext(), employeelist);
        listView.setAdapter(myAdapte);}
    }
}
