package com.dailymarketlist.securesoftbd.bitmfirebaseproject;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Adepter.DoctorAdepter;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Adepter.ItemClickLitener;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Database.DBHelper;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class ShowDoctorActivity extends AppCompatActivity implements ItemClickLitener {

  /*  ImageView profileImage;
    TextView userName,userDetails,userAppoinmentDate,userPhone,userEmail;
*/

  private List<Doctor> doctorList;
  private RecyclerView recyclerView;
  private DBHelper dbHelper;
  private DoctorAdepter doctorAdepter;
  //private Doctor doctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initialization();

        doctorList = dbHelper.getDoctorDBdata();

        if(doctorList==null)
        {
            Toast.makeText(this, "Data Empty", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ShowDoctorActivity.this, MainActivity.class));
            finish();
        }
        else
        {


            //dataRefresh();
            doctorAdepter = new DoctorAdepter(this,doctorList);
            recyclerView.setAdapter(doctorAdepter);
            doctorAdepter.setClickListener(this);

        }




/*

        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int st = recyclerView.getChildPosition(v);
                System.out.println(st);
                String s = String.valueOf(v.getVerticalScrollbarPosition());
                System.out.println(s);
                return false;
            }
        });
*/


//recyclerViewMethod();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        dataRefresh();
    }

    private void dataRefresh() {
        doctorList = dbHelper.getDoctorDBdata();
        if (doctorList==null){
            finish();
        }else {
            doctorAdepter = new DoctorAdepter(this,doctorList);
            recyclerView.setAdapter(doctorAdepter);}
    }

   /* private void recyclerViewMethod()
    {


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iiiii= recyclerView.getChildViewHolder(v).getAdapterPosition();
                System.out.println(iiiii);
                Toast.makeText(ShowDoctorActivity.this, iiiii, Toast.LENGTH_SHORT).show();
            }
        });






      *//*  recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {


                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });*//*
    }*/

    private void initialization()
    {
        dbHelper = new DBHelper(this);
      //  doctorList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onClick(View view, int position)
    {

        final Doctor doctor = doctorList.get(position);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(ShowDoctorActivity.this).inflate(R.layout.activity_update_custom_dialog,null);
        alertDialog.setView(v);

       // CardView updateData,deleteData;
        Button buttonUpdate,buttonDelete;
        buttonUpdate = v.findViewById(R.id.updateButtonId);
        buttonDelete = v.findViewById(R.id.deleteButttonId);

        final AlertDialog dialog = alertDialog.create();
        dialog.show();


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Are You Sure to delete it !!");
                tost.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dbHelper.deleteData(doctor.getDoctorId());
                        dataRefresh();

                        /*

                        doctorAdepter.notifyDataSetChanged();
                        recyclerView.setAdapter(doctorAdepter);*/
                        AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                        tost.setMessage("Success ");
                        tost.show();
                    }
                });
                tost.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                tost.show();

            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                updateCode(doctor);
                doctorAdepter.notifyDataSetChanged();
                recyclerView.setAdapter(doctorAdepter);
             /*   AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Update Your data Succesfully");
                tost.show();*/

            }
        });

      /*  updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialog.dismiss();
            updateCode(doctor);
            dataRefresh();*/
            //doctorAdepter.notifyDataSetChanged();
            //recyclerView.setAdapter(doctorAdepter);

              /*  AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Update Your data Succesfully");
                tost.show();*/


                //finish();
                /*             AlertDialog.Builder builder = new AlertDialog.Builder(ShowDoctorActivity.this);
                View updateDataView = LayoutInflater.from(ShowDoctorActivity.this).inflate(R.layout.activity_add_doctor,null);
                builder.setView(updateDataView);*/

                //    updatemethod(updateDataView,doctor);

/*            }
        });*/

   /*     deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            *//*    dbHelper.deleteData(doctor.getDoctorId());
                AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Delete Succesfully");
                tost.show();*//*

                dialog.dismiss();

                AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Are You Sure to delete it !!");
                tost.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dbHelper.deleteData(doctor.getDoctorId());
                        //doctorAdepter.notifyDataSetChanged();
                        //recyclerView.setAdapter(doctorAdepter);
                        dataRefresh();
                        AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                        tost.setMessage("Success ");
                        tost.show();
                    }
                });
                tost.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                tost.show();

            }
        });*/

    }

    private void updateCode(Doctor doctor)
    {
        Intent intent = new Intent(ShowDoctorActivity.this,AddDoctorActivity.class);
        //intent.putStringArrayListExtra("Data",);
        intent.putExtra("Data",doctor.getDoctorId());
        startActivity(intent);
    }

    private void updatemethod(View updateDataView, Doctor doctor)
    {
        EditText addDoctorNameETVar,addDoctorDetailsETVar,addDoctorPhoneETVar,addDoctorEmailETVar;
        TextView showAppoinmentET_Var;
        ImageView addAppoinmentVar,setImageVar;
        ImageButton imageGetVar;
        Button saveDoctorInformaitonBTNVar;
        DatePickerDialog datePickerDialog;

        final int CAMERA_REQUEST = 10;
        final int GALLERY_REQUST = 20;

        Bitmap bitmap = null;

        setImageVar = updateDataView.findViewById(R.id.profile_image);
        imageGetVar = updateDataView.findViewById(R.id.setImageCameraAndGallary);
        addAppoinmentVar = updateDataView.findViewById(R.id.doctorAppoinmentDatePickUpId);
        showAppoinmentET_Var = updateDataView.findViewById(R.id.showAppoinmetnTV_Id);
        addDoctorNameETVar = updateDataView.findViewById(R.id.addDoctorNameEditTextId);
        addDoctorDetailsETVar = updateDataView.findViewById(R.id.addDoctorDetailsEditTextId);
        addDoctorPhoneETVar = updateDataView.findViewById(R.id.addDoctorPhoneEditTextId);
        addDoctorEmailETVar = updateDataView.findViewById(R.id.addDoctorEmailEditTextId);
        saveDoctorInformaitonBTNVar = updateDataView.findViewById(R.id.saveButtonId);

        addDoctorNameETVar.setText(doctor.getDoctorName());
        addDoctorDetailsETVar.setText(doctor.getDoctorDetails());
        addDoctorEmailETVar.setText(doctor.getDoctorEmail());
        addDoctorPhoneETVar.setText(doctor.getDoctorPhone());


    }





    /*    private void initialization()
    {
        profileImage = findViewById(R.id.show_doctorProfilId);
        userName = findViewById(R.id.showDoctorNameTV_Id);
        userDetails = findViewById(R.id.showDoctorDetailsTV_Id);
        userAppoinmentDate = findViewById(R.id.showDoctorDateTV_Id);
        userPhone = findViewById(R.id.showDoctorPhoneTV_Id);
        userEmail = findViewById(R.id.showDoctorEmailTV_Id);
    }*/
}
