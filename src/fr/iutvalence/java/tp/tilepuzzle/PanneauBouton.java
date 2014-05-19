package fr.iutvalence.java.tp.tilepuzzle;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanneauBouton extends JPanel
{
	private int largeur;
	private int hauteur;
	
	public PanneauBouton(int largeur, int hauteur)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		
		this.setLayout(new GridLayout(hauteur, largeur));
		for (int ligne = 0; ligne < hauteur; ligne++)
			for (int colonne = 0; colonne < largeur; colonne++)
				this.add(new JButtonCase(ligne, colonne));
	}

	// NON FONCTIONNEL
	public void remiseAZero()
	{
		Component[] cases = this.getComponents();
		for (int ligne = 0; ligne < hauteur; ligne++)
			for (int colonne = 0; colonne < largeur; colonne++)
				((JButtonCase)cases[ligne * colonne]).modifierEtat(false);
	}
}
