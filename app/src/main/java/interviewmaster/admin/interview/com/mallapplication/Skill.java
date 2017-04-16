package interviewmaster.admin.interview.com.mallapplication;

/**
 * Created by ADMIN on 14-02-2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {

    @SerializedName("technical")
    @Expose
    private List<String> technical = null;
    @SerializedName("extra_curricular")
    @Expose
    private List<String> extraCurricular = null;

    public List<String> getTechnical() {
        return technical;
    }

    public void setTechnical(List<String> technical) {
        this.technical = technical;
    }

    public List<String> getExtraCurricular() {
        return extraCurricular;
    }

    public void setExtraCurricular(List<String> extraCurricular) {
        this.extraCurricular = extraCurricular;
    }

}