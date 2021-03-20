/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author suha
 */
public class clientf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
       File f = new File("C:\\Users\\suha\\Desktop\\test1.txt");
        try (FileInputStream input = new FileInputStream(f)) {
            int data =0;
            String s="";
            while (data != -1) {
                data = input.read();
                System.out.print((char) data);
                s+=(char)data;
//            System.out.println(input.available());
            }
            FileOutputStream out=new FileOutputStream("C:\\Users\\suha\\Desktop\\test2.txt");
            System.out.print(s);
            char[] c=new char[s.length()];
            for(int i=0;i<s.length();i++){
                c[i]=s.charAt(i);
                int p=(int)c[i]; 
                out.write(p);
                System.out.print(p);
            }
            System.out.print("");
        }
    }
    
}
