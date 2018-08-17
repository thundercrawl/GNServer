package com.ifarm.console.shared.domain.vo;


import java.util.ArrayList;
import java.util.List;

import com.ifarm.console.shared.domain.po.FundEfficientPO;

public class BusinessStatItemVO  {
	private Integer passedpeople;
	private Integer finishpeople;
	private Integer waitPeople;

	private Integer fundSum;
	private Integer leftfundSum;
	private Integer monthlyFundSum;
	
	
	private List<FundEfficientPO> values;

    /**
     * @return Integer return the passedpeople
     */
    public Integer getPassedpeople() {
        return passedpeople;
    }

    /**
     * @param passedpeople the passedpeople to set
     */
    public void setPassedpeople(Integer passedpeople) {
        this.passedpeople = passedpeople;
    }

    /**
     * @return Integer return the finishpeople
     */
    public Integer getFinishpeople() {
        return finishpeople;
    }

    /**
     * @param finishpeople the finishpeople to set
     */
    public void setFinishpeople(Integer finishpeople) {
        this.finishpeople = finishpeople;
    }

    /**
     * @return Integer return the waitPeople
     */
    public Integer getWaitPeople() {
        return waitPeople;
    }

    /**
     * @param waitPeople the waitPeople to set
     */
    public void setWaitPeople(Integer waitPeople) {
        this.waitPeople = waitPeople;
    }

    /**
     * @return Integer return the fundSum
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
     * @return Integer return the monthlyFundSum
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
     * @return List<FundEfficientPO> return the values
     */
    public List<FundEfficientPO> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<FundEfficientPO> values) {
        this.values = values;
    }


    /**
     * @return Integer return the leftfundSum
     */
    public Integer getLeftfundSum() {
        return leftfundSum;
    }

    /**
     * @param leftfundSum the leftfundSum to set
     */
    public void setLeftfundSum(Integer leftfundSum) {
        this.leftfundSum = leftfundSum;
    }

}
