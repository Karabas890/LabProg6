package Server;

public class ServerEntryPoint2 {
    public static void main(String[] args) throws Exception {
        UDPServerOneThread server = new UDPServerOneThread();
        server.Start();
    }
}
