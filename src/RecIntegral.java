import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RecIntegral  implements Serializable {


    ArrayList<Double> dataList = new ArrayList<Double>();

    private static int counter;
    public RecIntegral(){
        counter++;
    }

    /////////////////////////////////СЕТЫ///////////////////////////////////
    public void setDataA(Double dataA)throws NumberFormatException {

        if(dataA<0.000001 || dataA>1000000){
            throw new NumberFormatException();
        }
        dataList.add(0, dataA);
    }
    public void setDataB(Double dataB) throws NumberFormatException {
        if(dataB<0.000001 || dataB>1000000){
            throw new NumberFormatException();
        }
        dataList.add(1, dataB);

    }
    public void setDataC(Double dataC) throws NumberFormatException {
        if(dataC<0.000001 || dataC>1000000){
            throw new NumberFormatException();
        }
        dataList.add(2, dataC);

    }
    public void setDataD(Double dataD) {

        dataList.add(3, dataD);

    }
    //////////////////////////////ГЕТЫ////////////////////////////////////////
    public Double[] getDataList() {
        Double[] dblArray = new Double[dataList.size()];
        dblArray = dataList.toArray(dblArray);
        return dblArray;
    }
    public Double getDataA() {
        return dataList.get(0);
    }
    public Double getDataB() {
        return dataList.get(1);
    }
    public Double getDataC() {
        return dataList.get(2);
    }
    public Double getDataD() {
        return dataList.get(3);
    }
    public static int getCount() {
        return counter;
    }

    public void clearObject() throws NumberFormatException {

        setDataA(null);
        setDataB(null);
        setDataC(null);
        setDataD(null);
    }


    //////////////////////////////ВЫЧИСЛЕНИЯ////////////////////////////////////////
  /*  static double InFunction(double x) //Подынтегральная функция
    {
        return 1/(Math.log(x));
    }


    public void Trap() throws InterruptedException {
        final double[] result = {0};
        int n = (int)((getDataA()-getDataC() - getDataB()) / getDataC());
        result[0] += (InFunction(getDataA()) + InFunction(getDataB())) / 2;
        int chunkSize = n / 7; // Размер частей
        Thread[] threads = new Thread[7];
        for (int i = 0; i < 7; i++) {

            int startIndex = i * chunkSize +1;
            int endIndex = (i +1) * chunkSize;

            if (i == 6) {
                endIndex = n;
            }
            int finalEndIndex = endIndex;
            //////////////////////////////////////////////////////////////////
            Runnable task = new Runnable() {
                public void run() {
                    double localResult = 0;
                    for (int j = startIndex; j <= finalEndIndex; j++) {
                        localResult += InFunction(getDataB() + getDataC() * j);
                    }

                    synchronized(this) {
                        result[0] += localResult;
                    }
                }
            };
            ////////////////////////////////////////////////////////////////
            threads[i] = new Thread(task);
            threads[i].start();
           // threads[i].join();
        }
        for (Thread thread : threads) {
            try {
                thread.join(); // Ждём завершения всех потоков
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        setDataD(getDataC() * result[0]);

    }*/

    public void SendData() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        byte[] data = (getDataA().toString()+","+getDataB().toString()+","+getDataC().toString()).getBytes();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
        int serverPort = 12345;
        DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
        socket.send(packet);
        socket.close();
    }
    public double ReadData() throws IOException {
        DatagramSocket socket = new DatagramSocket(12346);
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        String response = new String(message);
        socket.close();
        return Double.valueOf(response);
    }
    public void runServer() throws IOException, InterruptedException {
        SendData();
        setDataD(ReadData());

    }
}
