package fr.eni.quelPokemon.bo;

import java.util.Random;

/**
 * Classe Pokemon
 * <ul>
 *     <li><b>nom :</b> Le nom du pokemon</li>
 *     <li><b>tailles :</b> La taille du pokemon en centimètre</li>
 *     <li><b>poids :</b> le poids du pokemon en gramme</li>
 *     <li><b>pv :</b> Les points de vie du pokemon</li>
 *     <li><b>niveau :</b> Le niveau du pokemon</li>
 *     <li><b>experience :</b> L'expérience qu'il va falloir au pokémon pour passer au niveau suivant</li>
 *     <li><b>type :</b> Le type du pokemon/li>
 *     <li><b>attaque01 :</b> La première attaque du pokemon</li>
 *     <li><b>attaque02 :</b> La deuxième attaque du pokemon</li>
 *     <li><b>dresseur :</b> Le dresseur de ce pokemon</li>
 * </ul>
 *
 * <ul>
 *     <li><b>xpRestant :</b> Représente la jauge d'expérience du pokémon, lorsqu'elle est égale à la variable expérience il change de niveau</li>
 *     <li><b>pvRestants :</b> Représente la barre de vie du pokémon lors d'un combat</li>
 * </ul>
 * @author Yoann Drouet
 */
public class Pokemon{
    private String nom;
    private int tailles;
    private int poids;
    private int pv;
    private int niveau;
    private int experience;
    private Type type;
    private Attaque attaque01;
    private Attaque attaque02;
    private Dresseur dresseur;

    private int xpAjout;
    private int pvRestants;

    public Pokemon(String nom, int tailles, int poids, int pv, int niveau, Type type, Attaque attaque01, Attaque attaque02) {
        this.nom = nom;
        this.tailles = tailles;
        this.poids = poids;
        this.pv = pv;
        this.niveau = niveau;
        this.type = type;
        this.attaque01 = attaque01;
        this.attaque02 = attaque02;

        this.experience = niveau*10;
        xpAjout = 0;
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

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getXpAjout() {
        return xpAjout;
    }

    public void setXpAjout(int xpAjout) {
        this.xpAjout = xpAjout;
    }

    public int getExperience() {
        return experience;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setExperience(int experience) {
        this.experience = experience;
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
            System.out.printf("%s est KO%n%s à gagné%n", p.getNom(), this.getNom());
            xpAjout(this,p);
        }else{
            System.out.printf("%s est KO%n%s à gagné%n", this.getNom(), p.getNom());
            xpAjout(p, this);
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
     * Appel également la méthode multiplicateurDegats() qui permet d'augmenter au de diminuer les dégats,
     * en fonction des types et des coups et échecs critiques
     * @param p
     */
    private void attaque(Pokemon p) {
        Random rd = new Random();
        int proba = rd.nextInt(11);

        float multiplicateur;
        System.out.printf("%s utilise ",this.nom);
        if ((this.pvRestants /this.pv*100)>25){
            if (proba<=9){
                multiplicateur = attaque01.getType().multiplicateurDegats((this.pvRestants /this.pv*100), attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque01.getDegats()*multiplicateur)));
            }else {
                multiplicateur = attaque02.getType().multiplicateurDegats((this.pvRestants /this.pv*100), attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque02.getDegats()*multiplicateur)));
            }
        } else{
            if (proba>=5){
                multiplicateur = attaque01.getType().multiplicateurDegats((this.pvRestants /this.pv*100), attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque01.getDegats()*multiplicateur)));
            }else {
                multiplicateur = attaque02.getType().multiplicateurDegats((this.pvRestants /this.pv*100), attaque01, p);
                p.setPvRestants((int)(p.getPvRestants()-(attaque02.getDegats()*multiplicateur)));
            }
        }
        if (p.getPvRestants()>0){
            System.out.printf("Il reste %dpv à %s%n", p.getPvRestants(),p.getNom());
        }
    }

    /**
     * Augmente la jauge d'expérience du Pokémon. Et lui permet de passer un niveau s'il a atteint assez d'expérience
     * @param gagnant
     * @param perdant
     */
    public void xpAjout(Pokemon gagnant, Pokemon perdant){
        gagnant.setXpAjout(gagnant.getXpAjout()+perdant.getNiveau());
        System.out.printf("%s gagne %d point d'expérience%n", gagnant.getNom(), perdant.getNiveau());
        if (gagnant.getXpAjout() > gagnant.getExperience()){
            System.out.printf("Félicitations ! %s passe au niveau %d%n", gagnant.getNom(), gagnant.getNiveau()+1);
            gagnant.setXpAjout(gagnant.getXpAjout()-gagnant.getExperience());
            gagnant.lvlUp(this);
        }
    }

    /**
     * Augmente la vie du pokémon qui passe de niveau d'un chiffre aléatoire entre 1 et 3.
     * Réinitialise sa jauge d'expérience et augmente l'expérience à atteindre pour passer au niveau suivant.
     * @param pokemon
     */
    private void lvlUp(Pokemon pokemon) {
        Random rd = new Random();
        int vieEnPlus = rd.nextInt(3)+1;
        System.out.printf("");
        pokemon.setNiveau(pokemon.getNiveau()+1);
        System.out.printf("Il gagne %d point(s) de vie", vieEnPlus);
        pokemon.setPv(vieEnPlus);
        pokemon.setExperience(pokemon.getNiveau()*10);
    }

    public void soignerPokemon(){
        this.pvRestants = this.pv;
    }
}
