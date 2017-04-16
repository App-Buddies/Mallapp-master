package interviewmaster.admin.interview.com.mallapplication;

/**
 * Created by ADMIN on 14-02-2017.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("employee")
    @Expose
    private List<Employee> employee = new ArrayList<>();

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

}