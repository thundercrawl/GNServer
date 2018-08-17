package com.ifarm.console.shared.domain.po;

import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

public class ZhiDeContractPO extends BasePO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6147793489716326001L;

	private String filename;
	private String serialnum;
	private String contractname;
	private String contractusage;
	private String signedPart;
	private String comments;
	private byte[] filedata;
	

    /**
     * @return String return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return String return the serialnum
     */
    public String getSerialnum() {
        return serialnum;
    }

    /**
     * @param serialnum the serialnum to set
     */
    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    /**
     * @return String return the contractname
     */
    public String getContractname() {
        return contractname;
    }

    /**
     * @param contractname the contractname to set
     */
    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    /**
     * @return String return the contractusage
     */
    public String getContractusage() {
        return contractusage;
    }

    /**
     * @param contractusage the contractusage to set
     */
    public void setContractusage(String contractusage) {
        this.contractusage = contractusage;
    }

    /**
     * @return String return the signedPart
     */
    public String getSignedPart() {
        return signedPart;
    }

    /**
     * @param signedPart the signedPart to set
     */
    public void setSignedPart(String signedPart) {
        this.signedPart = signedPart;
    }

    /**
     * @return String return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }


    /**
     * @return Blob return the filedata
     */
    public byte[] getFiledata() {
        return filedata;
    }

    /**
     * @param filedata the filedata to set
     */
    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

}
