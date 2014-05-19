package fr.iutvalence.java.tp.tilepuzzle;

import javax.swing.*;

/**
 * @author Bouix Loïc et Sanfilippo Max
 */

/**
 * Classe initiale de l'application, permet de lancer la partie.
 */
public class LanceurDeTilePuzzle
{
	/**
	 * Methode initiale, crée la partie et affiche son 
	 * état actuel(en création, démarrage ou terminé)
	 * @param args Arguments de la ligne de commande, il n'y en a aucun pour
	 * l'instant.
	 */
	public static void main(String[] args)
	{
		System.out.println("Création de la partie");
		FabriqueDePlateau fdp = new FabriqueDePlateauAleatoire();
		Joueur joueur = new JoueurClavier();
		TilePuzzle partieDeTilePuzzle = new TilePuzzle(joueur, fdp);
		Affichage affichage = new FenetreHM(partieDeTilePuzzle.obtenirNombreLignes(), partieDeTilePuzzle.obtenirNombreColonnes());
		partieDeTilePuzzle.associerAffichage(affichage);
		SwingUtilities.invokeLater((Runnable)affichage);
		System.out.println("Demarrage de la partie");
		partieDeTilePuzzle.jouer();
		System.out.println("Fin de la partie");
	}
}