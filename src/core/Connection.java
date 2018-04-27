package core;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;


public class Connection {

    private Socket socket;
    private InputStream input;
    private BufferedReader bufferedReader;
    private OutputStream output;
    private PrintWriter out;
    private static Connection ourInstance = new Connection();

    public static Connection getInstance() {
        return ourInstance;
    }

    private Connection() {
        this.socket = new Socket();
    }

   public Connection open() throws IOException {
       this.socket.setKeepAlive(true);
     if(!socket.isConnected()) this.socket.connect(new InetSocketAddress("fep.dev.xplatform.cc",30063));
       return ourInstance;
   }


    public Connection connect() throws IOException {

        return ourInstance;
    }

    public Connection send(String request) throws IOException {
        output = socket.getOutputStream();
        out = new PrintWriter(output,true);
        out.println(request);
//        out.flush();
//        out.close();
        return this;
    }

    public Connection close() throws IOException {
        output.close();
//        input.close();
//        socket.close();
        return this;
    }

    public String getResponse() throws IOException {
        StringBuilder sb = new StringBuilder();
        bufferedReader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
