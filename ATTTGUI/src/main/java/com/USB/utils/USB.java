package com.USB.utils;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

public class USB {
    public static File getUSBDisk(){
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] f = File.listRoots();
        for(int i = 0; i < f.length; i++)
        {
            if(fsv.getSystemDisplayName(f[i]).contains("Duc Pro Vjp Da Crack USB Nay")){
                return f[i];
            }
        }
        return null;
    }
}
