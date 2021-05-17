package fr.eni.quelPokemon.bo;

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

    public float multiplicateurDegats(Attaque attaque, Pokemon p) {
        for (int i = 0; i < fort.length; i++) {
            if (fort[i].equals(p.getType().getNom())){
                attaque.afficherFort();
                System.out.printf("C'est très efficace. ");
                return (float)2;
            }
        }

        for (int i = 0; i < faible.length; i++) {
            if (faible[i].equals(p.getType().getNom())){
                attaque.afficherFaible();
                System.out.printf("Ce n'est pas très efficace. ");
                return (float)0.5;
            }
        }
        attaque.afficher();
        return (float)1;
    }
}
