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
        System.out.print("Entrez les emplacements des dés à relancer (séparés par des espaces) ou rien si vous voulez tout garder : ");
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

    public boolean is_more_than(ArrayList<Integer> liste, int iterations){
        int count=0;
        for(int i=1; i<=6; i++){
            count = this.count(this.dices, i);
            if (count>=iterations){
                return true;
            }
        }
        return false;
    }

    public int somme(ArrayList<Integer> liste){
        int sum = 0;
        for(int i=0; i<liste.size();i++){
            sum += liste.get(i);
        }
        return sum;
    }

    public boolean is_full(ArrayList<Integer> liste){
        boolean is_brelan = false;
        boolean is_pair = false;
        int deja_fait = 7;

        int count=0;
        for(int i=1; i<=6; i++){
            count = this.count(this.dices, i);
            if (count>=3){
                is_brelan = true;
                deja_fait=i;
            }
        }

        for(int i=1; i<=6; i++){
            count = this.count(this.dices, i);
            if (count>=2 && i!=deja_fait) {
                is_pair= true;
                
            }
        }

        return is_brelan && is_pair;
    }

    public boolean is_grande_suite(ArrayList<Integer> liste){
        liste.sort(null);
        ArrayList<Integer> test1 = new ArrayList<>();
        ArrayList<Integer> test2 = new ArrayList<>();

        for(int i=1; i<=5;i++){
            test1.add(i);
        }

        for(int i=2; i<=6;i++){
            test1.add(i);
        }

        return(liste.equals(test1) || liste.equals(test2));
    }


    public boolean is_petite_suite(ArrayList<Integer> liste){
        liste.sort(null);
        int count = 1;

        for(int i=0; i<liste.size()-1; i++){
            if (count >= 4){
                return true;
            }
            if(liste.get(i) == liste.get(i+1) + 1){
                count ++;
            }
            else{

                if(liste.get(i) == liste.get(i+1)){
                ;
            }
            else{
                count=1;
            }
        }

        }
        return false;


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
        trois.add(3*this.count(this.dices, 3));

        quatre.add(4);
        quatre.add(4*this.count(this.dices, 4));

        cinq.add(5);
        cinq.add(5*this.count(this.dices, 5));
        
        six.add(6);
        six.add(6*this.count(this.dices, 6));

        brelan.add(7);
        if(this.is_more_than(this.dices, 3)){ // s'il y a plus de 3 cartes du meme type alors il y a un brelan et le score est la somme des 5 dés. sinon 0 
            brelan.add(this.somme(this.dices));
        }
        else{brelan.add(0);}


        carre.add(8);
        if(this.is_more_than(this.dices, 4)){ // s'il y a plus de 3 cartes du meme type alors il y a un brelan et le score est la somme des 5 dés. sinon 0 
            carre.add(this.somme(this.dices));
        }
        else{carre.add(0);}

        full.add(9);
        if(is_full(this.dices)){
            full.add(25);
        }else{full.add(0);}


        ps.add(10);
        if(this.is_petite_suite(this.dices)){
            ps.add(25);
        }else{ps.add(0);}

        gs.add(11);
        if(this.is_grande_suite(this.dices)){
            gs.add(40);
        }else{gs.add(0);}


        yams.add(12);
        if(this.is_more_than(this.dices, 5)){
            yams.add(50);
        }else{yams.add(0);}

        chance.add(13);
        chance.add(this.somme(this.dices));

        combos.add(as);
        combos.add(deux);
        combos.add(trois);
        combos.add(quatre);
        combos.add(cinq);
        combos.add(six);
        combos.add(brelan);
        combos.add(carre);
        combos.add(full);
        combos.add(ps);
        combos.add(gs);
        combos.add(yams);
        combos.add(chance);
        
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
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getScoreSheet() {
        //On renvoie le scoresheet
        return this.scoresheet;
    }

    public void setScoreSheet(int numJoueur, ArrayList<ArrayList<Integer>> score) {
        //On met un score au bon endroit dans le ScoreSheet (se référencer à binding.txt)
        this.displayScoresheet();
        System.out.println("\nEntrez le numéro d'une catégorie vide dans laquelle vous voulez écrire votre score :");
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
        while (choixCategorie == 0) {
            try {
                choixCategorie = Integer.parseInt(System.console().readLine());
                if (choixCategorie >= 7) {
                    choixCategorie += 1;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Entrez un nombre valide :");
                this.setScoreSheet(numJoueur, score);
            }
        }
        System.out.println(score);
        if (this.scoresheet.get(numJoueur).get(choixCategorie - 1).get(1) == null) {
            this.scoresheet.get(numJoueur).get(choixCategorie - 1).set(1, score.get(choixCategorie).get(1));
        }
        else {
            System.out.println("Vous avez déja un score pour cette catégorie, veuillez choisir une catégorie vide :");
            this.setScoreSheet(numJoueur, score);
        }
    }

    public void setBonus() {
        //On regarde pour tous les joueurs s'ils gagnent ou non le bonus
        int total = 0;
        for (int i = 0; i < this.scoresheet.size(); i++) {
            for (int j = 0; j < this.scoresheet.get(i).size(); j++) {
                total += this.scoresheet.get(i).get(j).get(1); 
            }
            if (total >= 63) {
                this.scoresheet.get(i).get(6).set(1, 35);
            }
            else {
                this.scoresheet.get(i).get(13).set(1, 0);
            }
            total = 0;
        }
    }

    public boolean estRemplie() {
        //On regarde si le jeu est fini
        for (int i = 0; i < this.scoresheet.size(); i++) {
            for (int j = 0; j < this.scoresheet.get(i).size(); j++) {
                if (this.scoresheet.get(i).get(j).get(1) == null) {
                    return false;
                }
            }
        }
        return true;
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
        while (!game.scores.estRemplie()) {
            for (int i = 0; i < game.scores.getScoreSheet().size(); i++) {
                System.out.println("\nAu joueur " + i + " de jouer :");
                //On lance une première fois les dés
                game.partie.rollDices();
                game.partie.displayDices();
                //On sélectionne les dés pour le deuxième roll
                int choixJoueur = game.partie.chooseDice();
                //Si le joueur rentre un input invalide on redemande
                while (choixJoueur == 0) {
                    System.out.println("L'entrée est invalide, veuillez entrer les emplacements des dés que vous souhaitez relancer ou aucun si vous ne voulez pas relancer");
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
                        System.out.println("L'entrée est invalide, veuillez entrer les emplacements des dés que vous souhaitez relancer ou aucun si vous ne voulez pas relancer");
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
        //On calcule le bonus
        game.scores.setBonus();
        System.out.println("La partie est finie, les scores sont :");
        game.scores.displayScoresheet();
    }
}