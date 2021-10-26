package BookDB.DBwork;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月17日22时05分
 */
public class Reader {
    private String rdID;
    private int rdType;
    private String rdName;
    private String rdDept;
    private String rdQQ;
    private int rdBorrowQty;

    public String getRdID() {
        return rdID;
    }

    public void setRdID(String rdID) {
        this.rdID = rdID;
    }

    public int getRdType() {
        return rdType;
    }

    public void setRdType(int rdType) {
        this.rdType = rdType;
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
    }

    public String getRdDept() {
        return rdDept;
    }

    public void setRdDept(String rdDept) {
        this.rdDept = rdDept;
    }

    public String getRdQQ() {
        return rdQQ;
    }

    public void setRdQQ(String rdQQ) {
        this.rdQQ = rdQQ;
    }

    public int getRdBorrowQty() {
        return rdBorrowQty;
    }

    public void setRdBorrowQty(int rdBorrowQty) {
        this.rdBorrowQty = rdBorrowQty;
    }

    @Override
    public String toString() {
        return "读者信息{" +
                "读者ID='" + rdID + '\'' +
                ", 读者类型='" + rdType + '\'' +
                ", 读者姓名='" + rdName + '\'' +
                ", 读者组织='" + rdDept + '\'' +
                ", QQ='" + rdQQ + '\'' +
                ", 读者已借数量=" + rdBorrowQty +
                '}';
    }
}
