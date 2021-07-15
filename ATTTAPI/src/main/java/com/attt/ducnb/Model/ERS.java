package com.attt.ducnb.Model;

public final class ERS {
    private String e;
    private String r;
    private String s;
    
    public ERS(String ers[]){
        setE(ers[0]);
        setR(ers[1]);
        setS(ers[2]);
    }
    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
    
}
