package com.dailymarketlist.securesoftbd.bitmfirebaseproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Database.DBHelper;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Medical;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

public class AddMedicalDetailsActivity extends AppCompatActivity {

    private final int CAMERA_REQUEST_CODE = 10;
    private DBHelper dbHelper;
    private TextView imageViewVar;
    private ImageView imageView,dateImageButton;
    private TextView prescriptionDateVar, prescriptionDetailsVar;
    private Spinner doctorNameVar;
    private Button saveButton;
    private DatePickerDialog datePickerDialog;
    private Bitmap bitmap = null;
    private List<String> stringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medical_details);

        initialization();
        List<String> s = dbHelper.DoctorName();
        ArrayAdapter<String> spinnerAdepter = new ArrayAdapter(AddMedicalDetailsActivity.this,android.R.layout.simple_spinner_item,s);
        doctorNameVar.setAdapter(spinnerAdepter);
        onClick();

    }

    private void onClick() {

        imageViewVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String doctorName, details,prescriptionDate;
                doctorName = doctorNameVar.getSelectedItem().toString();
                details = prescriptionDetailsVar.getText().toString();
                prescriptionDate = prescriptionDateVar.getText().toString();

                if(bitmap == null)
                {
                  /*  AlertDialog.Builder tost = new AlertDialog.Builder(AddMedicalDetailsActivity.this);
                    tost.setMessage("Please Select The  Prescripltion!!");
                    tost.show();*/
                    AlartDialog("Please Select The  Prescripltion!!");

                    // Toast.makeText(AddMedicalDetailsActivity.this, "Please Select The  Prescripltion!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String image = getEncoded64ImageStringFromBitmap(bitmap);
                    Medical medical = new Medical(doctorName,details,prescriptionDate,image);
                    dbHelper.MedicalDataInsert(medical);

                    AlartDialog("Insert Succesfull");
                    cleareData();
                }
            }
        });

        dateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int Year = c.get(Calendar.YEAR);
                int Month = c.get(Calendar.MONTH);
                int Day = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AddMedicalDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        prescriptionDateVar.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                    }
                },Year,Month,Day);

                datePickerDialog.show();
            }
        });

    }

    private void cleareData() {
        prescriptionDetailsVar.setText("");
        prescriptionDateVar.setText("");
        imageView.setImageResource(R.drawable.prescription_icon);
      //  private TextView prescriptionDateVar, prescriptionDetailsVar;
    }

    private void AlartDialog(String s) {
        AlertDialog.Builder tost = new AlertDialog.Builder(AddMedicalDetailsActivity.this);
        tost.setMessage(s);
        tost.show();
    }

    private void initialization() {

        dbHelper = new DBHelper(this);
        dateImageButton = findViewById(R.id.doctorPrescriptionDatePickUpId);
        imageViewVar = findViewById(R.id.takePrescriptionId);
        doctorNameVar = findViewById(R.id.addDoctorNameSpinnerId);
        prescriptionDateVar = findViewById(R.id.showAddPrecriptionDate_Id);
        prescriptionDetailsVar = findViewById(R.id.addPrescriptionDetailsEditTextId);
        saveButton = findViewById(R.id.savePrescriptionButtonId);
        imageView = findViewById(R.id.prescription_image);

    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

}