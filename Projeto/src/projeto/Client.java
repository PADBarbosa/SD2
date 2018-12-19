/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author ze
 */
public class Client {
    public static void main(String args[]) throws IOException, UnknownHostException{
		Socket cs = new Socket("127.0.0.1", 9999);
               
                
                
                
                
                

		PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));

		String current;
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
		while((current = sin.readLine()) != null){
			out.println(current);
			System.out.println(in.readLine());

		}
		System.out.println("Shutdown Output");
		cs.shutdownOutput();
		System.out.println("Waiting for final result");
		System.out.println("The answer is: " + in.readLine());
		System.out.println("Done");

		in.close();
		out.close();
		sin.close();

	}
}
