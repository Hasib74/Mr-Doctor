package com.dailymarketlist.securesoftbd.bitmfirebaseproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Adepter.ItemClickLitener;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Adepter.MedicalAdepter;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Database.DBHelper;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Medical;

import java.util.List;

public class ShowMedicalActivity extends AppCompatActivity
{

    private List<Medical> medicalList;
    private RecyclerView recyclerView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        initialization();

       // Toast.makeText(ShowMedicalActivity.this, medicalList.toString(), Toast.LENGTH_SHORT).show();
        medicalList = dbHelper.getMedicalDBdata();
        //Toast.makeText(ShowMedicalActivity.this, medicalList.toString(), Toast.LENGTH_SHORT).show();


        if(medicalList == null)
        {
            Toast.makeText(this, "Data Empty", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ShowMedicalActivity.this, MainActivity.class));
            finish();
        }
        else
        {
          //  Toast.makeText(this, "Data Have data", Toast.LENGTH_SHORT).show();
            MedicalAdepter medicalAdepter = new MedicalAdepter(this,medicalList);
            recyclerView.setAdapter(medicalAdepter);
        }
    }


    private void initialization()
    {
        dbHelper = new DBHelper(this);
        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


 /*   @Override
    public void onClick(View view, int position)
    {

        final Medical medical = medicalList.get(position);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(ShowMedicalActivity.this).inflate(R.layout.activity_update_custom_dialog,null);
        alertDialog.setView(v);
        CardView updateData,deleteData;
        Button buttonUpdate,buttonDelete;
        updateData = v.findViewById(R.id.addUpdateDoctorInformationEditTextId);
        deleteData = v.findViewById(R.id.addDelectDoctorinformationEditTextId);
        buttonUpdate = v.findViewById(R.id.updateButtonId);
        buttonDelete = v.findViewById(R.id.deleteButttonId);

        final AlertDialog dialog = alertDialog.create();
        dialog.show();


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AlertDialog.Builder tost = new AlertDialog.Builder(ShowMedicalActivity.this);
                tost.setMessage("Are You Sure to delete it !!");
                tost.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //dbHelper.deleteData(medical.getDoctorId());
                        //     dataRefresh();

                        *//*

                        doctorAdepter.notifyDataSetChanged();
                        recyclerView.setAdapter(doctorAdepter);*//*
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
             *//*   AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Update Your data Succesfully");
                tost.show();*//*

            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                updateCode(doctor);
                dataRefresh();
                //doctorAdepter.notifyDataSetChanged();
                //recyclerView.setAdapter(doctorAdepter);

              *//*  AlertDialog.Builder tost = new AlertDialog.Builder(ShowDoctorActivity.this);
                tost.setMessage("Update Your data Succesfully");
                tost.show();*//*


                //finish();
                *//*             AlertDialog.Builder builder = new AlertDialog.Builder(ShowDoctorActivity.this);
                View updateDataView = LayoutInflater.from(ShowDoctorActivity.this).inflate(R.layout.activity_add_doctor,null);
                builder.setView(updateDataView);*//*

                //    updatemethod(updateDataView,doctor);

            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
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
        });

    }*/
}
