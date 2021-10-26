package BookDB.DBwork;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月17日22时05分
 */

/**
 * 书本类型实现类
 */
public class Book {
    private String bkID;
    private String bkName;
    private String bkAuthor;
    private String bkPress;
    private double bkPrice;
    private int bkState;

    public String getBkID() {
        return bkID;
    }

    public void setBkID(String bkID) {
        this.bkID = bkID;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public String getBkAuthor() {
        return bkAuthor;
    }

    public void setBkAuthor(String bkAuthor) {
        this.bkAuthor = bkAuthor;
    }

    public String getBkPress() {
        return bkPress;
    }

    public void setBkPress(String bkPress) {
        this.bkPress = bkPress;
    }

    public double getBkPrice() {
        return bkPrice;
    }

    public void setBkPrice(double bkPrice) {
        this.bkPrice = bkPrice;
    }

    public int getBkState() {
        return bkState;
    }

    public void setBkState(int bkState) {
        this.bkState = bkState;
    }

    @Override
    public String toString() {
        String State;
        if(bkState == 1){
            State = "在馆";
        }
        else{
            State = "不在馆";
        }
        return "Book{" +
                "书本号='" + bkID + '\'' +
                ", 书名='" + bkName + '\'' +
                ", 作者='" + bkAuthor + '\'' +
                ", 出版社='" + bkPress + '\'' +
                ", 价格='" + bkPrice+ '\''+
                ", 状态='"+State+ '\''+
                '}';
    }
}
