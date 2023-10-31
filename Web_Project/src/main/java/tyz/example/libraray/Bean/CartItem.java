package tyz.example.libraray.Bean;

import java.math.BigDecimal;

public class CartItem {
    private book boo;
    private Integer count;
    private double amount;//购物车中的金额

    @Override
    public String toString() {
        return "CartItem{" +
                "boo=" + boo +
                ", count=" + count +
                ", amount=" + amount +
                '}';
    }

    public CartItem() {
    }

    public CartItem(book boo, Integer count) {
        this.boo = boo;
        this.count = count;
        BigDecimal price=new BigDecimal(this.boo.getPrice()+"");
        BigDecimal Count=new BigDecimal(this.count+"");
        this.amount=price.multiply(Count).doubleValue();
    }

    public book getBoo() {
        return boo;
    }

    public void setBoo(book boo) {
        this.boo = boo;
        //设置图书时计算的金额
        BigDecimal price=new BigDecimal(boo.getPrice()+"");
        BigDecimal Count=new BigDecimal(this.count+"");
        this.amount=price.multiply(Count).doubleValue();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        BigDecimal price=new BigDecimal(this.boo.getPrice()+"");
        BigDecimal Count=new BigDecimal(this.count+"");
        this.amount=price.multiply(Count).doubleValue();
    }

    public double getAmount() {
        return amount;
    }

}
