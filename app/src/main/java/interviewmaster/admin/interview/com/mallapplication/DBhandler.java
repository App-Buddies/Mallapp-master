package interviewmaster.admin.interview.com.mallapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ADMIN on 15-02-2017.
 */
public class DBhandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "employee.db";
    private static final String TABLE_NAME = "employeedata";

    private static final String KEY_ID = "employeeid";
    private static final String KEY_FIRST_NAME = "employeefirstname";
    private static final String KEY_LAST_NAME = "employeelastname";
    private static final String KEY_ADDRESS = "employeeaddress";
    private static final String KEY_CITY = "employeecity";
    private static final String KEY_ZIPCODE = "employeezipcode";
    private static final String KEY_GENDER = "employeegender";
    private static final String KEY_DOB = "employeedob";
    private static final String KEY_DESIGNATION = "employeedesignation";
    private static final String KEY_MOBILE = "employeemobile";
    private static final String KEY_EMAIL = "employeemail";
    private static final String KEY_NATIONALITY = "employeenationality";
    private static final String KEY_LANGUAGE = "employeelanguage";
    private static final String KEY_IMAGEURL = "employeeimageurl";
    private static final String KEY_TECHNICAL = "employeetechnical";
    private static final String KEY_EXTRACURRICULAR = "employeeextracurricular";
    List<Example> array;

    private static final String KEY_IMAGE = "foodcountryimage";
    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " TEXT,"+ KEY_FIRST_NAME + " TEXT,"+ KEY_LAST_NAME + " TEXT,"+ KEY_ADDRESS + " TEXT,"+ KEY_CITY
            + " TEXT,"+ KEY_ZIPCODE + " TEXT,"+ KEY_GENDER + " TEXT,"+ KEY_DOB + " TEXT,"+ KEY_DESIGNATION + " TEXT,"+ KEY_MOBILE + " TEXT,"+ KEY_EMAIL + " TEXT,"+ KEY_NATIONALITY + " TEXT,"+ KEY_LANGUAGE + " TEXT,"+
            KEY_IMAGEURL + " TEXT," + KEY_TECHNICAL + " TEXT," + KEY_EXTRACURRICULAR + " TEXT"+")";
    String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBhandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    public void addFoodCountryName(List<Example> item) {
        this.array = item;
        SQLiteDatabase db = this.getWritableDatabase();
        Employee it = array.get(0).getEmployee().get(0);
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, it.getId());
            values.put(KEY_FIRST_NAME, it.getFirstName());
            values.put(KEY_LAST_NAME, it.getLastName());
            values.put(KEY_ADDRESS, it.getAddress());
            values.put(KEY_CITY, it.getCity());
            values.put(KEY_ZIPCODE, it.getZipcode());
            values.put(KEY_GENDER, it.getGender());
            values.put(KEY_DOB, it.getDob());
            values.put(KEY_DESIGNATION, it.getDesignation());
            values.put(KEY_MOBILE, it.getMobile());
            values.put(KEY_EMAIL, it.getEmail());
            values.put(KEY_NATIONALITY, it.getNationality());
            values.put(KEY_LANGUAGE, it.getLanguage());
Log.d("string",it.getDesignation());
            for (int i = 0; i < it.getSkills().get(0).getTechnical().size(); i++) {
                values.put(KEY_TECHNICAL, it.getSkills().get(0).getTechnical().get(i));
            }
            for (int i = 0; i < it.getSkills().get(0).getExtraCurricular().size(); i++) {
                values.put(KEY_EXTRACURRICULAR, it.getSkills().get(0).getExtraCurricular().get(i));
            }

            db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            //db.insert(TABLE_NAME, null, values);
            db.close();
        } catch (Exception e) {
            Log.e("problem", e + "");
        }
    }



    public ArrayList<Example> getAllFoodCountryName() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employee> cityList = null;
        ArrayList<Example> xyz=null;
        try {
            cityList = new ArrayList<>();
            String QUERY = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    Employee item=new Employee();
                    item.setId(cursor.getString(0));
                    item.setFirstName(cursor.getString(1));
                    item.setLastName(cursor.getString(2));
                    item.setAddress(cursor.getString(3));
                    item.setCity(cursor.getString(4));
                    item.setZipcode(cursor.getString(5));
                    item.setGender(cursor.getString(6));
                    item.setDob(cursor.getString(7));
                    item.setMobile(cursor.getString(8));
                    item.setEmail(cursor.getString(9));
                    item.setNationality(cursor.getString(10));
                    item.setLanguage(cursor.getString(11));
                    Example example=new Example();
                    example.setEmployee(cityList);
                    xyz=new ArrayList<Example>();
                    xyz.add(example);
                    //Log.d("string",xyz.get(0).getEmployee().get(1).toString());


                  /*  ArrayList<String> mArrayList = new ArrayList<String>();
                    while(cursor.moveToNext()) {
                        mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_TECHNICAL))); //add the item
                    }
                    item.setSkills(mArrayList);

                    ArrayList<String> mAryList = new ArrayList<String>();
                    while(cursor.moveToNext()) {
                        mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_EXTRACURRICULAR))); //add the item
                    }
                    item.setSkills(mAryList);
*/
/*
                    for (int i = 0; i < item.getSkills().get(0).getExtraCurricular().size(); i++) {
                        List<Skill> listuy = new ArrayList<Skill>();
                        listuy.add(i, item.getSkills().get(i));
                    }*/

                    //cityList.addAll((Collection<? extends Example>) item);
                }
            }
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return  xyz;
    }

   /* public ArrayList<Example> getAllFoodCountryName() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Employee> cityList = null;
        try {
            cityList = new ArrayList<>();
            String QUERY = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);


            if (!cursor.isLast()) {
                while (cursor.moveToNext()) {
                    //Log.d("problem", String.valueOf(j++));
                    Employee item = array.get(0).getEmployee().get(0);
                    item.setId(cursor.getString(0));
                    item.setFirstName(cursor.getString(1));
                    item.setLastName(cursor.getString(2));
                    item.setAddress(cursor.getString(3));
                    item.setCity(cursor.getString(4));
                    item.setZipcode(cursor.getString(5));
                    item.setGender(cursor.getString(6));
                    item.setDob(cursor.getString(7));
                    item.setMobile(cursor.getString(8));
                    item.setEmail(cursor.getString(9));
                    item.setNationality(cursor.getString(10));
                    item.setLanguage(cursor.getString(11));


                    ArrayList<String> mArrayList = new ArrayList<String>();
                    while(cursor.moveToNext()) {
                        mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_TECHNICAL))); //add the item
                    }
                    item.setSkills(mArrayList);

                    ArrayList<String> mAryList = new ArrayList<String>();
                    while(cursor.moveToNext()) {
                        mArrayList.add(cursor.getString(cursor.getColumnIndex(KEY_EXTRACURRICULAR))); //add the item
                    }
                    item.setSkills(mAryList);


                    for (int i = 0; i < item.getSkills().get(0).getExtraCurricular().size(); i++) {
                        List<Skill> listuy = new ArrayList<Skill>();
                        listuy.add(i, item.getSkills().get(i));
                    }

                    cityList.add(item);
                }
            }
            db.close();
        } catch (Exception e) {
            Log.e("error", e + "");
        }
        return cityList;
    }*/

}
