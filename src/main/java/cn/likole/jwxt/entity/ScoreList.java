package cn.likole.jwxt.entity;

import java.util.List;

/**
 * Created by likole on 1/23/18.
 * 成绩列表
 */
public class ScoreList {
    /**
     * 成绩列表
     */
    private List<Score> cjList;
    /**
     * 学期总学分
     */
    private int zxf;
    /**
     * 学期总学时
     */
    private int zxs;
    /**
     * 最低修读学分
     */
    private int yqxf;
    /**
     * 已修读课程总学分
     */
    private int yxxf;
    /**
     * 通过课程门数
     */
    private int tgms;
    /**
     * 已修读课程门数
     */
    private int zms;
    /**
     * 列表名称
     */
    private String cjlx;

    public List<Score> getCjList() {
        return cjList;
    }

    public void setCjList(List<Score> cjList) {
        this.cjList = cjList;
    }

    public int getZxf() {
        return zxf;
    }

    public void setZxf(int zxf) {
        this.zxf = zxf;
    }

    public int getZxs() {
        return zxs;
    }

    public void setZxs(int zxs) {
        this.zxs = zxs;
    }

    public int getYqxf() {
        return yqxf;
    }

    public void setYqxf(int yqxf) {
        this.yqxf = yqxf;
    }

    public int getYxxf() {
        return yxxf;
    }

    public void setYxxf(int yxxf) {
        this.yxxf = yxxf;
    }

    public int getTgms() {
        return tgms;
    }

    public void setTgms(int tgms) {
        this.tgms = tgms;
    }

    public int getZms() {
        return zms;
    }

    public void setZms(int zms) {
        this.zms = zms;
    }

    public String getCjlx() {
        return cjlx;
    }

    public void setCjlx(String cjlx) {
        this.cjlx = cjlx;
    }

    @Override
    public String toString() {
        return "ScoreList{" +
                "cjList=" + cjList +
                ", zxf=" + zxf +
                ", zxs=" + zxs +
                ", yqxf=" + yqxf +
                ", yxxf=" + yxxf +
                ", tgms=" + tgms +
                ", zms=" + zms +
                ", cjlx='" + cjlx + '\'' +
                '}';
    }
}
