package com.dkit.oop.sd2.Server;

import com.dkit.oop.sd2.Core.CAOService;
import com.dkit.oop.sd2.DTOs.Course;
import com.dkit.oop.sd2.Exceptions.DaoException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args)
    {
        try
        {
            //Step 1: Set up a listening socket - this just listens for connections
            //Once a socket connects we create a dataSocket for the rest of the
            //communication
            ServerSocket listeningSocket = new ServerSocket(CAOService.PORT_NUM);
            Socket dataSocket = new Socket();

            boolean continueRunning = true;

            while(continueRunning)
            {
                //Step 2
                //Once a connection is accepted on the listening socket
                //spawn a dataSocket for the rest of the communication
                dataSocket = listeningSocket.accept();

                //Step 3 - set up input and output streams
                OutputStream out = dataSocket.getOutputStream();
                //Decorator pattern
                PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

                InputStream in = dataSocket.getInputStream();
                Scanner input = new Scanner(new InputStreamReader(in));

                String incomingMessage = "";
                String response;
                List<Course> response2 = null;

                while(true)
                {

                    response = null;

                    //Take the information from the client
                    incomingMessage = input.nextLine();
                    System.out.println("Received message: " + incomingMessage);

                    String[] messageComponents = incomingMessage.split(CAOService.BREAKING_CHARACTER);
                    //echo%%Hello World, echo%%Hello%%World
                    //0%%1                  0%%1%%2
                    if (messageComponents[0].equals(CAOService.REGISTER_COMMAND))
                    {
                        StringBuffer echoMessage = new StringBuffer("");
                        if (messageComponents.length > 1)
                        {
                            echoMessage.append(messageComponents[1]);
                        }
                        for (int i = 2; i < messageComponents.length; i++)
                        {
                            echoMessage.append(CAOService.BREAKING_CHARACTER);
                            echoMessage.append(messageComponents[i]);
                        }
                        response = echoMessage.toString();
                    }


                    else if (messageComponents[0].equals(CAOService.LOGIN_COMMAND)) {
                    StringBuffer echoMessage = new StringBuffer("");
                    if (messageComponents.length > 1)
                    {
                        echoMessage.append(messageComponents[1]);
                    }
                    for (int i = 2; i < messageComponents.length; i++)
                    {
                        echoMessage.append(CAOService.BREAKING_CHARACTER);
                        echoMessage.append(messageComponents[i]);
                    }
                    response = echoMessage.toString();
                }

                    else if (messageComponents[0].equals(CAOService.DISPLAY_COURSE_COMMAND)) {
                        StringBuffer echoMessage = new StringBuffer("");
                        if (messageComponents.length > 1)
                        {
                            echoMessage.append(messageComponents[1]);
                        }
                        for (int i = 2; i < messageComponents.length; i++)
                        {
                            echoMessage.append(CAOService.BREAKING_CHARACTER);
                            echoMessage.append(messageComponents[i]);
                        }
                        response = echoMessage.toString();
                    }

                    else if (messageComponents[0].equals(CAOService.END_SESSION))
                    {
                        response = CAOService.SESSION_TERMINATED;
                    }else if (messageComponents[0].equals(CAOService.LOGOUT))
                    {
                        response = CAOService.SESSION_TERMINATED;
                    } else if (messageComponents[0].equals(CAOService.DISPLAY_ALL))
                    {
                        MySqlCourseDao courses = new MySqlCourseDao();
                        response = courses.displayAllCourses().toString();
                    } else
                    {
                        response = CAOService.UNRECOGNISED;
                    }

                    //Send response back
                    output.println(response);
//                    output.println(response2);
                    output.flush();
                }

            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());;
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Shutting down.");
            System.exit(1);
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }
}
