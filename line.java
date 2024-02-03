
public class line{
    private String instruction;
    private boolean legal;

    /**
     * Construie une ligne de commande pour le robot. Comme dans le jeux, elle doit indiquer au joueur si sa ligne est règlementaire ou non 
     * afin de pouvoir lancer le code
     * 
     * @param instru  l'instruction de type String donner par l'utilisateur
     * 
     * @throws NullPointerException Si instru est null
     */
    public line(String instru) {
        if (instru == null) {
            throw new NullPointerException("line: instru est null");
        }

        PrimarRegister listRegiste = new PrimarRegister();
        instru = instru.trim();//Peut importe comment tu le rentres, il ne contera pas les espaces, juste les chara
        instru = instru.toUpperCase(); //Peut importe comment tu le rentres, il le met en MAJ
        String[] argum = instru.split("\\s+");
        this.instruction = instru;
        this.legal = false;
        int i;

        //Verifiaction des commandes à 1 argument (FJMP,JUMP ou LINK)
        if (argum[0].equals("JUMP") || argum[0].equals("FJMP") || argum[0].equals("LINK")) {

            if (argum.length == 2) {//On vérifie que JUMP,FJMP ou LINK à bien 2 arguments(lui et son entier ) car nous sommes 4, ils sont obligatoirement des int
                this.legal = true;
                for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un

                    if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                        this.legal = false;
                    }
                }


            }


        }

        //Verifiaction des commandes à 2 argument (COPY)
        if (argum[0].equals("COPY")) {
            if (argum.length == 3) { //COPY à bien 2 arguments + lui meme

                if (listRegiste.contains(argum[2]) && !argum[2].equals("M")) {//on verifie si le 2e argument de copy est un registre qui n'est pas M(pas le droit d'ecrire sur M vue qu'on est que 4)
                    this.legal = true;
                    for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                        if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                            this.legal = false;
                        }

                    }
                    if (!this.legal) {
                        this.legal = listRegiste.contains(argum[1]);
                    }


                }
            }
        }

        //Verifiaction des commande à 3 argument (ADDI,MULI et SUBI)
        if (argum[0].equals("ADDI") || argum[0].equals("MULI") || argum[0].equals("SUBI")) {
            if (argum.length == 4) { //ADDI,MULI et SUBI ont  bien 3 arguments+ eux meme
                if (listRegiste.contains(argum[3]) && !argum[3].equals("M")) {//on verifie si le 3e argument de copy est un registre
                    this.legal = true;
                    for (i = 1; i < 3; i++) {
                        for (int j = 1; j < argum[i].length(); j++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                            if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                this.legal = false;
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
}


    /**
     * Vérifie si la ligne rentrer est bel et bien considèrer comme etant réglementaire pour la suite du jeux
     * 
     * @return True si legal est true, false sinon
     */
    public boolean Legal(){
        return this.legal;
    }


    /**
     * Renvoie simplement l'instruction qui a servit à construire cette ligne.(en MAJ car le jeux fait tout en MAJ)
     * 
     * @return instruction
     */
    public String ToSrting(){
        return this.instruction.toUpperCase();
    }

   
   
}

