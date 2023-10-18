

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChatClientTask implements Runnable {
    private ChatClient c;
    private List<String> msgs;
    private int wait;
    private boolean logReady = false;

    private ChatClientTask(ChatClient c, List<String> msgs, int wait) {
        this.c = c;
        this.msgs = msgs;
        this.wait = wait;
    }


    public static ChatClientTask create(ChatClient c, List<String> msgs, int wait) {
        return new ChatClientTask(c, msgs, wait);
    }

    @Override
    public void run() {
        if (Thread.interrupted()) return;
        c.login();
        Iterator<String> it = msgs.iterator();
        while (it.hasNext()) {
            if (Thread.interrupted()) return;
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Thread.interrupted()) return;
            c.send(it.next());
        }
        if (Thread.interrupted()) return;
        c.logout();
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logReady = true;
    }


    public void get() throws ExecutionException {
        while (!logReady) {

        }

    }

    public ChatClient getClient() {
        return c;
    }
}