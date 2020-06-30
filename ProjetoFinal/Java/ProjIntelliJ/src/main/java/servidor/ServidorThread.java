package servidor;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

// Classe responsável por gerenciar as conexoes TCP que servidor realiza com clientes
public class ServidorThread extends Thread {

    Scanner teclado = new Scanner(System.in);
    Socket conexao;

    public ServidorThread(Socket c) {
        this.conexao = c;
    }

    public void run() {
        System.out.println("Raspberry conectada " + conexao);
        try {
            /* Estabelece fluxos de entrada e saida */
            DataInputStream fluxoEntrada = new DataInputStream(new BufferedInputStream(conexao.getInputStream()));
            DataOutputStream fluxoSaida = new DataOutputStream(conexao.getOutputStream());
            //--------------------------------------------------------------------
            /* Inicia comunicacao */
            fluxoSaida.writeUTF("Ola Raspberry");
            fluxoSaida.flush();

                String mensagem = fluxoEntrada.readUTF();
                System.out.println("Raspberry -> " + mensagem);



            /* Inicia comunicacao com menu*/
            String resposta = "";
            do {
                System.out.println("entrou");
                System.out.println(fluxoEntrada.readUTF()); // mensagem da Raspberry
                resposta = teclado.nextLine();
                // resposta
                fluxoSaida.writeUTF(resposta);
                //System.out.println(opcao);
                fluxoSaida.flush();
                // Quando se invoca o flush(), significa enviar todos os conteúdos naquele momento.
                // ele é chamado implicitamente, não precisa realmente invocar esse método em todas as situações,
                // em ambiente simples provavelmente sempre vai funcionar sem o flush, agora em um ambiente mais complexo,
                // talvez os dados se percam no meio do caminho.
                // System.out.println(fluxoEntrada.readUTF());
            }while (!resposta.equals("4"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
