package fr.iutvalence.java.tp.tilepuzzle;

import java.awt.Color;

import javax.swing.*;

public class JButtonCase extends JButton
{
	private static final Color CASE_SELECTIONNEE = Color.GREEN;
	private static final Color CASE_NON_SELECTIONNEE = Color.ORANGE;

	private Position position;

	private boolean etat;

	public JButtonCase(int ligne, int colonne)
	{
		this.position = new Position(ligne, colonne);
		this.modifierEtat(false);
	}
	
	public Position obtenirPosition()
	{
		return this.position;
	}
	
	public void modifierEtat(boolean etat)
	{
		this.etat = etat;

			if(etat)
				this.setBackground(CASE_SELECTIONNEE);
			else
				this.setBackground(CASE_NON_SELECTIONNEE);
	}

	public void inverser()
	{
		if (this.getBackground() == CASE_NON_SELECTIONNEE)
		{
			this.setBackground(CASE_SELECTIONNEE);
			this.etat = true;
		}
		else
		{
			this.setBackground(CASE_NON_SELECTIONNEE);
			this.etat = false;
		}
	}
}
