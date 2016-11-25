package jeu_2048_de_farida.graph;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import moteur.*;


public class PanelCentreTest extends JPanel implements ColorsInterface,MouseListener,MouseMotionListener {
		
	
	private static final long serialVersionUID = 1L;
	ArrayList<Point> p;
	private Graphics g;
	private GrilleTuiles mJ = null;
	private int hauteur;
	private int largeur;
	private Image img;
	private boolean pressed;
	
	public PanelCentreTest(GrilleTuiles mj){
		p = new ArrayList<Point>();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.largeur = this.getWidth();
		this.hauteur = this.getHeight();
		this.img = Toolkit.getDefaultToolkit().getImage("trolDance.gif");
		this.mJ = mj;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.g = g;
		this.ajoutePoint(p);
	}
	
	private void ajoutePoint(ArrayList<Point> p){
		/*for(int i =0;i<p.size();i++){
			for(int j =0;j<p.size();j++){
				this.g.drawLine((int)p.get(i).getX(),(int) p.get(i).getY(),(int)p.get(j).getX(), (int)p.get(j).getY());
				g.drawRect(0, 0, 100, 100);
				
			}
		}*/
		if(this.p.size() >2){
			for(int i = 2; i< p.size();i++){
					
				
				Point un = this.p.get(i-2);
				Point deux = this.p.get(i-1);
				g.drawLine((int)un.getX(), (int)un.getY(), (int)deux.getX(), (int) deux.getY() );
			}
		}
		repaint();
	}
	
	

		

		
		public void mouseClicked(MouseEvent e) {
	
			
		}
		public void mouseEntered(MouseEvent e) {
			
			
			
		}
		public void mouseExited(MouseEvent e) {
	
			
		}
		public void mousePressed(MouseEvent e) {
			this.p.add(e.getPoint());

			repaint();
			this.pressed = true;
			
		}
		public void mouseReleased(MouseEvent e) {
			this.pressed = false;
			
			
		
	}

		public void mouseDragged(MouseEvent e) {
			if(this.pressed){
				this.p.add(e.getPoint());
			}
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			
		}
	
}
