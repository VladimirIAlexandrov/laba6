import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    private String[] stlArray;
    private Double[] dblArray;
    double dataD;

    public Main() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        serverData server = new serverData();
        System.out.println("Сервер запущен");


        while (true) {
            System.out.println("-----------Ч--М--С--Мс--------Ожидаю...-------------------------------");
            server.runi();
        }
    }
}