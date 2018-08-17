package com.ifarm.console.shared.domain.vo;

import java.util.List;

import com.ifarm.console.shared.domain.po.DingShengPO;
import com.ifarm.console.shared.domain.po.KunPengPO;

public class BusinessStatVO extends BaseVO {
private List<KunPengPO> kunpeng;
private List<KunPengPO> dingsheng;
private BusinessStatItemVO kunpengitem;
private BusinessStatItemVO dingshengitem;


    /**
     * @return List<KunPengPO> return the kunpeng
     */
    public List<KunPengPO> getKunpeng() {
        return kunpeng;
    }

    /**
     * @param kunpeng the kunpeng to set
     */
    public void setKunpeng(List<KunPengPO> kunpeng) {
        this.kunpeng = kunpeng;
    }

    /**
     * @return List<DingShengPO> return the dingsheng
     */
    public List<KunPengPO> getDingsheng() {
        return dingsheng;
    }

    /**
     * @param dingsheng the dingsheng to set
     */
    public void setDingsheng(List<KunPengPO> dingsheng) {
        this.dingsheng = dingsheng;
    }



    /**
     * @return BusinessStatVO return the kunpengitem
     */
    public BusinessStatItemVO getKunpengitem() {
        return kunpengitem;
    }

    /**
     * @param kunpengitem the kunpengitem to set
     */
    public void setKunpengitem(BusinessStatItemVO kunpengitem) {
        this.kunpengitem = kunpengitem;
    }

    /**
     * @return BusinessStatVO return the dingshengitem
     */
    public BusinessStatItemVO getDingshengitem() {
        return dingshengitem;
    }

    /**
     * @param dingshengitem the dingshengitem to set
     */
    public void setDingshengitem(BusinessStatItemVO dingshengitem) {
        this.dingshengitem = dingshengitem;
    }

}
