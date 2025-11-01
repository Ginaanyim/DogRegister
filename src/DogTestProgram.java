
//Virginia Anyim vian2657

public class DogTestProgram {

    public static void main(String[] args) {

        Dog myDog = new Dog("Bruno", "Staffy", 3, 20);
        System.out.println(myDog.toString());

        Dog taxDog = new Dog("Elsa", "Tax", 2, 15);
        System.out.println(taxDog.toString());

        myDog.increaseAge(0);
        System.out.println("New Age: " + myDog.getAge());

        taxDog.increaseAge(0);
        System.out.println("New Age " + taxDog.getAge());

    }

}
