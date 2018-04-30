package core;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private BufferedReader bufferedReader;
    private OutputStream output;
    private PrintWriter out;

    public Connection() {
        this.socket = new Socket();
    }

    public Connection open() throws IOException {
        this.socket.setKeepAlive(true);
        if (!socket.isConnected()) this.socket.connect(new InetSocketAddress("fep.dev.xplatform.cc", 30063));
        return this;
    }

    public Connection send(String request) throws IOException {
        output = socket.getOutputStream();
        out = new PrintWriter(output, true);
        out.println(request);
        return this;
    }

    private Connection close() throws IOException {
        output.close();
        socket.close();
        return this;
    }

    public String getResponse() throws IOException {
        StringBuilder sb = new StringBuilder();
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        this.close();
        return sb.toString();
    }
}
