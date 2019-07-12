import mysql.Controller;

public class Main {
    public static void main(String[] args) {
        try {
            new Controller().write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
