package jeu_2048_de_farida;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import jeu_2048_de_farida.graph.*;

import javax.swing.*;
import javax.swing.border.Border;


import moteur.*;

public class FenJeu2048DeFarida extends JFrame implements ColorsInterface{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//contien le content pane pour qu'il soit accessible partout
	private Container c;
	//contient le moteur du jeu permettant de modifier et d'interagir avec le jeux
	private MoteurJeu m;
	
	//lier au north
	private int deplacement;
	private JTextField textDep;
	private JTextField textScore;
	
	//lier a Ouest
	private JButton raz;
	private JButton recommencer;
	
	//lier a South
	private int meilleurScore;
	private JTextField textMeilScor;
	

	
	//lier a Center
	private JTabbedPane jOnglet;
	private GrilleTuilesGraphique plateauBackground;
	private JList historique ;
	private DefaultListModel model;
	private PanelCentre pc[][];
	
	//pour savoir quand vous avez gagner
	private boolean PartiGagnee ;
	
	//initialisation des constante de class pour repérer les bouton dans le listener
	private static final int HAUT=666, DROITE=1, BAS=2, GAUCHE=3, RECOMMENCER=4, RAZ=5 ;
	private static final int TAILLE_JEU = 4;
	
	
	
	public FenJeu2048DeFarida(String titre,int w,int h){
		super(titre);
		this.initialise();
		this.setVisible(true);
		this.centreEcran(w,h);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public FenJeu2048DeFarida(String titre){
		super(titre);
		this.initialise();
		this.setVisible(true);
		this.pleinEcran();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	private void centreEcran(int w,int h){
		Toolkit aTK = Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int x = dim.width;
		int y = dim.height;	
		x= (x-w)/2;
		y= (y-h)/2;
		
		this.setBounds( x, y, w, h);
		
	}
	
	private void pleinEcran(){
		Toolkit aTK = Toolkit.getDefaultToolkit();
		Dimension dim = aTK.getScreenSize();
		int x = dim.width;
		int y = dim.height;
		this.setBounds(0,0,x,y);
	}
	
	private void initialise(){
		this.PartiGagnee = false ;
		this.m = MoteurJeu.getInstance();
		this.m.setup();
		this.deplacement = this.m.getNombreDeplacements();

		this.c = this.getContentPane() ;
		this.c.setLayout(new BorderLayout());
		
		
		this.c.addKeyListener(new ToucheAction());
	
		
		JPanel PScoreDeplacement = this.creePanelNord();
		this.c.add(PScoreDeplacement,"North");
		
		JPanel PDeplacement = this.creePanelOuest();
		this.c.add(PDeplacement,"West");
		
		JPanel PMScore = this.creePanelSud();
		this.c.add(PMScore,"South");
		
		JTabbedPane PGrille = this.creePanelCentre();
		
		this.c.add(PGrille,"Center");
		//this.m.getGrilleTuiles().insertTuile(new Tuile(0,0,1024));
		
		
		
	}
	
	private JPanel creePanelNord(){
		JPanel pTot = new JPanel();
		
		JPanel pNord = new JPanel(new GridLayout(2,2,20,20));
		JLabel labScore = new JLabel("score courant :");
		JLabel labDep = new JLabel("nombre de deplacement :");
		
		textScore = new JTextField(10);
		textDep = new JTextField(10);
		
		textDep.setForeground(Color.GREEN);
		
			
		if(this.deplacement == 0){
			textScore.setVisible(false);
			textDep.setVisible(false);
		}
		else{
			textScore.setVisible(true);
			textDep.setVisible(true);
			
			textDep.setText(""+this.deplacement);
			textScore.setText(""+this.m.getScore());
		}
			
		
		textDep.setEnabled(false);
		textScore.setEnabled(false);
		
		pNord.add(labScore);
		pNord.add(labDep);
		pNord.add(textScore);
		pNord.add(textDep);
		
		pTot.add(pNord);
		
		Border b = BorderFactory.createTitledBorder("Score et déplacement");
		pTot.setBorder(b);
		
		return pTot;
	}
	
	private JPanel creePanelOuest(){
		//cr�er un JPanel qui va englober deux autre panel
		JPanel pOuest = new JPanel(new BorderLayout());
		
		JPanel haut = new JPanel(new GridLayout(3,3,10,10));
		for(int i = 0;i<9;i++){
			//donne les JButton Pour interagir avec le jeu
			JButton b = new JButton("");
			if(i==1){
				b.setText("Haut");
				b.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.HAUT));
				
			}
			else if(i==3){
				b.setText("Gauche");
				b.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.GAUCHE));
			}
			else if(i==5){
				b.setText("Droite");
				b.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.DROITE));
			}
			else if(i==7){
				b.setText("Bas");
				b.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.BAS));
			}
			else
				b.setEnabled(false);
			haut.add(b);
			b.addMouseListener(new BoutonTest(b));
		}
		
		
		haut.setBorder(BorderFactory.createTitledBorder("Déplacement"));
		
		
		
		
		JPanel bas = new JPanel(new FlowLayout());
		
		this.recommencer = new JButton("Recommencer");
		recommencer.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.RECOMMENCER));
		
		this.raz = new JButton("RAZ");
		recommencer.addActionListener(new BoutonListenerMnemo(FenJeu2048DeFarida.RAZ));
		
		if(this.m.getNombreDeplacements() == 0){
			recommencer.setEnabled(false);
			raz.setEnabled(false);
		}
		
		
		bas.add(recommencer);
		bas.add(raz);
		bas.setBorder(BorderFactory.createTitledBorder("commande Partie"));
		
		pOuest.add(haut,"Center");
		pOuest.add(bas,"South");
		
		return pOuest;
		
	}
	
	private JPanel creePanelSud(){
		JPanel pSud = new JPanel();
		
		//definie le nord pour mettre les champs ou apparaitron le nombre de deplacement et le meilleur score
		 JLabel labMeilScor = new JLabel("Meilleur Score : ");
		 this.textMeilScor = new JTextField(10);
		 textMeilScor.setEditable(false);
		 pSud.add(labMeilScor);
		 pSud.add(textMeilScor);
		 
		 //border recuperer sur la correction
		 Border bord=BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Meilleur Score"),
				                                 BorderFactory.createEmptyBorder(20,10,3,2));
		 
		 pSud.setBorder(bord);
		
		return pSud;
		
	}
	

	private JTabbedPane creePanelCentre(){
	
		
		
		this.jOnglet = new JTabbedPane();

		
	
		jOnglet.addTab("truc",this.PanelCentreNormale());
		jOnglet.addTab("fond vache", this.PanelCentreVache());
		jOnglet.addTab("test", this.PanelCentreTest());
		jOnglet.addTab("historique",this.PanelCentreHistorique());
		
		
		
		return jOnglet;
	}
	
	private JPanel PanelCentreNormale(){
		
		//pCentre est un layout de type grille avec 4x4 
		JPanel pCentre = new JPanel(new GridLayout(FenJeu2048DeFarida.TAILLE_JEU,FenJeu2048DeFarida.TAILLE_JEU));
		this.pc = new PanelCentre[FenJeu2048DeFarida.TAILLE_JEU][FenJeu2048DeFarida.TAILLE_JEU];

		

		for(int i=0;i<FenJeu2048DeFarida.TAILLE_JEU;i++){
			for(int j =0;j<FenJeu2048DeFarida.TAILLE_JEU;j++){
				
				
				PanelCentre pcs ;
				Border b = BorderFactory.createBevelBorder(1) ;
				if(this.m.getGrilleTuiles().tuiles[i][j] == null ){
					pcs = new PanelCentre(0);
				}
				else{
					pcs = new PanelCentre(this.m.getGrilleTuiles().tuiles[i][j].getValue() );
				}
				this.pc[i][j]=pcs;
				pcs.setBorder(b);
				pCentre.add(pcs);
				
			}
			
		}
			//creer un bord avec pour nom grille
		Border b = BorderFactory.createTitledBorder("Grille");
		//donne un border a pCentre
		pCentre.setBorder(b);
		
		pCentre.addKeyListener(new ToucheAction());
		return pCentre;
	}
	
	private JPanel PanelCentreVache(){
		this.plateauBackground = new GrilleTuilesGraphique(this.m.getGrilleTuiles());
		plateauBackground.addKeyListener(new ToucheAction());
		return plateauBackground;
	}
	
	private JPanel PanelCentreTest(){
		return new PanelCentreTest(this.m.getGrilleTuiles());
	}
	
	private JScrollPane PanelCentreHistorique(){
		this.model = new DefaultListModel();
		this.model.addElement(new String("salut"));
		this.historique = new JList(model);
		JScrollPane listScrollPane = new JScrollPane(historique);
		
		
		
		
		return listScrollPane;
	}
	
	public void refresh(){	
		
		this.rCenter();
		this.rSouth();
		this.rNorth();
		this.rOuest();
		this.partiFini();


	}
	
	private void rCenter(){
		
		
		for(int i=0;i<FenJeu2048DeFarida.TAILLE_JEU;i++){
			for(int j =0;j<FenJeu2048DeFarida.TAILLE_JEU;j++){
				if(this.m.getGrilleTuiles().tuiles[i][j] == null ){
					this.pc[i][j].setValues(0);
					this.pc[i][j].rColor();
				}
				else{
					this.pc[i][j].setValues(this.m.getGrilleTuiles().tuiles[i][j].getValue() ) ;
					this.pc[i][j].rColor();
				}
				
				
			}
		}
		this.rHistorique();
		
		repaint();
	}
	
	private void rHistorique(){
		
		this.model.addElement(new String ("coup "+this.m.getNombreDeplacements()+" - score : "+this.m.getScore()  ) );
		
		
	}
	
	private void rSouth(){
	//affiche le meilleur score
	if( FenJeu2048DeFarida.this.meilleurScore < FenJeu2048DeFarida.this.m.getScore() );	
		FenJeu2048DeFarida.this.meilleurScore = FenJeu2048DeFarida.this.m.getScore();
	FenJeu2048DeFarida.this.textMeilScor.setText(""+FenJeu2048DeFarida.this.meilleurScore);

	}
	
	private void rNorth(){
		//met la variable deplacement a jour
		FenJeu2048DeFarida.this.deplacement = FenJeu2048DeFarida.this.m.getNombreDeplacements();
		//affiche sur le bon champ deplacement
		textDep.setText(""+FenJeu2048DeFarida.this.deplacement);
		
		//desactive les champs si les deplacement sont a 0
		if(FenJeu2048DeFarida.this.deplacement == 0){
			FenJeu2048DeFarida.this.textScore.setVisible(false);
			FenJeu2048DeFarida.this.textDep.setVisible(false);
		}
		else{
			textScore.setVisible(true);
			textDep.setVisible(true);
			
			textDep.setText(""+FenJeu2048DeFarida.this.deplacement);
			textScore.setText(""+FenJeu2048DeFarida.this.m.getScore());
		}
	}
	
	private void rOuest(){
		if(FenJeu2048DeFarida.this.deplacement == 0){
			FenJeu2048DeFarida.this.recommencer.setEnabled(false);
			FenJeu2048DeFarida.this.raz.setEnabled(false);
		}
		else{
			FenJeu2048DeFarida.this.recommencer.setEnabled(true);
			FenJeu2048DeFarida.this.raz.setEnabled(true);
		}
	}
	
	private void partiFini(){
		new JOptionPane();
		ImageIcon img;
		//le m est sous entendu FenJeu2048DeFarida.this.m

		if(m.partieGagnee()  && !this.PartiGagnee) {
			this.PartiGagnee = true ;
			img = new ImageIcon("youwin.gif");
			JOptionPane.showMessageDialog(null,"YOU WIN VOUS AVEZ FAIT 2048","WIN",JOptionPane.INFORMATION_MESSAGE,img);
			
		}
		
		else if( m.partieTerminee()){
			 img= new ImageIcon("rivers-or-tears.gif");
			JOptionPane.showMessageDialog(null,"votre partie est terminé","attention",JOptionPane.INFORMATION_MESSAGE,img);
			 Image imgFond =Toolkit.getDefaultToolkit().getImage("trolDance.gif");
			this.plateauBackground.setImage(imgFond);	
		}			


	}
    
	

	
	

	public class ToucheAction implements KeyListener{
       // private static final int INDETERMINATE = -1; 
        //private int previousKeyPressed = INDETERMINATE; 
       
        public void keyPressed(KeyEvent evt) { 
           //System.out.println("keyPressed " + evt.getKeyCode()); 
            if(evt.getKeyCode() == 38){
            	m.deplacer(MoteurJeu.HAUT);
            }
            else if(evt.getKeyCode() == 39){
            	m.deplacer(MoteurJeu.DROITE);
            }
            else if(evt.getKeyCode() == 40){
            	m.deplacer(MoteurJeu.BAS);
            }
            else if(evt.getKeyCode() == 37){
            	m.deplacer(MoteurJeu.GAUCHE);
            }
            else if(evt.getKeyCode() == 8){
            	m.recommencerPartie();
            }
            
            FenJeu2048DeFarida.this.refresh();

        } 

        public void keyTyped(KeyEvent evt) {} 

        public void keyReleased(KeyEvent evt) {} 
		

        
	}

	public class BoutonListenerMnemo implements ActionListener{
		private int val;
		
		//donne a l'event un nombre pour le reconnaitre
		public BoutonListenerMnemo(int i){
			this.val=i;
		}
		//active les event pour chaque bouton utilisable
		public void actionPerformed(ActionEvent e){
			switch (val){
				case FenJeu2048DeFarida.HAUT : FenJeu2048DeFarida.this.m.deplacer(MoteurJeu.HAUT);
					break;
				case FenJeu2048DeFarida.GAUCHE : FenJeu2048DeFarida.this.m.deplacer(MoteurJeu.GAUCHE);
					break;
				case FenJeu2048DeFarida.BAS : FenJeu2048DeFarida.this.m.deplacer(MoteurJeu.BAS);
					break;
				case FenJeu2048DeFarida.DROITE : FenJeu2048DeFarida.this.m.deplacer(MoteurJeu.DROITE);
					break;
				case FenJeu2048DeFarida.RECOMMENCER :
					FenJeu2048DeFarida.this.m.recommencerPartie();
					model.clear();
					break;
				case FenJeu2048DeFarida.RAZ : FenJeu2048DeFarida.this.m.resetJeu();
					break;
				
				
			}
			FenJeu2048DeFarida.this.refresh();
		}	
	}
	
	public class BoutonTest implements MouseListener{
		private JButton c;
		
		public BoutonTest(JButton j){
			this.c =j;
		}
		
		
		public void mouseClicked(MouseEvent e) {

			
		}
		public void mouseEntered(MouseEvent e) {

			
		}
		public void mouseExited(MouseEvent e) {

			
		}
		public void mousePressed(MouseEvent e) {
			c.setCursor(new Cursor(1));
			
		}
		public void mouseReleased(MouseEvent e) {
			c.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
		}
	}
}
	
	

	
	


