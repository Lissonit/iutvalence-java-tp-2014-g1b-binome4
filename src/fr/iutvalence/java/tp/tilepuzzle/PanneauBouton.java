package fr.iutvalence.java.tp.tilepuzzle;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanneauBouton extends JPanel
{
	private int largeur;
	private int hauteur;
	
	private JButtonCase[][] cases;
	
	public PanneauBouton(int largeur, int hauteur, ActionListener listener)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cases = new JButtonCase[this.largeur][this.hauteur];
		
		this.setLayout(new GridLayout(hauteur, largeur));
		for (int ligne = 0; ligne < hauteur; ligne++)
			for (int colonne = 0; colonne < largeur; colonne++)
			{
				JButtonCase caseCourante = new JButtonCase(ligne, colonne);
				caseCourante.addActionListener(listener);
				this.obtenirBoutons()[ligne][colonne] = caseCourante;
				this.add(caseCourante);
			}
	}

	/**
	 * Remet à zero toute les cases dans la grille de jeu
	 */
	public void remiseAZero(Plateau plateau)
	{
		for (int ligne = 0; ligne < hauteur; ligne++)
			for (int colonne = 0; colonne < largeur; colonne++)
			{
				this.obtenirBoutons()[ligne][colonne].modifierEtat(false);
				plateau.reinitialiser();
			}
	}
	
	public void refresh(Plateau plateau)
	{
		for (int ligne = 0; ligne < hauteur; ligne++)
			for (int colonne = 0; colonne < largeur; colonne++)
				if(plateau.estCaseAllumee(new Position(ligne, colonne)))
					this.cases[ligne][colonne].modifierEtat(true);
				else
					this.cases[ligne][colonne].modifierEtat(false);
	}

	/**
	 * Obtention des boutons du panneau
	 * @return Les boutons du panneaux
	 */
	public JButtonCase[][] obtenirBoutons()
	{
		return cases;
	}
	
	public int obtenirLargeur()
	{
		return this.largeur;
	}
	
	public int obtenirHauteur()
	{
		return this.hauteur;
	}
}
