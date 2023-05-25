package Server;

public class ServerEntryPoint {
    public static void main(String[] args) throws Exception {
        UDPServerOneThread server = new UDPServerOneThread();
        server.Start();
    }
}
