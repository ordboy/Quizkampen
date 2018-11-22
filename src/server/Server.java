package server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    private Socket clientSocket1;
    private Socket clientSocket2;
    private List<Question> questions = new ArrayList<>();
    private Question tempQuestion;

    public Server(Socket clientSocket1, Socket clientSocket2) throws IOException{
        this.clientSocket1 = clientSocket1;
        this.clientSocket2 = clientSocket2;


        ObjectOutputStream player1Out = new ObjectOutputStream(clientSocket1.getOutputStream());
        ObjectInputStream Player1In = new ObjectInputStream(clientSocket1.getInputStream());

        ObjectOutputStream Player2Out = new ObjectOutputStream(clientSocket2.getOutputStream());
        ObjectInputStream Player2In = new ObjectInputStream(clientSocket2.getInputStream());
    }


}