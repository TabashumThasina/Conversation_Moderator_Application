
package com.conversationmoderator.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FinalDf implements Serializable
{

    @SerializedName("NN")
    @Expose
    private Nn nn;
    @SerializedName("JJ")
    @Expose
    private Jj jj;
    @SerializedName("MD")
    @Expose
    private Md md;
    @SerializedName("VB")
    @Expose
    private Vb vb;
    @SerializedName("NNS")
    @Expose
    private Nns nns;
    @SerializedName("DT")
    @Expose
    private Dt dt;
    @SerializedName("IN")
    @Expose
    private In in;
    @SerializedName("RB")
    @Expose
    private Rb rb;
    @SerializedName("VBP")
    @Expose
    private Vbp vbp;
    private final static long serialVersionUID = 958844195473198637L;

    public Nn getNn() {
        return nn;
    }

    public void setNn(Nn nn) {
        this.nn = nn;
    }

    public Jj getJj() {
        return jj;
    }

    public void setJj(Jj jj) {
        this.jj = jj;
    }

    public Md getMd() {
        return md;
    }

    public void setMd(Md md) {
        this.md = md;
    }

    public Vb getVb() {
        return vb;
    }

    public void setVb(Vb vb) {
        this.vb = vb;
    }

    public Nns getNns() {
        return nns;
    }

    public void setNns(Nns nns) {
        this.nns = nns;
    }

    public Dt getDt() {
        return dt;
    }

    public void setDt(Dt dt) {
        this.dt = dt;
    }

    public In getIn() {
        return in;
    }

    public void setIn(In in) {
        this.in = in;
    }

    public Rb getRb() {
        return rb;
    }

    public void setRb(Rb rb) {
        this.rb = rb;
    }

    public Vbp getVbp() {
        return vbp;
    }

    public void setVbp(Vbp vbp) {
        this.vbp = vbp;
    }

}
