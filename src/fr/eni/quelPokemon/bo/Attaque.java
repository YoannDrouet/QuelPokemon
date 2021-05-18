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

    /**
     * Permet de formater l'affichage de l'attaque en fonction des coups critiques et des avantages de types
     * @param coupCritique
     * @param multiplicateur
     */
    public void afficherType(float coupCritique, float multiplicateur){
        if (coupCritique == 1.5){
            System.out.printf("%s (Coup critique !) - %.0f points de dégats%n", this.nom, this.degats*multiplicateur*coupCritique);
        }else if (coupCritique == 0){
            System.out.printf("%s et rate son attaque%n", this.nom);
        }else {
            System.out.printf("%s - %.0f points de dégats%n", this.nom, this.degats*multiplicateur);
        }
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
}
