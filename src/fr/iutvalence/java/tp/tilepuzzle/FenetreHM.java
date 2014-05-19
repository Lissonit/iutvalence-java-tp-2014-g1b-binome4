package fr.iutvalence.java.tp.tilepuzzle;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FenetreHM implements Affichage, Runnable, ActionListener
{
	private JFrame fenetre;
	private JButton boutonRemiseAZero;
	private PanneauBouton panneau;
	
	public FenetreHM(int nombreDeLignes, int nombreDeColonnes)
	{
		this.panneau = new PanneauBouton(nombreDeLignes, nombreDeColonnes);
	}
	
	@Override
	public void afficherDemandePosition()
	{
		
	}

	@Override
	public void afficherPlateau(Plateau plateau)
	{
		
	}

	@Override
	public void run()
	{
		this.fenetre = new JFrame();
		this.fenetre.setTitle("Puzzle");
		this.fenetre.setSize(400, 200);
		this.fenetre.setResizable(false);
		
		this.boutonRemiseAZero = new JButton("Remise à zero");
		this.boutonRemiseAZero.setFocusable(false);
		//this.boutonRemiseAZero.addActionListener((ActionListener)this);
		
		
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
		// Identification de la source
		JComponent source = (JComponent) event.getSource();

		if (source == this.boutonRemiseAZero)
		{
			this.panneau.remiseAZero();
			return;
		}
	}
}
