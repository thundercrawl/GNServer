package com.ifarm.console.shared.domain.dto;

public class FundEfficientDTO extends BaseDTO {

	private  String alreadyLentFundSum;

    /**
     * @return String return the alreadyLentFundSum
     */
    public String getAlreadyLentFundSum() {
        return alreadyLentFundSum;
    }

    /**
     * @param alreadyLentFundSum the alreadyLentFundSum to set
     */
    public void setAlreadyLentFundSum(String alreadyLentFundSum) {
        this.alreadyLentFundSum = alreadyLentFundSum;
    }

}
