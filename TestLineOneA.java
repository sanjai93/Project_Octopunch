public class TestLineOneA{
        private String instruction;
        private boolean legal;
    
        public static void main(String[] args) {
            boolean One= false;
            boolean Two =false;
            boolean Three =false;
            String[] instru = {"  SUBI                                               x                                T                      f "};
            int i;
            PrimarRegister listRegiste = new PrimarRegister();
          
            for (String co : instru) {
                co=co.trim();
                co=co.toUpperCase();
                String[] words = co.split("\\s+");

                
                if(words[0].equals("JUMP") || words[0].equals("FJMP")){
    
                    if (words.length==2) {//On vérifie que JUMP ou FJMP à bien 2 arguments(lui et sont entier )
                        One=true;
                        for (i= 0; i < words[1].length(); i++) {//Verifie si 2e element(l'entier toujours sous forme de String) en est bien un
                            
                            if(!Character.isDigit(words[1].charAt(i))){//Passe au vérificateur chaque String qui le compose
                               One=false;
                            }
                        }
        
                        
                    }
                   
        
                }


            if(words[0].equals("COPY")|| words[0].equals("LINK") ){
              
                if (words.length==3){ //COPY à bien 2 arguments+ lui meme
                    Two=true;
                    for(i=1;i<3;i++){
                        if(!listRegiste.contains(words[i])){ //Les arguments qui suivent COPY sont bien des register Primaire
                            Two=false;
                        }                
                    }
                }
            }

            if(words[0].equals("ADDI")|| words[0].equals("MULI") || words[0].equals("SUBI")){
              
                if (words.length==4){ //COPY à bien 2 arguments+ lui meme
                    Three=true;
                    for(i=1;i<4;i++){
                        if(!listRegiste.contains(words[i])){ //Les arguments qui suivent COPY sont bien des register Primaire
                            Three=false;
                        }                
                    }
                }
            }
            /*
             * 
             //Verifiaction des commande à 2 argument (FJMP et JUMP)
             
               
                
        
             */

            if(One){
                System.out.println("Soit un FJMP soit un JUMP");
            }
            if(Two){
                System.out.println("On part sur un COPY/LINK nan ?");
            }
            if(Three){
                System.out.println("On part sur un 3 arg");
            }
            if(!One && !Two && !Three){
                System.out.println("Nop");
            }
        }
    }
}
    

    
    

