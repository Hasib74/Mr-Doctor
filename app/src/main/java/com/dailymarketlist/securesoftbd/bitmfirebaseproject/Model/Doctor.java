package com.dailymarketlist.securesoftbd.bitmfirebaseproject.Model;

public class Doctor
{
    String doctorId;
    String doctorName;
    String doctorDetails;
    String doctorAppoinmentDate;
    String doctorPhone;
    String doctorEmail;
    String doctorImage;

    public Doctor(String doctorId, String doctorName, String doctorDetails, String doctorAppoinmentDate, String doctorPhone, String doctorEmail, String doctorImage) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppoinmentDate = doctorAppoinmentDate;
        this.doctorPhone = doctorPhone;
        this.doctorEmail = doctorEmail;
        this.doctorImage = doctorImage;
    }

    public Doctor(String doctorName, String doctorDetails, String doctorAppoinmentDate, String doctorPhone, String doctorEmail, String doctorImage) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.doctorAppoinmentDate = doctorAppoinmentDate;
        this.doctorPhone = doctorPhone;
        this.doctorEmail = doctorEmail;
        this.doctorImage = doctorImage;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public String getDoctorAppoinmentDate() {
        return doctorAppoinmentDate;
    }

    public void setDoctorAppoinmentDate(String doctorAppoinmentDate) {
        this.doctorAppoinmentDate = doctorAppoinmentDate;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }
}
