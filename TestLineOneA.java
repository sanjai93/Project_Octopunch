public class TestLineOneA {
    private String instruction;
    private boolean legal;

    public static void main(String[] args) {
        boolean One = false;
        boolean Two = false;
        boolean Three = false;
        String[] instru = {"    ADdI 100 M X"};
        int i;
        XA robot = new XA();
        PrimarRegister listRegiste = new PrimarRegister();

        for (String co : instru) {
            co = co.trim();
            co = co.toUpperCase();
            String[] argum = co.split("\\s+");


            if (argum[0].equals("JUMP") || argum[0].equals("FJMP") || argum[0].equals("LINK")) {

                if (argum.length == 2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )
                    One = true;
                    for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un

                        if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                            One = false;
                        }
                    }


                }


            }


            if (argum[0].equals("COPY")) {
                System.out.println("cOPY 1");
                if (argum.length == 3) { //COPY à bien 2 arguments+ lui meme
                    if (listRegiste.contains(argum[2]) && !argum[2].equals("M")) {//on verifie si le 2e argument de copy est un registre qui n'est pas M(pas le droit d'ecrire sur M vue qu'on est que 4)
                        Two = true;
                        System.out.println("cOPY 2");
                        for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                Two = false;

                            }
                            System.out.println("cOPY INT");

                        }
                        if (!Two) {
                            Two = listRegiste.contains(argum[1]);
                            System.out.println("cOPY REGISTER");
                        }


                    }
                }

            }
                if (argum[0].equals("ADDI") || argum[0].equals("MULI") || argum[0].equals("SUBI")) {
                    System.out.println("ADDI 1");
                    if (argum.length == 4) { //ADDI,MULI et SUBI ont  bien 3 arguments+ eux meme
                        System.out.println("ADDI 2 taille");
                        if (listRegiste.contains(argum[3]) && !argum[3].equals("M")) {//on verifie si le 3e argument de copy est un registre
                            System.out.println("ADDI 3");
                            Three = true;
                            for (i = 1; i < 3; i++) {

                                for (int j = 0; j < argum[i].length(); j++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                                    System.out.println("entier ?");
                                    if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                        System.out.println("???????????,");
                                        Three = false;
                                        System.out.println("ADDI int");
                                    }

                                }

                                if (!Three) { //Si tu n'es pas un nombre, es-tu un registre ?
                                    System.out.println("ADDI REGISTER");
                                    Three = listRegiste.contains(argum[i]);
                                }

                                if (!Three) {//Si tu es arriver jusqu'ici , c'est que tu es ni un registre ni un nombre,BREF invalide
                                    System.out.println("ADDI marche po");
                                    break;
                                }
                            }
                        }
                    }
                }


        }
            /*
             * 
             //Verifiaction des commande à 2 argument (FJMP et JUMP)
             
               
                
        
             */
        if (One) {
            System.out.println("Soit un FJMP soit un JUMP");
        }
        if (Two) {
            System.out.println("On part sur un COPY/LINK nan ?");
        }
        if (Three) {
            System.out.println("On part sur un 3 arg");
        }
        if (!One && !Two && !Three) {
            System.out.println("Nop");
        }
    }
}
    

    
    

