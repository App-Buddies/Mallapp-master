package interviewmaster.admin.interview.com.mallapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ADMIN on 14-02-2017.
 */
public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("/employeesList")
    Call<List<Example>> getMyJSON();
}