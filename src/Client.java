import java.util.Date;

public class Client {
    private String firstName;
    private String secondName;
    private String username;
    private String password;
    private boolean hasMembership;
    private ShoppingCart[] carts = new ShoppingCart[0];
    private double overallSumOverPurchases;
    private Date lastPurchase;

    public String getFirstName(){
        return this.firstName;
    }
    public String getSecondName(){
        return this.secondName;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public boolean getHasMembership(){
        return this.hasMembership;
    }
    public ShoppingCart [] getCarts(){
        return this.carts;
    }
    public double getOverallSumOverPurchases(){
        return this.overallSumOverPurchases;
    }
    public Date getLastPurchase(){
        return this.lastPurchase;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setSecondName(String secondName){
        this.secondName = secondName;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setHasMembership(boolean clubMember){
        this.hasMembership = clubMember;
    }
    public void setCarts(ShoppingCart [] carts){
        this.carts = carts;
    }
    public void setOverallSumOverPurchases(double overallSumOverPurchases){
        this.overallSumOverPurchases = overallSumOverPurchases;
    }
    public void setLastPurchase(Date lastPurchase){
        this.lastPurchase = lastPurchase;
    }


    public Client(String firstName, String secondName, String username, String password,
                  boolean hasMembership){
        this.firstName = firstName;
        this.secondName = secondName;
        this.username = username;
        this.password = password;
        this.hasMembership = hasMembership;
    }

    public String toString(){
        String str = "";
        if (this.carts.length == 0){
            str = this.firstName + " " + this.secondName + (this.hasMembership? "(VIP) ":"") +
                    "\nNumber of purchases: " + this.carts.length + ", spent: " + this.overallSumOverPurchases
                    +"$";
        } else {
            str = this.firstName + " " + this.secondName + (this.hasMembership ? "(VIP) " : "") +
                    "\nNumber of purchases: " + this.carts.length + ", spent: " + this.overallSumOverPurchases
                    + "$" + "\nThe last purchase on: " + this.lastPurchase;
        }
        return str;
    }
}
