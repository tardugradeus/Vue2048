package jeu_2048_de_farida.graph;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JPanel;

import moteur.*;

public class PanelCentre extends JPanel implements ColorsInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int values;

	
	public PanelCentre(int values){
		this.values = values;
		switch(values){
			case 0 : this.setBackground(Color.white);
				break;
			case 2 : this.setBackground(TUILE_COLOR_2);
				break;
			case 4 : this.setBackground(TUILE_COLOR_4);
				break;
			case 8 : this.setBackground(TUILE_COLOR_8);
				break;
			case 16 : this.setBackground(TUILE_COLOR_16);
				break;
			case 32 : this.setBackground(TUILE_COLOR_32);
				break;
			case 64 : this.setBackground(TUILE_COLOR_64);
				break;
			case 128 : this.setBackground(TUILE_COLOR_128);
				break;
			case 256 : this.setBackground(TUILE_COLOR_256);
				break;
			case 512 : this.setBackground(TUILE_COLOR_512);
				break;
			case 1024 : this.setBackground(TUILE_COLOR_1024);
				break;
			case 2048 : this.setBackground(TUILE_COLOR_2048);
				break;
			default : this.setBackground(TUILE_COLOR_SUPER);
				break;
		}
	}
	
	public void setValues(int v){
		this.values = v;
	}
	public int getValues(){
		return this.values ;
	}
	
	public void rColor(){
		switch(values){
		case 0 : this.setBackground(Color.white);
			break;
		case 2 : this.setBackground(TUILE_COLOR_2);
			break;
		case 4 : this.setBackground(TUILE_COLOR_4);
			break;
		case 8 : this.setBackground(TUILE_COLOR_8);
			break;
		case 16 : this.setBackground(TUILE_COLOR_16);
			break;
		case 32 : this.setBackground(TUILE_COLOR_32);
			break;
		case 64 : this.setBackground(TUILE_COLOR_64);
			break;
		case 128 : this.setBackground(TUILE_COLOR_128);
			break;
		case 256 : this.setBackground(TUILE_COLOR_256);
			break;
		case 512 : this.setBackground(TUILE_COLOR_512);
			break;
		case 1024 : this.setBackground(TUILE_COLOR_1024);
			break;
		case 2048 : this.setBackground(TUILE_COLOR_2048);
			break;
		default : this.setBackground(TUILE_COLOR_SUPER);
			break;
	}
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
			
			int larg = this.getWidth();
			int haut = this.getHeight();
			
			
			
			
			g.setFont(new Font(Font.SANS_SERIF,Font.CENTER_BASELINE,15));
		
			g.drawString(""+this.values,larg/2 - (this.getFont().getSize())/2 , haut/2 + (this.getFont().getSize())/2 );
			
			/*String nom = "trolDance.gif" ;
			Image img = Toolkit.getDefaultToolkit().getImage(nom);
			g.drawImage(img,0,0,larg,haut,this);
			*/

		
	}
	
	


}
