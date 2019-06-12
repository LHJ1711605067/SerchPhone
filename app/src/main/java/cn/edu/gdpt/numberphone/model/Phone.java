package cn.edu.gdpt.numberphone.model;

public class Phone {

    /**
     * mts : 1301638
     * province : 广东
     * catName : 中国联通
     * telString : 13016380201
     * areaVid : 30517
     * ispVid : 137815084
     * carrier : 广东联通
     */

    private String mts;
    private String province;
    private String catName;
    private String telString;
    private String areaVid;
    private String ispVid;
    private String carrier;

    public String getMts() {
        return mts;
    }

    public void setMts(String mts) {
        this.mts = mts;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getTelString() {
        return telString;
    }

    public void setTelString(String telString) {
        this.telString = telString;
    }

    public String getAreaVid() {
        return areaVid;
    }

    public void setAreaVid(String areaVid) {
        this.areaVid = areaVid;
    }

    public String getIspVid() {
        return ispVid;
    }

    public void setIspVid(String ispVid) {
        this.ispVid = ispVid;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
