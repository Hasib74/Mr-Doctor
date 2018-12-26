package com.dailymarketlist.securesoftbd.bitmfirebaseproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Medical;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASEVERSION = 01;
    private static final String DATABASENAME = "BITM_MID_PROJECT";
    private final String DOCTOR_TABLENAME = "DOCTORTABLE";
    private final String DOCTOR_ID = "ID";
    private final String DOCTOR_NAME = "DOCTORNAME";
    private final String DOCTOR_DETAILS = "DOCTORDETAILS";
    private final String DOCTOR_APPOINMENT_DATE = "DOCTORAPPOINMENTDATE";
    private final String DOCTOR_PHONE = "DOCTORPHONE";
    private final String DOCTOR_EMAIL = "DOCTOREMAIL";
    private final String DOCTOR_IMAGE = "DOCTORIMAGE";

    String doctor_sql = "CREATE TABLE " + DOCTOR_TABLENAME+ " (" + DOCTOR_ID
            + " INTEGER primary key, " + DOCTOR_NAME + " text, " + DOCTOR_DETAILS+" TEXT ,"+ DOCTOR_APPOINMENT_DATE+ " TEXT,"+
            DOCTOR_PHONE+ " TEXT,"+DOCTOR_EMAIL+ " TEXT,"+ DOCTOR_IMAGE+ " TEXT" + ");";

    private final String MEDICAL_TABLENAME= "MEDICALTABLE";
    private final String MEDICAL_ID = "MEDICALID";
    private final String MEDICAL_DOCTOR_NAME = "MEDICALDOCTORNAME";
    private final String MEDICAL_DETAILS = "MEDICALDETAILS";
    private final String MEDICAL_DATE = "MEDIALDATE";
    private final String MEDICAL_PRESCRIPTION = "MEDICALPRESCRIPTION";

    String medical_sql = "CREATE TABLE " + MEDICAL_TABLENAME+ " (" + MEDICAL_ID
            + " INTEGER primary key, " + MEDICAL_DOCTOR_NAME+ " text, " + MEDICAL_DETAILS+" TEXT ,"+ MEDICAL_DATE+ " TEXT,"+
            MEDICAL_PRESCRIPTION+ " TEXT" + ");";




    public DBHelper(Context context) {
        super(context, DATABASENAME, null,DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(doctor_sql);
        db.execSQL(medical_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DOCTOR_TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + MEDICAL_TABLENAME);
        onCreate(db);
    }

    public void DoctorDataInsert(Doctor doctor) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DOCTOR_NAME,doctor.getDoctorName());
        contentValues.put(DOCTOR_DETAILS,doctor.getDoctorDetails());
        contentValues.put(DOCTOR_APPOINMENT_DATE,doctor.getDoctorAppoinmentDate());
        contentValues.put(DOCTOR_PHONE,doctor.getDoctorPhone());
        contentValues.put(DOCTOR_EMAIL,doctor.getDoctorEmail());
        contentValues.put(DOCTOR_IMAGE,doctor.getDoctorImage());

        long l = sqLiteDatabase.insert(DOCTOR_TABLENAME, null , contentValues);

        if(l > -1)
        {
            System.out.println(" doctor databse insert ");
        }
        else
        {
            System.out.println(" doctor inseted Error ");
        }

    }

    public void DoctorDataUpdate(String id,Doctor doctor) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DOCTOR_NAME,doctor.getDoctorName());
        contentValues.put(DOCTOR_DETAILS,doctor.getDoctorDetails());
        contentValues.put(DOCTOR_APPOINMENT_DATE,doctor.getDoctorAppoinmentDate());
        contentValues.put(DOCTOR_PHONE,doctor.getDoctorPhone());
        contentValues.put(DOCTOR_EMAIL,doctor.getDoctorEmail());
        contentValues.put(DOCTOR_IMAGE,doctor.getDoctorImage());

        long l = sqLiteDatabase.update(DOCTOR_TABLENAME,  contentValues,"ID = ?",new String[] { id });

        if(l > -1)
        {
            System.out.println(" doctor databse insert ");
        }
        else
        {
            System.out.println(" doctor inseted Error ");
        }

    }
    public void MedicalDataInsert(Medical medical) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(MEDICAL_DOCTOR_NAME,medical.getMedicalDoctorName());
        contentValues.put(MEDICAL_DETAILS,medical.getMedicalDetails());
        contentValues.put(MEDICAL_DATE,medical.getMedicalDate());
        contentValues.put(MEDICAL_PRESCRIPTION,medical.getMedicalPrescriptionImage());

        long l = sqLiteDatabase.insert(MEDICAL_TABLENAME, null , contentValues);

        if(l > -1)
        {
            System.out.println(" medical databse insert ");
        }
        else
        {
            System.out.println(" medical inseted Error ");
        }


    }

    public List<Doctor> getDoctorDBdata() {
        List<Doctor> doctorList;// = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DOCTOR_TABLENAME;

        doctorList = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        System.out.println(doctorList);


            System.out.println(" cursor have data " );
            if(cursor.moveToFirst())
            {
                doctorList = new ArrayList<>();
                do {


                    String doctorId = cursor.getString(cursor.getColumnIndex(DOCTOR_ID));
                    String doctorName = cursor.getString(cursor.getColumnIndex(DOCTOR_NAME));
                    String doctorDetails = cursor.getString(cursor.getColumnIndex(DOCTOR_DETAILS));
                    String doctorAppoinmentDate = cursor.getString(cursor.getColumnIndex(DOCTOR_APPOINMENT_DATE));
                    String doctorPhone = cursor.getString(cursor.getColumnIndex(DOCTOR_PHONE));
                    String doctorEmail = cursor.getString(cursor.getColumnIndex(DOCTOR_EMAIL));
                    String doctorImage = cursor.getString(cursor.getColumnIndex(DOCTOR_IMAGE));

                    Doctor doctor = new Doctor(doctorId, doctorName, doctorDetails, doctorAppoinmentDate, doctorPhone, doctorEmail, doctorImage);
                    doctorList.add(doctor);

                }while (cursor.moveToNext());

            }

        return doctorList;
    }

    public List<Doctor> getDoctorDBdataId(String id) {
        List<Doctor> doctorList;// = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + DOCTOR_TABLENAME+ " WHERE "+DOCTOR_ID+" ="+id;

        doctorList = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        System.out.println(doctorList);


            System.out.println(" cursor have data " );
            if(cursor.moveToFirst())
            {
                doctorList = new ArrayList<>();
                do {


                    String doctorId = cursor.getString(cursor.getColumnIndex(DOCTOR_ID));
                    String doctorName = cursor.getString(cursor.getColumnIndex(DOCTOR_NAME));
                    String doctorDetails = cursor.getString(cursor.getColumnIndex(DOCTOR_DETAILS));
                    String doctorAppoinmentDate = cursor.getString(cursor.getColumnIndex(DOCTOR_APPOINMENT_DATE));
                    String doctorPhone = cursor.getString(cursor.getColumnIndex(DOCTOR_PHONE));
                    String doctorEmail = cursor.getString(cursor.getColumnIndex(DOCTOR_EMAIL));
                    String doctorImage = cursor.getString(cursor.getColumnIndex(DOCTOR_IMAGE));

                    Doctor doctor = new Doctor(doctorId, doctorName, doctorDetails, doctorAppoinmentDate, doctorPhone, doctorEmail, doctorImage);
                    doctorList.add(doctor);

                }while (cursor.moveToNext());

            }

        return doctorList;
    }

    public List<Medical> getMedicalDBdata()
    {
        List<Medical> medicalList;
        // = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + MEDICAL_TABLENAME;
        medicalList = null;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

            if(cursor.moveToFirst())
            {
                medicalList = new ArrayList<>();

                do {
                    String medicalId = cursor.getString(cursor.getColumnIndex(MEDICAL_ID));
                    String medicalName = cursor.getString(cursor.getColumnIndex(MEDICAL_DOCTOR_NAME));
                    String medicalDetails = cursor.getString(cursor.getColumnIndex(MEDICAL_DETAILS));
                    String medicalDate = cursor.getString(cursor.getColumnIndex(MEDICAL_DATE));
                    String medicalImage = cursor.getString(cursor.getColumnIndex(MEDICAL_PRESCRIPTION));

                    Medical medical = new Medical(medicalId,medicalName,medicalDetails,medicalDate,medicalImage);
                    medicalList.add(medical);

                }while (cursor.moveToNext());

            }

        return medicalList;
    }

    public List<String> DoctorName() {
        List<String> medicalList = new ArrayList<>();

        medicalList.add("Select Doctor Name");

        String selectQuery = "SELECT "+ DOCTOR_NAME +" FROM " + DOCTOR_TABLENAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do {

                String medicalDoctorName = cursor.getString(cursor.getColumnIndex(DOCTOR_NAME));
                medicalList.add(medicalDoctorName);

            }while (cursor.moveToNext());

        }

        return medicalList;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DOCTOR_TABLENAME, "ID = ?",new String[] {id});
    }


}