public class ShoppingCart {
    private Product [] productsInCart;

    public Product [] getProductsInCart(){
        return this.productsInCart;
    }
    public void setProductsInCart(Product[]productsInCart){
        this.productsInCart = productsInCart;
    }
    public ShoppingCart (Product[]productsInCart){
        this.productsInCart = productsInCart;
    }
    public String toString (){
        String cartProducts = "";
        for (int i = 0; i < this.productsInCart.length; i++){
            cartProducts = cartProducts + this.productsInCart[i] + ",";
        }
        return cartProducts;
    }
}
