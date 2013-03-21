package outil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HistoriqueUtils {

	public static void ecrireHistorique(String text) 
	{
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter("histo.txt"));
			ecri.print(text);
			ecri.flush();
			ecri.close();
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO");
		}
	}
	
	public String lire () 
	{
		BufferedReader lect ;
		String tmp = "";
		try
		{
			lect = new BufferedReader(new FileReader("histo.txt")) ;
			while (lect.ready()==true) 
			{
				tmp += lect.readLine() ; 
			}//while
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a) 
		{
			System.out.println("Problème d'IO");
		}
		return tmp;
	}//lecture 
	
}
