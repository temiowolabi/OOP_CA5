package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.Core.CAOService;
import com.dkit.oop.sd2.Menus.MainMenu;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        DatagramSocket clientSocket = null;

        try{
            InetAddress serverHost = InetAddress.getByName(CAOService.HOSTNAME);
            clientSocket = new DatagramSocket(CAOService.PORT_NUM);

            boolean continueRunning = true;
            MainMenu choice;
            int getChoice;
            while(continueRunning)
            {
                displayMenu();

                String message = null;
                boolean sendMessage = true;
                getChoice = keyboard.nextInt();
                keyboard.nextLine();
                choice = MainMenu.values()[getChoice];
                switch(choice)
                {
                    case QUIT_APPLICATION:
                        continueRunning = false;
                        sendMessage = false;
                        break;
                    case REGISTER:
                        break;
                    case LOGIN:
                        break;
                }
            }
        }
        catch (UnknownHostException e)
        {
            System.out.println("Problem getting server address " + e.getMessage());
        }
        catch(SocketException e)
        {
            System.out.println("Problem binding clientSocket to port " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        }

    private static void displayMenu()
    {
        System.out.println("Please enter one of the following options: ");
        System.out.println("0) QUIT APPLICATION");
        System.out.println("1) REGISTER");
        System.out.println("2) LOGIN");
    }
    }

