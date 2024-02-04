public class Test_Fonction {
    private String instruction;
    private boolean legal;

    public static void main(String[] args) {
        boolean One = false;
        boolean Two = false;
        boolean Three = false;
        String instru = "   link  -5";//<----------ici pour tester
        int i,j;
        XA robot = new XA();
        robot.addline("Copy 12 x");

        robot.addline(" 12 x");

        robot.addline(" addi 5 3 T ");
        robot.next();
        robot.next();
        robot.next();
        //robot.getRegister().getRegister("T").Set(5);
        PrimarRegister listRegiste = new PrimarRegister();


            instru = instru.trim();
            instru = instru.toUpperCase();
            String[] argum = instru.split("\\s+");


            if (argum[0].equals("LINK")) {
                System.out.println("link");
                if (argum.length == 2) {//On vérifie que LINK à bien 2 arguments(lui et sont entier )
                    One = true;
                    if (argum[1].charAt(0) == '-') {
                        System.out.println("- baby");

                        if (!(argum[1].length() > 1) ){
                            System.out.println("juste un moin");
                            One=false;
                        }
                        else{
                            for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                    One = false;
                                    System.out.println("JUMP -int ");
                                }
                            }
                        }
                    } else {//cas positif
                        for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                One = false;
                                System.out.println("JUMP int");
                            }
                        }

                    }
                }

            }

            if (argum[0].equals("JUMP")) {
                System.out.println("JUMP 1");
                if (argum.length == 2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )
                    One = true;

                    //cas négatif
                    if (argum[1].charAt(0) == '-') {
                        System.out.println("- baby");

                        if (!(argum[1].length() > 1) ){
                            System.out.println("juste un moin");
                           One=false;
                        }
                        else{
                            for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                    One = false;
                                    System.out.println("JUMP -int ");
                                }
                            }
                        }
                    } else {//cas positif
                        for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                            if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                One = false;
                                System.out.println("JUMP int");
                            }
                        }

                    }
                }
                System.out.println("JUMP 2");
                if (One) {
                        int taille = robot.lastIndex() + Integer.parseInt(argum[1]); // Jump et FJMP ce déplace entre les lignes. Il faut vérifier que le déplacement est possible
                        //Donc position initiale + entier duquel tu veux te déplacer=taille
                        System.out.println("JUMP mytho");
                        System.out.println(taille);
                        if (taille < 0) {
                            One = false;
                            System.out.println("JUMP <0");
                        } else if (taille > robot.getRobotBOX().size()-1) {


                            One = false;
                            System.out.println("JUMP >taille");
                        }
                        else {
                            System.out.println("oeeeooo");
                            System.out.println(robot.getRobotBOX().get(taille).ToSrting());
                        }
                }



            }





            if (argum[0].equals("FJMP")) {
                System.out.println("FJMP 1");
                if (argum.length == 2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )

                    System.out.println(robot.getRegister().getRegister("T").getValue());
                    if (robot.getRegister().getRegister("T").getValue() == 0) {
                        One = true;
                        //cas négatif
                        if (argum[1].charAt(0) == '-') {
                            System.out.println("- baby2");
                            if (!(argum[1].length() > 1) ){
                                System.out.println("juste un moin");
                                One=false;
                            }
                            else {
                                for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                    if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                        One = false;
                                        System.out.println("FJMP -int ");
                                    }
                                }
                            }
                        } else {//cas positif
                            for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                    One = false;
                                    System.out.println("FJMP int");
                                }
                            }

                        }
                        System.out.println("FJMP 2");
                        if (One) {
                            int taille = robot.lastIndex() + Integer.parseInt(argum[1]); // Jump et FJMP ce déplace entre les lignes. Il faut vérifier que le déplacement est possible
                            //Donc position initiale + entier duquel tu veux te déplacer=taille
                            System.out.println("JUMP mytho");
                            System.out.println(taille);
                            if (taille < 0) {
                                One = false;
                                System.out.println("FJMP <0");

                            }
                            else if (taille > robot.getRobotBOX().size() - 1) {
                                One = false;
                                System.out.println("FJMP >taille");

                            }
                            else {
                                System.out.println("oeeeooo");
                                System.out.println(robot.getRobotBOX().get(taille).ToSrting());
                            }
                        }
                    }
                    else {
                        System.out.println("not build for this shit");
                    }
                }
            }




            if (argum[0].equals("COPY")) {
                System.out.println("cOPY 1");
                if (argum.length == 3) { //COPY à bien 2 arguments+ lui meme
                    if (listRegiste.contains(argum[2]) && !argum[2].equals("M")) {//on verifie si le 2e argument de copy est un registre qui n'est pas M(pas le droit d'ecrire sur M vue qu'on est que 4)
                        Two = true;
                        if (argum[1].charAt(0) == '-') {
                            System.out.println("- baby2");
                            if (!(argum[1].length() > 1) ){
                                System.out.println("juste un -");
                                Two=false;
                            }
                            else {
                                for (i = 1; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien unif (Character.isDigit(argum[1].charAt(1).)
                                    if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                        Two = false;
                                        System.out.println("copy -int ");
                                    }
                                }
                            }
                        }
                        else {


                            System.out.println("cOPY 2");
                            for (i = 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                                if (!Character.isDigit(argum[1].charAt(i))) {//Passe au vérificateur chaque String qui le compose
                                    Two = false;

                                }
                                System.out.println("cOPY INT");

                            }
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
                            Three = true;
                            for (i = 1; i < 3; i++) {
                                if (argum[i].charAt(0) == '-') {
                                    if (!(argum[i].length() > 1) ){
                                        System.out.println("juste un moin");
                                        Three=false;
                                    }
                                    else {
                                        for (j = 1; j < argum[i].length(); j++) {
                                            if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                                Three = false;
                                                System.out.println("ADDI -int ");
                                            }
                                        }
                                    }
                                } else {

                                    for (j = 0; j < argum[i].length(); j++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                                        System.out.println("entier ?");
                                        if (!Character.isDigit(argum[i].charAt(j))) {//Passe au vérificateur chaque String qui le compose
                                            System.out.println("???????????,");
                                            Three = false;
                                            System.out.println("ADDI int");
                                        }

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
    

    
    

