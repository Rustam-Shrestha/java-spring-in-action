package autowirejava;

public class Alien {

    private int age;
    private Computer com;

    public Alien() {
        System.out.println("Alien Object Created");
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {    // Setter Injection
        //System.out.println("Setter called");
        this.age = age;
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }

    public void code() {
        System.out.println("Coding");
        com.compile();
    }

}