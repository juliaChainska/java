
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class ChatServer extends Thread{

    private String host;
    private int port;
    private StringBuilder logs = new StringBuilder();

    private Map<SocketChannel, String>  name = new HashMap<>();

    private ServerSocketChannel ssc = null;
    private Selector selector = null;
    private HashMap<String, SocketChannel> channels = new HashMap<>();
    private Map<SocketChannel, Queue<ByteBuffer>> pending = new HashMap<>();
    private LinkedList<String> serverLog = new LinkedList<>();
    private Map<SocketChannel, StringBuilder>  userLog = new HashMap<>();




    public ChatServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer(){
        this.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void stopServer() {
        this.interrupt();
    }

    public String getServerLog() {
        return logs.toString();
    }

    public void run() {
        try {
            ssc = ServerSocketChannel.open();

            ssc.configureBlocking(false);

            ssc.socket().bind(new InetSocketAddress(host, port));

            selector = Selector.open();

            ssc.register(selector, SelectionKey.OP_ACCEPT);



        } catch(Exception exc){
            exc.printStackTrace();
            System.exit(1);
        }
        System.out.println("Server started");
        serviceConnections();
    }

    private void serviceConnections(){
        boolean serverIsRunning = true;

        while(serverIsRunning){
            try{
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();

                    if(key.isAcceptable()){
                        SocketChannel cc = ssc.accept();
                        cc.configureBlocking(false);
                        cc.register(selector, SelectionKey.OP_READ);
                        channels.put(cc.toString(), cc);
                        pending.put(cc, new LinkedList<ByteBuffer>());
                        continue;
                    }

                    if (key.isReadable()){
                        SocketChannel cc = (SocketChannel) key.channel();
                        serviceRequest2(cc);
                        key.interestOps(SelectionKey.OP_WRITE);
                        continue;
                    }

                    if(key.isValid()&&key.isWritable()){
                        SocketChannel cc = (SocketChannel) key.channel();
                        Queue<ByteBuffer> queue = pending.get(cc);

                        while (!queue.isEmpty()) {
                            ByteBuffer bb = queue.peek();
                            int write = cc.write(bb);
                            if (write == -1) {
                                pending.remove(cc);
                                cc.close();
                                return;
                            } else if (bb.hasRemaining()) {
                                return;
                            }
                            queue.remove();
                        }
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }
            } catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
        }
    }

    public void serviceRequest2(SocketChannel sc) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(1000);

        int read = sc.read(bb);
        if (read == -1) {
            pending.remove(sc);
            sc.close();
        } else if (read > 0) {
            bb.flip();

            String str = StandardCharsets.UTF_8.decode(bb).toString();


            String response = "";

            if (str.split(" ")[0].equals("login")) {
                serverLog.add(str.split(" ")[1] + " logged in at " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                name.put(sc, str.split(" ")[1]);
                userLog.put(sc, new StringBuilder());
                userLog.get(sc).append("=== " + str.split(" ")[1] + " log start ===\nlogged in\n");
                response = "logged in";
            } else if (str.equals("bye")) {
                serverLog.add(name.get(sc) + " logged out at " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                userLog.get(sc).append("logged out");
                response = "logged out";
            } else if (str.equals("bye and log transfer")) {
                serverLog.add(name.get(sc) + " logged out at " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                userLog.get(sc).append("logged out\n=== " + name.get(sc) + " log end ===");
                response = userLog.get(sc).toString();

            } else if (str.split(" ").length == 2) {
                serverLog.add(name.get(sc) + " request at " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": \"" + str + "\"");

            }

            pending.get(sc).add(StandardCharsets.UTF_8.encode(CharBuffer.wrap(response)));

        }
    }


}
