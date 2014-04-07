package fr.iutvalence.java.tp.tilepuzzle;

import java.util.Random;

/**
 * Définit un plateau de jeu, il est défini par une hauteur et une largeur, 
 * représentées en nombre de cases, chacune pouvant être allumée ou éteinte.
 */
public class Plateau
{
	/**
	 * Largeur par défaut d'un plateau
	 */
	protected static final int TAILLE_PAR_DEFAUT = 3;
	/**
	 * Valeur d'une case éteinte sur la grille
	 */
	protected static final boolean CASE_ETEINTE = false;
	
	/**
	 * Largeur du plateau
	 */
	protected int largeur;
	
	/**
	 * Hauteur du plateau
	 */
	protected int hauteur;
	
	/**
	 * Nombre de cases allumées sur le plateau
	 */
	protected int casesAllumees;
	
	/**
	 * Tableau des cases du plateau
	 */
	protected boolean[][] cases;

	/**
	 * Crée un nouveau plateau de largeur et hauteur par défaut, avec toutes les cases
	 * éteintes.
	 */
	public Plateau()
	{
		this.hauteur = TAILLE_PAR_DEFAUT;
		this.largeur = TAILLE_PAR_DEFAUT;
		this.cases = new boolean[this.hauteur][this.largeur];
		this.casesAllumees = 0;
		for  (int ligne = 0; ligne < this.hauteur; ligne++)
		{
			for (int colonne = 0; colonne < this.largeur; colonne++)
			{
				this.cases[ligne][colonne] = CASE_ETEINTE;
			}
		}
	}
	
	/**
	 * Crée un nouveau plateau de hauteur et largeur indiquées, avec toutes les cases
	 * éteintes.
	 * @param hauteur hauteur du plateau
	 * @param largeur largeur du plateau
	 */
	public Plateau(int hauteur, int largeur)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cases = new boolean[this.hauteur][this.largeur];
		this.casesAllumees = 0;
		for  (int ligne = 0; ligne < this.hauteur; ligne++)
		{
			for (int colonne = 0; colonne < this.largeur; colonne++)
			{
				this.cases[ligne][colonne] = CASE_ETEINTE;
			}
		}
	}
	
	/**
	 * Crée un nouveau plateau de largeur par défaut, avec un certain nombre de cases allumées
	 * en fonction d'une difficulté(difficulté 10 --> 50% de cases allumées)
	 * @param hauteur hauteur du plateau
	 * @param largeur largeur du plateau
	 * @param difficulte difficulté
	 */
	public Plateau(int hauteur, int largeur, int difficulte)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cases = new boolean[this.hauteur][this.largeur];
		this.casesAllumees = 0;
		for  (int ligne = 0; ligne < this.hauteur; ligne++)
		{
			for (int colonne = 0; colonne < this.largeur; colonne++)
			{
				this.cases[ligne][colonne] = CASE_ETEINTE;
			}
		}
		
		int nombreDeCasesAAllumer = (int) Math.round((difficulte/20.0)*this.hauteur*this.largeur);
		Random machineAlea = new Random();
		while (this.casesAllumees != nombreDeCasesAAllumer )
		{
			
			Position positionAlea = new Position(machineAlea.nextInt(this.hauteur-1), machineAlea.nextInt(this.largeur-1));
			this.inverserCasesAutourDe(positionAlea);
		}
	}
	
	/**
	 *  Renvoi le contenu du plateau sous forme d'une chaine
	 * @return Chaine representant le plateau
	 */
	public String toString()
	{
		String plateauAffichable = "";
		for (int ligne = 0; ligne < this.hauteur; ligne++)
		{
			for (int colonne = 0; colonne < this.largeur; colonne++)
			{
				plateauAffichable = plateauAffichable+this.cases[ligne][colonne]+" ";
			}
			plateauAffichable = plateauAffichable+"\n";
		}
		return plateauAffichable;
	}
	
	/**
	 * Retourne si oui ou non la case se trouve sur le plateau
	 * @param position Position a vérifier
	 * @return true si la position est valide, false si elle ne l'est pas
	 */
	public boolean estPositionValide(Position position)
	{
		return ((position.getLigne()>=0)&&(position.getLigne()<this.hauteur)&&((position.getColonne()>=0)&&(position.getColonne()<this.largeur)));
	}
	/**
	 * Inverse l'état de la case indiquée et met à jour le compteur
	 * @param caseAInverser Case à inverser
	 */
	private void inverserCase(Position caseAInverser)
	{
		if (!estPositionValide(caseAInverser)) return;
		this.casesAllumees += inverserEtat(caseAInverser);
	}

	/**
	 * Inverse l'état d'une case
	 * @param caseAInverser Case sur laquelle on va agir
	 * @return Renvoi 1 si la case s'est allumée ou -1 si elle s'est éteinte
	 */
	private int inverserEtat(Position caseAInverser)
	{
		this.cases[caseAInverser.getLigne()][caseAInverser.getColonne()] =! this.cases[caseAInverser.getLigne()][caseAInverser.getColonne()];
		if (this.cases[caseAInverser.getLigne()][caseAInverser.getColonne()]) return 1;
		return -1;
	}
	
	/**
	 * @param caseCiblee Case centrale du motif qui va changer
	 */
	public void inverserCasesAutourDe(Position caseCiblee)
	{
		inverserCase(caseCiblee);
		for (Direction direction : Direction.values())
			if ((caseCiblee.adjacente(direction).getLigne() >= 0) && (caseCiblee.adjacente(direction).getLigne() < this.hauteur) &&
				(caseCiblee.adjacente(direction).getColonne() >= 0) && (caseCiblee.adjacente(direction).getColonne() < this.largeur))
			inverserCase(caseCiblee.adjacente(direction));
	}

	/**
	 * @return la largeur
	 */
	public int getLargeur()
	{
		return this.largeur;
	}
	
	/**
	 * @return la hauteur
	 */
	public int getHauteur()
	{
		return this.hauteur;
	}
	
	/**
	 * @return le nombre de cases allumées
	 */
	public int getCasesAllumees()
	{
		return this.casesAllumees;
	}
}
