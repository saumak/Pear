package com.pervasive.pear.pear;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shubh on 11/28/2018.
 */

public class Group implements Serializable{

    String tittle;
    String desc;
    String groupImg;
    ArrayList<String> memebrs;

    public Group(String tittle, String desc, String groupImg,ArrayList<String> memebrs) {
        this.tittle = tittle;
        this.desc = desc;
        this.groupImg = groupImg;
        this.memebrs = memebrs;

    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(String groupImg) {
        this.groupImg = groupImg;
    }

    public ArrayList<String> getMemebrs() {
        return memebrs;
    }

    public void setMemebrs(ArrayList<String> memebrs) {
        this.memebrs = memebrs;
    }
}
