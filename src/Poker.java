// 승패, 덱체크한걸 갖고 베팅하는거. 이미지삽입.  카드 딜링.
import java.applet.*;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*;

import javax.swing.*;

/* <applet code="Poker.class" width=1200 height=900>
 <param name = "◆ A" value="◆A.png">
<param name = "◆ 2" value="◆2.png">
<param name = "◆   3" value="◆3.png">
<param name = "◆ 4" value="◆4.png">
<param name = "◆ 5" value="◆5.png">
<param name = "◆ 6" value="◆6.png">
<param name = "◆ 7" value="◆7.png">
<param name = "◆ 8" value="◆8.png">
<param name = "◆ 9" value="◆9.png">
<param name = "◆ 0" value="◆10.png">
<param name = "◆ J" value="◆J.png">
<param name = "◆ Q" value="◆Q.png">
<param name = "◆ K" value="◆K.png">
 <param name = "♥ A" value="♥A.png">
<param name = "♥ 2" value="♥2.png">
<param name = "♥ 3" value="♥3.png">
<param name = "♥ 4" value="♥4.png">
<param name = "♥ 5" value="♥5.png">
<param name = "♥ 6" value="♥6.png">
<param name = "♥ 7" value="♥7.png">
<param name = "♥ 8" value="♥8.png">
<param name = "♥ 9" value="♥9.png">
<param name = "♥ 0" value="♥10.png">
<param name = "♥ J" value="♥J.png">
<param name = "♥ Q" value="♥Q.png">
<param name = "♥ K" value="♥K.png">
 <param name = "♠ A" value="♠A.png">
<param name = "♠ 2" value="♠2.png">
<param name = "♠ 3" value="♠3.png">
<param name = "♠ 4" value="♠4.png">
<param name = "♠ 5" value="♠5.png">
<param name = "♠ 6" value="♠6.png">
<param name = "♠ 7" value="♠7.png">
<param name = "♠ 8" value="♠8.png">
<param name = "♠ 9" value="♠9.png">
<param name = "♠ 0" value="♠10.png">
<param name = "♠ J" value="♠J.png">
<param name = "♠ Q" value="♠Q.png">
<param name = "♠ K" value="♠K.png">
 <param name = "♣ A" value="♣A.png">
<param name = "♣ 2" value="♣2.png">
<param name = "♣ 3" value="♣3.png">
<param name = "♣ 4" value="♣4.png">
<param name = "♣ 5" value="♣5.png">
<param name = "♣ 6" value="♣6.png">
<param name = "♣ 7" value="♣7.png">
<param name = "♣ 8" value="♣8.png">
<param name = "♣ 9" value="♣9.png">
<param name = "♣ 0" value="♣10.png">
<param name = "♣ J" value="♣J.png">
<param name = "♣ Q" value="♣Q.png">
<param name = "♣ K" value="♣K.png">
<param name = "back_c" value="Back_C.png">
 Ballet */


class Player{
	char PD[][] = new char[7][2];
	String PD_w[] = new String[7];
	String PD_wa[] = new String[7];
	
	String PDD[] = new String[7];
	int Card_P[] = new int[7];//문양 1~4, 4:스 3: 다 2:하 1:클
	int Card_N[] = new int[7];//숫자
	int Max = 0;
	int Bat_M;
	 int cardch[] =new int[13];
	 int paturnch[] =new int[4];

	 
	int onep;
	 int twop;
	 int triple;
	 int str;
	 int flush;
	 int fullhouse;
	 int fourc;
	 int stf;
	 
	 int score;
	 
	int money;
	Boolean P_type;//true : pc. false:npc;
	int Fake_late;
	
	Player(int i){
		if(i==0)
			P_type = false;
		else P_type = true;
		money = 100000;
		Fake_late = 0;
		for(int k = 0; i<7; i++)
		{
			for(int j = 0; j<2;j++)
			{
				PD[k][j] = ' ';
				PD_w[i] += PD[i][j];
				PD_wa[i] += PD[i][j];

			}
			
		}
		 for(int j = 0; i<14;i++)
		 {
			 cardch[j] = 0;
		 }
		 for(int j = 0; i<4;i++)
		 {
			 cardch[j] = 0;
		 }
	}
	
	void game_Start()
	{
		for(int i = 0; i<7; i++)
		{
			for(int j = 0; j<2;j++)
			{
				PD[i][j] = ' ';
			}
		}
	}
	
	void change_CP(int i, int j){
		String temp = PD_w[i];
		PD_w[i] = PD_w[j];
		PD_w[j] = temp;
	}
	
}

class Card{
	char deck[][] = new char[52][2];//카드 덱 [카드갯수][문양,숫자]
	int al_C[] = new int[52];
	String decks[] = new String[52];
	Card()
	{
		for(int i = 0 ; i <52;i++)
		{
			String temp = new String();
			if(i/13 == 0)
				deck[i][0] = '♠';
			if(i/13 == 1)
				deck[i][0] = '◆';
			if(i/13 == 2)
				deck[i][0] = '♥';
			if(i/13 == 3)
				deck[i][0] = '♣';
			//문자 결정
			if(i%13+1 == 1)
				deck[i][1] = 'A';
			else if(i%13+1 == 10)
				deck[i][1] = '0';
			else if(i%13+1 == 11)
				deck[i][1] = 'J';
			else if(i%13+1 == 12)
				deck[i][1] = 'Q';
			else if(i%13+1 == 13)
				deck[i][1] = 'K';
			
			else{
				int l = (i%13)+1;
			temp = "" + l;
			deck[i][1] = temp.charAt(0);
			}
			decks[i] = ""+ deck[i][0]+" "+deck[i][1];
			al_C[i] = 0;
		}
		
	}
	void Init()
	{
		for(int i = 0 ; i <52;i++)
			al_C[i] = 0;
	}
	void game_Start()
	{
		for(int i=0; i<52; i++)
			al_C[i] = 0;
	}
	
	char[] get_card()
	{
		
		int j;
		
		do{
			j = (int)(Math.random()*52);
			if(al_C[j] == 0)
				break;
		}while(true);
		
		char temp[] = new char[2];
		temp = deck[j];
		al_C[j] = 1;
		return temp;
	}
	{}
}


