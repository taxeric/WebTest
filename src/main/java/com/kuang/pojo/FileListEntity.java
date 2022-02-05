package com.kuang.pojo;

/**
 * 文件列表
 */
public class FileListEntity {

    public String filename;
    public String fileUrl;
    public String type;
    public String fileSize;
    public long fileRealSize;

    @Override
    public String toString() {
        return "FileListEntity{" +
                "filename=\"" + filename + '\"' +
                ", fileUrl=\"" + fileUrl + '\"' +
                ", type=\"" + type + '\"' +
                ", fileSize=\"" + fileSize + '\"' +
                ", fileRealSize=" + fileRealSize +
                '}';
    }
}
