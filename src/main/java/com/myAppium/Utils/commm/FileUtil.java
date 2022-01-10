package com.myAppium.Utils.commm;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public File creatNewFile(String filePath){
        File file = new File(filePath);

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                file = null;
                e.printStackTrace();
                System.out.println(e.getMessage()+": " +filePath);
            }
        }
        return file;
    }
}
