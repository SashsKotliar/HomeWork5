public class Worker extends Client{
    private String rank;

    public String getRank(){
        return  this.rank;
    }
    public void setRank(String rang){
        this.rank = rang;
    }

    public Worker(String firstName, String secondName, String username, String password,
                  boolean membership, String rang){
        super(firstName, secondName, username, password, membership);
        this.rank = rang;
    }

    public String toString(){
        return super.toString() + "\n(worker - " + this.rank + ")";
    }
}
