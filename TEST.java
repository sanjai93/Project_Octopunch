public class TEST {
    public void TEST(String A, String B, String C, PrimarRegister Reg){
        int valA,valC;
        boolean test;
        if(Reg.contains(A)){
            valA=Reg.getRegister(A).getValue();
        }
        else{
            valA=Integer.parseInt(A);
        }

        if(Reg.contains(C)){
            valC=Reg.getRegister(C).getValue();
        }
        else{
            valC=Integer.parseInt(C);
        }
        switch (B) {
            case "<":
                test=valA<valC;
                break;
            case ">":
                test=valA>valC;
                break;
            default:
                test=(valA==valC);
                break;
        }
        Reg.getRegister("T").Set(test ? 1 : 0);

    }

    public static void main(String[] args) {
        PrimarRegister register= new PrimarRegister();
        register.getRegister("X").Set(-18);
        register.getRegister("F").Set(-18);
        TEST test =new TEST();
        test.TEST("X", "=", "F", register);
        System.out.println(register.getRegister("T").getValue());
    }
}
