
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    private String host;
    private int port;
    private String id;

    private SocketChannel sc;
    private StringBuilder personalLog = new StringBuilder();
    Thread thred = new Thread();


    public ChatClient(String host, int port, String id) {
        this.host = host;
        this.port = port;
        this.id = id;
    }

    public String send(String s) {
        byte[] messege = new String(s).getBytes(StandardCharsets.UTF_8);
        ByteBuffer b = ByteBuffer.wrap(messege);
        try {
            while (!sc.finishConnect()) {
                Thread.sleep(20);
            }
            while (true) {
                sc.write(b);
                ByteBuffer bb = ByteBuffer.allocate(1000);
                int read = sc.read(bb);
                if (read == -1) {
                    sc.close();
                } else if (read > 0) {
                    bb.flip();
                    String queryOut = StandardCharsets.UTF_8.decode(bb).toString();
                    return queryOut;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void connect() {
        try{
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(host, port));

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void login() {
        connect();
        send("login "+id);
    }



    public void logout() {
        try {
            send("logout "+id);
            thred.interrupt();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sc.close();
        } catch (IOException ignored) {

        }
    }

    public String getChatView() {
        return "=== "+id+" chat view\n"+ personalLog.toString();
    }

}
