package com.example.pratyushsingh.janelaaj;

import java.util.ArrayList;

/**
 * Created by pratyushsingh on 21/05/18.
 */

public class Response2 {

    String status;
    int devtime;
    int mantime;
    int saletime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    ArrayList<info> data;

    public int getDevtime() {
        return devtime;
    }

    public void setDevtime(int devtime) {
        this.devtime = devtime;
    }

    public int getMantime() {
        return mantime;
    }

    public void setMantime(int mantime) {
        this.mantime = mantime;
    }

    public int getSaletime() {
        return saletime;
    }

    public void setSaletime(int saletime) {
        this.saletime = saletime;
    }

    public ArrayList<info> getData() {
        return data;
    }

    public void setData(ArrayList<info> data) {
        this.data = data;
    }

    class info{


        int id;
        int age;
        String fname;
        String lname;
        String did;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }

}
