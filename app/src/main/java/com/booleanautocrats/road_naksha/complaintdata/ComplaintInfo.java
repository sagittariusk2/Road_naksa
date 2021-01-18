package com.booleanautocrats.road_naksha.complaintdata;

public class ComplaintInfo {

    String ImageName;
    String priority;
    String address;
    String done;
    String text;
    String nagarName;

    public ComplaintInfo() {

    }

    public ComplaintInfo(String imageName, String priority, String done, String text, String nagarName, String address) {
        ImageName = imageName;
        this.priority = priority;
        this.address = address;
        this.done = done;
        this.text = text;
        this.nagarName = nagarName;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getnagarName() {
        return nagarName;
    }

    public void setnagarName(String nagarName) {
        this.nagarName = nagarName;
    }
}
