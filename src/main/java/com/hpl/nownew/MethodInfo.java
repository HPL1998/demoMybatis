package com.hpl.nownew;

public class MethodInfo {
    private int keyId;
    private String patientName;
    private String patientSex;
    private int patientAge;
    private String doctorName;
    private String disease;
    private String cure;
    private String symptom;
    private String methodName;
    private String methodDetail;
    private int isOk;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDetail() {
        return methodDetail;
    }

    public void setMethodDetail(String methodDetail) {
        this.methodDetail = methodDetail;
    }

    public int getIsOk() {
        return isOk;
    }

    public void setIsOk(int isOk) {
        this.isOk = isOk;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "keyId=" + keyId +
                ", patientName='" + patientName + '\'' +
                ", patientSex=" + patientSex +
                ", patientAge=" + patientAge +
                ", doctorName='" + doctorName + '\'' +
                ", disease='" + disease + '\'' +
                ", cure='" + cure + '\'' +
                ", sympton='" + symptom + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodDetail='" + methodDetail + '\'' +
                ", isOk=" + isOk +
                '}';
    }
}
