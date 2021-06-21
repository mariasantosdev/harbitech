package br.com.harbitech.school;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket client = serverSocket.accept();

        try(
                InputStream clientInputStream = client.getInputStream();
                OutputStream clientOutputStream = client.getOutputStream();

                Scanner clientScanner = new Scanner(clientInputStream);
                Scanner htmlScanner  = new Scanner(new FileInputStream("planilha-dados-escola - Categoria.html"));
                PrintStream clientPrintStream = new PrintStream(clientOutputStream);) {

            clientPrintStream.print("HTTP/1.1 200 OK\r\n");
            clientPrintStream.print("Content-Type: text/html\r\n");
            clientPrintStream.print("\r\n");

            while (htmlScanner.hasNextLine()) {
                String line = htmlScanner.nextLine();
                clientPrintStream.println(line);
            }

        }

    }

}
