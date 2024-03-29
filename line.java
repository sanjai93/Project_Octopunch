
public class line{
    private String instruction;
    private boolean legal;

    /**
     * Construie une ligne de commande pour le robot. Comme dans le jeu, elle doit indiquer au joueur si sa ligne est règlementaire ou non
     * afin de pouvoir lancer le code
     * 
     * @param instru  l'instruction de type String donnée par l'utilisateur
     * 
     * @throws NullPointerException Si instru est null
     */
    public line(String instru) {
        if (instru == null) {
            throw new NullPointerException("line: instru est null");
        }
       
        PrimarRegister listRegiste = new PrimarRegister();
        instru = instru.trim();//Peu importe comment tu le rentres, il ne comtera pas les espaces, juste les chara
        instru = instru.toUpperCase(); //Peu importe comment tu le rentres, il le met en MAJ
        this.instruction = instru;
        this.legal = false;
        String[] operateur = {"<",">","="};
        String[] argum = instru.split("\\s+");
        int i,j;

        if ((argum[0].equals("HALT") || argum[0].equals("NOOP"))  && argum.length == 1){
            this.legal = true;
        }
        
        //Verifiaction des commandes à 1 argument ( JUMP ou GRAB,etc)
        if (argum[0].equals("LINK") || argum[0].equals("GRAB") || argum[0].equals("MARK") ) {
            if (argum.length == 2) {//On vérifie qu'il y à bien 2 arguments(lui et son entier)
                this.legal = true;
                if (argum[1].charAt(0) == '-') {

                    if (!(argum[1].length() > 1) ){
                        this.legal=false;
                    }
                    else{
                        for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                this.legal = false;
                            }
                        }
                    }
                } else {//cas positif
                    for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                        if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                            this.legal = false;
                        }
                    }

                }
            }

        }


        if (argum[0].equals("JUMP") || argum[0].equals("FJMP")) {
            if (argum.length == 2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )
                this.legal = true;

                //cas négatif
                if (argum[1].charAt(0) == '-') {

                    if (!(argum[1].length() > 1) ){
                        this.legal=false;
                    }
                    else{
                        for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                this.legal = false;
                            }
                        }
                    }
                } else {//cas positif
                    for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                        if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                            this.legal = false;
                        }
                    }

                }
            }
        }








        if (argum[0].equals("COPY")) {
            if (argum.length == 3) { //COPY à bien 2 arguments+ lui meme
                if (listRegiste.contains(argum[2]) && !argum[2].equals("M")) {//on verifie si le 2e argument de copy est un registre qui n'est pas M(pas le droit d'ecrire sur M vue qu'on est que 4)
                    this.legal = true;
                    if (argum[1].charAt(0) == '-') {
                        if (!(argum[1].length() > 1) ){
                            this.legal=false;
                        }
                        else {
                            for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                    this.legal = false;
                                }
                            }
                        }
                    }
                    else {


                        for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                this.legal = false;

                            }

                        }
                    }
                    if (!this.legal) {
                        this.legal = listRegiste.contains(argum[1]);
                    }


                }
            }

        }





        if (argum[0].equals("ADDI") || argum[0].equals("MULI") || argum[0].equals("SUBI") || argum[0].equals("DIVI") || argum[0].equals("MODI") || argum[0].equals("SWIZ") ) {
            if (argum.length == 4) { //ADDI,MULI et SUBI ont  bien 3 arguments+ eux meme

       
                if (listRegiste.contains(argum[3]) && !argum[3].equals("M")) {//on verifie si le 3e argument de copy est un registre
                    this.legal = true;
                
                    for (i = 1; i < 3; i++) {
                   
                        if (argum[i].charAt(0) == '-') {
                            if (!(argum[i].length() > 1) ){
                                this.legal=false;
                            }
                            else {
                                for (j = 1; j < argum[i].length(); j++) {
                                    if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                        this.legal = false;
                                    }
                                }
                            }
                        } else {

                            for (j = 0; j < argum[i].length(); j++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                                if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                    this.legal = false;
                                }

                            }
                        }


                        if (!this.legal) { //Si tu n'es pas un nombre, es-tu un registre ?
                            this.legal = listRegiste.contains(argum[i]);
                        }

                        if (!this.legal) {//Si tu es arriver jusqu'ici , c'est que tu es ni un registre ni un nombre,BREF invalide
                            break;
                        }
                    }

                }


            }
        }
    

        if (argum[0].equals("TEST") ) {
            if (argum.length == 4) { 

                this.legal = true;
                for (i = 1; i < 4; i=i+2) {// Vérifie les argument de test
                    if (argum[i].charAt(0) == '-') {
                        if (!(argum[i].length() > 1) ){
                            this.legal=false;
                        }
                        else {
                            for (j = 1; j < argum[i].length(); j++) {
                                if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                    this.legal = false;
                                }
                            }
                        }
                    } else {

                        for (j = 0; j < argum[i].length(); j++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                            if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                this.legal = false;
                            }

                        }
                    }


                    if (!this.legal) { //Si tu n'es pas un nombre, es-tu un registre ?
                        this.legal = listRegiste.contains(argum[i]);
                    }



                    if (!this.legal) {//Si tu es arriver jusqu'ici , c'est que tu es ni un registre ni un nombre,BREF invalide
                        break;
                    }



                }
   
                if(this.legal){//Si tu à passé tout les tests on va vérifier l'operateur au millieu 
                    this.legal=false;
                    for(String op : operateur){
                        if(op.equals(argum[2])){
                            this.legal=true;
                        }
                    }
                }
            }
        }
    }


    /**
     * Vérifie si la ligne rentrer est bel et bien considèrer comme etant réglementaire pour la suite du jeux
     * 
     * @return True si legal est true, false sinon
     */
    public boolean Legal(){
        return this.legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    /**
     * Renvoie simplement l'instruction qui a servit à construire cette ligne.(en MAJ car le jeux fait tout en MAJ)
     * 
     * @return instruction
     */
    public String ToSrting(){
        return this.instruction;
    }

    public static void main(String[] args) {
        line line = new line("TEST M > M");
        System.out.println(line.legal);

    }

   
   
}

