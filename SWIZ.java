public class SWIZ {

    public void SWIZ(String A, String B, Register C, PrimarRegister Reg){
        int valA,valB,i=1,convert,TailleA,TailleB;
        String num = "";
        String c;
        String SvalA="",SvalB="";

        if(Reg.contains(A)){
            valA=Reg.getRegister(A).getValue();
            SvalA +=Reg.getRegister(A).getValue();
            TailleA= SvalA.length();

        }
        else{
            //coupe les
            valA=Integer.parseInt(A);
            SvalA +=A;
            TailleA= SvalA.length();
        }

        if(Reg.contains(B)){
            valB=Reg.getRegister(B).getValue();
            SvalB += Reg.getRegister(B).getValue();
            TailleB= SvalB.length();
        }
        else{
            valB=Integer.parseInt(B);
            SvalB += B;
            TailleB= SvalB.length();
        }

        if(valA != 0 && valB !=0){
         
            if(SvalB.charAt(0)=='-' ^ SvalA.charAt(0)=='-' ){
                num += "-";
                i++;
            }
            else if(SvalB.charAt(0)=='-' && SvalA.charAt(0)=='-'){
                i++;
            }
            for(;i<=TailleB;i++){
         
                convert=SvalB.charAt(i-1)-'0';
                
            
                if (convert>0 && convert<=TailleA){
      
                    num += SvalA.charAt(TailleA-(convert));
              ;
                    
                }
                else { 
                    num += "0";
                 
                }
            }
            convert=Integer.parseInt(num);
        
            C.Set(convert);
        }
        else{
 
            C.Set(0);
        }
        
    }
        
    public static void main(String[] args) {
        PrimarRegister register =new PrimarRegister();
        register.getRegister("M").Set(-4321);
        register.getRegister("X").Set(6789);
        SWIZ swiz =new SWIZ();
        System.out.println();
        swiz.SWIZ("X","-4321",register.getRegister("T"),register);
        System.out.println(register.getRegister("T").getValue() );
    }
}
