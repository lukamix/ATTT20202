package com.attt.ducnb.Model;

public class EsknZMAC {
    private String Eskn;
    private String z;
    private String MAC;
    
    public EsknZMAC(String[] EsknZMAC){
        this.Eskn = EsknZMAC[0];
        this.z = EsknZMAC[1];
        this.MAC = EsknZMAC[2];
    }
    
    public String getEskn() {
        return Eskn;
    }

    public void setEskn(String Eskn) {
        this.Eskn = Eskn;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
    
}
