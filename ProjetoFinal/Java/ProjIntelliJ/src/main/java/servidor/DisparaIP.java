package servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// CLASSE responsável por disparar IP do
// servidor na rede idependentemente do fluxo
// principal do programa através do uso de Threads

public class DisparaIP extends Thread {
    String IPServidor;
    InetAddress enderecoBroadcast;
    private boolean statusBroadcast = true;

    public void interromperBroadcast() {
        this.statusBroadcast = false;
    }

    public DisparaIP(String mensagem, InetAddress endereco) {
        this.IPServidor = mensagem;
        this.enderecoBroadcast = endereco;
    }

    public void broadcast(String mensagem, InetAddress endereco) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        // buffer para arazenar IP do servidor e ser enviado no datagrama
        byte[] buffer = mensagem.getBytes();
        // enviando pacote UDP para endereços de broadcast na porta 1234,
        // na qualtodo cliente o estará aguardando ao ser iniciado
        DatagramPacket pacote = new DatagramPacket(buffer, buffer.length, endereco, 1234);
        // enviando o pacote
        socket.setBroadcast(true);
        socket.send(pacote);
        socket.close();
    }

    public void run() {

        while (statusBroadcast) {
            try {
                broadcast(IPServidor, enderecoBroadcast);
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        // System.out.println("parou a thread de broadcast");
        this.statusBroadcast = true;
    }
}
