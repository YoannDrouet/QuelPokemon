package fr.eni.quelPokemon.main;

import fr.eni.quelPokemon.bo.Attaque;
import fr.eni.quelPokemon.bo.Dresseur;
import fr.eni.quelPokemon.bo.Pokemon;

public class Main {
    public static void main(String[] args) {
        Dresseur sacha = new Dresseur("Sacha");
        Dresseur ondine = new Dresseur("Ondine");
        Attaque statik = new Attaque("Statik",20);
        Attaque paratonnerre = new Attaque("Paratonnerre",75);
        Pokemon pikachu = new Pokemon("Pikachu",40,6000,120,statik,paratonnerre);
        Attaque cran = new Attaque("Cran",10);
        Attaque agitation = new Attaque("Agitation",35);
        Pokemon ratatta = new Pokemon("Ratatta",30,3500,90,cran,agitation);
        Attaque crocFeu = new Attaque("Croc Feu",30);
        Attaque lanceFlammes = new Attaque("Lance Flammes", 85);
        Pokemon dracaufeu = new Pokemon("Dracaufeu",250, 50000, 230, crocFeu, lanceFlammes);

        sacha.capture(pikachu);
        sacha.capture(ratatta);
        ondine.capture(dracaufeu);

        sacha.afficher();
        ondine.afficher();

        sacha.echange(ratatta,ondine,dracaufeu);

        sacha.afficher();
        ondine.afficher();

        dracaufeu.combat(ratatta);
    }
}
