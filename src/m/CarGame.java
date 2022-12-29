package m;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CarGame extends JFrame implements KeyListener,ActionListener
{

	private int xpos=300;
	private int ypos=720;
	private ImageIcon car,b;
	private Timer timer;
	Random random=new Random();
	
	private int num1=400,num2=0,num3=0;
	
	private int carxpos[]={100,200,300,400,500}; // for the distance between cars in X-axiz
	private int carypos[]= {-240,-480,-720,-960,-1200};//for the distance between cars in Y-axiz
	private int cxpos1=0,cxpos2=2,cxpos3=4;
        private int l_score=150;
	private int cypos1=random.nextInt(5),cypos2=random.nextInt(5),cypos3=random.nextInt(5);
	int y1pos=carypos[cypos1],y2pos=carypos[cypos2],y3pos=carypos[cypos3];
	private ImageIcon car1,car2,car3;
        
	private int score=0,delay=100,speed=90;
        double time=0;
	
	private boolean rightrotate=false,gameover=false,paint=false,start=false;
	public CarGame(String title) // for visible the project
	{
		super(title);
                
		setBounds(300,10,700,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addKeyListener(this);
		setFocusable(true);
		setResizable(false);
                
		start = true;
	
	
		
	}
	public void paint(Graphics gl) 
	{       
                b=new ImageIcon("./assets/road_34.jpg");// background of project
                b.paintIcon(this, gl, 0, 0);
		car=new ImageIcon("./assets/car1.png");
		car.paintIcon(this,gl,xpos,ypos);
		ypos-=40;
		if(ypos<450)
		{
		ypos=450;	
		}

		// opposite cars
		car1=new ImageIcon("./assets/car2.png");
		car2=new ImageIcon("./assets/car3.png");
		car3=new ImageIcon("./assets/car4.png");
		
		car1.paintIcon(this, gl, carxpos[cxpos1], y1pos);
		car2.paintIcon(this, gl, carxpos[cxpos2], y2pos);
		car3.paintIcon(this, gl, carxpos[cxpos3], y3pos);
		y1pos+=50;
		y2pos+=50;
		y3pos+=50;
                
		if(y1pos>700)
		{
			cxpos1=random.nextInt(5);
			cypos1=random.nextInt(5);
			y1pos=carypos[cypos1];
				
		}
		if(y2pos>700)
		{
			cxpos2++;
			if(cxpos2>4)
			{
				cxpos2=0;
			}
			
			cxpos2=random.nextInt(5);
			cypos2=random.nextInt(5);
			y2pos=carypos[cypos2];
			
		}
		if(y3pos>700)
		{
			cxpos3++;
			if(cxpos3>4)
			{
				cxpos3=0;
			}
			cxpos3=random.nextInt(5);
			cypos3=random.nextInt(5);
			y3pos=carypos[cypos3];
		}
	
		if(cxpos1==cxpos2 && cypos1>-100 && cypos2>-100)
		{
			
			
			cxpos1-=1;
			if(cxpos1<0)
			{
				cxpos1+=2;
			}
		}
		if(cxpos1==cxpos3&& cypos1>-100 && cypos3>-100)
		{
			cxpos3-=1;
			if(cxpos3<0)
			{
				cxpos3+=2;
			}
		}
		if(cxpos2==cxpos3&& cypos3>-100 && cypos2>-100)
		{
			cxpos2-=1;
			if(cxpos2<0)
			{
				cxpos2+=2;
			}
		}
		if(cxpos1<2 && cxpos2<2 && cxpos3<2)
		{
			if(cxpos1==0 && cxpos2==0 && cxpos3==1)
			{
 cxpos3++;
				cxpos2++;
			}
			else if(cxpos1==0 && cxpos2==1 && cxpos3==0)
			{
				cxpos3++;
				cxpos2++;
			}
			else if(cxpos1==1 && cxpos2==0 && cxpos3==0)
			{
				cxpos1++;
				cxpos2++;
			}
		}
		
		if(y1pos<ypos && y1pos+175>ypos && carxpos[cxpos1]==xpos)
		{
		gameover=true;	
		}
		if(y2pos<ypos && y2pos+175>ypos && carxpos[cxpos2]==xpos)
		{
		gameover=true;	
		}
		if(y3pos<ypos  && y3pos+175>ypos && carxpos[cxpos3]==xpos)
		{
		gameover=true;	
		}
		if(ypos<y1pos && ypos+175>y1pos && carxpos[cxpos1]==xpos)
		{
		gameover=true;	
		}
		if(ypos<y2pos && ypos+175>y2pos && carxpos[cxpos2]==xpos)
		{
		gameover=true;	
		}
		if(ypos<y3pos  && ypos+175>y3pos && carxpos[cxpos3]==xpos)
		{
		gameover=true;	
		}
		//score 
		gl.setColor(Color.gray);
		gl.fillRect(120,35,220,50);
		gl.setColor(Color.DARK_GRAY);
		gl.fillRect(125,40, 210, 40);
		gl.setColor(Color.gray);
		gl.fillRect(385,35,180,50);
		gl.setColor(Color.DARK_GRAY);
		gl.fillRect(390,40, 170, 40);
		gl.setColor(Color.white);
		gl.setFont(new Font("Arial",Font.BOLD,30));
		gl.drawString("Score : "+score, 130, 67);
		gl.drawString(speed+" Km/h", 400, 67);
                
                gl.setColor(Color.DARK_GRAY);
              
		gl.fillRect(275,80,160,40);
		
                  gl.setColor(Color.white);
		gl.drawString(" time :"+time, 290, 110);

		score++;
                time+=0.25;
		speed++;
		
                if(speed>140)
		{
			speed=240-delay;
		
		}
		if(score%50==0)
		{
			delay-=10;
			if(delay<60)
			{
				delay=60;
			}
		}
		//delay 
		try
		{
			
			TimeUnit.MILLISECONDS.sleep(delay);
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(y1pos<ypos && y1pos+175>ypos && carxpos[cxpos1]==xpos)
		{
		gameover=true;	
		}
		if(y2pos<ypos && y2pos+175>ypos && carxpos[cxpos2]==xpos)
			
		{
		gameover=true;	
		}
		if(y3pos<ypos  && y3pos+175>ypos && carxpos[cxpos3]==xpos)
		{
		gameover=true;	
		}
                
		if(gameover)
		{
		gl.setColor(Color.gray);
		gl.fillRect(120, 210, 460, 230);	
		gl.setColor(Color.DARK_GRAY);
		gl.fillRect(130, 220, 440, 200);
		gl.setFont(new Font("Serif",Font.BOLD,50));
		gl.setColor(Color.yellow);
		gl.drawString("Game Over !",210, 270);
                gl.setColor(Color.yellow);
                gl.drawString("your score is :" +(score-1),180, 320);
                gl.setColor(Color.yellow);
                gl.drawString("your time is :" +(time-1),180, 370);
		gl.setColor(Color.white);
		gl.setFont(new Font("Arial",Font.BOLD,30));
		gl.drawString("Press Enter to Restart", 180, 410);
		if(!paint)
		{
			repaint();
			paint=true;
		}
                
		}
                else if(score>=l_score){
                   
                gl.setColor(Color.gray);
		gl.fillRect(120, 210, 460, 200);	
		gl.setColor(Color.DARK_GRAY);
		gl.fillRect(130, 220, 440, 180);
		gl.setFont(new Font("Serif",Font.BOLD,50));
		gl.setColor(Color.yellow);
		gl.drawString("  you win!",210, 270);
                gl.setColor(Color.yellow);
                gl.drawString("your score is :" +(score-1),180, 320);
		gl.setColor(Color.white);
		gl.setFont(new Font("Arial",Font.BOLD,30));
		gl.drawString("Press Enter to Restart", 190, 360);
                
		if(!paint)
		{
			repaint();
			paint=true;
		}
                
                
                }
		else
		{
		repaint();
		}
	}
		
	public static void main(String args[])
	{
		CarGame c=new CarGame("Car Game");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_LEFT && !gameover)
		{
			xpos-=100;
			if(xpos<100)
			{
				xpos=100;
			}
			
			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT&&!gameover)
		{
			xpos+=100;
			if(xpos>500)
			{
				xpos=500;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER && gameover || score>=l_score)
		{
			gameover=false;
                        
			paint=false;
			cxpos1=0;
			cxpos2=2;
			cxpos3=4;
			cypos1=random.nextInt(5);
			cypos2=random.nextInt(5);
			cypos3=random.nextInt(5);
			y1pos=carypos[cypos1];
			y2pos=carypos[cypos2];
			y3pos=carypos[cypos3];
			speed=90;
			score=0;
			delay=100;
			xpos=300;
			ypos=700;
                        score=0;
			time=0;
			
		}
	    if(e.getKeyCode()==KeyEvent.VK_SPACE && start )
		{
			start = false;
                        
			paint=false;
			cxpos1=0;
			cxpos2=2;
			cxpos3=4;
			cypos1=random.nextInt(5);
			cypos2=random.nextInt(5);
			cypos3=random.nextInt(5);
			y1pos=carypos[cypos1];
			y2pos=carypos[cypos2];
			y3pos=carypos[cypos3];
			speed=90;
			score=0;
			delay=100;
			xpos=300;
			ypos=700;
                        score=0;
			time=0;
			
		}
	
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		repaint();
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
	
		
	}
}