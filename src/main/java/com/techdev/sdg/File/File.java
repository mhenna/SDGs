package com.techdev.sdg.File;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.techdev.sdg.NGO.NGO;

import javax.persistence.*;

@Entity
@Table(name = "File")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fileName", nullable = false, unique = true)
    private String fileName;

    @Column(name = "fileType", nullable = false)
    private String fileType;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ngo_id", nullable = false)
    @JsonBackReference
    private NGO ngo;

    public File() {

    }

    public File(String fileName, String fileType, byte[] data) {
        setFileName(fileName);
        setFileType(fileType);
        setData(data);
    }

    public void setNgo(NGO ngo) {
        this.ngo = ngo;
    }

    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public void setData(byte[] data) { this.data = data; }

    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public byte[] getData() { return data; }

    @Override
    public String toString() {
        return "File: {\n" +
                "\tid: " + id + ",\n" +
                "\tfileName: " + fileName + ",\n" +
                "\tfileType: " + fileType + "\n" +
                '}';
    }
}
