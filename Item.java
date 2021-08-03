import java.util.Scanner;

public class Item {// Oguz Ocal 181101019
	Scanner k=new Scanner(System.in);
	String item;
	int row=0;
	int column=0;
	public Item(String item) {
		this.item =item;
	}
	public void hamleYap(String hamle) {
		row=(hamle.charAt(0)-'1');
		column=((hamle.charAt(1))-'a');
		
		if(row>Map.map.length||row<0||column>Map.map[0].length||column<0) {
			System.out.println("Miss match map location please enter correct form of it\n (Example: 1d or 6g) ");
			String hamle2=k.nextLine();
			hamleYap(hamle2);
		}
		else
			GenerateMap(row,column);
	}
	public String[][] GenerateMap(int row,int column){
		if(Map.map[row][column].equals("-")) {
			Map.map[row][column]=item;
			Map.Cage(item);
		}
		else {
			System.out.println("Sectiginiz yer dolu ya da gecersiz\n Lutfen yeni yer seciniz");
			String hamleTemp=k.nextLine();
			hamleYap(hamleTemp);
		}
			
		return Map.map;
		
	}

	 
}
