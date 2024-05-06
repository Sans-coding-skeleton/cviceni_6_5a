import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket server;
    private boolean isRunning;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
        isRunning = true;
        serverLoop();
    }

    private void serverLoop() throws IOException {
        System.out.println("server is running");
        while (isRunning) {
            Socket socket = server.accept();
            clientLoop(socket);
        }
    }

    private void clientLoop(Socket socket) {
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer.println("connected");
            writer.flush();
            boolean run = true;
            while (run) {
                String data = reader.readLine();
                data = data.trim();
                System.out.println(data);
                if (data.equals("end")) {
                    run = false;
                } else if(data.equals("thy end is now")) {
                    writer.println("crush");
                    writer.flush();
                }
                Scanner sc = new Scanner(System.in);
                String text = sc.next();
                writer.println(text);
                writer.flush();
            }
            writer.println("good night");
            writer.flush();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
