package fr.iutvalence.java.tp.tilepuzzle;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FenetreHM implements Affichage, Runnable, ActionListener, Joueur
{
	private JFrame fenetre;
	private JButton boutonRemiseAZero;
	private PanneauBouton panneau;
	private boolean eventArrivee;
	private Position position;
	private Plateau plateau;

	public FenetreHM(Plateau plateau)
	{
		this.eventArrivee = false;
		this.plateau = plateau;
		this.panneau = new PanneauBouton(this.plateau.obtenirLargeur(), this.plateau.obtenirHauteur(), this);
	}

	@Override
	public void afficherDemandePosition()
	{
		System.out.println("Position ?");
	}

	@Override
	public void afficherPlateau(Plateau plateau)
	{
		this.panneau.refresh(plateau);
	}

	@Override
	public void run()
	{
		this.fenetre = new JFrame();
		this.fenetre.setTitle("Puzzle");
		this.fenetre.setSize(400, 400);
		this.fenetre.setResizable(false);

		this.boutonRemiseAZero = new JButton("Remise à zero");
		this.boutonRemiseAZero.setFocusable(false);
		this.boutonRemiseAZero.addActionListener((ActionListener) this);

		JSplitPane panelIntermediaire = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panelIntermediaire.setBorder(null);
		panelIntermediaire.setDividerSize(0);

		panelIntermediaire.add(this.boutonRemiseAZero);
		panelIntermediaire.add(this.panneau);

		this.fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.fenetre.setVisible(true);
		this.fenetre.getContentPane().add(panelIntermediaire);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{
		System.out.println("click");
		// Identification de la source
		JComponent source = (JComponent)event.getSource();
		
		if (source == this.boutonRemiseAZero)
		{
			this.panneau.remiseAZero(this.plateau);
			return;
		}
		
		if(!this.eventArrivee)
		{
			this.position = ((JButtonCase)source).obtenirPosition();
			this.eventArrivee = true;
			System.out.println(this.position);
		}
		
	}

	@Override
	public Position getPosition(int hauteurPlateau, int largeurPlateau)
	{
		this.eventArrivee = false;
		while(!this.eventArrivee)
		{
			// Attente
			try{
			Thread.sleep(500);
			} catch(Exception e) { }
		}
		return this.position;
	}
	
	public JFrame obtenirFenetre()
	{
		return this.fenetre;
	}

	public void refresh(Plateau plateau)
	{
		this.plateau = plateau;
	}
}
