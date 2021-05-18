package fr.eni.quelPokemon.bo;

import java.util.Random;

/**
 * Classe Type
 * <ul>
 *     <li><b>nom :</b> Le nom du type</li>
 *     <li><b>fort :</b> un tableau donnant l'ensemble des types contre lesquelles notre instance n'a l'avantage</li>
 *     <li><b>faible :</b> un tableau donnant l'ensemble des types contre lesquelles notre instance n'a pas l'avantage</li>
 * </ul>
 */
public class Type {
    protected String nom;
    protected String[] fort;
    protected String[] faible;

    public Type(String nom, String[] fort, String[] faible) {
        this.nom = nom;
        this.fort = fort;
        this.faible = faible;
    }

    public String getNom() {
        return nom;
    }

    /**
     * Permet de calculer les multiplicateur de dégats, tel que les coups critiques, et les avantages de type.<br>
     * x 2 s'il y a un avantage de type.<br>
     * x 0.5 s'il y a un désavantage de type.<br>
     * x 1.5 s'il y a un coup critique.<br>
     * Le pourcentage de coups et d'échecs critique est calculer aléatoirement.
     * @param pourcentagePv
     * @param attaque
     * @param p
     * @return le multiplicateur d'attaque
     */
    public float multiplicateurDegats(int pourcentagePv, Attaque attaque, Pokemon p) {
        Random rd = new Random();
        int frappe = rd.nextInt(101);
        float coupCritique = 1;
        if(frappe>=85){
            coupCritique = (float)1.5;
        }

        if (pourcentagePv>25 && frappe<10){
            coupCritique = 0;
        } else if (pourcentagePv<25 && frappe<25){
            coupCritique = 0;
        }

        for (int i = 0; i < fort.length; i++) {
            if (fort[i].equals(p.getType().getNom())){
                attaque.afficherType(coupCritique,2);
                System.out.printf("C'est très efficace. ");
                return (float)2*coupCritique;
            }
        }

        for (int i = 0; i < faible.length; i++) {
            if (faible[i].equals(p.getType().getNom())){
                attaque.afficherType(coupCritique,(float)0.5);
                System.out.printf("Ce n'est pas très efficace. ");
                return (float)0.5*coupCritique;
            }
        }
        attaque.afficherType(coupCritique,1);
        return (float)1*coupCritique;
    }


}
