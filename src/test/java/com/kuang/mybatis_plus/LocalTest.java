package com.kuang.mybatis_plus;

import com.kuang.dto.HttpResponse;
import com.kuang.pojo.FileListEntity;
import com.kuang.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalTest {

    @Test
    void testFile(){
        File file = new File("E:\\资料\\数据结构");
        System.out.println(file.isDirectory());
        System.out.println();
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null){
                for (File f :
                        files) {
                    String name = f.getName();
                    if (name.contains(".")) {
                        int i = name.lastIndexOf(".");
                        System.out.println(name.substring(0, i));
                        String k = name.substring(i + 1);
                        System.out.println(k);
//                        String suffix = f.getName().split("[.]")[1];
//                        System.out.println(suffix);
                    } else {
                        System.out.println(name);
                    }
                    System.out.println(f.getPath());
                    System.out.println(f.getTotalSpace());
                    System.out.println(f.length());
                    System.out.println();
                }
            }
        }
    }

    @Test
    void testFileList(){
        System.out.println("--------------------------------------------");
        System.out.println(getFileList("target\\test-classes\\com\\kuang\\mybatis_plus"));
        System.out.println("--------------------------------------------");
    }

    public HttpResponse<List<FileListEntity>> getFileList(String path){
        List<FileListEntity> fileListEntities = new ArrayList<>();
        String parentFolder = FileUtils.runtimeFolder;
        StringBuilder childFolder = new StringBuilder("\\" + path);
        if (childFolder.toString().lastIndexOf(childFolder.length() - 1) != '\\'){
            childFolder.append("\\");
        }
        String resultFolder = parentFolder + childFolder;
        System.out.println(resultFolder);
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
                            entity.type = filename.substring(i + 1) + "文件";
                        } else {
                            entity.filename = filename;
                            entity.type = f.isDirectory() ? "文件夹" : "未知类型";
                        }
                        childFolder.append(filename);
                        entity.fileUrl = childFolder.toString();
                        childFolder.delete(childFolder.length() - filename.length(), childFolder.length());
                        if (f.isDirectory()){
                            entity.fileSize = "待计算";
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
}
