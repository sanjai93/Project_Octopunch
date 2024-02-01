
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
    public line(String instru){
        if(instru==null){
            throw new NullPointerException("line: instru est null");
        }

        PrimarRegister listRegiste = new PrimarRegister();
        instru=instru.trim();//Peut importe comment tu le rentres, il ne contera pas les espaces, juste les chara
        instru=instru.toUpperCase(); //Peut importe comment tu le rentres, il le met en MAJ
        String[] argum =instru.split("\\s+");
        this.instruction=instru;
        this.legal=false;
        int i;
        
        //Verifiaction des commandes à 1 argument (FJMP et JUMP)
        if(argum[0].equals("JUMP") || argum[0].equals("FJMP")){

            if (argum.length==2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )
                this.legal=true;
                for (i= 0; i < argum[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                    
                    if(!Character.isDigit(argum[1].charAt(i))){//Passe au vérificateur chaque String qui le compose
                        this.legal=false;
                    }
                }

                
            }
           

        }

         //Verifiaction des commande à 2 argument (FJMP et JUMP)
        if(argum[0].equals("COPY") || argum[0].equals("LINK")){
            if (argum.length==3){ //COPY et LINK à bien 2 arguments + lui meme
                this.legal=true;
                for(i=1;i<3;i++){
                    if(!listRegiste.contains(argum[i])){ //Les arguments qui suivent COPY et LINK sont bien des register Primaire
                       this.legal=false; 
                    }                
                }
            }
        }

         //Verifiaction des commande à 3 argument (ADDI,MULI et SUBI)
         if(argum[0].equals("ADDI") || argum[0].equals("MULI") || argum[0].equals("SUBI") ){
            if (argum.length==4){ //ADDI,MULI et SUBI ont  bien 3 arguments+ eux meme
                this.legal=true;
                for(i=1;i<4;i++){
                    if(!listRegiste.contains(argum[i])){ //Les arguments qui suivent ADDI,MULI et SUBI sont bien des register Primaire
                       this.legal=false; 
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

