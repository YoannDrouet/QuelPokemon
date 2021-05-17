package fr.eni.quelPokemon.main;

import fr.eni.quelPokemon.bo.Attaque;
import fr.eni.quelPokemon.bo.Dresseur;
import fr.eni.quelPokemon.bo.Pokemon;
import fr.eni.quelPokemon.bo.Type;

public class Main {
    public static void main(String[] args) {
        Dresseur sacha = new Dresseur("Sacha");
        Dresseur ondine = new Dresseur("Ondine");

        String[] forceFeu = {"Insecte", "Plante", "Glace", "Acier"};
        String[] faibleFeu = {"Feu", "Eau", "Roche", "Dragon"};
        Type feu = new Type("Feu", forceFeu, faibleFeu);

        String[] forceElec = {"Eau", "Vol"};
        String[] faibleElec = {"Plante", "Electrique", "Dragon"};
        Type electrique = new Type("Electrique", forceElec, faibleElec);

        String[] forcePlante = {"Eau", "Sol", "Roche"};
        String[] faiblePlante = {"Feu","Plante", "Poison", "Vol", "Insecte", "Dragon", "Acier"};
        Type plante = new Type("Plante", forcePlante, faiblePlante);

        Attaque statik = new Attaque("Statik", electrique,20);
        Attaque paratonnerre = new Attaque("Paratonnerre",electrique,75);

        Pokemon pikachu = new Pokemon("Pikachu",40,6000,120,electrique, statik,paratonnerre);

        Attaque fouetLiane = new Attaque("Fouet Liane", plante,20);
        Attaque tranchHerb = new Attaque("Tranch' Herbe",plante,75);

        Pokemon bulbizard = new Pokemon("Bulbizard",60,7500,120,plante, fouetLiane,tranchHerb);

        Attaque crocFeu = new Attaque("Croc Feu",feu,30);
        Attaque lanceFlammes = new Attaque("Lance Flammes", feu,85);

        Pokemon dracaufeu = new Pokemon("Dracaufeu",250, 50000, 230, feu, crocFeu, lanceFlammes);

        sacha.capture(pikachu);
        sacha.capture(bulbizard);
        ondine.capture(dracaufeu);

        //sacha.afficher();
        //ondine.afficher();

        //sacha.echange(bulbizard,ondine,dracaufeu);

        //sacha.afficher();
        //ondine.afficher();

        dracaufeu.combat(bulbizard);
    }
}
