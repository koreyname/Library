package tyz.example.libraray.Bean;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class Cart {
    public Map<Integer,CartItem> map=new HashMap<>();
    private Integer totalCount;
    private Double totalAmount;
   public Cart(){
    }
    public Collection<CartItem> getAllConnection(){
       return map.values();
    }

    public Double getTotalAmount() {
        Collection<CartItem> values = map.values();
        BigDecimal t=new BigDecimal(0);
        for(CartItem ci:values){
            BigDecimal am=new BigDecimal(ci.getAmount()+"");
            t=t.add(am);
        }
        this.totalAmount=t.doubleValue();
        return totalAmount;
    }
    public Integer getTotalCount(){
        Collection<CartItem> values = map.values();
        Integer t=0;
        for(CartItem ci:values){
            t+=ci.getCount();
        }
        this.totalCount=t;
        return totalCount;
   }
   public void addCart(book b){
       CartItem ci= map.get(b.getBookId());
       if(ci==null){
           CartItem c_t=new CartItem(b,1);
           map.put(b.getBookId(),c_t);
       }
       else{
        ci.setCount(ci.getCount()+1);
       }
   }
   public void deleteCartItem(int id){
       map.remove(id);
   }
    public void addCount(int id){
       CartItem cartItem=map.get(id);
       cartItem.setCount(cartItem.getCount()+1);
    }

    public void subCount(int id) {
        CartItem cartItem = map.get(id);
        cartItem.setCount(cartItem.getCount() - 1);
        if(cartItem.getCount()==0){
            map.remove(id);
        }
    } public void changeCount(int id,int count) {
        CartItem cartItem = map.get(id);
        cartItem.setCount(count);
        if(cartItem.getCount()==0){
            map.remove(id);
        }
    }
}
