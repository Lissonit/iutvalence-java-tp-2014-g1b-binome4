package fr.iutvalence.java.tp.tilepuzzle;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * Classe principale du jeu, initialise la partie en créant un plateau et un joueur.
 */
public class TilePuzzle
{

	/**
	 * Plateau
	 */
	private Plateau plateau;
	
	/**
	 * Joueur
	 */
	private FenetreHM joueur;
	
	/**
	 * Support d'affichage
	 */
	private Affichage affichage;
	
	private FabriqueDePlateau fdp;
	
	/**
	 * Crée une partie prête à être jouée.
	 * Cela implique la mise en place d'un plateau et d'un joueur pour cette partie.
	 * @param joueur Joueur a utiliser
	 * @param fdp Fabrique de plateau utilisée pour la partie
	 */
	public TilePuzzle(FabriqueDePlateau fdp)
	{
		this.fdp = fdp;
		randomizePlateau();
	}
	
	private void randomizePlateau()
	{
		this.plateau = this.fdp.obtenirPlateauDefini(3, 3, 10);
	}

	/**
	 * Lance la partie
	 */
	public void jouer()
	{
		while (this.plateau.obtenirNombreDeCasesAllumees() < this.plateau.obtenirHauteur() * this.plateau.obtenirLargeur())
		{
			this.affichage.afficherPlateau(this.plateau);
			this.affichage.afficherDemandePosition();
			this.plateau.inverserCasesAutourDe(this.joueur.getPosition(this.plateau.obtenirHauteur(),this.plateau.obtenirLargeur()));
		}
		
		if(JOptionPane.showConfirmDialog((Component) this.joueur.obtenirFenetre(), "Vous avez gagnez la partie !\nSouhaitez-vous rejouer ?", "Partie gagnée", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
		{
			randomizePlateau();
			this.joueur.refresh(this.plateau);
			this.affichage.afficherPlateau(this.plateau);
			this.jouer();
		}
		else
		{
			this.joueur.obtenirFenetre().dispose();
		}
	}
	
	public int obtenirNombreLignes()
	{
		return plateau.obtenirHauteur();
	}
	
	public int obtenirNombreColonnes()
	{
		return plateau.obtenirLargeur();
	}

	public void associerAffichage(Affichage affichage)
	{
		this.affichage = affichage;
	}

	public void associerJoueur(FenetreHM joueur)
	{
		this.joueur = joueur;
		
	}

	public Plateau obtenirPlateau()
	{
		return this.plateau;
	}
}
