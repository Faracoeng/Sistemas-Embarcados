package servidor;
// ok

import java.io.IOException;
import java.net.*;
import java.util.*;

public class Servidor {

    // descobre ip
    static public String getIPV4Semlo() {
        // NetworkInterface.getNetworkInterfaces() retorna Enumeration<NetworkInterface>
        String elementos = "";
        Enumeration nis = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces(); // retorna todas as interfaces de rede da máquina para
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (nis.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) nis.nextElement();
            // procura uma interface de rede que tenha o endereço IP especificado.
            Enumeration ias = ni.getInetAddresses();
            while (ias.hasMoreElements()) {
                InetAddress ia = (InetAddress) ias.nextElement();
                if (!ni.getName().equals("lo")) // remove interface de loopback
                    elementos = ia.getHostAddress();
            }
        }
        return elementos;
    }

    public static void main(String[] args) {

        try {
            Thread broadcast = new DisparaIP(getIPV4Semlo(), InetAddress.getByName("255.255.255.255"));

            /* Após realizar broadcast, registra servico na porta 1235 e aguarda por conexoes */
            ServerSocket servidor = new ServerSocket(4321);
            // dispara broadcast na rede
            broadcast.start();
            System.out.println("Aguardando por conexoes");
            // Aguarda cliente solicitar conexao TCP
            Socket conexao = servidor.accept();
            // Encerra Thread que dispara broadcast na rede
            broadcast.stop();

            Thread conexaoComRasp = new ServidorThread(conexao);
            conexaoComRasp.start();



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
