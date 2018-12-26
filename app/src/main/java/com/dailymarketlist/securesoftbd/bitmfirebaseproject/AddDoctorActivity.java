package com.dailymarketlist.securesoftbd.bitmfirebaseproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Database.DBHelper;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

public class AddDoctorActivity extends AppCompatActivity {

    private EditText addDoctorNameETVar,addDoctorDetailsETVar,addDoctorPhoneETVar,addDoctorEmailETVar;
    private TextView showAppoinmentET_Var;
    private ImageView addAppoinmentVar,setImageVar;
    private ImageButton imageGetVar;
    private Button saveDoctorInformaitonBTNVar;
    private DatePickerDialog datePickerDialog;

    private final int CAMERA_REQUEST = 10;
    private final int GALLERY_REQUST = 20;

    private Bitmap bitmap = null;
    private String data;
    private DBHelper dbHelper;
    private List<Doctor> doctorList;


    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        initialization();
        data = getIntent().getStringExtra("Data");
        setData(data);

        onClick();
    }

    private void setData(String data)
    {
        if(!data.equals("add"))
        {
           doctorList = dbHelper.getDoctorDBdataId(data);
            Doctor doctor = doctorList.get(0);

            addDoctorNameETVar.setText(doctor.getDoctorName());
           addDoctorDetailsETVar.setText(doctor.getDoctorDetails());
           showAppoinmentET_Var.setText(doctor.getDoctorAppoinmentDate());
           addDoctorPhoneETVar.setText(doctor.getDoctorPhone());
           addDoctorEmailETVar.setText(doctor.getDoctorEmail());
           imageGetVar.setImageBitmap(StringToBitMap(doctor.getDoctorImage()));
        }
    }


    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }



    private void onClick()
    {



        imageGetVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddDoctorActivity.this);
                 View view = LayoutInflater.from(AddDoctorActivity.this).inflate(R.layout.custom_alertdialog,null);
                 alertDialog.setView(view);

                 TextView camera,gallery;
                 camera = view.findViewById(R.id.cameraProfilId);
                 gallery  = view.findViewById(R.id.galleryProflId);

                 final AlertDialog dialog = alertDialog.create();
                 dialog.show();
                 camera.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                       dialog.dismiss();
                       Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                         startActivityForResult(cameraIntent, CAMERA_REQUEST);


                     }
                 });

                 gallery.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                       dialog.dismiss();
                       Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                       galleryIntent.setType("image/*");
                       startActivityForResult(galleryIntent,GALLERY_REQUST);
                     }
                 });






            }
        });


        addAppoinmentVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int Year = c.get(Calendar.YEAR);
                int Month = c.get(Calendar.MONTH);
                int Day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddDoctorActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                         showAppoinmentET_Var.setText(dayOfMonth + "-"
                                 + (month + 1) + "-" + year);

                    }
                },Year,Month,Day);

                datePickerDialog.show();
            }
        });


        saveDoctorInformaitonBTNVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //SQLiteDatabase sqLiteDatabase  = getApplicationContext().getW

                String doctorName, details,appoinmentDate,phoneNumber,emailNumber;

                doctorName = addDoctorNameETVar.getText().toString();
                details = addDoctorDetailsETVar.getText().toString();
                appoinmentDate = showAppoinmentET_Var.getText().toString();
                phoneNumber = addDoctorPhoneETVar.getText().toString();
                emailNumber = addDoctorEmailETVar.getText().toString();

                if(doctorName.isEmpty() || details.isEmpty() || appoinmentDate.isEmpty() || phoneNumber.isEmpty()
                        || emailNumber.isEmpty())
                {
                    Toast.makeText(AddDoctorActivity.this, " Please Filup the all filde ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(bitmap == null)
                    {
                       // Toast.makeText(AddDoctorActivity.this, " Pro ", Toast.LENGTH_SHORT).show();
                        Drawable d = getResources().getDrawable(R.drawable.profil_icon);
                        bitmap = ((BitmapDrawable)d).getBitmap();
                        setImageVar.setImageBitmap(bitmap);


                    }
                        String image = getEncoded64ImageStringFromBitmap(bitmap);
                        Doctor doctor = new Doctor(doctorName,details,appoinmentDate, phoneNumber,emailNumber,image);

                        if(!data.equals("add"))
                        {
                            dbHelper.DoctorDataUpdate(data,doctor);
                            finish();
                        }
                        else
                        {

                            dbHelper.DoctorDataInsert(doctor);
                            clearAll();
                            AlertDialog.Builder tost = new AlertDialog.Builder(AddDoctorActivity.this);
                            tost.setMessage("Insert data Succesfully");
                            tost.show();

                        }
                }

            }
        });
    }

    private void clearAll()
    {
        addDoctorNameETVar.setText(null);
        addDoctorDetailsETVar.setText(null);
        showAppoinmentET_Var.setText(null);
        addDoctorPhoneETVar.setText(null);
        addDoctorEmailETVar.setText(null);
        setImageVar.setImageResource(R.drawable.profil_icon);
    }

    private void initialization()
    {

        dbHelper = new DBHelper(this);

        setImageVar = findViewById(R.id.profile_image);
        imageGetVar = findViewById(R.id.setImageCameraAndGallary);
        addAppoinmentVar = findViewById(R.id.doctorAppoinmentDatePickUpId);
        showAppoinmentET_Var = findViewById(R.id.showAppoinmetnTV_Id);
        addDoctorNameETVar = findViewById(R.id.addDoctorNameEditTextId);
        addDoctorDetailsETVar = findViewById(R.id.addDoctorDetailsEditTextId);
        addDoctorPhoneETVar = findViewById(R.id.addDoctorPhoneEditTextId);
        addDoctorEmailETVar = findViewById(R.id.addDoctorEmailEditTextId);
        saveDoctorInformaitonBTNVar = findViewById(R.id.saveButtonId);
    }
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }

    }
  */

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            setImageVar.setImageBitmap(bitmap);
        }
        else if(requestCode == GALLERY_REQUST && resultCode == Activity.RESULT_OK)
        {
            Uri imgaeUri = data.getData();
            try {
                InputStream imageStream = getContentResolver().openInputStream(imgaeUri);
                bitmap  =BitmapFactory.decodeStream(imageStream);
                setImageVar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }

}
