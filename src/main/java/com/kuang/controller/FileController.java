package com.kuang.controller;

import com.kuang.TopContract;
import com.kuang.utils.FileUtils;
import com.kuang.utils.TextUtils;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

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
