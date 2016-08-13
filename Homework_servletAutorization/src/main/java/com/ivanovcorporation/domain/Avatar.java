package com.ivanovcorporation.domain;

/**
 * Created by java on 19.03.2016.
 */
public class Avatar {

    private Long avatarid;
    private String filename;
    private byte[] imagedata;
    private Long userid;

    public Avatar(Long avatarid, String filename, byte[] imagedata, Long userid) {
        this.avatarid = avatarid;
        this.filename = filename;
        this.imagedata = imagedata;
        this.userid = userid;
    }

    public Long getAvatarid() {
        return avatarid;
    }

    public void setAvatarid(Long avatarid) {
        this.avatarid = avatarid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getImagedata() {
        return imagedata;
    }

    public void setImagedata(byte[] imagedata) {
        this.imagedata = imagedata;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
