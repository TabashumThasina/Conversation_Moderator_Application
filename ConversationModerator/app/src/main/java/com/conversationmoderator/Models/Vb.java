
package com.conversationmoderator.Models;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vb implements Serializable
{

    @SerializedName("1")
    @Expose
    private Double _1;
    @SerializedName("2")
    @Expose
    private Double _2;
    private final static long serialVersionUID = 8319788539281916672L;

    public Double get1() {
        return _1;
    }

    public void set1(Double _1) {
        this._1 = _1;
    }

    public Double get2() {
        return _2;
    }

    public void set2(Double _2) {
        this._2 = _2;
    }

}