public class Poker extends JApplet 
 implements ActionListener, ItemListener
{ 
  Player PC;
  Player NPC;
  int[] NPCBB = new int[8];
  
  Card card_D;
  int Table_M;
  int Bat_M_H;
  int Bat_M_D;
  int Bat_M_C;
  
  
  int Turn;
  

  
  boolean open_card[] = new boolean[4];
  
  JButton Start_G = new JButton("Start");
  JButton OPEN = new JButton("Open card");
  JButton Half = new JButton("Half");
  JButton Call = new JButton("Call");
  JButton Double = new JButton("Double");
  JButton Fold = new JButton("Fold");
  JButton ALL_in = new JButton("All In");
  JButton NEXT = new JButton("NEXT");
  /*JButton  = new JButton("");
   * JButton  = new JButton("");
  */
  JCheckBox Cno_1 = new JCheckBox("First card");
  JCheckBox Cno_2 = new JCheckBox("Second card");
  JCheckBox Cno_3 = new JCheckBox("Third card");
  JCheckBox Hidden_C = new JCheckBox("HIDDEN card");
  
  JPanel P_Panel = new JPanel();
  
  JLabel P_Label[] = new JLabel[7];
  JLabel P_Label2[] = new JLabel[7];
  
  JLabel NP_Label[] = new JLabel[7];
  ImageIcon PC_card_II[] = new ImageIcon[7];
  ImageIcon PC_card_III[] = new ImageIcon[7];
  
  ImageIcon NPC_card_II[] = new ImageIcon[7];
  
  JPanel PB_Panel = new JPanel();
  JPanel PB_Panel2 = new JPanel();
  JPanel PB_Panel3 = new JPanel();
  
  JPanel PB_Panel4 = new JPanel();
  
  
 
  JPanel NP_Panel = new JPanel();
  JPanel NPB_Panel = new JPanel();
  
  JPanel T_Panel = new JPanel();
  JPanel T_Panel2 = new JPanel();
  
  JTextField MTF[] = new JTextField[5];//0번 np돈 1번 np베팅 2번 테이블돈 3번 p베팅 4번 p돈
  JLabel WMTF[] = new JLabel[5];
  
  
  public void init()
  {
	
	  Container root = getContentPane();
	  
		 Half.addActionListener(this);
		 Double.addActionListener(this);
		 Call.addActionListener(this);
		 Fold.addActionListener(this);
		 ALL_in.addActionListener(this);
		 NEXT.addActionListener(this);
		 OPEN.addActionListener(this);
		 Cno_1.addItemListener(this);
		 Cno_2.addItemListener(this);
		 Cno_3.addItemListener(this);
		 Hidden_C.addItemListener(this);
	 Start_G.addActionListener(this);
	 
	 for(int i = 0; i <5; i++)
	 {
		 MTF[i] = new JTextField(10);
	 }
	
	 WMTF[0] = new JLabel("NPC의 현재 소지금");
	 WMTF[1] = new JLabel("NPC의 배팅 금액");
	 WMTF[2] = new JLabel("현제 테이블위의 총 금액");
	 WMTF[3] = new JLabel("PC의 배팅 금액");
	 WMTF[4] = new JLabel("PC의 현재 소지금");
	 
	 
	 PC = new Player(1);
	 NPC = new Player(0);
	 card_D = new Card();
	 Table_M = 0;
	 Turn = 1;
	 
	 PC.score = 0;
	 NPC.score = 0;
	 
	 BorderLayout rootL = new BorderLayout(); 
	 BorderLayout PL = new BorderLayout();
	 GridLayout NPL = new GridLayout(1,7);
	 BorderLayout TL = new BorderLayout();
	  root.setLayout(rootL);

	 
	 P_Panel.setLayout(PL);
	 PB_Panel.setLayout(new GridLayout(3,2));
	 PB_Panel2.setLayout(new GridLayout(5,1));
	 PB_Panel3.setLayout(new GridLayout(1,7));
	 PB_Panel4.setLayout(new GridLayout(1,7));
	 

	 T_Panel.setLayout(new BorderLayout());
	 
	 PB_Panel.add(Half);
	 PB_Panel.add(Call);
	 PB_Panel.add(Double);
	 PB_Panel.add(Fold);
	 PB_Panel.add(ALL_in);
	 PB_Panel.add(NEXT);
	 
	 PB_Panel2.add(Cno_1);
	 PB_Panel2.add(Cno_2);
	 PB_Panel2.add(Cno_3);
	 PB_Panel2.add(Hidden_C);
	 PB_Panel2.add(OPEN);
	 
	 P_Panel.add(PB_Panel, BorderLayout.EAST);
	 P_Panel.add(PB_Panel2, BorderLayout.WEST);
	 
	 
	 for(int i = 0; i<7; i++)
	 {
		 PC_card_II[i] = new ImageIcon("Back_C.png");
		 P_Label[i] = new JLabel(PC_card_II[i]);
		 PB_Panel3.add(P_Label[i]);
		 
	 }
	 
	 NPB_Panel.setLayout(new GridLayout(1,7));
	 
	 for(int i = 0; i<7; i++)
	 {
		 NPC_card_II[i] = new ImageIcon("Back_C.png");
		 NP_Label[i] = new JLabel(NPC_card_II[i]);
		 NPB_Panel.add(NP_Label[i]);
		 
	 }
	 P_Panel.add(PB_Panel3, BorderLayout.CENTER);
	 root.add(P_Panel, BorderLayout.SOUTH);
	 NP_Panel.add(NPB_Panel);
	 root.add(NP_Panel, BorderLayout.NORTH);
	 
	 for(int i = 0; i<7; i++)
	 {
		 PC_card_III[i] = new ImageIcon("Back_C.png");
		 P_Label2[i] = new JLabel(PC_card_III[i]);
		 PB_Panel4.add(P_Label2[i]);
		 
	 }
	 T_Panel2.setLayout(new GridLayout(1,10));
	 for(int i = 0; i<5;i++)
	 {
		
		 T_Panel2.add(WMTF[i]);
		 T_Panel2.add(MTF[i]);
		 MTF[i].setEditable(false);
	 }
	 
	 T_Panel2.setSize(1200, 30);
	 T_Panel.add(PB_Panel4, BorderLayout.SOUTH);	 
	 T_Panel.add(T_Panel2,BorderLayout.NORTH);
	 T_Panel.add(Start_G);
	
	 
	 
	 
	 root.add(T_Panel, BorderLayout.CENTER);
	 Hidden_C.setEnabled(false);
	 hideButton();
	 
	 for(int i = 0; i<3; i++){
			PC.PD[i] = card_D.get_card();
			NPC.PD[i] = card_D.get_card();  
	 }
	 
	 for(int i = 0;i<7;i++)
	 {
		 PC.PD_w[i] = "" + PC.PD[i][0] + " "+ PC.PD[i][1];

	 }

	 NEXT.setEnabled(false);
	 repaint();
  }


  	public void showButton(){                        // 버튼을 보이게 하는 메서드
	  Half.show();
	  Double.show();
		 Call.show();
		 Fold.show();
		 ALL_in.show();
		 NEXT.show();
		 OPEN.show();
		 Cno_1.show();
		 Cno_2.show();
		 Cno_3.show();
		 Hidden_C.show();
		 PB_Panel3.show();
		 PB_Panel4.show();
		 NPB_Panel.show();
		 T_Panel2.show();
	 }
	 public void hideButton(){                        // 버튼을 안 보이게 하는 메서드
		  Half.hide();
		  Double.hide();
			 Call.hide();
			 Fold.hide();
			 ALL_in.hide();
			 NEXT.hide();
			 OPEN.hide();
			 Cno_1.hide();
			 Cno_2.hide();
			 Cno_3.hide();
			 Hidden_C.hide();
			 PB_Panel3.hide();
			 PB_Panel4.hide();
			 NPB_Panel.hide();
			 T_Panel2.hide();
					
	 }
	 public void Check_STF(Player ch){
		 int tt, pp;
		 int num[] = new int[7];
		 int pat[] = new int[7];
		 if(ch.str == 1)
		 {
			 
			 for(int i = 0;i<7;i++)
			 {
					 if(PC.PD[i][1] == 'A')
						num[i] = 14;
					 if(PC.PD[i][1] == 'K')
						num[i] = 13;
					 if(PC.PD[i][1] == 'Q')
						 num[i] = 12;
					 if(PC.PD[i][1] == 'J')
						 num[i] = 11;
					 if(PC.PD[i][1] == '0')
						 num[i] = 10;
					 if(PC.PD[i][1] == '9')
						 num[i] = 9;
					 if(PC.PD[i][1] == '8')
						 num[i] = 8;
					 if(PC.PD[i][1] == '7')
						 num[i] = 7;
					 if(PC.PD[i][1] == '6')
						 num[i] = 6;
					 if(PC.PD[i][1] == '5')
						 num[i] = 5;
					 if(PC.PD[i][1] == '4')
						 num[i] = 4;
					 if(PC.PD[i][1] == '3')
						 num[i] = 3;
					 if(PC.PD[i][1] == '2')
						 num[i] = 2;
					
					if(PC.PD[i][0] == '♠')	
						pat[i] = 4;
					if(PC.PD[i][0] == '◆')	
						pat[i] = 3;
					if(PC.PD[i][0] == '♥')
						pat[i] = 2;	
					if(PC.PD[i][0] == '♣')	
						pat[i] = 1;
				
				 }
			 int temp= 0;
			 for(int i = 0;i<7;i++)
				 for(int j = i; j<7;j++)
				 {
					 if(num[i]<num[j])
					{
						 temp = num[i];
						 num[i] = num[j];
						 num[j] = temp;
						 temp = pat[i];
						 pat[i] = pat[j];
						 pat[j] = temp;
					}
				 }
			 temp = 0;
			 for(int i=0;i<3;i++)
			 {
				 if(num[i] == num[i+1]-1 & pat[i] == pat[i+1])
					 for(int j = i; j<i+5;j++)
					 {
						 if(num[j] != (num[j+1] - 1) | pat[j] != pat[j+1])
							 break;
						 else temp++;
					 }
				
			 }
			 if(temp>=5) ch.stf++;
		 }
		  tt=num[0];
		  pp=pat[0];
		  if(ch.stf == 1)
			  ch.score = tt*100000000+pp*100000;
	 }
	 public void Check_Power(Player npc, Player pc){
	
		 int PCP = 0;
		 int NPCP = 0;
		 
		 int pn = 0;
		 int pp = 0;
		 int npn = 0;
		 int npp = 0;
		 
		 int pcp[] = new int[7];
		 int pcc[] = new int[7];
		
		 int npcp[] = new int[7];
		 int npcc[] = new int[7];
		 
		 
		 for(int i = 0;i<7;i++)
		 {
			 if(PC.PD[i][1] != ' ')
			 {

				 if(PC.PD[i][1] == 'A')
				 {
					 PC.cardch[12]++;
					 pcc[i] = 14;
				 }
				 if(PC.PD[i][1] == 'K')
				 {
					 PC.cardch[11]++;
					 pcc[i] = 13;
				 }
				 if(PC.PD[i][1] == 'Q')
				 {
					 PC.cardch[10]++;
					 pcc[i] = 12;
				 }
				 if(PC.PD[i][1] == 'J')
				 {
					 PC.cardch[9]++;
					 pcc[i] = 11;
				 }
				 if(PC.PD[i][1] == '0')
				 {
					 PC.cardch[8]++;
					 pcc[i] = 10;
				 }
				 if(PC.PD[i][1] == '9')
				 {
					 PC.cardch[7]++;
					 pcc[i] = 9;
				 }
				 if(PC.PD[i][1] == '8')
				 {
					 PC.cardch[6]++;
					 pcc[i] = 8;
				 }
				 if(PC.PD[i][1] == '7')
				 {
					 PC.cardch[5]++;
					 pcc[i] = 7;
				 }
				 if(PC.PD[i][1] == '6')
				 {
					 PC.cardch[4]++;
					 pcc[i] = 6;
				 }
				 if(PC.PD[i][1] == '5')
				 {
					 PC.cardch[3]++;
					 pcc[i] = 5;
				 }
				 if(PC.PD[i][1] == '4')
				 {
					 PC.cardch[2]++;
					 pcc[i] = 4;
				 }
				 if(PC.PD[i][1] == '3')
				 {
					 PC.cardch[1]++;
					 pcc[i] = 3;
				 }
				 if(PC.PD[i][1] == '2')
				 {
					 PC.cardch[0]++;
					 pcc[i] = 2;
				 }
				 
				if(PC.PD[i][0] == '♠')	
					pcp[i] = 4;
				if(PC.PD[i][0] == '◆')	
					pcp[i] = 3;
				if(PC.PD[i][0] == '♥')
					pcp[i] = 2;	
				if(PC.PD[i][0] == '♣')	
					pcp[i] = 1;

			 }
			 if(NPC.PD[i][1] != ' ')
			 {

				 if(NPC.PD[i][1] == 'A')
				 {
					 NPC.cardch[12]++;
					npcc[i] = 14;
				 }
				 if(NPC.PD[i][1] == 'K')
				 {
					 NPC.cardch[11]++;
					 npcc[i] = 13;
				 }
				 if(NPC.PD[i][1] == 'Q')
				 {
					 NPC.cardch[10]++;
					 npcc[i] = 12;
				 }
				 if(NPC.PD[i][1] == 'J')
				 {
					 NPC.cardch[9]++;
					 npcc[i] = 11;
				 }
				 if(NPC.PD[i][1] == '0')
				 {
					 NPC.cardch[8]++;
					 npcc[i] = 10;
				 }
				 if(NPC.PD[i][1] == '9')
				 {
					 NPC.cardch[7]++;
					 npcc[i] = 9;
				 }
				 if(NPC.PD[i][1] == '8')
				 {
					 NPC.cardch[6]++;
					 npcc[i] = 8;
				 }
				 if(NPC.PD[i][1] == '7')
				 {
					 NPC.cardch[5]++;
					 npcc[i] = 7;
				 }
				 if(NPC.PD[i][1] == '6')
				 {
					 NPC.cardch[4]++;
					 npcc[i] = 6;
				 }
				 if(NPC.PD[i][1] == '5')
				 {
					 NPC.cardch[3]++;
					 npcc[i] = 5;
				 }
				 if(NPC.PD[i][1] == '4')
				 {
					 NPC.cardch[2]++;
					 npcc[i] = 4;
				 }
				 if(NPC.PD[i][1] == '3')
				 {
					 NPC.cardch[1]++;
					 npcc[i] = 3;
				 }
				 if(NPC.PD[i][1] == '2')
				 {
					 NPC.cardch[0]++;
					 npcc[i] = 2;
				 }
				 
				if(NPC.PD[i][0] == '♠')	
					npcp[i] = 4;
				if(NPC.PD[i][0] == '◆')	
					npcp[i] = 3;
				if(NPC.PD[i][0] == '♥')
					npcp[i] = 2;	
				if(NPC.PD[i][0] == '♣')	
					npcp[i] = 1;

			 }
		 }
		 for(int i =0; i<4; i++)
		 {
			 if(PC.PD[i][0] != ' ')
			 {
				 if(PC.PD[i][0] == '♠')	
					PC.paturnch[3]++;
				if(PC.PD[i][0] == '◆')	
					PC.paturnch[2]++;
				if(PC.PD[i][0] == '♥')
					PC.paturnch[1]++;		
				if(PC.PD[i][0] == '♣')	
					PC.paturnch[0]++;
			}
			 if(NPC.PD[i][0] != ' ')
				 {
					if(NPC.PD[i][0] == '♠')	
						NPC.paturnch[3]++;
					if(NPC.PD[i][0] == '◆')	
						NPC.paturnch[2]++;
					if(NPC.PD[i][0] == '♥')
						NPC.paturnch[1]++;		
					if(NPC.PD[i][0] == '♣')	
						NPC.paturnch[0]++;
				 }
		 }
		 pp=0;
		 npp=0;
		 for(int i = 0;i<13;i++)
		 {
			 int pcstr =0;
			 int npcstr = 0;
			 
			 if(i == 12)
			 {	
				 for(int j =0; j<4;j++)
					{
						if(PC.cardch[j] <1)
							break;
						pcstr++;
					}
				if(pcstr==5)
				{
					PC.str++;
					pn = 14;
					for(int j = 0;j<7;j++)
					 {
						 if(pcc[j] == 14)
							if(pp<pcp[j])
								pp = pcp[j];
					 }
				}
				
				else
					pcstr=0;
				for(int k =0; k<4;k++)
				{
					if(NPC.cardch[k] <1)
						break;
				 	npcstr++;
				}
				 if(npcstr==5)
				 {
						NPC.str++;
						npn = 14;
						for(int j = 0;j<7;j++)
						 {
							 if(npcc[j] == 14)
								if(npp<npcp[j])
									npp = npcp[j];
						 }
					}
				 else 
				 	npcstr=0;
			 }
			 if(i<=8 & PC.cardch[i] >=1)
			 {
				for(int j =i; j<i+5;j++)
				{
					if(PC.cardch[j] <1)
						break;
					pcstr++;
				}
				
				if(pcstr==5)
				{
					PC.str++;
					pn = i+6;
					for(int j = 0;j<7;j++)
					 {
						 if(pcc[j] == i+6)
							if(pp<pcp[j])
								pp = pcp[j];
					 }
				}
				else
					pcstr=0;
				int k=0;
			 	for(k =i; k<i+5;k++)
			 	{
			 		if(NPC.cardch[k] <1)
			 			break;
			 		npcstr++;
			 	}
			 	if(npcstr==5)
			 	{
					NPC.str++;
					npn = i+6;
					for(int j = 0;j<7;j++)
					 {
						 if(npcc[j] == i+6)
							if(npp<npcp[j])
								npp = npcp[j];
					 }
				}
			 	else 
			 		npcstr=0;
			 	if(NPC.str>1)
			 		NPC.Max = k;
			 }
			 //스트레이트 체크
			 
			 if(PC.cardch[i] == 2)
			 {
				 PC.onep++;
				 pn = i+2;
				 for(int j = 0;j<7;j++)
				 {
					 if(pcc[j] == i+2)
						if(pp<pcp[j])
							pp = pcp[j];
				 }
			 }
			 if(PC.cardch[i] == 3)
			 {
				 PC.triple++;
				 pn=i+2;
				 for(int j = 0;j<7;j++)
				 {
					 if(pcc[j] == i+2)
						if(pp<pcp[j])
							pp = pcp[j];
				 }
			 }
			 if(PC.cardch[i] == 4)
			 {
				 PC.fourc++; 
				 pn=i+2;
				 for(int j = 0;j<7;j++)
				 {
					 if(pcc[j] == i+2)
						if(pp<pcp[j])
							pp = pcp[j];
				 }
			 }


			 if(NPC.cardch[i] == 2)
			 {
				 NPC.onep++;
				 npn=i+2;
					for(int j = 0;j<7;j++)
					 {
						 if(npcc[j] == i+2)
							if(npp<npcp[j])
								npp = npcp[j];
					 }
			 }
			 if(NPC.cardch[i] == 3)
			 { 
				 NPC.triple++;
				 npn=i+2;
					for(int j = 0;j<7;j++)
					 {
						 if(npcc[j] == i+2)
							if(npp<npcp[j])
								npp = npcp[j];
					 }
			 }
			 if(NPC.cardch[i] == 4)
			 {
				 NPC.fourc++; 
				 npn=i+2;
					for(int j = 0;j<7;j++)
					 {
						 if(npcc[j] == i+2)
							if(npp<npcp[j])
								npp = npcp[j];
					 }
			 }
		 }
		 for(int i = 0;i<4;i++)
		 {
			 if(PC.paturnch[i] >= 5)
			 {
				 PC.flush++;
				 pp = i+2;
			 }
			 if(NPC.paturnch[i] >= 5)
			 {
				 NPC.flush++;
				 npp = i+2;
			 }
		 }
		 
		 if(PC.onep == 2 & PC.triple == 0)
		 {
			 PC.onep = 0;
			 PC.twop++;
		 }
		 if(PC.onep >=1 & PC.triple == 1)
		 { 
			 PC.onep = 0;
			 PC.triple = 0;
			 PC.fullhouse++;
		 }
		 if(PC.flush >0 & PC.str >0)
		 {
			 Check_STF(PC);
			 PC.flush = 0; 
			 PC.str =0;
			
		 }
		 
		 
		 if(NPC.onep == 2 & NPC.triple == 0)
		 {
			 NPC.onep = 0;
			 NPC.twop++;
		 }
		 if(NPC.onep >=1 & NPC.triple == 1)
		 { 
			 NPC.onep = 0;
			 NPC.triple = 0;
			 NPC.fullhouse++;
		 }
		 if(NPC.flush >0 & NPC.str >0)
		 {
			 Check_STF(NPC);
			 NPC.flush = 0; 
			 NPC.str =0;
		 }
		 if(pn == 0)
			 for(int l = 0; l< 13; l++)
			 {
				 if(PC.cardch[l] >1)
					 pn = l+2;
			 }
		 if(npn == 0)
			 for(int l = 0; l< 13; l++)
			 {
				 if(NPC.cardch[l] >1)
					 npn = l+2;
			 }
			/* top : 1
			 * onepair: 10
			 * two P : 100
			 * Trip : 1000
			 * ST : 10000	 
			 * Flush : 100000
			 * Full : 1000000
			 * Four : 10000000 
			 * STF : 100000000
			 */
		 if(PC.onep > 1 & PC.flush == 0 & PC.str == 0)
			 PC.score = pn * 10 + pp;
		 if(PC.twop > 1 & PC.flush == 0 & PC.str == 0)
			 PC.score = pn*100 + pp;
		 if(PC.triple > 1 & PC.flush == 0 & PC.str == 0)
			 PC.score = pn * 1000 + pp;
		 if(PC.str >1 &PC.flush == 0)
			 PC.score = pn * 10000 + pp;
		 if(PC.flush > 1)
			 PC.score = pp * 100000;
		 if(PC.fullhouse >1)
			 PC.score = pn * 1000000 + pp;
		 if(PC.fourc > 1 & PC.flush == 0 & PC.str == 0)
			 PC.score = pn * 10000000;
		 if(PC.onep == 0 & PC.flush == 0 & PC.str == 0 & PC.twop == 0 & PC.triple == 0 & PC.flush == 0 & PC.fullhouse == 0 & PC.fourc == 0)
			 PC.score = pn;
		 
		 if(NPC.onep > 1 & NPC.flush == 0 & NPC.str == 0)
			 NPC.score = npn * 10 + npp;
		 if(NPC.twop > 1 & NPC.flush == 0 & NPC.str == 0)
			 NPC.score = npn*100 + npp;
		 if(NPC.triple > 1 & NPC.flush == 0 & NPC.str == 0)
			 NPC.score = npn * 1000 + npp;
		 if(NPC.str >1 &NPC.flush == 0)
			 NPC.score = npn * 10000 + npp;
		 if(NPC.flush > 1)
			 NPC.score = npp * 100000;
		 if(NPC.fullhouse >1)
			 NPC.score = npn * 1000000 + npp;
		 if(NPC.fourc > 1 & NPC.flush == 0 & NPC.str == 0)
			 NPC.score = npn * 10000000;
		 if(NPC.onep == 0 & NPC.flush == 0 & NPC.str == 0 & NPC.twop == 0 & NPC.triple == 0 & NPC.flush == 0 & NPC.fullhouse == 0 & NPC.fourc == 0)
			 NPC.score = npn;
	}
	 public void BAI(){
		 //베팅 판단 만들기.
		 
		 NPCBB[0] = NPC.onep;
		 NPCBB[1] = NPC.twop;
		 NPCBB[2] = NPC.triple;
		 NPCBB[3] = NPC.str;
		 NPCBB[4] = NPC.flush;
		 NPCBB[5] = NPC.fullhouse;
		 NPCBB[6] = NPC.fourc;
		 NPCBB[7] = NPC.stf;
		 
		 
		 int RB = (int)(Math.random()*1000);
		 if(RB>990) Fold(PC,NPC);
		 int k =0;
		 for(int i = 0; i<8;i++)
		 {
			 if(NPCBB[i] > 0)
			 {
				 k = i+1;
			 }
		 }
		 if(Turn <2)
			 NPC.Bat_M = 400;
		 else
		 {
		switch(k){
		case 0:
		{
			if(RB<500) Fold(PC,NPC);
			else if(RB<700) callB(NPC,PC);
			else if(RB<900) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 1:
		{
			if(RB<500) Fold(PC,NPC);
			else if(RB<700) callB(NPC,PC);
			else if(RB<900) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 2:{
			if(RB<200) Fold(PC,NPC);
			else if(RB<600) callB(NPC,PC);
			else if(RB<800) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 3:{
			if(RB<100) Fold(PC,NPC);
			else if(RB<600) callB(NPC,PC);
			else if(RB<800) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 4:{
			if(RB<50) Fold(PC,NPC);
			else if(RB<500) callB(NPC,PC);
			else if(RB<750) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 5:{
			if(RB<10) Fold(PC,NPC);
			else if(RB<400) callB(NPC,PC);
			else if(RB<800) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 6:{
			if(RB<5) Fold(PC,NPC);
			else if(RB<300) callB(NPC,PC);
			else if(RB<750) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 7:{
			if(RB<1) Fold(PC,NPC);
			else if(RB<200) callB(NPC,PC);
			else if(RB<600) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		case 8:{
			if(RB<1) Fold(PC,NPC);
			else if(RB<300) callB(NPC,PC);
			else if(RB<500) halfB(NPC,PC);
			if(RB<=990) doubleB(NPC, PC); 
			break;
		}
		default :callB(PC,NPC);
			
			
		}
		 }
	 }
	 public void finish(){
		 if(PC.score>NPC.score)
			 PC.money+=Table_M;
		 else
			 NPC.money += Table_M;
		 Table_M = 0;
	 }
	 public void continue_G(){
		if(Turn == 0)
		{
			repaint();
			Turn++;
		}
		else if(Turn <5)
		 {
			 if(Turn < 4)
			 { 	Turn++;
			 	PC.PD[Turn+1] = card_D.get_card();
				NPC.PD[Turn+1] = card_D.get_card(); 
				
				PC.PD_w[Turn+1] = "" + PC.PD[Turn+1][0] + " " + PC.PD[Turn+1][1];
				PC.PD_wa[Turn+1] = "" + PC.PD[Turn+1][0] + " " + PC.PD[Turn+1][1];
			
				NPC.PD_w[Turn+1] = "" + NPC.PD[Turn+1][0] + " " + NPC.PD[Turn+1][1];
				
				System.out.println(PC.PD_w[Turn+1]);
				System.out.println(NPC.PD_w[Turn+1]);
			 }
			 else {
				 Turn++;
				PC.PD[Turn+1] = card_D.get_card();
				NPC.PD[Turn+1] = card_D.get_card(); 
					
				PC.PD_w[Turn+1] = "" + PC.PD[Turn+1][0] + " " + PC.PD[Turn+1][1];
			 }
				 
			 System.out.println(Turn);
		 }
		 
		 else if(Turn == 5)
		 {
			 Turn++;
			 for(int i = 0; i< 7;i++)
			 {
				 PC.PD_wa[i] = PC.PD_w[i];
				 NPC.PD_w[i] = "" + NPC.PD[i][0] + " " + NPC.PD[i][1];
			 }
			 Check_Power(NPC,PC);
			 finish();
		 }
		 else if(Turn == 6)
		 {
			 char[] TCC = new char[2];
			 TCC[0] = ' '; TCC[1] = ' ';
			 Turn = 1;
			 
			 card_D.Init();
			 
			 for(int i = 0; i<7; i++){
				 PC.PD[i] = TCC;
				 NPC.PD[i] = TCC;
					
				 PC.PD_w[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
				PC.PD_wa[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
				
				NPC.PD_w[i] = "" + NPC.PD[i][0] + " " + NPC.PD[i][1];
			 }
			 repaint();
			 for(int i=0;i<3;i++)
			 {
				 open_card[i] = false;
					PC.PD[i] = card_D.get_card();
					NPC.PD[i] = card_D.get_card();
					PC.PD_w[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
					PC.PD_wa[i] = "  ";
				
					NPC.PD_w[i] = "  ";
			 }
			 Image im = getImage(getCodeBase(), getParameter("back_c"));
			 for(int i = 0; i<7; i++)
			 {
				 PC_card_II[i].setImage(im);
				
				 
			 }
			 
			 for(int i = 0; i<7; i++)
			 {
				 NPC_card_II[i].setImage(im);
				 
				 
			 }
			 for(int i = 0; i<7; i++)
			 {
				 PC_card_III[i].setImage(im);
				
				 
			 }
			 start_G();
		 }
		 
			 repaint();
	 }
	 public void start_G(){
		 Turn =0;
		 PC.money -=400;
		  NPC.money -=400;
		  PC.Bat_M = 400;
		  NPC.Bat_M = 400;
		  Table_M = 800;
		 for(int i = 0; i<3; i++){

			
			  if(NPC.PD[i][0] == '♠') {PC.Card_P[i] = 4;NPC.Card_P[i] = 4;}
			  if(NPC.PD[i][0] == '◆') {PC.Card_P[i] = 3;NPC.Card_P[i] = 3;}
			  if(NPC.PD[i][0] == '♥') {PC.Card_P[i] = 2;NPC.Card_P[i] = 2;}
			  if(NPC.PD[i][0] == '♣') {PC.Card_P[i] = 1;NPC.Card_P[i] = 1;}
			 String s = ""+PC.PD[i][1];
			 if(PC.PD[i][1] == '0')
				 s="10";
			 if(PC.PD[i][1] == 'J')
				 s="11";
			 if(PC.PD[i][1] == 'Q')
				 s="12";
			 if(PC.PD[i][1] == 'K')
				 s="13";
			 if(PC.PD[i][1] == 'A')
				 s="14";
			 
			  PC.Card_N[i] = Integer.parseInt(s);
			  
			  s = ""+NPC.PD[i][1];
				 if(NPC.PD[i][1] == '0')
					 s="10";
				 if(NPC.PD[i][1] == 'J')
					 s="11";
				 if(NPC.PD[i][1] == 'Q')
					 s="12";
				 if(NPC.PD[i][1] == 'K')
					 s="13";
				 if(NPC.PD[i][1] == 'A')
					 s="14";
				 
			  NPC.Card_N[i] = Integer.parseInt(s);
			  NPC.PDD[i] = s;
			  open_card[i] = false;
			  }
		 
		 if(Cno_1.isSelected() == true)
			 open_card[0] = true;
		 if(Cno_2.isSelected() == true)
			 open_card[1] = true;
		 if(Cno_3.isSelected() == true)
			 open_card[2] = true;
		 for(int i = 0;i<7;i++)
		 {
			 PC.PD_w[i] = ""+PC.PD[i][0]+" "+PC.PD[i][1];
		 }
		 
		 
	 }
	 public void open_CG(){
		 int check_F[] = new int[3];

		  for(int i = 0;i<3;i++)
		  {
			  if(NPC.PD[i][0] == '♠') check_F[i] = 4;
			  if(NPC.PD[i][0] == '◆') check_F[i] = 3;
			  if(NPC.PD[i][0] == '♥') check_F[i] = 2;
			  if(NPC.PD[i][0] == '♣') check_F[i] = 1;
		  }

		  for(int i = 0; i<3;i++)
		  {
			  if(open_card[i] == true)
				  PC.PD_wa[i] = PC.PD_w[i];
		  }
		  //플레이어 오픈할 카드 선택된거 체크, 엔피시의 선택. 엔피시는 2가지 고민을 통해 결정. 1. 높은카드 두개, 2. 원페어로 받을 경우 중복되지 않게, 3. 트리플일떈 그냥 앞에서 2개. 
		  if(Integer.parseInt(NPC.PDD[0]) > Integer.parseInt(NPC.PDD[1]) && Integer.parseInt(NPC.PDD[0]) > Integer.parseInt(NPC.PDD[2]))
		  {
			  if(Integer.parseInt(NPC.PDD[1]) > Integer.parseInt(NPC.PDD[2]))
			  {
				  NPC.PD_w[0] =""+ NPC.PD[1][0] + " " + NPC.PD[0][1];
				  NPC.PD_w[1] = ""+NPC.PD[1][0] + " " + NPC.PD[1][1];
			  }
			  else
			  {
				  NPC.PD_w[0] =""+ NPC.PD[1][0] + " " + NPC.PD[0][1];
				  NPC.PD_w[2] = ""+NPC.PD[2][0] + " " + NPC.PD[2][1];
			  }
		  }
		  if(Integer.parseInt(NPC.PDD[1]) > Integer.parseInt(NPC.PDD[2]) && Integer.parseInt(NPC.PDD[1]) > Integer.parseInt(NPC.PDD[0]))
		  {
			  if(Integer.parseInt(NPC.PDD[1]) > Integer.parseInt(NPC.PDD[0]))
			  {
				  NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
				  NPC.PD_w[2] = ""+NPC.PD[2][0] + " " + NPC.PD[2][1];
			  }
			  else
			  {
				  NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
				  NPC.PD_w[0] = ""+NPC.PD[0][0] + " " + NPC.PD[0][1];
			  }
		  }
		  if(Integer.parseInt(NPC.PDD[2]) > Integer.parseInt(NPC.PDD[0])&&Integer.parseInt(NPC.PDD[2]) > Integer.parseInt(NPC.PDD[1]))
		  {
			  if(Integer.parseInt(NPC.PDD[0]) > Integer.parseInt(NPC.PDD[1]))
			  {
				  NPC.PD_w[0] =""+ NPC.PD[0][0] + " " + NPC.PD[0][1];
				  NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
			  }
			  else
			  {
				  NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
				  NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
			  }
		  }
		  //큰수 뽑기.
		  
		  if(Integer.parseInt(NPC.PDD[0]) == Integer.parseInt(NPC.PDD[1]) && Integer.parseInt(NPC.PDD[1]) != Integer.parseInt(NPC.PDD[2]))
		  {
			  if(check_F[0] > check_F[1])
				  NPC.PD_w[0] =""+ NPC.PD[0][0] + " " + NPC.PD[0][1];
			  else
				  NPC.PD_w[1] = ""+NPC.PD[1][0] + " " + NPC.PD[1][1];
			  NPC.PD_w[2] = ""+NPC.PD[2][0] + " " + NPC.PD[2][1];
			  
		  }
		  if(Integer.parseInt(NPC.PDD[1]) == Integer.parseInt(NPC.PDD[2]) && Integer.parseInt(NPC.PDD[2]) != Integer.parseInt(NPC.PDD[0]))
		  {
			  if(check_F[1] > check_F[2])
				  NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
			  else
				  NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
			  
			  NPC.PD_w[0] = ""+NPC.PD[0][0] + " " + NPC.PD[0][1];
		  }
		  if(Integer.parseInt(NPC.PDD[2]) == Integer.parseInt(NPC.PDD[0]) && Integer.parseInt(NPC.PDD[0]) != Integer.parseInt(NPC.PDD[1]))
		  {
			  if(check_F[2] > check_F[0])
				  NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
			  else
				  NPC.PD_w[0] = ""+NPC.PD[0][0] + " " + NPC.PD[0][1];
			  
			  NPC.PD_w[1] = ""+NPC.PD[1][0] + " " + NPC.PD[1][1];
		 
		  }
		  if(Integer.parseInt(NPC.PDD[2]) == Integer.parseInt(NPC.PDD[0]) && Integer.parseInt(NPC.PDD[0]) == Integer.parseInt(NPC.PDD[1]))
		  {
			if(check_F[0] > check_F[1]& check_F[1] > check_F[2])
			{
				NPC.PD_w[0] =""+ NPC.PD[0][0] + " " + NPC.PD[0][1];
				NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
			}
			if(check_F[1] > check_F[2]& check_F[2] > check_F[0])
			{
				NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
				NPC.PD_w[1] =""+ NPC.PD[1][0] + " " + NPC.PD[1][1];
			}
			if(check_F[2] > check_F[0]& check_F[0] > check_F[1])
			{
				NPC.PD_w[0] =""+ NPC.PD[0][0] + " " + NPC.PD[0][1];
				NPC.PD_w[2] =""+ NPC.PD[2][0] + " " + NPC.PD[2][1];
			}
		  }
		  
		  	Cno_1.setEnabled(false);
			Cno_2.setEnabled(false);
			Cno_3.setEnabled(false);
	 }
	 public void halfB(Player Ch, Player Ch2){
		 
			 Ch.Bat_M = Table_M/2;
		
		  Table_M += Ch.Bat_M;
		  Ch.money -= Ch.Bat_M;
		  
		
		  
			  
	 }
	 
	 public void doubleB(Player Ch, Player Ch2){
		  Ch.Bat_M = Ch2.Bat_M*2;
		  Table_M += Ch.Bat_M;
		  Ch.money -= Ch.Bat_M;
		  
		  
	 }
	 
	 public void callB(Player Ch, Player Ch2){
		  Ch.Bat_M = Ch2.Bat_M;
		  Table_M += Ch.Bat_M;
		  Ch.money -= Ch.Bat_M;
		  
		  
	 }
	 
	 public void All_INB(Player Ch, Player Ch2){
		  if(Ch2.Bat_M>Ch.money)
	    		Ch.Bat_M = Ch.money;
		  
		 Table_M += Ch.Bat_M;
		 Ch.money -= Ch.Bat_M;
	    		
	    	
		  
	 }
	 
	 public void Fold(Player P1, Player P2){
		 Turn = 1;
		 P1.money += Table_M;
		 char[] TCC = new char[2];
		 TCC[0] = ' '; TCC[1] = ' ';
		 Turn = 0;
		 
		 card_D.Init();
		 
		 for(int i = 0; i<7; i++){
			 PC.PD[i] = TCC;
			 NPC.PD[i] = TCC;
				
			 PC.PD_w[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
			PC.PD_wa[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
			
			NPC.PD_w[i] = "" + NPC.PD[i][0] + " " + NPC.PD[i][1];
		 }
		 repaint();
		 for(int i=0;i<3;i++)
		 {
			 open_card[i] = false;
				PC.PD[i] = card_D.get_card();
				NPC.PD[i] = card_D.get_card();
				PC.PD_w[i] = "" + PC.PD[i][0] + " " + PC.PD[i][1];
				PC.PD_wa[i] = "  ";
			
				NPC.PD_w[i] = "  ";
		 }
		 Image im = getImage(getCodeBase(), getParameter("back_c"));
		 for(int i = 0; i<7; i++)
		 {
			 PC_card_II[i].setImage(im);
			
			 
		 }
		 
		 for(int i = 0; i<7; i++)
		 {
			 NPC_card_II[i].setImage(im);
			 
			 
		 }
		 for(int i = 0; i<7; i++)
		 {
			 PC_card_III[i].setImage(im);
			
			 
		 }
		 Cno_1.setEnabled(true);
		  Cno_2.setEnabled(true);
		  Cno_3.setEnabled(true);
		  Table_M = 0;
		 start_G();
	 }
	 
  public void paint(Graphics screen)
  { 
	 
	  super.paint(screen);
	  
		Graphics2D screen2D = (Graphics2D) screen;
		for(int i=0; i<7;i++)
		{
			
			//if(NPC.PD_w[i] == "  ");
				
				
			
				/*System.out.println(PC.PD_w[i]);
				System.out.println(PC.PD_wa[i]);
				System.out.println(NPC.PD[i][0] + " " + NPC.PD[i][1]);
				System.out.println(NPC.PD_w[i]);*/
				
				Image im;
				Image im2;
				Image im3;
				for(int k = 0; k <52; k++)
				{
					im = getImage(getCodeBase(), getParameter("back_c"));
					if(card_D.decks[k].equals(PC.PD_w[i])){
						String IS = new String();
						IS = card_D.decks[k];
						im = getImage(getCodeBase(), getParameter(IS));
						
					PC_card_II[i].setImage(im);
					break;
					}
					PC_card_II[i].setImage(im);
				}
				for(int k = 0; k <52; k++)
				{
					im3 = getImage(getCodeBase(), getParameter("back_c"));
					if(card_D.decks[k].equals(PC.PD_wa[i])){
						String IS = new String();
						IS = card_D.decks[k];
						im3 = getImage(getCodeBase(), getParameter(IS));
					PC_card_III[i].setImage(im3);
					break;
					}
					PC_card_III[i].setImage(im3);
				}
				for(int k = 0; k <52; k++)
				{
					im2 = getImage(getCodeBase(), getParameter("back_c"));
					if(card_D.decks[k].equals(NPC.PD_w[i])){
						String IS = new String();
						IS = card_D.decks[k];
						im2 = getImage(getCodeBase(), getParameter(IS));
						NPC_card_II[i].setImage(im2);
					
					break;
					}
					NPC_card_II[i].setImage(im2);
				}
			
		}
		
		String MTFs[] = new String[5];
		MTFs[0] = "" + NPC.money;
		MTFs[1] = "" + NPC.Bat_M;
		MTFs[2] = "" + Table_M;
		MTFs[3] = "" + PC.Bat_M;
		MTFs[4] = "" + PC.money;
		for(int i = 0; i< 5; i++)
		{
			MTF[i].setText(MTFs[i]);
		}
		 
  }
		



  public void itemStateChanged(ItemEvent e){
	  if(e.getStateChange() == ItemEvent.SELECTED)
	  {

	if(Cno_1.isSelected() == true)
		open_card[0] = true;
	if(Cno_2.isSelected() == true)
		open_card[1] = true;
	if(Cno_3.isSelected() == true)
		open_card[2] = true;
	if(Hidden_C.isSelected() == true)
		open_card[3] = true;
	
	if(Cno_1.isSelected() == false)
		open_card[0] = false;
	if(Cno_2.isSelected() == false)
		open_card[1] = false;
	if(Cno_3.isSelected() == false)
		open_card[2] = false;
	if(Hidden_C.isSelected() == false)
		open_card[3] = false;

		if(Cno_1.isSelected() == true&Cno_2.isSelected() == true)
		{
			Cno_3.setEnabled(false);
		}
		
		
		if(Cno_1.isSelected() == true&Cno_3.isSelected() == true)
		{
			Cno_2.setEnabled(false);
		}
		
		if(Cno_3.isSelected() == true&Cno_2.isSelected() == true)
		{
			Cno_1.setEnabled(false);
		}
		if((Cno_3.isSelected() == false & Cno_2.isSelected() == false) | (Cno_3.isSelected() == false & Cno_1.isSelected() == false) | (Cno_1.isSelected() == false & Cno_2.isSelected() == false))
		{
			Cno_1.setEnabled(true);
			Cno_2.setEnabled(true);
			Cno_3.setEnabled(true);
		}
		
	}
	 
  }
  
  public void actionPerformed(ActionEvent e)
  {
	  String typed = e.getActionCommand();
	  if(typed.equals("Start"))
	  {
		  showButton();
		  Start_G.hide();
		   repaint();
		 
		 start_G();
		repaint();
	  }
	  
	  if(typed.equals("Open card"))
	  {
		  open_CG();
		  
		  repaint();
	  }
	  if(typed.equals("Half"))
	  {
		  if(PC.money>0){
			  halfB(PC,NPC);
		  
			PC.Fake_late = 10;
	    	
	    	BAI();
		  }
		  Half.setEnabled(false);
		  Call.setEnabled(false);
		  Double.setEnabled(false);
		  Fold.setEnabled(false);
		  ALL_in.setEnabled(false);
		  NEXT.setEnabled(true);
		  continue_G();
	    repaint();
	  }
	  if(typed.equals("Call"))
	  {
		  if(PC.money>0){
			 callB(PC,NPC);
			 PC.Fake_late = 50;
			 
			 BAI();
		  }
		  Half.setEnabled(false);
		  Call.setEnabled(false);
		  Double.setEnabled(false);
		  Fold.setEnabled(false);
		  ALL_in.setEnabled(false);
		  NEXT.setEnabled(true);
		  continue_G();
	    repaint();
	  }
	  if(typed.equals("Double"))
	  {
		 if(PC.money>0){
			  doubleB(PC,NPC);
			  PC.Fake_late = 100;
			
			  
		  }
		BAI();
		Half.setEnabled(false);
			  Call.setEnabled(false);
			  Double.setEnabled(false);
			  Fold.setEnabled(false);
			  ALL_in.setEnabled(false);
			  NEXT.setEnabled(true);
		  continue_G();
	    repaint();
	  } 
	  if(typed.equals("All In"))
	    {

	    	All_INB(PC,NPC);
	    	Half.setEnabled(false);
			  Call.setEnabled(false);
			  Double.setEnabled(false);
			  Fold.setEnabled(false);
			  ALL_in.setEnabled(false);
			  NEXT.setEnabled(true);
	    	repaint();
	    }
	  if(typed.equals("Fold"))
	    {
		  Fold(NPC,PC);
		  
		  Half.setEnabled(false);
		  Call.setEnabled(false);
		  Double.setEnabled(false);
		  Fold.setEnabled(false);
		  ALL_in.setEnabled(false);
		  NEXT.setEnabled(true);
		  continue_G();
		  repaint();
	    }
	  if(typed.equals("NEXT"))
	  {
		  Half.setEnabled(true);
		  Call.setEnabled(true);
		  Double.setEnabled(true);
		  Fold.setEnabled(true);
		  ALL_in.setEnabled(true);
		  //NEXT.setEnabled(false);
		  
		  if(Turn>5 | Turn ==0)
		  {
			  Cno_1.setEnabled(true);
			  Cno_2.setEnabled(true);
			  Cno_3.setEnabled(true);
			  
		  }
		  
		  repaint();
	  }
	  
	
  }
  public void destroy(){
  
  }


	
} 
