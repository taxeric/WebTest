package com.kuang.controller;

import com.kuang.TopContract;
import com.kuang.dto.HttpResponse;
import com.kuang.pojo.FileListEntity;
import com.kuang.utils.FileUtils;
import com.kuang.utils.TextUtils;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class FileController {

    @GetMapping(value = "/{parentUrl}/{fileName}")
    public ResponseEntity<FileSystemResource> getFile(
            @PathVariable("parentUrl") String parentUrl,
            @PathVariable("fileName") String fileName
    ) throws FileNotFoundException {
        String parentFolder = FileUtils.runtimeFolder;
        if (parentUrl.contains("except")){
            parentFolder += TopContract.EXCEPTION_FOLDER;
        } else {
            parentFolder += TopContract.FILE_FOLDER;
        }
        File file = new File(parentFolder, fileName);
        if (file.exists()) {
            return export(file);
        }
        return export(new File(parentFolder + TopContract.FILE_FOLDER, TopContract.NOT_FOUND_FILE_NAME));
    }

    @GetMapping("/getFileList/{path}")
    public HttpResponse<List<FileListEntity>> getFileList(
            @PathVariable("path") String path
    ){
        List<FileListEntity> fileListEntities = new ArrayList<>();
        String parentFolder = FileUtils.runtimeFolder;
        StringBuilder childFolder = new StringBuilder("/" + path);
        if (childFolder.toString().lastIndexOf(childFolder.length() - 1) != '/'){
            childFolder.append("/");
        }
        String resultFolder = parentFolder + childFolder;
        HttpResponse<List<FileListEntity>> response;
        File file = new File(resultFolder);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (File f : files) {
                        String filename = f.getName();
                        FileListEntity entity = new FileListEntity();
                        if (filename.contains(".")){
                            int i = filename.lastIndexOf(".");
                            entity.filename = filename.substring(0, i);
                            entity.type = filename.substring(i + 1) + "??????";
                        } else {
                            entity.filename = filename;
                            entity.type = f.isDirectory() ? "?????????" : "????????????";
                        }
                        childFolder.append(filename);
                        entity.fileUrl = childFolder.toString();
                        childFolder.delete(childFolder.length() - filename.length(), childFolder.length());
                        if (f.isDirectory()){
                            entity.fileSize = "?????????";
                            entity.fileRealSize = 0L;
                        } else {
                            long size = f.length();
                            int sizeInt = (int) size / 1024 / 1024;
                            String sizeStr;
                            if (sizeInt <= 0) {
                                sizeStr = "<1 MB";
                            } else {
                                sizeStr = sizeInt + " MB";
                            }
                            entity.fileSize = sizeStr;
                            entity.fileRealSize = size;
                        }
                        fileListEntities.add(entity);
                    }
                }
                response = new HttpResponse<>(200, "ok", fileListEntities);
            } else {
                response = new HttpResponse<>(103, "This is a file", fileListEntities);
            }
        } else {
            response = new HttpResponse<>(104, "The path isn't exists", fileListEntities);
        }
        return response;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }
}
