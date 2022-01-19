import java.util.Date;
import java.util.Scanner;

public class VirtualMarket {
    private Client[] clients;
    private Client[] workers;
    private Product[] products;
    private Product[] productsInCart;
    public static final int WORKER = 1;
    public static final int REGULAR_WORKER = 1;
    public static final int MANAGER = 2;
    public static final int MEMBER_IN_MANAGEMENT_TEAM = 3;
    public static final int VIP = 1;
    public static final int ALL_CLIENTS = 1;
    public static final int ALL_VIPS = 2;
    public static final int ALL_CLIENTS_DID_PURCHASE = 3;
    public static final int CLIENT_MOST_EXPENSIVE_PURCHASES = 4;
    public static final int ADD_PRODUCT = 5;
    public static final int CHANGE_CONDITION_IN_STOCK = 6;
    public static final int MAKE_PURCHASE = 7;
    public static final int LOG_OUT = 8;
    public static final int ADD_TO_CART = 1;
    public static final int BACK_TO_MENU_OR_LOG_OUT = 2;
    public static final int FINISH_SHOPPING = -1;
    public static final int IN_STOCK = 1;
    public static final String REGULAR_WORKER_W = "regular worker";
    private static final String MANAGER_W = "manager";
    private static final String MEMBER_IN_MANAGEMENT_TEAM_W = "member in management team";
    public static final int DISCOUNT_ON_CART_FOR_REGULAR_WORKER = 10;
    private static final int DISCOUNT_ON_CART_FOR_MANAGER = 20;
    private static final int DISCOUNT_ON_CART_FOR_MEMBER_IN_MANAGEMENT_TEAM = 30;

