import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Map {// Oguz Ocal 181101019
	
	int xArea=0;
	int oArea=0;
	int player1=0;
	int player2=0;
	Scanner k=new Scanner(System.in);
	Item i1=new Item("x");
	Item i2=new Item("o");
	
	public static String[][] map = new String [9][9];
	public void CreateMap() {
		
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				map[i][j]="-";
			}
		}
	}
	public static void Cage(String item) {// if given item is surrounded by counter item turn it  into other item
		String counterItem=null;
		if(item.equals("*"))
			counterItem="o";
		else
			counterItem="x";
		
		for(int i=0;i<map.length;i++) {// check that for all elements
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j].equals(counterItem)) {
					if(i==0) {
						if(j==0) {// check if it is on the corners
							if(map[i+1][j].equals(item)&&map[i][j+1].equals(item)){
								map[i][j]=item;
							}
						}
						else if(j==map.length-1) {
							if(map[i+1][j].equals(item)&&map[i][j-1].equals(item)){
								map[i][j]=item;
							}
						}
						else
						if(map[i+1][j].equals(item)&&map[i][j+1].equals(item)&&map[i][j-1].equals(item)){
							map[i][j]=item;
						}
					}
					else if(j==0) {// check if it is on the corners
						if(i==0) {
							if(map[i+1][j].equals(item)&&map[i][j+1].equals(item)){
								map[i][j]=item;
							}
						}
						else if(i==map.length-1) {
							if(map[i-1][j].equals(item)&&map[i][j+1].equals(item)){
								map[i][j]=item;
							}
						}
						else
						if(map[i+1][j].equals(item)&&map[i-1][j].equals(item)&&map[i][j+1].equals(item)){
							map[i][j]=item;
						}
					}
					else if(i==map.length-1) {// check if it is on the corners
						if(j==map.length-1) {
							if(map[i-1][j].equals(item)&&map[i][j-1].equals(item)){
								map[i][j]=item;
							}
						}
						
						else if(j==0) {
							if(map[i-1][j].equals(item)&&map[i][j+1].equals(item)){
								map[i][j]=item;
							}
						}
						else
						if(map[i-1][j].equals(item)&&map[i][j+1].equals(item)&&map[i][j-1].equals(item)){
							map[i][j]=item;
						}
					}
					else if(j==map.length-1) {// check if it is on the corners
						if(i==map.length-1) {
							if(map[i-1][j].equals(item)&&map[i][j-1].equals(item)){
								map[i][j]=item;
							}
						}
						else if(i==0) {
							if(map[i+1][j].equals(item)&&map[i][j-1].equals(item)){
								map[i][j]=item;
							}
						}
						else
						if(map[i+1][j].equals(item)&&map[i-1][j].equals(item)&&map[i][j-1].equals(item)){
							map[i][j]=item;
						}
					}
					else {// check if it is not on the corners
						if(map[i+1][j].equals(item)&&map[i-1][j].equals(item)&&map[i][j+1].equals(item)&&map[i][j-1].equals(item)){
							map[i][j]=item;
						}

					}
				}
			}
		}

	}
	public void loadBinary(String binary) {

		
		int index=binary.indexOf(".");
		String type=binary.substring(index+1);
		if(type.equals("txt")) {
			System.out.println("The file you enter is a text file/n Lutfen bir binary dosya giriniz");
			binary=k.next();
		}
		try {
			ObjectInputStream o= new ObjectInputStream(new FileInputStream(binary));
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[0].length;j++) {
					map[i][j]=""+o.readChar();
				}
				
			}
			
			o.close();
		
		
		} catch (FileNotFoundException e) {
			System.out.println("Binary file is not exist");
			System.out.println("Please enter another binary file");
			String repeat=k.nextLine();
			loadBinary(repeat);
			
		} catch (IOException e) {

			System.out.println("Binary file is not exist");
			System.out.println("Please enter another binary file");
			String repeat=k.nextLine();
			loadBinary(repeat);
		}
	
	}
	
	public void loadText(String text) {

		System.out.println(text);
		int index=text.indexOf(".");
		String type=text.substring(index+1);
		if(!(type.equals("txt"))) {
			System.out.println("Girdiginiz dosya bir text dosyasi degil/n Lutfen bir text dosyasý giriniz");
			text=k.nextLine();
		}
		Scanner in=null;
		try {
			in=new Scanner(new FileInputStream (text));
			for(int i=0;i<map.length;i++) {
				String newLine= in.nextLine();
				for(int j=0;j<map[0].length;j++) {
					map[i][j]=""+newLine.charAt(j);
				}
				
			}
		} catch (FileNotFoundException e) {

			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			loadText(repeatText);
		}
	}
	public void WhoWon(String name1,String name2) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]!=null&&map[i][j].equals("x"))
					xArea++;
				else if(map[i][j]!=null&&map[i][j].equals("o"))
					oArea++;
			}	
		}
		if(oArea>xArea) {
			System.out.println(name2+" won \nCongratulations");
		}
		else if(xArea>oArea) {
			System.out.println(name1+" won \nCongratulations");
		}
		else if(xArea==oArea) {
			System.out.println("Skors are equal");
		}
		System.exit(0);
	}
	public String[][] ShowMap() {
		
		System.out.println("  a|b|c|d|e|f|g|h|i");
		for(int i=0;i<map.length;i++) {
			System.out.print((i+1)+"|");
			for(int j=0;j<map[0].length;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		return map;
	}
	public boolean isOver() {
		boolean isEmpty=false;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j]!=null) {
					if(Map.map[i][j].equals("-")) {
						isEmpty=true;
						break;
					}
				}
			}
			if(isEmpty)
				break;
		}
		return isEmpty;
		
	}
	public void saveMaptoText(String text) {
		PrintWriter p;
		try {
			p = new PrintWriter(new FileOutputStream(text));
			
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[0].length;j++) {
					p.print(map[i][j]);
				}
				p.println("");
			}
			p.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			saveMaptoText(repeatText);
		}
		
	}
	public boolean isTurnX(){
		player1=0;
		player2=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]!=null&&map[i][j].equals("x"))
					player1++;
				else if(map[i][j]!=null&&map[i][j].equals("o"))
					player2++;
			}	
		}
		if(player2>player1) {
			return true;
		}
		else if(player1>player2) {
			return false;
		}
		else if(player1==player2) {
			return true;
		}
		else
			return true;
	}
	public void saveMaptoBinary(String binary) {
		
		try {
			ObjectOutputStream o= new ObjectOutputStream(new FileOutputStream(binary));
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[0].length;j++) {
					o.writeChars(map[i][j]);
				}
			}
			o.close();
		} catch (FileNotFoundException e) {
			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			saveMaptoBinary(repeatText);
		} catch (IOException e) {
			System.out.println("Text file is not exist");
			System.out.println("Please enter another text file");
			String repeatText=k.nextLine();
			saveMaptoBinary(repeatText);
		}

	}

}
