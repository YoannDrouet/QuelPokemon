package fr.eni.quelPokemon.bo;

import java.util.Random;

public class Pokemon {
    private String nom;
    private int tailles;
    private int poids;
    private int pv;
    private Attaque attaque01;
    private Attaque attaque02;
    private Dresseur dresseur;

    private int copiePV;

    public Pokemon(String nom, int tailles, int poids, int pv, Attaque attaque01, Attaque attaque02) {
        this.nom = nom;
        this.tailles = tailles;
        this.poids = poids;
        this.pv = pv;
        this.attaque01 = attaque01;
        this.attaque02 = attaque02;

        this.copiePV = pv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTailles() {
        return tailles;
    }

    public void setTailles(int tailles) {
        this.tailles = tailles;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public Attaque getAttaque01() {
        return attaque01;
    }

    public void setAttaque01(Attaque attaque01) {
        this.attaque01 = attaque01;
    }

    public Attaque getAttaque02() {
        return attaque02;
    }

    public void setAttaque02(Attaque attaque02) {
        this.attaque02 = attaque02;
    }

    public Dresseur getDresseur() {
        return dresseur;
    }

    public int getCopiePV() {
        return copiePV;
    }

    public void setCopiePV(int copiePV) {
        this.copiePV = copiePV;
    }

    public void setDresseur(Dresseur dresseur) {

        if (this.dresseur == null){
            this.dresseur = dresseur;
        }else {
            this.dresseur.supprimerPokemon(this);
            this.dresseur = dresseur;
        }
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
        System.out.printf("%s (%s) ayant %dpv qui connait :%n    ", this.nom, this.dresseur.getNom(), this.pv);
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

            if (p.getCopiePV()>0){
                p.attaque(this);
            }
        }while (p.getCopiePV() > 0 && this.getCopiePV() >0);
        if (p.getCopiePV() > 0) {
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
        if ((this.copiePV/this.pv*100)>25){
            if (proba<=9){
                System.out.printf("%s utilise ",this.nom);
                attaque01.afficher();
                p.setCopiePV((p.getCopiePV()-attaque01.getDegats()));
            }else {
                System.out.printf("%s utilise ",this.nom);
                attaque02.afficher();
                p.setCopiePV((p.getCopiePV()-attaque02.getDegats()));
            }
        } else{
            if (proba>=5){
                System.out.printf("%s utilise ",this.nom);
                attaque02.afficher();
                p.setCopiePV((p.getCopiePV()-attaque02.getDegats()));
            }else {
                System.out.printf("%s utilise ",this.nom);
                attaque01.afficher();
                p.setCopiePV((p.getCopiePV()-attaque01.getDegats()));
            }
        }
        if (p.getCopiePV()>0){
            System.out.printf("Il reste %dpv à %s%n", p.getCopiePV(),p.getNom());
        }
    }
}
