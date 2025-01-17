package com.Erp.service;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    public String uploadFile(String upLoadPath, String originalFileName,byte[] fileData) throws Exception{
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName=uuid.toString()+extension;

        String fileUploadFullUrl= upLoadPath+"/"+savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);

        fos.write(fileData);

        fos.close();

        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception{
        File delete = new File(filePath);
        if (delete.canExecute()){
            delete.delete();
            log.info("파일을 삭제하였습니다.");
        }else {
            log.info("해당 파일이 존재하지 않습니다.");
        }
    }
}
