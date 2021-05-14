package fr.eni.quelPokemon.bo;

/**
 * Classe Attaque
 * <ul>
 *     <li><b>nom :</b> Le nom de l'attaque</li>
 *     <li><b>degats :</b> Le nombre de points de dégats infligés par cette attaque</li>
 * </ul>
 */
public class Attaque {
    private String nom;
    private int degats;

    public Attaque(String nom, int degats) {
        this.nom = nom;
        this.degats = degats;
    }

    public void afficher(){
        System.out.printf("%s - %d points de dégats%n", this.nom, this.degats);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}
