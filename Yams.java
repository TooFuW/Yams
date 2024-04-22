import java.util.Random;
import java.util.ArrayList;

public class Yams {
    //On initialise les variables
    private ArrayList<Integer> dices;
    private ArrayList<Integer> keepedDice;

    public Yams(){
        //Constructeur d'une partie de yams
        this.dices = new ArrayList<Integer>();
        this.keepedDice = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.dices.add(0);
        }
    }

    public ArrayList<Integer> getDices() {
        //On renvoie la liste des dés
        return this.dices;
    }

    public void rollDices() {
        //On lance les dés
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            if (!this.keepedDice.contains(i)) {
                this.dices.set(i, rand.nextInt(6) + 1);
            }
        }
    }

    public int chooseDice() {
        //On choisir les dés à garder
        System.out.print("Entrez les numéros (emplacements) des dés à garder (séparés par des espaces) : ");
        String input = System.console().readLine();
        this.keepedDice = new ArrayList<Integer>();
        for (String s : input.split(" ")) {
            if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5")) {
                this.keepedDice.add(Integer.parseInt(s) - 1);
            }
            else {
                return 0;
            }
        }
        return 1;
    }

    public void verificationCombos() {
        //On cherche les combinaisons de score possibles par rapport aux dés actuels
    }

    public void displayDices() {
        //On affiche les dés
        System.out.println("Les dés sont : " + this.dices.get(0) + " - " + this.dices.get(1) + " - " + this.dices.get(2) + " - " + this.dices.get(3) + " - " + this.dices.get(4));
    }

    public static void main(String[] args) {
        //On exécute le jeu
        Yams y = new Yams();
        y.rollDices();
        y.displayDices();
        y.chooseDice();
        //On teste ScoreSheet
        ScoreSheet s = new ScoreSheet(3);
        s.displayScoresheet();
        s.setScoreSheet(0, 0, 1);
        s.displayScoresheet();
    }
}

class ScoreSheet {
    //On initialise les variables
    private ArrayList<ArrayList<ArrayList<Integer>>> scoresheet;

    public ScoreSheet(int nb_joueurs) {
        //Constructeur d'un scoresheet contenant les résultats de tous les joueurs
        this.scoresheet = new ArrayList<ArrayList<ArrayList<Integer>>>();
        for (int i = 0; i < nb_joueurs; i++) {
            this.scoresheet.add(new ArrayList<>());
            for (int j = 0; j < 14; j++) {
                this.scoresheet.get(i).add(new ArrayList<>());
            }
        }
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getScoreSheet() {
        //On renvoie le scoresheet
        return this.scoresheet;
    }

    public void setScoreSheet(int numJoueur, int numCategorie, int score) {
        //On met un score au bon endroit dans le ScoreSheet (se référencer à binding.txt)
        this.scoresheet.get(numJoueur).get(numCategorie).add(score);
    }

    public void displayScoresheet()  {
        //On affiche les scores de tous les joueurs
        for(int i = 0; i < this.scoresheet.size(); i++) {
            System.out.println("Joueur " + i + " : " + this.scoresheet.get(i));
        }
    }
}

class Partie {
    //On initialise les variables
    ScoreSheet scores;
    Yams partie;

    public Partie(int nb_joueurs) {
        //Constructeur d'une partie de yams
        this.scores = new ScoreSheet(nb_joueurs);
        this.partie = new Yams();
    }

    public static void main(String[] args) {
        //On exécute la boucle du jeu
        Partie p = new Partie(2);
        while (true) { //True pour l'instant, mais on mettra une vérification de si la feuille de score est remplie à la palce
            for (int i = 0; i < p.scores.getScoreSheet().size(); i++) {
                System.out.println("Au joueur " + i + " de jouer :");
                //On lance une première fois les dés
                p.partie.rollDices();
                p.partie.displayDices();
                //On sélectionne les dés pour le deuxième roll
                while (p.partie.chooseDice() == 0) {
                    p.partie.chooseDice();
                }
                p.partie.rollDices();
                p.partie.displayDices();
                //On sélectionne les dés pour le dernier roll
                while (p.partie.chooseDice() == 0) {
                    p.partie.chooseDice();
                }
                p.partie.rollDices();
                p.partie.displayDices();
                //On met le score au bon endroit
                p.scores.setScoreSheet(i, 1, 5);//Valeurs placeholder
                p.scores.displayScoresheet();
            }
        }
    }
}