package com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model;

public class Medical
{
    String medicalId;
    String medicalDoctorName;
    String medicalDetails;
    String medicalDate;
    String medicalPrescriptionImage;

    public Medical(String medicalId, String medicalDoctorName, String medicalDetails, String medicalDate, String medicalPrescriptionImage) {
        this.medicalId = medicalId;
        this.medicalDoctorName = medicalDoctorName;
        this.medicalDetails = medicalDetails;
        this.medicalDate = medicalDate;
        this.medicalPrescriptionImage = medicalPrescriptionImage;
    }

    public Medical(String medicalDoctorName, String medicalDetails, String medicalDate, String medicalPrescriptionImage) {
        this.medicalDoctorName = medicalDoctorName;
        this.medicalDetails = medicalDetails;
        this.medicalDate = medicalDate;
        this.medicalPrescriptionImage = medicalPrescriptionImage;
    }


    public String getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(String medicalId) {
        this.medicalId = medicalId;
    }

    public String getMedicalDoctorName() {
        return medicalDoctorName;
    }

    public void setMedicalDoctorName(String medicalDoctorName) {
        this.medicalDoctorName = medicalDoctorName;
    }

    public String getMedicalDetails() {
        return medicalDetails;
    }

    public void setMedicalDetails(String medicalDetails) {
        this.medicalDetails = medicalDetails;
    }

    public String getMedicalDate() {
        return medicalDate;
    }

    public void setMedicalDate(String medicalDate) {
        this.medicalDate = medicalDate;
    }

    public String getMedicalPrescriptionImage() {
        return medicalPrescriptionImage;
    }

    public void setMedicalPrescriptionImage(String medicalPrescriptionImage) {
        this.medicalPrescriptionImage = medicalPrescriptionImage;
    }
}
