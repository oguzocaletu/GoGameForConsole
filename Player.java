
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Player {// Oguz Ocal 181101019
	public String item="";
	static int pause=0;
	public String name="";
	Map m1;
	public Player() {
		this.name="Unknown";
		 m1=new Map();
	}
	public Player(String name) {
		this.name=name;
		 m1=new Map();
	}
	Item i1=new Item(item);

	Scanner k = new Scanner(System.in);
	public void takeInput(String inputFile) {
		int index=inputFile.indexOf(".");
		String type=inputFile.substring(index+1);
		if(!(type.equals("txt"))) {
			System.out.println("Girdiginiz dosya bir text dosyasi degil \nLutfen bir text dosyasý giriniz");
			inputFile=k.nextLine();
		}
		BufferedReader inputFromFile=null;
		try {
			inputFromFile=new BufferedReader( new FileReader(inputFile));
			String Line=inputFromFile.readLine();
			while(Line!=null) {
				Hamle("x",Line);
				Line=inputFromFile.readLine();
				if((Line != null) && (!Line.isEmpty())){
				Hamle("o",Line);
				}
				Line=inputFromFile.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			takeInput(repeatText);
		} catch (IOException e) {
			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			takeInput(repeatText);
		}
		
	}
	public String Hamle(String item,String hamle) {				
			System.out.println("your move: "+hamle);
				
			
			Item i1=new Item(item);
			if(hamle.length()==2) {
				i1.hamleYap(hamle);
			}
			else if(hamle.equals("p")) {
				pause++;
				if(pause>1) {
					System.out.println("Oyun sonlanýyor...");
					m1.WhoWon("Player 1","Player 2");
				}
			}
			else if(hamle.length()>9&&hamle.substring(0,10).equals("save text ")) {
				String text=hamle.substring(10);
				m1.saveMaptoText(text);
				
			}
			else if(hamle.length()>12&&hamle.substring(0,12).equals("save binary ")) {
				String binary=hamle.substring(12);
				m1.saveMaptoBinary(binary);
			}
			else {
				System.out.print("Gecersiz hamle girdiniz \n Lutfen tekrar deneyin:");
				hamle=k.nextLine();
				Hamle(item,hamle);
			}
			return hamle;
		
	}

}
