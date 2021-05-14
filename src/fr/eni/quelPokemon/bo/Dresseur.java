package fr.eni.quelPokemon.bo;

/**
 * Classe Dresseur
 * <ul>
 *     <li><b>nom :</b> Le nom du dresseur</li>
 *     <li><b>pokemons :</b> Le tableau de pokemons qu'il possède, limité à 6</li>
 * </ul>
 * @author Yoann Drouet
 */
public class Dresseur {
    private String nom;
    private Pokemon[] pokemons;

    public Dresseur(String nom) {
        this.nom = nom;
        pokemons = new Pokemon[6];
    }

    public void afficher(){
        if (this.nbPokemons() == 0){
            System.out.printf("Le dresseur %s ne possède pas encore de pokemon%n", this.nom);
        }else {
            System.out.printf("Le dresseur %s possède %d pokemon(s) :%n", this.nom, this.nbPokemons());
            for (int i = 0; i < this.nbPokemons(); i++) {
                System.out.printf(" - ");
                pokemons[i].afficher();
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    /**
     * Compte le nombre de pokemon de l'instance de dresseur
     * @return le total de pokemon
     */
    public int nbPokemons(){
        int nb = 0;
        for (int i = 0; i < pokemons.length; i++) {
            if (pokemons[i] != null){
                nb++;
            }
        }
        return nb;
    }

    /**
     * Commence par vérifier que cette instance de dresseur à moins de 6 pokemons.
     * Permet ensuite de rajouter le pokemon en paramètre dans le tableau de pokemons.
     * Incrémente ensuite le nombre de pokémon
     * @param pokemon
     */
    public void capture(Pokemon pokemon){
        if (this.nbPokemons()<6){
            for (int i = 0; i < pokemons.length; i++) {
                if (pokemons[i] == null){
                    pokemons[i] = pokemon;
                    pokemon.setDresseur(this);
                    break;
                }
            }
        }else{
            System.out.println("Vous avez atteint le nombre maximum de pokemons");
        }
    }

    /**
     * Commence par vérifier que cette instance de dresseur a au moins un pokemon.
     * Recherche ensuite le pokemon en paramètre, et le supprime du tableau de pokemon.
     * Décrémente le nombre de pokemon de cette instance de dresseur.
     * @param pokemon
     */
    public void supprimerPokemon(Pokemon pokemon) {
        if (this.nbPokemons()>0){
            for (int i = 0; i < pokemons.length; i++) {
                if (pokemons[i].compareTo(pokemon)){
                    pokemons[i].supprimerDresseur();
                    pokemons[i] = null;
                    break;
                }
            }
        }else {
            System.out.println("Vous n'avez pas de pokemons à supprimer");
        }
    }

    /**
     * Commence par vérifier que les deux instance de dresseur possèdent bien les pokemons qu'ils échangent.
     * Supprimer ensuite les dresseurs  des instances de ces deux pokemons.
     * Provoque la suppression du pokemon dans chaques instance de dresseurs.
     * capture ensuite le pokemon de l'autre dresseur.
     * @param leMien
     * @param d
     * @param leSien
     */
    public void echange(Pokemon leMien, Dresseur d, Pokemon leSien){
        if (this.verification(leMien) && d.verification(leSien)){
            leMien.setDresseur(null);
            leSien.setDresseur(null);

            this.capture(leSien);
            d.capture(leMien);

            System.out.println("Echange réalisé avec succès");
        }else{
            System.out.println("Echange impossible");
        }
    }

    /**
     * Vérifie que le pokemon se trouve bien dans la liste de pokemons de l'instance de dresseur
     * @param p
     * @return true si le pokemon existe, false sinon
     */
    public boolean verification(Pokemon p){
        if (this.nbPokemons()>0){
            for (int i = 0; i < pokemons.length; i++) {
                if (pokemons[i].compareTo(p)){
                    return true;
                }
            }
        }else {
            System.out.printf("%s n'a pas de pokémon%n", this.nom);
        }
        return false;
    }
}
