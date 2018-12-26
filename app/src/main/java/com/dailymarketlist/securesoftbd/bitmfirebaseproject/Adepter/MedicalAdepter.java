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

import com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model.Medical;
import com.dailymarketlist.securesoftbd.bitmfirebaseproject.R;

import java.util.List;

public class MedicalAdepter extends RecyclerView.Adapter<MedicalAdepter.MedicalViewHolder> {

    private Context context;
    private List<Medical> medicalList;
    private ItemClickLitener itemClickLitener;
    public MedicalAdepter(Context context, List<Medical> medicalList)
    {
        this.context = context;
        this.medicalList = medicalList;
    }

    @NonNull
    @Override
    public MedicalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_show_medical_adepter,null);
        return new MedicalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalViewHolder medicalViewHolder, int i)
    {

        Medical medical = medicalList.get(i);

        medicalViewHolder.doctorName.setText(medical.getMedicalDoctorName());
        medicalViewHolder.prescriptionDetails.setText(medical.getMedicalDetails());
        medicalViewHolder.prescriptionDate.setText(medical.getMedicalDate());
        medicalViewHolder.prescriptionIV.setImageBitmap(StringToBitMap(medical.getMedicalPrescriptionImage()));

    }

    public Bitmap StringToBitMap(String encodedString)
    {
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
    public int getItemCount()
    {
        return medicalList.size();
    }

    public void setClickListener(ItemClickLitener itemClickListener) {
        this.itemClickLitener = itemClickListener;
    }

    class MedicalViewHolder extends RecyclerView.ViewHolder implements ItemClickLitener
    {
        ImageView prescriptionIV;
        TextView doctorName,prescriptionDetails,prescriptionDate;

        public MedicalViewHolder(@NonNull View itemView)
        {
            super(itemView);
            prescriptionIV = itemView.findViewById(R.id.show_Prescription_imageId);
            doctorName = itemView.findViewById(R.id.showPrescriptionDoctorNameId);
            prescriptionDetails = itemView.findViewById(R.id.showPrescriptionDetailsId);
            prescriptionDate = itemView.findViewById(R.id.showPrescriptionDateId);
        }

        @Override
        public void onClick(View view, int position) {
            if(itemClickLitener != null)
            {
                itemClickLitener.onClick(view,getAdapterPosition());
            }
        }
    }
}
