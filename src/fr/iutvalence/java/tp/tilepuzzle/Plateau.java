package fr.iutvalence.java.tp.tilepuzzle;

import java.util.Set;

/**
 * Définit un plateau de jeu, il est défini par une hauteur et une largeur,
 * représentées en nombre de cases, chacune pouvant être allumée ou éteinte.
 */
public class Plateau
{
	/**
	 * Largeur par défaut d'un plateau
	 */
	public static final int TAILLE_PAR_DEFAUT = 3;
	/**
	 * Valeur d'une case éteinte sur la grille
	 */
	public static final boolean CASE_ETEINTE = false;

	/**
	 * Largeur du plateau
	 */
	private final int largeur;

	/**
	 * Hauteur du plateau
	 */
	private final int hauteur;

	/**
	 * Nombre de cases allumées sur le plateau
	 */
	private int nombreDeCasesAllumees;

	/**
	 * Tableau des cases du plateau
	 */
	private final Set<Position> cases;

	/**
	 * Crée un nouveau plateau a partir de la hauteur, de la largeur et de
	 * l'ensemble de cases fournis
	 * 
	 * @param hauteur
	 *            Hauteur du plateau
	 * @param largeur
	 *            Largeur du plateau
	 * @param cases
	 *            Ensemble des cases allumées
	 */
	public Plateau(int hauteur, int largeur, Set<Position> cases)
	{
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.cases = cases;
		this.nombreDeCasesAllumees = this.cases.size();
	}

	/**
	 * Retourne si oui ou non la case se trouve sur le plateau
	 * 
	 * @param position
	 *            Position a vérifier
	 * @return true si la position est valide, false si elle ne l'est pas
	 */
	public boolean estPositionValide(Position position)
	{
		return ((position.getLigne() >= 0)
				&& (position.getLigne() < this.hauteur) && ((position
				.getColonne() >= 0) && (position.getColonne() < this.largeur)));
	}

	/**
	 * Inverse l'état de la case indiquée et met à jour le compteur
	 * 
	 * @param caseAInverser
	 *            Case à inverser
	 */
	private void inverserCase(Position caseAInverser)
	{
		if (!estPositionValide(caseAInverser))
			return;
		this.inverserEtat(caseAInverser);
		this.nombreDeCasesAllumees = this.cases.size();
	}

	/**
	 * Inverse l'état d'une case
	 * 
	 * @param caseAInverser
	 *            Case sur laquelle on va agir
	 */
	private void inverserEtat(Position caseAInverser)
	{
		if (this.cases.contains(caseAInverser))
		{
			this.cases.remove(caseAInverser);
		}
		else
		{
			this.cases.add(caseAInverser);
		}
	}

	/**
	 * @param caseCiblee
	 *            Case centrale du motif qui va changer
	 */
	public void inverserCasesAutourDe(Position caseCiblee)
	{
		inverserCase(caseCiblee);
		for (Direction direction : Direction.values())
			if ((caseCiblee.adjacente(direction).getLigne() >= 0)
					&& (caseCiblee.adjacente(direction).getLigne() < this.hauteur)
					&& (caseCiblee.adjacente(direction).getColonne() >= 0)
					&& (caseCiblee.adjacente(direction).getColonne() < this.largeur))
				inverserCase(caseCiblee.adjacente(direction));
	}

	/**
	 * @return la largeur
	 */
	public int obtenirLargeur()
	{
		return this.largeur;
	}

	/**
	 * @return la hauteur
	 */
	public int obtenirHauteur()
	{
		return this.hauteur;
	}

	/**
	 * @return le nombre de cases allumées
	 */
	public int obtenirNombreDeCasesAllumees()
	{
		return this.nombreDeCasesAllumees;
	}

	public boolean estCaseAllumee(Position position)
	{
		return this.cases.contains(position);
	}

	public void reinitialiser()
	{
		this.cases.clear();
	}
}
