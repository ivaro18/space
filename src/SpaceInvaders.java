import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;

public class SpaceInvaders extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel bg;
	private JButton start, reset;
	private int xcora = 255, xcorb = 290, gestart = 0, telr=0, tell=0, ycorb=530, ball=0;
	private boolean links=true, geschoten=false;
	//ALIENS
	private boolean a1=true, a2=true, a3=true, a4=true, a5=true, a6=true, a7=true, a8=true, a9=true, a10=true, a11=true, a12=true, a13=true, a14=true, a15=true;
	private int xa1=20,xa2=100,xa3=180,xa4=260, xa5=340,xa6=20,xa7=100,xa8=180,xa9=260,xa10=340,xa11=20,xa12=100,xa13=180,xa14=260,xa15=340;
	private int ya1=20,ya2=20,ya3=20,ya4=20, ya5=20,ya6=100,ya7=100,ya8=100,ya9=100,ya10=100,ya11=180,ya12=180,ya13=180,ya14=180,ya15=180;
	private Timer alienright, alienleft, shoot, line;
	
	public SpaceInvaders(){
		//MAKING FRAME
		setLayout(new FlowLayout());
		setSize(600,700);
		setFocusable(true);
		setResizable(false);
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//BUTTONS AND SHIT
		bg = new JPanel(); add(bg); bg.setPreferredSize(new Dimension(580,600)); bg.setBackground(Color.BLACK); bg.setFocusable(true); 
		start = new JButton("Start"); add(start); start.addActionListener(this);
		reset = new JButton("Instant Win"); add(reset); reset.addActionListener(this); reset.setEnabled(false);
		
		//TIMERS
		alienright = new Timer(200, this);
		alienleft = new Timer(200, this);
		shoot = new Timer(10, this);
		line = new  Timer(1, this);
		
		setVisible(true);
	}
	
	   public void playSong(){
	       try {
	         URL url = new URL("File:eyeOfTheTiger2.wav");
	         AudioClip ac = Applet.newAudioClip(url);
	         ac.loop();
	       } catch (Exception e) {
	         System.out.println(e);
	       }
	    }
	
	public void check(){
		if(a1||a2||a3||a4||a5){
			if(ya1>460||ya2>460||ya3>460||ya4>460||ya5>460){
				start.setEnabled(false);
				reset.setEnabled(false);
				shoot.stop();
				alienright.stop();
				alienleft.stop();
				JOptionPane.showMessageDialog(null, "GAME OVER");
			}
			if(a6||a7||a8||a9||a10){
				if(ya6>460||ya7>460||ya8>460||ya9>460||ya10>460){
					start.setEnabled(false);
					reset.setEnabled(false);
					shoot.stop();
					alienright.stop();
					alienleft.stop();
					JOptionPane.showMessageDialog(null, "GAME OVER");	
				}
			}
				if(a11==true||a12==true||a13==true||a14==true||a15==true){
					if(ya11>460||ya12>460||ya13>460||ya14>460||ya15>460){
						start.setEnabled(false);
						reset.setEnabled(false);
						shoot.stop();
						alienright.stop();
						alienleft.stop();
						JOptionPane.showMessageDialog(null, "GAME OVER");	
					}
				}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==shoot){
			drawBullet();
		}
		
		if(e.getSource()==line){
			Graphics g = bg.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0,500,580,5);
			drawCannon();
		}
		
		if(e.getSource()==start){
			drawCannon();
			requestFocus();
			gestart +=1;
			drawAlien();
			alienright.start();
			start.setEnabled(false);
			reset.setEnabled(true);
			line.start();
			playSong();
		}
		
		if(e.getSource()==reset){
			requestFocus();
			JOptionPane.showMessageDialog(null, "Cheater!");
			System.exit(0);
		}
		
		if(e.getSource()==alienright){
			xa1+=15;xa2+=15;xa3+=15;xa4+=15;xa5+=15;xa6+=15;xa7+=15;xa8+=15;xa9+=15;xa10+=15;xa11+=15;xa12+=15;xa13+=15;xa14+=15;xa15+=15;
			Graphics g = bg.getGraphics();
			drawAlien();
			telr++;
			if(telr==12){
			alienright.stop();
			ya1+=30;
			ya2+=30;ya3+=30;ya4+=30;ya5+=30;ya6+=30;ya7+=30;ya8+=30;ya9+=30;ya10+=30;ya11+=30;ya12+=30;ya13+=30;ya14+=30;ya15+=30;
			telr-=12;
			alienleft.start();
			links=false;
			g.fillRect(xa1-25, ya1-30, 400, 200);
			}
			check();
		}
		
		if(e.getSource()==alienleft){
			xa1-=15;xa2-=15;xa3-=15;xa4-=15;xa5-=15;xa6-=15;xa7-=15;xa8-=15;xa9-=15;xa10-=15;xa11-=15;xa12-=15;xa13-=15;xa14-=15;xa15-=15;
			Graphics g = bg.getGraphics();
			drawAlien();
			tell++;
			if(tell==12){
				alienleft.stop();
				ya1+=30;
				ya2+=30; ya3+=30;ya4+=30;ya5+=30;ya6+=30;ya7+=30;ya8+=30;ya9+=30;ya10+=30;ya11+=30;ya12+=30;ya13+=30;ya14+=30;ya15+=30;
				tell-=12;
				links=true;
				alienright.start();
				g.fillRect(xa1-25, ya1-30, 400, 200);
			}
			check();
		}
	}
	
	public void drawCannon() {
		Graphics g = bg.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 540, 580, 45);
		g.setColor(Color.GREEN);
		g.fillRect(xcora, 550, 80, 35);
		g.fillRect(xcora-5, 555, 5, 30);
		g.fillRect(xcora-10, 560, 5, 25);
		g.fillRect(xcora+80, 555, 5, 30);
		g.fillRect(xcora+85, 560, 5, 25);
		g.fillRect(xcorb, 540, 10, 10);
		g.fillRect(xcorb-5, 545, 5, 5);
		g.fillRect(xcorb+10, 545, 5, 5);
	}
	
	public void drawAlien(){
		Graphics g = bg.getGraphics();
		g.setColor(Color.WHITE);
		if(a1==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa1-25, ya1, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa1+10, ya1, 60, 40);
			}
			
			g.setColor(Color.WHITE);
			g.fillRect(xa1, ya1, 5, 5);
			g.fillRect(xa1 + 5, ya1 + 5, 5, 5);
			g.fillRect(xa1, ya1 + 10, 35, 5);
			g.fillRect(xa1 + 30, ya1, 5, 5);
			g.fillRect(xa1 + 25, ya1 + 5, 5, 5);
			g.fillRect(xa1 - 5, ya1 + 15, 10, 5);
			g.fillRect(xa1 + 10, ya1 + 15, 15, 5);
			g.fillRect(xa1 + 30, ya1 + 15, 10, 5);
			g.fillRect(xa1 - 10, ya1 + 20, 55, 5);
			g.fillRect(xa1 - 10, ya1 + 25, 5, 10);
			g.fillRect(xa1 + 40, ya1 + 25, 5, 10);
			g.fillRect(xa1, ya1 + 25, 35, 5);
			g.fillRect(xa1, ya1 + 30, 5, 5);
			g.fillRect(xa1 + 30, ya1 + 30, 5, 5);
			g.fillRect(xa1 + 5, ya1 + 35, 10, 5);
			g.fillRect(xa1 + 20, ya1 + 35, 10, 5);
			
		}
		if(a2==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa2-25, ya2, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa2+10, ya2, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa2, ya2, 5, 5);
			g.fillRect(xa2 + 5, ya2 + 5, 5, 5);
			g.fillRect(xa2, ya2 + 10, 35, 5);
			g.fillRect(xa2 + 30, ya2, 5, 5);
			g.fillRect(xa2 + 25, ya2 + 5, 5, 5);
			g.fillRect(xa2 - 5, ya2 + 15, 10, 5);
			g.fillRect(xa2 + 10, ya2 + 15, 15, 5);
			g.fillRect(xa2 + 30, ya2 + 15, 10, 5);
			g.fillRect(xa2 - 10, ya2 + 20, 55, 5);
			g.fillRect(xa2 - 10, ya2 + 25, 5, 10);
			g.fillRect(xa2 + 40, ya2 + 25, 5, 10);
			g.fillRect(xa2, ya2 + 25, 35, 5);
			g.fillRect(xa2, ya2 + 30, 5, 5);
			g.fillRect(xa2 + 30, ya2 + 30, 5, 5);
			g.fillRect(xa2 + 5, ya2 + 35, 10, 5);
			g.fillRect(xa2 + 20, ya2 + 35, 10, 5);
		}
		if(a3==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa3-25, ya3, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa3+10, ya3, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa3, ya3, 5, 5);
			g.fillRect(xa3 + 5, ya3 + 5, 5, 5);
			g.fillRect(xa3, ya3 + 10, 35, 5);
			g.fillRect(xa3 + 30, ya3, 5, 5);
			g.fillRect(xa3 + 25, ya3 + 5, 5, 5);
			g.fillRect(xa3 - 5, ya3 + 15, 10, 5);
			g.fillRect(xa3 + 10, ya3 + 15, 15, 5);
			g.fillRect(xa3 + 30, ya3 + 15, 10, 5);
			g.fillRect(xa3 - 10, ya3 + 20, 55, 5);
			g.fillRect(xa3 - 10, ya3 + 25, 5, 10);
			g.fillRect(xa3 + 40, ya3 + 25, 5, 10);
			g.fillRect(xa3, ya3 + 25, 35, 5);
			g.fillRect(xa3, ya3 + 30, 5, 5);
			g.fillRect(xa3 + 30, ya3 + 30, 5, 5);
			g.fillRect(xa3 + 5, ya3 + 35, 10, 5);
			g.fillRect(xa3 + 20, ya3 + 35, 10, 5);
		}
		if(a4==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa4-25, ya4, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa4+10, ya4, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa4, ya4, 5, 5);
			g.fillRect(xa4 + 5, ya4 + 5, 5, 5);
			g.fillRect(xa4, ya4 + 10, 35, 5);
			g.fillRect(xa4 + 30, ya4, 5, 5);
			g.fillRect(xa4 + 25, ya4 + 5, 5, 5);
			g.fillRect(xa4 - 5, ya4 + 15, 10, 5);
			g.fillRect(xa4 + 10, ya4 + 15, 15, 5);
			g.fillRect(xa4 + 30, ya4 + 15, 10, 5);
			g.fillRect(xa4 - 10, ya4 + 20, 55, 5);
			g.fillRect(xa4 - 10, ya4 + 25, 5, 10);
			g.fillRect(xa4 + 40, ya4 + 25, 5, 10);
			g.fillRect(xa4, ya4 + 25, 35, 5);
			g.fillRect(xa4, ya4 + 30, 5, 5);
			g.fillRect(xa4 + 30, ya4 + 30, 5, 5);
			g.fillRect(xa4 + 5, ya4 + 35, 10, 5);
			g.fillRect(xa4 + 20, ya4 + 35, 10, 5);
		}
		if(a5==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa5-25, ya5, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa5+10, ya5, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa5, ya5, 5, 5);
			g.fillRect(xa5 + 5, ya5 + 5, 5, 5);
			g.fillRect(xa5, ya5 + 10, 35, 5);
			g.fillRect(xa5 + 30, ya5, 5, 5);
			g.fillRect(xa5 + 25, ya5 + 5, 5, 5);
			g.fillRect(xa5 - 5, ya5 + 15, 10, 5);
			g.fillRect(xa5 + 10, ya5 + 15, 15, 5);
			g.fillRect(xa5 + 30, ya5 + 15, 10, 5);
			g.fillRect(xa5 - 10, ya5 + 20, 55, 5);
			g.fillRect(xa5 - 10, ya5 + 25, 5, 10);
			g.fillRect(xa5 + 40, ya5 + 25, 5, 10);
			g.fillRect(xa5, ya5 + 25, 35, 5);
			g.fillRect(xa5, ya5 + 30, 5, 5);
			g.fillRect(xa5 + 30, ya5 + 30, 5, 5);
			g.fillRect(xa5 + 5, ya5 + 35, 10, 5);
			g.fillRect(xa5 + 20, ya5 + 35, 10, 5);
		}
		if(a6==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa6-25, ya6, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa6+10, ya6, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa6, ya6, 5, 5);
			g.fillRect(xa6 + 5, ya6 + 5, 5, 5);
			g.fillRect(xa6, ya6 + 10, 35, 5);
			g.fillRect(xa6 + 30, ya6, 5, 5);
			g.fillRect(xa6 + 25, ya6 + 5, 5, 5);
			g.fillRect(xa6 - 5, ya6 + 15, 10, 5);
			g.fillRect(xa6 + 10, ya6 + 15, 15, 5);
			g.fillRect(xa6 + 30, ya6 + 15, 10, 5);
			g.fillRect(xa6 - 10, ya6 + 20, 55, 5);
			g.fillRect(xa6 - 10, ya6 + 25, 5, 10);
			g.fillRect(xa6 + 40, ya6 + 25, 5, 10);
			g.fillRect(xa6, ya6 + 25, 35, 5);
			g.fillRect(xa6, ya6 + 30, 5, 5);
			g.fillRect(xa6 + 30, ya6 + 30, 5, 5);
			g.fillRect(xa6 + 5, ya6 + 35, 10, 5);
			g.fillRect(xa6 + 20, ya6 + 35, 10, 5);
		}
		if(a7==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa7-25, ya7, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa7+10, ya7, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa7, ya7, 5, 5);
			g.fillRect(xa7 + 5, ya7 + 5, 5, 5);
			g.fillRect(xa7, ya7 + 10, 35, 5);
			g.fillRect(xa7 + 30, ya7, 5, 5);
			g.fillRect(xa7 + 25, ya7 + 5, 5, 5);
			g.fillRect(xa7 - 5, ya7 + 15, 10, 5);
			g.fillRect(xa7 + 10, ya7 + 15, 15, 5);
			g.fillRect(xa7 + 30, ya7 + 15, 10, 5);
			g.fillRect(xa7 - 10, ya7 + 20, 55, 5);
			g.fillRect(xa7 - 10, ya7 + 25, 5, 10);
			g.fillRect(xa7 + 40, ya7 + 25, 5, 10);
			g.fillRect(xa7, ya7 + 25, 35, 5);
			g.fillRect(xa7, ya7 + 30, 5, 5);
			g.fillRect(xa7 + 30, ya7 + 30, 5, 5);
			g.fillRect(xa7 + 5, ya7 + 35, 10, 5);
			g.fillRect(xa7 + 20, ya7 + 35, 10, 5);
		}
		if(a8==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa8-25, ya8, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa8+10, ya8, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa8, ya8, 5, 5);
			g.fillRect(xa8 + 5, ya8 + 5, 5, 5);
			g.fillRect(xa8, ya8 + 10, 35, 5);
			g.fillRect(xa8 + 30, ya8, 5, 5);
			g.fillRect(xa8 + 25, ya8 + 5, 5, 5);
			g.fillRect(xa8 - 5, ya8 + 15, 10, 5);
			g.fillRect(xa8 + 10, ya8 + 15, 15, 5);
			g.fillRect(xa8 + 30, ya8 + 15, 10, 5);
			g.fillRect(xa8 - 10, ya8 + 20, 55, 5);
			g.fillRect(xa8 - 10, ya8 + 25, 5, 10);
			g.fillRect(xa8 + 40, ya8 + 25, 5, 10);
			g.fillRect(xa8, ya8 + 25, 35, 5);
			g.fillRect(xa8, ya8 + 30, 5, 5);
			g.fillRect(xa8 + 30, ya8 + 30, 5, 5);
			g.fillRect(xa8 + 5, ya8 + 35, 10, 5);
			g.fillRect(xa8 + 20, ya8 + 35, 10, 5);
		}
		if(a9==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa9-25, ya9, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa9+10, ya9, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa9, ya9, 5, 5);
			g.fillRect(xa9 + 5, ya9 + 5, 5, 5);
			g.fillRect(xa9, ya9 + 10, 35, 5);
			g.fillRect(xa9 + 30, ya9, 5, 5);
			g.fillRect(xa9 + 25, ya9 + 5, 5, 5);
			g.fillRect(xa9 - 5, ya9 + 15, 10, 5);
			g.fillRect(xa9 + 10, ya9 + 15, 15, 5);
			g.fillRect(xa9 + 30, ya9 + 15, 10, 5);
			g.fillRect(xa9 - 10, ya9 + 20, 55, 5);
			g.fillRect(xa9 - 10, ya9 + 25, 5, 10);
			g.fillRect(xa9 + 40, ya9 + 25, 5, 10);
			g.fillRect(xa9, ya9 + 25, 35, 5);
			g.fillRect(xa9, ya9 + 30, 5, 5);
			g.fillRect(xa9 + 30, ya9 + 30, 5, 5);
			g.fillRect(xa9 + 5, ya9 + 35, 10, 5);
			g.fillRect(xa9 + 20, ya9 + 35, 10, 5);
		}
		if(a10==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa10-25, ya10, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa10+10, ya10, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa10, ya10, 5, 5);
			g.fillRect(xa10 + 5, ya10 + 5, 5, 5);
			g.fillRect(xa10, ya10 + 10, 35, 5);
			g.fillRect(xa10 + 30, ya10, 5, 5);
			g.fillRect(xa10 + 25, ya10 + 5, 5, 5);
			g.fillRect(xa10 - 5, ya10 + 15, 10, 5);
			g.fillRect(xa10 + 10, ya10 + 15, 15, 5);
			g.fillRect(xa10 + 30, ya10 + 15, 10, 5);
			g.fillRect(xa10 - 10, ya10 + 20, 55, 5);
			g.fillRect(xa10 - 10, ya10 + 25, 5, 10);
			g.fillRect(xa10 + 40, ya10 + 25, 5, 10);
			g.fillRect(xa10, ya10 + 25, 35, 5);
			g.fillRect(xa10, ya10 + 30, 5, 5);
			g.fillRect(xa10 + 30, ya10 + 30, 5, 5);
			g.fillRect(xa10 + 5, ya10 + 35, 10, 5);
			g.fillRect(xa10 + 20, ya10 + 35, 10, 5);
		}
		if (a11 == true) {

			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa11-25, ya11, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa11+10, ya11, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa11, ya11, 5, 5);
			g.fillRect(xa11 + 5, ya11 + 5, 5, 5);
			g.fillRect(xa11, ya11 + 10, 35, 5);
			g.fillRect(xa11 + 30, ya11, 5, 5);
			g.fillRect(xa11 + 25, ya11 + 5, 5, 5);
			g.fillRect(xa11 - 5, ya11 + 15, 10, 5);
			g.fillRect(xa11 + 10, ya11 + 15, 15, 5);
			g.fillRect(xa11 + 30, ya11 + 15, 10, 5);
			g.fillRect(xa11 - 10, ya11 + 20, 55, 5);
			g.fillRect(xa11 - 10, ya11 + 25, 5, 10);
			g.fillRect(xa11 + 40, ya11 + 25, 5, 10);
			g.fillRect(xa11, ya11 + 25, 35, 5);
			g.fillRect(xa11, ya11 + 30, 5, 5);
			g.fillRect(xa11 + 30, ya11 + 30, 5, 5);
			g.fillRect(xa11 + 5, ya11 + 35, 10, 5);
			g.fillRect(xa11 + 20, ya11 + 35, 10, 5);
		}
		if(a12==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa12-25, ya12, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa12+10, ya12, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa12, ya12, 5, 5);
			g.fillRect(xa12 + 5, ya12 + 5, 5, 5);
			g.fillRect(xa12, ya12 + 10, 35, 5);
			g.fillRect(xa12 + 30, ya12, 5, 5);
			g.fillRect(xa12 + 25, ya12 + 5, 5, 5);
			g.fillRect(xa12 - 5, ya12 + 15, 10, 5);
			g.fillRect(xa12 + 10, ya12 + 15, 15, 5);
			g.fillRect(xa12 + 30, ya12 + 15, 10, 5);
			g.fillRect(xa12 - 10, ya12 + 20, 55, 5);
			g.fillRect(xa12 - 10, ya12 + 25, 5, 10);
			g.fillRect(xa12 + 40, ya12 + 25, 5, 10);
			g.fillRect(xa12, ya12 + 25, 35, 5);
			g.fillRect(xa12, ya12 + 30, 5, 5);
			g.fillRect(xa12 + 30, ya12 + 30, 5, 5);
			g.fillRect(xa12 + 5, ya12 + 35, 10, 5);
			g.fillRect(xa12 + 20, ya12 + 35, 10, 5);
		}
		if(a13==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa13-25, ya13, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa13+10, ya13, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa13, ya13, 5, 5);
			g.fillRect(xa13 + 5, ya13 + 5, 5, 5);
			g.fillRect(xa13, ya13 + 10, 35, 5);
			g.fillRect(xa13 + 30, ya13, 5, 5);
			g.fillRect(xa13 + 25, ya13 + 5, 5, 5);
			g.fillRect(xa13 - 5, ya13 + 15, 10, 5);
			g.fillRect(xa13 + 10, ya13 + 15, 15, 5);
			g.fillRect(xa13 + 30, ya13 + 15, 10, 5);
			g.fillRect(xa13 - 10, ya13 + 20, 55, 5);
			g.fillRect(xa13 - 10, ya13 + 25, 5, 10);
			g.fillRect(xa13 + 40, ya13 + 25, 5, 10);
			g.fillRect(xa13, ya13 + 25, 35, 5);
			g.fillRect(xa13, ya13 + 30, 5, 5);
			g.fillRect(xa13 + 30, ya13 + 30, 5, 5);
			g.fillRect(xa13 + 5, ya13 + 35, 10, 5);
			g.fillRect(xa13 + 20, ya13 + 35, 10, 5);
		}
		if(a14==true){
			if(links==true){
				g.setColor(Color.BLACK);
			g.fillRect(xa14-25, ya14, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa14+10, ya14, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa14, ya14, 5, 5);
			g.fillRect(xa14 + 5, ya14 + 5, 5, 5);
			g.fillRect(xa14, ya14 + 10, 35, 5);
			g.fillRect(xa14 + 30, ya14, 5, 5);
			g.fillRect(xa14 + 25, ya14 + 5, 5, 5);
			g.fillRect(xa14 - 5, ya14 + 15, 10, 5);
			g.fillRect(xa14 + 10, ya14 + 15, 15, 5);
			g.fillRect(xa14 + 30, ya14 + 15, 10, 5);
			g.fillRect(xa14 - 10, ya14 + 20, 55, 5);
			g.fillRect(xa14 - 10, ya14 + 25, 5, 10);
			g.fillRect(xa14 + 40, ya14 + 25, 5, 10);
			g.fillRect(xa14, ya14 + 25, 35, 5);
			g.fillRect(xa14, ya14 + 30, 5, 5);
			g.fillRect(xa14 + 30, ya14 + 30, 5, 5);
			g.fillRect(xa14 + 5, ya14 + 35, 10, 5);
			g.fillRect(xa14 + 20, ya14 + 35, 10, 5);
		}
		if(a15==true){
			if(links==true){
				g.setColor(Color.BLACK);
				g.fillRect(xa15-25, ya15, 60, 40);
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(xa15+10, ya15, 60, 40);
			}
			g.setColor(Color.WHITE);
			g.fillRect(xa15, ya15, 5, 5);
			g.fillRect(xa15 + 5, ya15 + 5, 5, 5);
			g.fillRect(xa15, ya15 + 10, 35, 5);
			g.fillRect(xa15 + 30, ya15, 5, 5);
			g.fillRect(xa15 + 25, ya15 + 5, 5, 5);
			g.fillRect(xa15 - 5, ya15 + 15, 10, 5);
			g.fillRect(xa15 + 10, ya15 + 15, 15, 5);
			g.fillRect(xa15 + 30, ya15 + 15, 10, 5);
			g.fillRect(xa15 - 10, ya15 + 20, 55, 5);
			g.fillRect(xa15 - 10, ya15 + 25, 5, 10);
			g.fillRect(xa15 + 40, ya15 + 25, 5, 10);
			g.fillRect(xa15, ya15 + 25, 35, 5);
			g.fillRect(xa15, ya15 + 30, 5, 5);
			g.fillRect(xa15 + 30, ya15 + 30, 5, 5);
			g.fillRect(xa15 + 5, ya15 + 35, 10, 5);
			g.fillRect(xa15 + 20, ya15 + 35, 10, 5);
		}
	}

	public void drawBullet(){
		if(a1||a2||a3||a4||a5||a6||a7||a8||a9||a10||a11|a12||a13||a14||a15){
			if (ball >= xa1 -10&& ball < xa1 + 55 && ycorb == ya1 + 40
					&& a1 == true) {
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa1, ya1, 60, 45);
				shoot.stop();
				a1 = false;
				geschoten = false;
			}
			if (ball >= xa2 -10&& ball < xa2 + 55 && ycorb == ya2 + 40
					&& a2 == true) {
				shoot.stop();
				a2 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa2, ya2, 60, 45);
			}
			if (ball >= xa3 -10&& ball < xa3 + 55 && ycorb == ya3 + 40
					&& a3 == true) {
				shoot.stop();
				a3 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa3 - 10, ya3, 60, 45);
			}
			if (ball >= xa4 -10&& ball < xa4 + 55 && ycorb == ya4 + 40
					&& a4 == true) {
				shoot.stop();
				a4 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa4 - 10, ya4, 60, 45);
			}
			if (ball >= xa5 -10 && ball < xa5 + 55 && ycorb == ya5 + 40
					&& a5 == true) {
				shoot.stop();
				a5 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa5 - 10, ya5, 60, 45);
			}
			if (ball >= xa6-10 && ball < xa6 + 55 && ycorb == ya6 + 40
					&& a6 == true) {
				shoot.stop();
				a6 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa6 - 10, ya6, 60, 45);
			}
			if (ball >= xa7 -10 && ball < xa7 + 55 && ycorb == ya7 + 40
					&& a7 == true) {
				shoot.stop();
				a7 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa7 - 10, ya7, 60, 45);
			}
			if (ball >= xa8 -10 && ball < xa8 + 55 && ycorb == ya8 + 40
					&& a8 == true) {
				shoot.stop();
				a8 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa8 - 10, ya8, 60, 45);
			}
			if (ball >= xa9 -10 && ball < xa9 + 55 && ycorb == ya9 + 40
					&& a9 == true) {
				shoot.stop();
				a9 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa9 - 10, ya9, 60, 45);
			}
			if (ball >= xa10 -10 && ball < xa10 + 55 && ycorb == ya10 + 40
					&& a10 == true) {
				shoot.stop();
				a10 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa10 - 10, ya10, 60, 45);
			}
			if (ball >= xa11 -10 && ball < xa11 + 55 && ycorb == ya11 + 40
					&& a11 == true) {
				shoot.stop();
				a11 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa11 - 10, ya11, 60, 45);
			}
			if (ball >= xa12 -10 && ball < xa12 + 55 && ycorb == ya12 + 40
					&& a12 == true) {
				shoot.stop();
				a12 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa12 - 10, ya12, 60, 45);
			}
			if (ball >= xa13 -10 && ball < xa13 + 55 && ycorb == ya13 + 40
					&& a13 == true) {
				shoot.stop();
				a13 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa13 - 10, ya13, 60, 45);
			}
			if (ball >= xa14 -10 && ball < xa14 + 55 && ycorb == ya14 + 40
					&& a14 == true) {
				shoot.stop();
				a14 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa14 - 10, ya14, 60, 45);
			}
			if (ball >= xa15  -10 && ball < xa15 + 55 && ycorb == ya15 + 40
					&& a15 == true) {
				shoot.stop();
				a15 = false;
				geschoten = false;
				Graphics g = bg.getGraphics();
				g.setColor(Color.BLACK);
				g.fillRect(xa15 - 10, ya15, 60, 45);
			}
			if (ycorb >= ya1 - 20) {
				Graphics g = bg.getGraphics();
				ycorb -= 10;
				g.setColor(Color.GREEN);
				g.fillOval(ball, ycorb, 10, 10);
				g.setColor(Color.BLACK);
				g.fillOval(ball, ycorb + 10, 10, 10);
			} else {
				Graphics g = bg.getGraphics();
				shoot.stop();
				geschoten = false;
				g.setColor(Color.BLACK);
				g.fillOval(ball, ycorb, 10, 10);
			}
		}else{
			start.setEnabled(false);
			reset.setEnabled(false);
			shoot.stop();
			alienright.stop();
			alienleft.stop();
			
			Graphics g = bg.getGraphics();
			g.fillRect(0, 0, 580, 600);
			g.setColor(Color.GREEN);
			g.fillRect(90,20,380,20);
			g.fillRect(90,20,20,560);
			g.fillRect(90, 560, 380, 20);
			g.fillRect(470, 20, 20, 560);
			g.setColor(Color.WHITE);
			//Y
			g.fillRect(130,60,20,40);
			g.fillRect(140, 100, 40, 10);
			g.fillRect(170,60,20,40);
			g.fillRect(150,110,20,40);
			//O
			g.fillRect(240, 70, 20, 70);
			g.fillRect(250, 60, 60, 10);
			g.fillRect(300,70,20,70);
			g.fillRect(250, 140, 60, 10);
			//U
			g.fillRect(360,60,20,80);
			g.fillRect(370, 140, 50, 10);
			g.fillRect(410, 60, 20, 80);
			//SPACE INVADER
			g.fillRect(220,180,20,20);
			g.fillRect(240,200,20,20);
			g.fillRect(220,220,140,20);
			g.fillRect(320,200,20,20);
			g.fillRect(340,180,20,20);
			g.fillRect(200,240,40,20);
			g.fillRect(260,240,60,20);
			g.fillRect(340,240,40,20);
			g.fillRect(180, 260, 220, 20);
			g.fillRect(180,280,20,40);
			g.fillRect(220,280,140,20);
			g.fillRect(380,280,20,40);
			g.fillRect(220,300,20,20);
			g.fillRect(240,220,40,20);
			g.fillRect(340,300,20,20);	
			g.fillRect(240,320,40,20);
			g.fillRect(300, 320, 40, 20);
			//W
			g.fillRect(150, 400, 20, 80);
			g.fillRect(170,455,10,15);
			g.fillRect(180,445,10,15);
			g.fillRect(190,455,10,15);
			g.fillRect(200,400,20,80);
			//I
			g.fillRect(280,400,20,80);
			g.fillRect(270,400,20,10);
			g.fillRect(290,400,20,10);
			g.fillRect(270,470,20,10);
			g.fillRect(290,470,20,10);
			//N
			g.fillRect(360,400,20,80);
			g.fillRect(380,415,10,30);
			g.fillRect(390,425,10,30);
			g.fillRect(400,435,10,30);
			g.fillRect(410,400,20,80);
			JOptionPane.showMessageDialog(null, "Gefeliciteerd!");
		}
		
		
		
	}
	
	public void keyPressed(KeyEvent a) {
		int d = a.getKeyCode();
		if(d==KeyEvent.VK_SPACE && gestart==1){
			if(geschoten == false){
				shoot.start();
				ycorb=530;
				ball = xcorb;
				geschoten = true;
			}
			
		}
		
		if(d==KeyEvent.VK_RIGHT && gestart==1){
			if(xcora<=480){
				xcora+=10;
				xcorb+=10;
				drawCannon();
				requestFocus();
			}
			
		}
		
		if(d==KeyEvent.VK_LEFT && gestart==1){
			if(xcora>=20){
				xcora -= 10;
				xcorb -= 10;
				drawCannon();
				requestFocus();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
}
