package com.dailymarketlist.securesoftbd.bitmfirebaseproject.Adepter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Doctor;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.R;

import java.util.List;

public class DoctorAdepter extends RecyclerView.Adapter<DoctorAdepter.DoctorViewHolder> {
    private Context context;
    private List<Doctor> doctorList;
    private ItemClickLitener itemClickLitener;

    public DoctorAdepter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        /*LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_show_doctor_adepter,null);
        */

        View v = LayoutInflater.from(context).inflate(R.layout.activity_show_doctor_adepter,null);

        return new DoctorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder doctorViewHolder, int i) {

        Doctor doctor = doctorList.get(i);


        doctorViewHolder.userName.setText(doctor.getDoctorName());
        doctorViewHolder.userDetails.setText(doctor.getDoctorDetails());
        doctorViewHolder.userAppoinmentDate.setText(doctor.getDoctorAppoinmentDate());
        doctorViewHolder.userPhone.setText(doctor.getDoctorPhone());
        doctorViewHolder.userEmail.setText(doctor.getDoctorEmail());
        doctorViewHolder.profileImage.setImageBitmap(StringToBitMap(doctor.getDoctorImage()));
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

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public void setClickListener(ItemClickLitener itemClickListener) {
        this.itemClickLitener = itemClickListener;
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView profileImage;
        TextView userName,userDetails,userAppoinmentDate,userPhone,userEmail;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            initialization(itemView);
            itemView.setOnClickListener(this);
            //itemView.notifyAll();
        }
        private void initialization(View v)
        {
            profileImage = v.findViewById(R.id.show_doctorProfilId);
            userName = v.findViewById(R.id.showDoctorNameTV_Id);
            userDetails = v.findViewById(R.id.showDoctorDetailsTV_Id);
            userAppoinmentDate = v.findViewById(R.id.showDoctorDateTV_Id);
            userPhone = v.findViewById(R.id.showDoctorPhoneTV_Id);
            userEmail = v.findViewById(R.id.showDoctorEmailTV_Id);
        }


        @Override
        public void onClick(View v)
        {
            if(itemClickLitener != null)
            {
                itemClickLitener.onClick(v,getAdapterPosition());
            }
        }
    }

}
