package fr.eni.quelPokemon.bo;

/**
 * Classe Attaque
 * <ul>
 *     <li><b>nom :</b> Le nom de l'attaque</li>
 *     <li><b>degats :</b> Le nombre de points de dégats infligés par cette attaque</li>
 * </ul>
 * @author Yoann Drouet
 */
public class Attaque {
    private String nom;
    private int degats;
    private Type type;

    public Attaque(String nom, Type type, int degats) {
        this.nom = nom;
        this.type = type;
        this.degats = degats;
    }

    public void afficher(){
        System.out.printf("%s - %d points de dégats%n", this.nom, this.degats);
    }

    public void afficherFort(){
        System.out.printf("%s - %d points de dégats%n", this.nom, this.degats*2);
    }

    public void afficherFaible(){
        System.out.printf("%s - %d points de dégats%n", this.nom, this.degats/2);
    }

    public String getNom() {
        return nom;
    }

    public Type getType() {
        return type;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}
