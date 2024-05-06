import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Server s = new Server(65525);
        } catch (IOException e) {
            System.out.println("...");
        }


    }
}