    public VirtualMarket(){
        this.clients = new Client[0];
        this.workers = new Client[0];
        this.products = new Product[0];
        this.productsInCart = new Product[0];
    }
    //sing up - first option in main menu.
    public void signUp(Scanner scanner){
        Client client;
        if (worker(scanner)) {
            client = new Worker(name(), surname(), username(), password(), hasMembership(scanner),
                    rank(scanner));
            addWorkerToArray(client);
        } else {
            client = new Client(name(), surname(), username(), password(), hasMembership(scanner));
        }
        addClientToArray(client);
    }
    public boolean worker(Scanner scanner){
        System.out.println("Do you want to sing up as a worker or client?");
        System.out.println("For worker - press 1, for client press - any other number");
        int choice = scanner.nextInt();
        boolean worker = false;
        if (choice == WORKER){
            worker = true;
        }
        return worker;
    }
    //CLIENT:
    public String name (){
        String name;
        System.out.println("Enter your name: ");
        do {
            name = new Scanner(System.in).nextLine();
            if (!noDigits(name)){
                System.out.println("Invalid choice.");
            }
        } while (!noDigits(name));
        return name;
    }
    public String surname(){
        String surname;
        System.out.println("Enter your surname: ");
        do {
            surname = new Scanner(System.in).nextLine();
            if (!noDigits(surname)){
                System.out.println("Invalid surname");
            }
        } while (!noDigits(surname));
        return surname;
    }
    public String username(){
        System.out.println("Enter a username: ");
        String username;
        do {
            username = new Scanner(System.in).nextLine();
            if (usernameExists(username)){
                System.out.println("This username is taken. Please, choose another one.");
            }
        } while (usernameExists(username));
        return username;
    }
    public boolean noDigits(String str){
        boolean noDigits = true;
        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                noDigits = false;
            }
        }
        return noDigits;
    }
    public boolean usernameExists(String usernameToCheck){
        boolean exists = false;
        for (int i = 0; i < this.clients.length; i++){
            if (usernameToCheck.equals(this.clients[i].getUsername())){
                exists = true;
            }
        }
        return exists;
    }
    public String password(){
        System.out.println("Enter your password: ");
        String password;
        do {
            password = new Scanner(System.in).nextLine();
            if(password.length() < 6){
                System.out.println("Invalid password");
            }
        } while (password.length() < 6);
        return  password;
    }
    public String rank(Scanner scanner){
        System.out.println("What is your rang?");
        System.out.println("For regular worker - press 1.");
        System.out.println("For a manager - press 2.");
        System.out.println("For a member in management team - press 3");
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice != REGULAR_WORKER && choice != MANAGER && choice != MEMBER_IN_MANAGEMENT_TEAM){
                System.out.println("Invalid choice.");
            }
        } while (choice != REGULAR_WORKER && choice != MANAGER && choice != MEMBER_IN_MANAGEMENT_TEAM);
        String rank = "";
        if (choice == REGULAR_WORKER){
            rank = "regular worker";
        } else if (choice == MANAGER){
            rank = "manager";
        } else {
            rank = "member in management team";
        }
        return rank;
    }
    public boolean hasMembership(Scanner scanner){
        boolean isMember = false;
        System.out.println("If you have a membership - press 1. Else press any other digit");
        int choice = scanner.nextInt();
        if (choice == VIP){
            isMember = true;
        }
        return isMember;
    }
    //add everybody who signed in
    public void addClientToArray(Client client) {
        Client [] newArrayOfClients = new Client[this.clients.length + 1];
        for (int i = 0; i < this.clients.length; i++) {
            newArrayOfClients[i] = this.clients[i];
        }
        newArrayOfClients[this.clients.length] = client;
        this.clients = newArrayOfClients;
    }
    //add only workers
    public void addWorkerToArray(Client client) {
        Client [] newArrayOfWorkers = new Worker[this.workers.length + 1];
        for (int i = 0; i < this.workers.length; i++) {
            newArrayOfWorkers[i] = this.workers[i];
        }
        newArrayOfWorkers[this.workers.length] = client;
        this.workers = newArrayOfWorkers;
    }
    //log in - second choice in main menu
    public Client logIn(Scanner scanner){
        Client client = null;
        System.out.println("If you want to log in as a worker - press 1");
        System.out.println("If you want to log in as a client - press any other number");
        int choice = scanner.nextInt();
        System.out.println("Enter your username: ");
        String usernameToLogIn = new Scanner(System.in).nextLine();
        System.out.println("Enter your password: ");
        String passwordToLogIn = new Scanner(System.in).nextLine();
        if (choice == WORKER){
            if (workerExists(usernameToLogIn)){
                client = passwordOfUsername(usernameToLogIn, passwordToLogIn);
                System.out.println("Hi, " + client.getFirstName() + " " + client.getSecondName() +
                        " (" + ((Worker) client).getRank() + ")!");
                workerMenu(client, scanner);
            } else {
                System.out.println("There is no worker with such username.");
            }
        } else {
            if (clientExists(usernameToLogIn)){
                client = passwordOfUsername(usernameToLogIn, passwordToLogIn);
                System.out.println("Hi, " + client.getFirstName() + " " + client.getSecondName() +
                        " " + (client.getHasMembership()? "(VIP)":"") + "!");
                showAllGoods(client, scanner);
            } else {
                System.out.println("There is no client with such username.");
            }
        }
        return client;
    }
    public boolean workerExists(String usernameToCheck){
        boolean exists = false;
        for (int i = 0; i < this.workers.length; i++){
            if (usernameToCheck.equals(this.workers[i].getUsername())){
                exists = true;
                break;
            }
        }
        return exists;
    }
    public boolean clientExists(String usernameToCheck){
        boolean exists = false;
        for (int i = 0; i < this.clients.length; i++){
            if (usernameToCheck.equals(this.clients[i].getUsername()) && !workerExists(usernameToCheck)){
                exists = true;
                break;
            }
        }
        return exists;
    }
    //checks if it is worker's acc
    public boolean ifWorker(Client client){
        boolean worker = false;
        for (int i = 0; i < this.workers.length; i++){
            if (client == this.workers[i]){
                worker = true;
            }
        }
        return worker;
    }
    //Returns user who logged in if he exists
    public Client passwordOfUsername(String usernameToCheck, String passwordToCheck) {
        int i;
        for (i = 0; i < this.clients.length; i++) {
            if (usernameToCheck.equals(this.clients[i].getUsername())) {
                if (!passwordToCheck.equals(this.clients[i].getPassword())) {
                    do {
                        System.out.println("Invalid password. Try again: ");
                        passwordToCheck = new Scanner(System.in).nextLine();
                    } while (!passwordToCheck.equals(this.clients[i].getPassword()));
                }
                break;
            }
        }
        return this.clients[i];
    }
    //if worker logged in:
    public void workerMenu(Client client, Scanner scanner){
        int option;
        do {
            System.out.println("To show all clients - press 1");
            System.out.println("To show all VIP clients - press 2");
            System.out.println("To show all clients that made a purchase at least once - press 3");
            System.out.println("To show a client that did the purchases for the " +
                    "biggest sum - press 4");
            System.out.println("To add a product - press 5");
            System.out.println("To change the stock condition - press 6");
            System.out.println("To make a purchase - press 7");
            System.out.println("To log out - press 8");
            option = scanner.nextInt();
            switch (option) {
                case ALL_CLIENTS:
                    showAllClients();
                    break;
                case ALL_VIPS:
                    showAllVips();
                    break;
                case ALL_CLIENTS_DID_PURCHASE:
                    showWhoDidPurchase();
                    break;
                case CLIENT_MOST_EXPENSIVE_PURCHASES:
                    showClientThatPurchasedForBiggestSum(client);
                    break;
                case ADD_PRODUCT:
                    postTheProduct(scanner);
                    break;
                case CHANGE_CONDITION_IN_STOCK:
                    changeProductsStockCondition(scanner);
                    break;
                case MAKE_PURCHASE:
                    showAllGoods(client, scanner);
                    break;
            }
        }while (option != LOG_OUT);
    }
    //if regular client logged in or if worker choose to purchase(option 7 in worker's menu):
    public void showAllGoods(Client client, Scanner scanner){
        int number = 1;
        if (this.products.length == 0){
            System.out.println("No products in stock.");
        }else {
            for (int i = 0; i < this.products.length; i++) {
                if (this.products[i].getInStock()) {
                    System.out.println(number + ". " + this.products[i].toString());
                    number++;
                }
            }
            System.out.println("If you want to add something to cart - press 1.");
            if (ifWorker(client)) {
                System.out.println("If you want to go back to menu - press 2.");
            } else {
                System.out.println("If you want to log out - press 2.");
            }
            System.out.println("If you want to finish shopping - press -1.");
            int choice;
            do {
                choice = scanner.nextInt();
                if (choice != ADD_TO_CART && choice != BACK_TO_MENU_OR_LOG_OUT && choice != FINISH_SHOPPING) {
                    System.out.println("Invalid choice");
                }
            } while (choice != ADD_TO_CART && choice != BACK_TO_MENU_OR_LOG_OUT && choice != FINISH_SHOPPING);

            if (choice == ADD_TO_CART) {
                System.out.println("Press the number of a product that you want to add.");
                int choiceOfUser = validChoice(scanner, number);
                System.out.println("Quantity: ");
                int quantity;
                do {
                    quantity = scanner.nextInt();
                    if (quantity < 1) {
                        System.out.println("Invalid quantity.");
                    }
                } while (quantity < 1);

                for (int i = 0; i < quantity; i++) {
                    addProductToCart(this.products[choiceOfUser - 1]);
                }
                ShoppingCart tempCart = new ShoppingCart(this.productsInCart);
                showCart(tempCart);
                showAllGoods(client, scanner);
            } else if (choice == BACK_TO_MENU_OR_LOG_OUT) {
                if (ifWorker(client)) {
                    workerMenu(client, scanner);
                } else {
                    emptyCart();
                }
            } else {
                ShoppingCart cart = new ShoppingCart(this.productsInCart);
                showCart(cart);
                addCartToUsersCarts(cart, client);
                double spentOnShoppingByUser = client.getOverallSumOverPurchases() + priceOfCart(cart, client);
                client.setOverallSumOverPurchases(spentOnShoppingByUser);
                emptyCart();
                Date purchaseDate = new Date();
                client.setLastPurchase(purchaseDate);
            }
        }
    }
    public void addProductToCart(Product product){
        Product [] newArrayOfProducts = new Product[this.productsInCart.length + 1];
        for (int i = 0; i < this.productsInCart.length; i++) {
            newArrayOfProducts[i] = this.productsInCart[i];
        }
        newArrayOfProducts[this.productsInCart.length] = product;
        this.productsInCart = newArrayOfProducts;
    }
    public void showCart(ShoppingCart cart){
        System.out.println(cart);
    }
    public void addCartToUsersCarts(ShoppingCart cart, Client client){
        ShoppingCart [] newCartsOfUser = new ShoppingCart[client.getCarts().length + 1];
        for (int i = 0; i < client.getCarts().length; i++) {
            newCartsOfUser[i] = client.getCarts()[i];
        }
        newCartsOfUser[client.getCarts().length] = cart;
        client.setCarts(newCartsOfUser);
    }
    public void emptyCart(){
        this.productsInCart = new Product[0];
    }
    //option 1 in worker's menu:
    public void showAllClients(){
        System.out.println("All clients: ");
        int number = 1;
        for (int i = 0; i < this.clients.length; i++){
            System.out.println(number + ". " + this.clients[i]);
            number++;
        }
    }
    //option 2 in worker's menu:
    public void showAllVips(){
        System.out.println("Members (VIPS): ");
        int number = 1;
        for (int i = 0; i < this.clients.length; i++){
            if (this.clients[i].getHasMembership()){
                System.out.println(number+". "+this.clients[i]);
                number++;
            }
        }
    }
    //option 3 in worker's menu:
    public void showWhoDidPurchase(){
        System.out.println("Clients, that did at least one purchase: ");
        int number = 1;
        for (int i = 0; i < this.clients.length; i++){
            if (this.clients[i].getCarts().length > 0){
                System.out.println(number + ". " + this.clients[i]);
                number++;
            }
        }
    }
    //option 4 in worker's menu:
    public void showClientThatPurchasedForBiggestSum(Client client){
        Client biggestSumClient = client;
        double maxSum = 0;
        for(int i = 0; i < this.clients.length; i++) {
            if (this.clients[i].getOverallSumOverPurchases()>maxSum){
                maxSum =this.clients[i].getOverallSumOverPurchases();
                biggestSumClient = this.clients[i];
            }
        }
        if (maxSum == 0){
            System.out.println("No one did a purchase yet");
        } else {
            System.out.println("Client that bought for the highest sum: ");
            System.out.println(biggestSumClient);
            System.out.println("(spent: "+ maxSum+"$)" );
        }
    }
    public double priceOfCart(ShoppingCart cart, Client client){
        double priceOfTheCart = 0;
        for (int i = 0; i < cart.getProductsInCart().length; i ++){
            priceOfTheCart = priceOfTheCart + priceOfProduct(cart.getProductsInCart()[i], client);
        }
        if (ifWorker(client)){
            if(((Worker) client).getRank().equals(REGULAR_WORKER_W)){
                priceOfTheCart = priceAfterDiscount(priceOfTheCart, DISCOUNT_ON_CART_FOR_REGULAR_WORKER);
            } else if (((Worker) client).getRank().equals(MANAGER_W)){
                priceOfTheCart = priceAfterDiscount(priceOfTheCart, DISCOUNT_ON_CART_FOR_MANAGER);
            } else if (((Worker) client).getRank().equals(MEMBER_IN_MANAGEMENT_TEAM_W)){
                priceOfTheCart = priceAfterDiscount(priceOfTheCart, DISCOUNT_ON_CART_FOR_MEMBER_IN_MANAGEMENT_TEAM);
            }
        }
        return priceOfTheCart;
    }
    public double priceOfProduct(Product product, Client client){
        double priceOfTheProduct = product.getPrice();
        if (client.getHasMembership()){
            priceOfTheProduct = priceAfterDiscount(product.getPrice(), product.getDiscountForMembers());
        }
        return priceOfTheProduct;
    }
    //counts a price of product after discount
    public double priceAfterDiscount (double priceBeforeDiscount, int discountPercent){
        return priceBeforeDiscount-(priceBeforeDiscount*discountPercent/100);
    }
    //option 5 in worker's menu:
    public void postTheProduct(Scanner scanner){
        Product product = new Product(descriptionOfProduct(), true, price(scanner), discountForMembers(scanner));
        addProductToList(product);
    }
    //PRODUCT:
    public String descriptionOfProduct(){
        System.out.println("Add description to the product: ");
        String description = new Scanner(System.in).nextLine();
        return description;
    }
    public int price(Scanner scanner){
        System.out.println("What is the price of the product?");
        int price;
        do {
            price =scanner.nextInt();
            if (price<0){
                System.out.println("Invalid price. Enter again: ");
            }
        } while (price<0);
        return price;
    }
    public int discountForMembers(Scanner scanner){
        System.out.println("What is the discount for members on the product?");
        int discount;
        do {
            discount=scanner.nextInt();
            if (discount<0 || discount>100){
                System.out.println("Invalid discount. Enter again: ");
            }
        } while (discount<0 || discount>100);
        return discount;
    }
    public void addProductToList(Product product){
        Product [] newArrayOfProducts = new Product[this.products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            newArrayOfProducts[i] = this.products[i];
        }
        newArrayOfProducts[this.products.length] = product;
        this.products = newArrayOfProducts;
    }
    //option 6 in worker's menu:
    public void changeProductsStockCondition(Scanner scanner){
        int number = 1;
        for (int i = 0; i<this.products.length; i++){
            System.out.println(number + ". " + this.products[i].toString());
            number++;
        }
        System.out.println("Enter the number of the product, that it's stock condition" +
                " you would like to change: ");
        int productToChangeStatus = validChoice(scanner, number);
        this.products[productToChangeStatus - 1].setInStock(inStock(scanner));
    }
    public boolean inStock(Scanner scanner){
        boolean inStock = false;
        System.out.println("Please set a condition of the product.");
        System.out.println("If the product in stock - press 1.");
        System.out.println("Else, press any other digit.");
        int choice = scanner.nextInt();
        if (choice == IN_STOCK){
            inStock = true;
        }
        return  inStock;
    }
    //checks of user's choice is ok:
    public int validChoice(Scanner scanner, int number){
        int choiceOfUser;
        do {
            choiceOfUser = scanner.nextInt();
            if (choiceOfUser < 1 || choiceOfUser > number){
                System.out.println("Invalid number. Enter another one.");
            }
        } while (choiceOfUser < 1 || choiceOfUser > number);
        return choiceOfUser;
    }
}
