package fr.eni.quelPokemon.bo;

import java.util.Random;

/**
 * Classe Pokemon
 * <ul>
 *     <li><b>nom :</b> Le nom du pokemon</li>
 *     <li><b>tailles :</b> La taille du pokemon en centimètre</li>
 *     <li><b>poids :</b> le poids du pokemon en gramme</li>
 *     <li><b>pv :</b> Les points de vie du pokemon</li>
 *     <li><b>type :</b> Le type du pokemon/li>
 *     <li><b>attaque01 :</b> La première attaque du pokemon</li>
 *     <li><b>attaque02 :</b> La deuxième attaque du pokemon</li>
 *     <li><b>dresseur :</b> Le dresseur de ce pokemon</li>
 * </ul>
 * @author Yoann Drouet
 */
public class Pokemon{
    private String nom;
    private int tailles;
    private int poids;
    private int pv;
    private Type type;
    private Attaque attaque01;
    private Attaque attaque02;
    private Dresseur dresseur;

    private int pvRestants;

    public Pokemon(String nom, int tailles, int poids, int pv, Type type, Attaque attaque01, Attaque attaque02) {
        this.nom = nom;
        this.tailles = tailles;
        this.poids = poids;
        this.pv = pv;
        this.type = type;
        this.attaque01 = attaque01;
        this.attaque02 = attaque02;

        this.pvRestants = pv;
    }

    public String getNom() {
        return nom;
    }

    public int getTailles() {
        return tailles;
    }

    public int getPoids() {
        return poids;
    }

    public int getPv() {
        return pv;
    }

    public int getPvRestants() {
        return pvRestants;
    }

    public void setPvRestants(int pvRestants) {
        this.pvRestants = pvRestants;
    }

    public void setDresseur(Dresseur dresseur) {

        if (this.dresseur == null){
            this.dresseur = dresseur;
        }else {
            this.dresseur.supprimerPokemon(this);
            this.dresseur = dresseur;
        }
    }

    public Type getType() {
        return type;
    }

    /**
     * Compare deux pokemons
     * @param pokemon
     * @return true si les deux pokemons sont les même, false, s'ils sont différents
     */
    public boolean compareTo(Pokemon pokemon) {
        if (this.nom == pokemon.getNom() &&
            this.tailles == pokemon.getTailles() &&
            this.poids == pokemon.getPoids() &&
            this.pv == pokemon.getPv() &&
            this.attaque01.getNom() == pokemon.attaque01.getNom() &&
            this.attaque02.getNom() == pokemon.attaque02.getNom() &&
            this.dresseur.getNom() == pokemon.dresseur.getNom()){
                return true;
        }else{
            return false;
        }
    }

    public void supprimerDresseur() {
        this.dresseur = null;
    }

    public void afficher() {
        System.out.printf("%s (%dpv) qui connait :%n    ", this.nom, this.pv);
        attaque01.afficher();
        System.out.printf("    ");
        attaque02.afficher();
    }

    /**
     * Lance le combat entre l'instance de pokemon et le pokemon en paramètre.
     * Chaque pokemon attaque chacun son tour, jusqu'à ce que l'un des deux n'est plus de vie.
     * @param p
     */
    public void combat(Pokemon p){
        do {
            this.attaque(p);

            if (p.getPvRestants()>0){
                p.attaque(this);
            }
        }while (p.getPvRestants() > 0 && this.getPvRestants() >0);
        if (p.getPvRestants() <= 0) {
            System.out.printf("%s est KO%n%s à gagné", p.getNom(), this.getNom());
        }else{
            System.out.printf("%s est KO%n%s à gagné", this.getNom(), p.getNom());
        }
    }

    /**
     * Permet de déterminer quel attaque le pokemon en paramètre va utiliser :<br>
     * <ul>
     *     <li><b>S'il à plus de 25% de vie</b><ul>
     *         <li>10% de chance d'utilise là deuxième attaque</li>
     *         <li>90% de chance d'utiliser la première</li>
     *     </ul></li>
     *     <li><b>S'il à moins de 25% de vie</b><ul>
     *         <li>50% de chance d'utilise là deuxième attaque</li>
     *         <li>50% de chance d'utiliser la première</li>
     *      </ul></li>
     * </ul>
     * @param p
     */
    private void attaque(Pokemon p) {
        Random rd = new Random();
        int proba = rd.nextInt(11);
        int frappe = rd.nextInt(101);
        int coupCritique = 1;
        if(frappe>=85){
            coupCritique =2;
        }
        float multiplicateur;
        System.out.printf("%s utilise ",this.nom);
        if ((this.pvRestants /this.pv*100)>25){
            if(frappe<10){
                coupCritique =0;
            }
            if (proba<=9){
                multiplicateur = attaque01.getType().multiplicateurDegats(attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque01.getDegats()*multiplicateur*coupCritique)));
            }else {
                multiplicateur = attaque02.getType().multiplicateurDegats(attaque02, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque02.getDegats()*multiplicateur*coupCritique)));
            }
        } else{
            if(frappe<25){
                coupCritique =0;
            }
            if (proba>=5){
                multiplicateur = attaque01.getType().multiplicateurDegats(attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque01.getDegats()*multiplicateur*coupCritique)));
            }else {
                multiplicateur = attaque02.getType().multiplicateurDegats(attaque02,p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque02.getDegats()*multiplicateur*coupCritique)));
            }
        }
        if (p.getPvRestants()>0){
            System.out.printf("Il reste %dpv à %s%n", p.getPvRestants(),p.getNom());
        }
    }

    public void soignerPokemon(){
        this.pvRestants = this.pv;
    }
}
