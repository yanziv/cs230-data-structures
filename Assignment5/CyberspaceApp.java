import javafoundations.*;

import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Write a description of class CyberspaceApp here.
 *
 * @author sk105, yl102
 * @version 11/01/2021
 */
public class CyberspaceApp
{
    public static void main(String[] args){
        //mode 1: no command-line argument 
        Cyberspace c = new Cyberspace(); 
        if (args.length == 0){
            Scanner scan = new Scanner(System.in); 
            System.out.println("Please enter URLs (one per line) below; Type q to quit:");
            while(scan.hasNext()){
                String url = scan.nextLine();
                if (url.equals("q")){ 
                    break; 
                }
                else{
                    Webpage w = new Webpage(url); //create Webpage objects
                    w.retrieveContent();
                    c.add(w); //add the newly-created objects to ArrayStack
                }
            }
            scan.close();
        }
        else{
            //mode 2: user provides one command-line argument
            try{
                Scanner scan = new Scanner(new File(args[0]));
                while (scan.hasNext()){
                    String url = scan.nextLine();
                    Webpage w = new Webpage(url);
                    w.retrieveContent();
                    c.add(w);
                }
                scan.close();
            }catch(FileNotFoundException ex){
                System.out.println(ex);
            }
        }
        c.printWebStack();
        System.out.println();
        System.out.println("The largest Webpage was: " + c.findLargest());
    }
}
