public class Test1 {
        public void compare(String shape){
            if("null".equals(shape)){
                System.out.println("Hi");
            }
            if(shape.equals("null")){
                System.out.println("Hello");
            }
    }
    public static void main(String[] args){
        Test1 t = new Test1();
        t.compare(null);
    }
}
