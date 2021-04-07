package com.dkit.oop.sd2.Client;

import com.dkit.oop.sd2.Core.CAOService;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.dkit.oop.sd2.Menus.CAOCourseMenu;
import com.dkit.oop.sd2.Menus.MainMenu;
import com.dkit.oop.sd2.Server.MySqlCourseDao;
import com.dkit.oop.sd2.Server.MySqlStudentDao;

import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/*This class prints out the menu.*/

public class Client {
    private Scanner keyboard = new Scanner(System.in);
    private Socket dataSocket = new Socket(CAOService.HOSTNAME, CAOService.PORT_NUM);
    private String response = "";

    public Client() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        new Client().start();

    }
    private void start() {
        doMainMenuLoop();
    }

    public void doMainMenuLoop()
    {
        try
        {
            //Step 1: Establish a connection to the server
            // Like a phone call, first need to dial the number
            // before you can talk
//            Socket dataSocket = new Socket(CAOService.HOSTNAME, CAOService.PORT_NUM);

            //Step 2: Build input and output streams
            OutputStream out = dataSocket.getOutputStream();
            //Decorator pattern
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

//            Scanner keyboard = new Scanner(System.in);

            String message = "";
            MainMenu choice;
            int getChoice;

            while(!message.equals(CAOService.END_SESSION))
            {
                displayMainMenu();
                getChoice = keyboard.nextInt();
                keyboard.nextLine();
                choice = MainMenu.values()[getChoice];
//                String response = "";

                    switch(choice)
                    {
                        case QUIT_APPLICATION:
                            message = CAOService.END_SESSION;
                            //send message
                            output.println(message);
                            output.flush();

                            //Listen for response
                            response = input.nextLine();
                            if(response.equals(CAOService.SESSION_TERMINATED))
                            {
                                System.out.println("Session ended");
                            }
                            break;
                        case REGISTER:
                            message = register(keyboard);

                            //send message
                            output.println(message);
                            output.flush();
                            //listen for response
                            response = input.nextLine();
                            System.out.println("Received response: " + response);
                            break;

                        case LOGIN:
                            message = login(keyboard);
                            //send message
                            output.println(message);
                            output.flush();
                            //listen for response
                            response = input.nextLine();  //Doesn't like .next OR .nextLine. Find out why.
                            System.out.println("Received response: " + response);
                            break;
                    }
                    if(response.equals(CAOService.UNRECOGNISED))
                    {
                        System.out.println("Sorry, the request is not recognised.");
                    }
            }
            System.out.println("Thanks for using the CAO Application App.");
            dataSocket.close();
        }
        catch (UnknownHostException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    private void doAdminMenuLoop(MySqlStudentDao loginResult) throws IOException {

        OutputStream out = dataSocket.getOutputStream();

        //Decorator pattern
        PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

        InputStream in = dataSocket.getInputStream();
        Scanner input = new Scanner(new InputStreamReader(in));
        String message = "";
        boolean loop = true;
        CAOCourseMenu menuOption;
        int option;
        try
        {

        while(!message.equals(CAOService.LOGOUT))
        {
            MySqlCourseDao courses = new MySqlCourseDao();
            printCaoCourseMenu();

                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = CAOCourseMenu.values()[option];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        message = CAOService.END_SESSION;
                        //send message
                        output.println(message);
                        output.flush();

                        //Listen for response
                        response = input.nextLine();
                        if(response.equals(CAOService.SESSION_TERMINATED))
                        {
                            System.out.println("Session ended");
                        }
                        break;
                    case LOGOUT:
                        message = CAOService.LOGOUT;
                        //send message
                        output.println(message);
                        output.flush();
                        //listen for response
                        response = input.nextLine();
                        System.out.println("Received response: " + response);
                        doMainMenuLoop();
                        break;
                    case DISPLAY_COURSE:
                        message = getCourseById(keyboard);
                        //send message
                        output.println(message);
                        output.flush();
                        response = input.nextLine();
                        System.out.println("Course: " + response);
                        break;
                    case DISPLAY_ALL_COURSES:
                        message = CAOService.DISPLAY_ALL;
                        //send message
                        output.println(message);
                        output.flush();
                        response = input.nextLine();
                        System.out.println("All Courses: " + response);
                        break;
//                    case DISPLAY_CURRENT_CHOICES:
//                        addStudent(studentManager);
//                        break;
//                    case UPDATE_CURRENT_CHOICES:
//                        removeStudent(studentManager);
//                        break;
                }
                if(response.equals(CAOService.UNRECOGNISED))
                {
                    System.out.println("Sorry, the request is not recognised.");
                }
            }


        }
        catch (InputMismatchException ime)
        {
            System.out.println("InputMismatchException. The token retrieved does not match the pattern for the expected type. Please enter a valid option");
            keyboard.nextLine();
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBoundsException. The index is either negative or greater than or equal to the size of the array.");
            keyboard.nextLine();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("IllegalArgumentException. A method has been passed an illegal argument. Try again");
            keyboard.nextLine();
        } catch (DaoException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getCourseById(Scanner input) throws DaoException {
        System.out.println("Course Details: ");
        StringBuffer message = new StringBuffer(CAOService.DISPLAY_COURSE_COMMAND);
        message.append(CAOService.BREAKING_CHARACTER);
        System.out.println("Enter Course Code: ");
        String courseCode = input.nextLine();
        MySqlCourseDao courseId = new MySqlCourseDao();

//        System.out.println(courseId.findCoursesByCourseID(courseCode));

        message.append(courseId.findCoursesByCourseID(courseCode));

        return message.toString();
    }

    private String login(Scanner input) throws DaoException, IOException {
        System.out.println("Login: ");

        StringBuffer message = new StringBuffer(CAOService.LOGIN_COMMAND);
        String output = "";
        message.append(CAOService.BREAKING_CHARACTER);
        System.out.println("Enter CAO Number:\t");
        int caoNumber = input.nextInt();
        System.out.println("Enter Password:\t");
        String password = input.next();
        MySqlStudentDao login = new MySqlStudentDao();

        if(login.login(caoNumber, password))
        {
            output = CAOService.SUCCESSFUL_LOGIN;
            doAdminMenuLoop(login);
        }

        message.append(output);

        return message.toString();

    }

    public String register(Scanner input) throws DaoException {
//        System.out.println("");
//        System.out.println("Enter CAO Number:");
//        int caoNumber = keyboard.nextInt();
//        System.out.println("Enter Date of Birth:");
//        String dateOfBirth = keyboard.next();
//        System.out.println("Enter Password:");
//        String password = keyboard.next();
//        MySqlStudentDao register = new MySqlStudentDao();
//        String result1 = register.register(caoNumber, dateOfBirth, password);
//        System.out.println(result1);


        StringBuffer message = new StringBuffer(CAOService.REGISTER_COMMAND);
        message.append(CAOService.BREAKING_CHARACTER);
//        System.out.print("Enter message to echo:> ");
        System.out.println("Enter CAO Number:");
        int caoNumber = input.nextInt();
        System.out.println("Enter Date of Birth:");
        String dateOfBirth = input.next();
        System.out.println("Enter Password:");
        String password = input.next();
        //String echo = input.nextLine();
        MySqlStudentDao register = new MySqlStudentDao();
        String result1 = register.register(caoNumber, dateOfBirth, password);
        message.append(result1);

        return message.toString();
    }

    private void displayMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t"  + i + ". " + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select option (enter 0 to quit):>");
    }

    private void printCaoCourseMenu()
    {
        System.out.println("CAO Course Menu.");
        System.out.println("\n Select One The Following Options: ");
        for(int i = 0; i < CAOCourseMenu.values().length; i++)
        {
            System.out.println("\t" + i + ". " + CAOCourseMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select option (enter 0 to quit):>");
    }

}

