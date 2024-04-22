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
        System.out.print("Entrez les numéros des dés à garder (séparés par des espaces) : ");
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
        System.out.println(this.keepedDice);
        return 1;
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
    }
}

class ScoreSheet {
    //On initialise les variables
    private ArrayList<ArrayList<ArrayList<Integer>>> scoresheet;

    public ScoreSheet(int nb_joueurs) {
        //Constructeur d'un scoresheet contenant les résultats de tous les joueurs
        for(int i = 0; i < nb_joueurs; i++){
            this.scoresheet.add(new ArrayList<ArrayList<Integer>>());
            for(int j = 1; j <= 14; j++){
                this.scoresheet.get(i).add(new ArrayList<Integer>());
                this.scoresheet.get(i).get(j).add(i);
                this.scoresheet.get(i).get(j).add(null);
            }
        }
    }

    public void displayScoresheet()  {
        
    }

}

