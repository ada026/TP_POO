import java.util.Scanner;

public class Main {
    static public User user;
    
    public static void main(String[] args){
        user = new User("elyes2");
        user.startThread();
        
        
        
    }
    
    public User getUser(){
        return user;
    }
    
    
}