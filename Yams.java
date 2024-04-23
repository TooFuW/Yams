import java.util.Random;
import java.util.ArrayList;

public class Yams {
    //On initialise les variables
    private ArrayList<Integer> dices;
    private ArrayList<Integer> keepedDice;

    public Yams(){
        //Constructeur d'une partie de yams
        this.dices = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.dices.add(0);
        }
        this.keepedDice = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.keepedDice.add(i);
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
            if (this.keepedDice.contains(i)) {
                this.dices.set(i, rand.nextInt(6) + 1);
            }
        }
    }

    public int chooseDice() {
        //On choisir les dés à relancer
        System.out.print("Entrez les numéros (emplacements) des dés à relancer (séparés par des espaces) ou rien si vous voulez tout garder : ");
        String input = System.console().readLine();
        this.keepedDice = new ArrayList<Integer>();
        if (input.equals("")) {
            //On retourne 2 quand il n'y a pas d'input pour que l'on arrête de relancer la dés
            return 2;
        }
        for (String s : input.split(" ")) {
            if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5")) {
                this.keepedDice.add(Integer.parseInt(s) - 1);
            }
            else {
                //On retourne 0 si l'input est invalide
                return 0;
            }
        }
        //On retourne 1 quand l'input est valide et que les dès sont sauvegardés
        return 1;
    }

    public int count(ArrayList<Integer> liste, int elem ){
        int count = 0;
        for(int i=0; i<liste.size(); i++){
            if(liste.get(i) == elem){
                count++;
            }
        }
        return count;
    }

    public int somme(ArrayList<Integer> liste){
        int sum = 0;
        for(int i=0; i<liste.size();i++){
            sum += liste.get(i);
        }
        return sum;
    }

    public ArrayList<ArrayList<Integer>> verificationCombos() {
        //On cherche les combinaisons de score possibles par rapport aux dés actuels

        ArrayList<ArrayList<Integer>> combos = new ArrayList<>();


        ArrayList<Integer> as = new ArrayList<>();
        ArrayList<Integer> deux = new ArrayList<>();
        ArrayList<Integer> trois = new ArrayList<>();
        ArrayList<Integer> quatre = new ArrayList<>();
        ArrayList<Integer> cinq = new ArrayList<>();
        ArrayList<Integer> six = new ArrayList<>();
        ArrayList<Integer> brelan = new ArrayList<>();
        ArrayList<Integer> carre = new ArrayList<>();
        ArrayList<Integer> full = new ArrayList<>();
        ArrayList<Integer> ps = new ArrayList<>();
        ArrayList<Integer> gs = new ArrayList<>();
        ArrayList<Integer> yams = new ArrayList<>();
        ArrayList<Integer> chance = new ArrayList<>();

        as.add(1);
        as.add(this.count(this.dices, 1));

        deux.add(2);
        deux.add(2*this.count(this.dices, 2));

        trois.add(3);
        deux.add(3*this.count(this.dices, 3));
        





        return combos;
    }

    public void resetDices() {
        //On remet les éléments à zero
        this.dices = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.dices.add(0);
        }
        this.keepedDice = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            this.keepedDice.add(i);
        }
    }

    public void displayDices() {
        //On affiche les dés
        System.out.println("Les dés sont : " + this.dices.get(0) + " - " + this.dices.get(1) + " - " + this.dices.get(2) + " - " + this.dices.get(3) + " - " + this.dices.get(4));
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
                this.scoresheet.get(i).get(j).add(j + 1);
                this.scoresheet.get(i).get(j).add(null);
            }
        }
        this.displayScoresheet();
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getScoreSheet() {
        //On renvoie le scoresheet
        return this.scoresheet;
    }

    public void setScoreSheet(int numJoueur, ArrayList<ArrayList<Integer>> score) {
        //On met un score au bon endroit dans le ScoreSheet (se référencer à binding.txt)
        this.displayScoresheet();
        System.out.println("\nEntrez le numéro de la catégorie dans laquelle vous voulez ajouter votre score :");
        System.out.println("- As : 1");
        System.out.println("- 2 : 2");
        System.out.println("- 3 : 3");
        System.out.println("- 4 : 4");
        System.out.println("- 5 : 5");
        System.out.println("- 6 : 6");
        System.out.println("- Brelan : 7");
        System.out.println("- Carre : 8");
        System.out.println("- Full : 9");
        System.out.println("- Petite suite : 10");
        System.out.println("- Grande suite : 11");
        System.out.println("- Yams : 12");
        System.out.println("- Chance : 13");
        int choixCategorie = 0;
        while (choixCategorie == 1) {
            try {
                choixCategorie = Integer.parseInt(System.console().readLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Entrez un nombre valide :");
                this.setScoreSheet(numJoueur, score);
            }
        }
        if (this.scoresheet.get(numJoueur).get(choixCategorie).get(1) == null) {
            this.scoresheet.get(numJoueur).get(choixCategorie).add(score.get(choixCategorie).get(1));
        }
        else {
            System.out.println("Vous avez déja un score pour cette catégorie, veuillez choisir une catégorie vide :");
            this.setScoreSheet(numJoueur, score);
        }
    }

    public void displayScoresheet()  {
        //On affiche les scores de tous les joueurs
        System.out.println();
        for(int i = 0; i < this.scoresheet.size(); i++) {
            System.out.print("Joueur " + i + " : ");
            //On crée une copie de scoresheet
            ArrayList<ArrayList<String>> temp = new ArrayList<>();
            ArrayList<String> temp2;
            for (int j = 0; j < this.scoresheet.get(i).size(); j++) {
                switch (this.scoresheet.get(i).get(j).get(0)) {
                    case 1:
                        temp2 = new ArrayList<String>();
                        temp2.add("As");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 2:
                        temp2 = new ArrayList<String>();
                        temp2.add("2");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 3:
                        temp2 = new ArrayList<String>();
                        temp2.add("3");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 4:
                        temp2 = new ArrayList<String>();
                        temp2.add("4");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 5:
                        temp2 = new ArrayList<String>();
                        temp2.add("5");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 6:
                        temp2 = new ArrayList<String>();
                        temp2.add("6");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 7:
                        temp2 = new ArrayList<String>();
                        temp2.add("Bonus");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 8:
                        temp2 = new ArrayList<String>();
                        temp2.add("Brelan");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 9:
                        temp2 = new ArrayList<String>();
                        temp2.add("Carré");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 10:
                        temp2 = new ArrayList<String>();
                        temp2.add("Full");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 11:
                        temp2 = new ArrayList<String>();
                        temp2.add("Petite suite");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 12:
                        temp2 = new ArrayList<String>();
                        temp2.add("Grande suite");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 13:
                        temp2 = new ArrayList<String>();
                        temp2.add("Yams");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    case 14:
                        temp2 = new ArrayList<String>();
                        temp2.add("Chance");
                        if (this.scoresheet.get(i).get(j).get(1) == null) {
                            temp2.add(null);
                        }
                        else {
                            temp2.add(this.scoresheet.get(i).get(j).get(1).toString());
                        }
                        temp.add(temp2);
                        break;
                    default:
                        break;
                }
            }
        System.out.println(temp);
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
        Partie game = new Partie(2);
        while (true) { //True pour l'instant, mais on mettra une vérification de si la feuille de score est remplie à la place
            for (int i = 0; i < game.scores.getScoreSheet().size(); i++) {
                System.out.println("\nAu joueur " + i + " de jouer :");
                //On lance une première fois les dés
                game.partie.rollDices();
                game.partie.displayDices();
                //On sélectionne les dés pour le deuxième roll
                int choixJoueur = game.partie.chooseDice();
                //Si le joueur rentre un input invalide on redemande
                while (choixJoueur == 0) {
                    choixJoueur = game.partie.chooseDice();
                }
                //S'il ne rentre rien on ne relance pas les dés et on fini le tour
                if (choixJoueur == 2) {
                    ;
                }
                //Sinon on lance une dernière fois les dés
                else {
                    game.partie.rollDices();
                    game.partie.displayDices();
                    while (game.partie.chooseDice() == 0) {
                        game.partie.chooseDice();
                    }
                    game.partie.rollDices();
                }
                game.partie.displayDices();
                //On ajoute le score au bon endroit
                game.scores.setScoreSheet(i, game.partie.verificationCombos());
                game.scores.displayScoresheet();
                game.partie.resetDices();
            }
        }
    }
}