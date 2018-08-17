package com.ifarm.console.shared.domain.po;

import java.util.Date;
public class FundEfficientPO extends BasePO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6822541924912231741L;
	private int id;
	private Date fundDate;
	private String fundSource;
	private Integer fundSum;
	private String fundUsage;
	private Integer monthlyFundSum;
	private Integer alreadyLentFundSum;
	private Integer leftFundSum;
	

    /**
     * @return float return the LeftFundSum
     */
    public Integer getLeftFundSum() {
        return leftFundSum;
    }

    /**
     * @param LeftFundSum the LeftFundSum to set
     */
    public void setLeftFundSum(Integer LeftFundSum) {
        this.leftFundSum = LeftFundSum;
    }


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the fundSource
     */
    public String getFundSource() {
        return fundSource;
    }

    /**
     * @param fundSource the fundSource to set
     */
    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    /**
     * @return float return the fundSum
     */
    public Integer getFundSum() {
        return fundSum;
    }

    /**
     * @param fundSum the fundSum to set
     */
    public void setFundSum(Integer fundSum) {
        this.fundSum = fundSum;
    }

    /**
     * @return String return the fundUsage
     */
    public String getFundUsage() {
        return fundUsage;
    }

    /**
     * @param fundUsage the fundUsage to set
     */
    public void setFundUsage(String fundUsage) {
        this.fundUsage = fundUsage;
    }

    /**
     * @return float return the monthlyFundSum
     */
    public Integer getMonthlyFundSum() {
        return monthlyFundSum;
    }

    /**
     * @param monthlyFundSum the monthlyFundSum to set
     */
    public void setMonthlyFundSum(Integer monthlyFundSum) {
        this.monthlyFundSum = monthlyFundSum;
    }

    /**
     * @return float return the alreadyLentFundSum
     */
    public Integer getAlreadyLentFundSum() {
        return alreadyLentFundSum;
    }

    /**
     * @param alreadyLentFundSum the alreadyLentFundSum to set
     */
    public void setAlreadyLentFundSum(Integer alreadyLentFundSum) {
        this.alreadyLentFundSum = alreadyLentFundSum;
    }


    /**
     * @return Date return the fundDate
     */
    public Date getFundDate() {
        return fundDate;
    }

    /**
     * @param fundDate the fundDate to set
     */
    public void setFundDate(Date fundDate) {
        this.fundDate = fundDate;
    }

}
