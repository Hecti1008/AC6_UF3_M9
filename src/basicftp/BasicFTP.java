/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicftp;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Hector 
 */
public class BasicFTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Scanner scan = new Scanner (System.in);
       
       FTPClient client = new FTPClient();
       String ServerFTP;
       System.out.println("Server FTP que es vol connectar? ");
       ServerFTP = scan.next();
        System.out.println("Conectant al servidor: " + ServerFTP);
        
        String user;
        System.out.println("Introdueix Usuari: ");
        user = scan.next();
        String passw;
        System.out.println("Introdueix la Contrasenya: ");
        passw = scan.next();
        
        try {
            client.connect(ServerFTP);
            boolean login = client.login(user, passw);
            
            if (login)
                System.out.println("Login correcte... ");
            else{
                System.out.println("Login incorrecte... ");
                client.disconnect();
                System.exit(1);
            }
            
            System.out.println("Directori actual: " + client.printWorkingDirectory());
            FTPFile[] files = client.listFiles();
            System.out.println("Fitxers al directori actual: " + files.length);
            
            String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};
            
            for (int i=0; i<files.length; i++) {
                System.out.println("\t" + files[i].getName() + "=>" + tipus[files[i].getType()]);
            }
            
            boolean logout = client.logout();
            
            if(logout)
                System.out.println("Logout del servidor FTP... ");
            else
                System.out.println("Error en fer un logout... ");
            client.disconnect();
            System.out.println("Desconnectat... ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
