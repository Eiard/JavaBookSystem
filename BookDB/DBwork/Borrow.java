package BookDB.DBwork;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月18日22时17分
 */
public class Borrow {
    private String rdID;
    private String bkID;
    private String DateBorrow;
    private String DateLendPlan;
    private String DateLendAct;

    public String getRdID() {
        return rdID;
    }

    public void setRdID(String rdID) {
        this.rdID = rdID;
    }

    public String getBkID() {
        return bkID;
    }

    public void setBkID(String bkID) {
        this.bkID = bkID;
    }

    public String getDateBorrow() {
        return DateBorrow;
    }

    public void setDateBorrow(String dateBorrow) {
        DateBorrow = dateBorrow;
    }

    public String getDateLendPlan() {
        return DateLendPlan;
    }

    public void setDateLendPlan(String dateLendPlan) {
        DateLendPlan = dateLendPlan;
    }

    public String getDateLendAct() {
        return DateLendAct;
    }

    public void setDateLendAct(String dateLendAct) {
        DateLendAct = dateLendAct;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "rdID='" + rdID + '\'' +
                ", bkID='" + bkID + '\'' +
                ", DateBorrow='" + DateBorrow + '\'' +
                ", DateLendPlan='" + DateLendPlan + '\'' +
                ", DateLendAct='" + DateLendAct + '\'' +
                '}';
    }
}
