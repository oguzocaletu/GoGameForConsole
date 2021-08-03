import java.util.Scanner;

public class Go {// Oguz Ocal 181101019

	public static void main(String[] args) {
		Map m1=new Map();
		Player temp=new Player();
		Scanner k = new Scanner(System.in);
			//Scanner in=null;
			System.out.println("Welcome to the Go game");
			System.out.println("You are allowed to enter  your move with an integer(1,2,3,...) and an Lowerrcase letter(a,b,c,..)");
			System.out.println("Example  moves: 1a - 3c - 4f");

			try {
				System.out.println(args[0]);
			}
			catch(Exception e) {
				args= new String [1];
				args[0]="";
			}
				if(args[0].equals("")) {
					m1.CreateMap();
				}
				
				else if(args[0].length()>7&&args[0].substring(0,7).equals("-load_b")){
					m1.CreateMap();
					m1.loadBinary(args[0].substring(7,args[0].length()));
				}
				else if(args[0].length()>7&&args[0].substring(0,7).equals("-load_t")){
					m1.CreateMap();
					m1.loadText(args[0].substring(7,args[0].length()));// there is a space between load_t and filename
				}
				else if(args[0].length()>6&&args[0].substring(0,6).equals("-input")){
					m1.CreateMap();
					temp.takeInput( args[0].substring(6,args[0].length()));
				}
				else {
					System.out.println("(dosya ismi yazarken araya bosluk brakmayin lutfen)\n(-load_tornek),(-load_tornek.txt) ya da (-inputornek.txt) seklinde arguman BULUNAMADI\n Yeni map baslatiliyor...");
					System.out.println();
					System.out.println("------------------------------");
					System.out.println();
					m1.CreateMap();
				}
				System.out.println("Player 1 please enter a nickname");
				String name1=k.nextLine();
				Player p1=new Player(name1);
				System.out.println("Player 2 please enter a nickname");
				String name2=k.nextLine();
				Player p2=new Player(name2);
				
				int round=1;
		while(m1.isOver()) {
				String newLine;
				if(round==1) {//sadece ilk round icin kontrol et cunku daha sonra xler o yu kusatip one gecmis olabilir 
					if(m1.isTurnX()) {// load game yapildiginda eger x sayisi 0 sayisina esit ya da o fazla ise sira player 1'dedir
						m1.ShowMap();
						System.out.println(p1.name+", its your turn \nPlease enter Your move: ");
						newLine=k.nextLine();
						if(newLine.equals(""))
							newLine=k.nextLine();
						p1.Hamle("x",newLine);
					}
					else {// load game yapildiginda eger x sayisi fazla ise sira player 2 ye gecer
						m1.ShowMap();
						System.out.println(p2.name+", its your turn\nPlease enter Your move:");
						newLine=k.nextLine();
						p2.Hamle("o",newLine);
					}
					round++;
				}
				else {//eger 1. round degilse kim onde sorgulama cunku xler o yu kusatip one gecmis olabilir
					m1.ShowMap();
					System.out.println(p1.name+", its your turn \nPlease enter Your move: ");
					newLine=k.nextLine();
					if(newLine.equals(""))
						newLine=k.nextLine();
					p1.Hamle("x",newLine);
					if(!m1.isOver()) {
						break;
					}
					m1.ShowMap();
					System.out.println(p2.name+", its your turn\nPlease enter Your move:");
					newLine=k.nextLine();
					p2.Hamle("o",newLine);
				}
			}
		m1.WhoWon(p1.name,p2.name);
		k.close();
	
	}

}
