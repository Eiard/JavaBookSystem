package BookDB.DBwork;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月18日16时58分
 */

/**
 * 读者类型实现类
 */
public class ReaderType {
    private int rdType;
    private String rdTypeName;
    private int canlendQty;
    private int canlendDay;

    public int getRdType() {
        return rdType;
    }

    public void setRdType(int rdType) {
        this.rdType = rdType;
    }

    public String getRdTypeName() {
        return rdTypeName;
    }

    public void setRdTypeName(String rdTypeName) {
        this.rdTypeName = rdTypeName;
    }

    public int getCanlendQty() {
        return canlendQty;
    }

    public void setCanlendQty(int canlendQty) {
        this.canlendQty = canlendQty;
    }

    public int getCanlendDay() {
        return canlendDay;
    }

    public void setCanlendDay(int canlendDay) {
        this.canlendDay = canlendDay;
    }

    @Override
    public String toString() {
        return "读者类别{" +
                "读者类别编号=" + rdType +
                ", 读者类别名='" + rdTypeName + '\'' +
                ", 可借数量=" + canlendQty +
                ", 可借天数=" + canlendDay +
                '}';
    }
}
