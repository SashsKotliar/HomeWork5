public class Product {
    private String description;
    private boolean inStock;
    private double price;
    private int discountForMembers;

    public String getDescription(){
        return this.description;
    }
    public boolean getInStock(){
        return this.inStock;
    }
    public double getPrice(){
        return this.price;
    }
    public int getDiscountForMembers(){
        return this.discountForMembers;
    }
    public void getDescription(String name){
        this.description = name;
    }
    public void setInStock(boolean inStock){
        this.inStock = inStock;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setDiscountForMembers(int discountForMembers){
        this.discountForMembers = discountForMembers;
    }
    public Product(String name, boolean inStock, int price, int discountForMembers){
        this.description = name;
        this.inStock = inStock;
        this.price = price;
        this.discountForMembers = discountForMembers;
    }

    public String toString(){
        return this.description + " - " + this.price + "$ (-" + discountForMembers + "% for members)";
    }
}
