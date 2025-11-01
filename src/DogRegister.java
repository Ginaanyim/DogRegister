import java.util.ArrayList;

public class DogRegister {
    // Skapa en instans av InputReader för att läsa inmatning
    private InputReader input = new InputReader();

    // instanser av OwnerCollection & DogCollection för att hantera ägare & hundar
    private OwnerCollection ownerCollection = new OwnerCollection();
    private DogCollection dogCollection = new DogCollection();

    private void start() {
        setUp();
        runCommandLoop();
        shutDown();
    }

    private void setUp() {
        System.out.println("Hello and welcome to the dog register! \n");
        System.out.println("Choose by following commands: \n");
        System.out.println("* Register new owner");
        System.out.println("* Remove owner");
        System.out.println("* Register new dog");
        System.out.println("* Remove dog");
        System.out.println("* List dogs");
        System.out.println("* List owners");
        System.out.println("* Increase age");
        System.out.println("* Give dog to owner");
        System.out.println("* Remove dog from owner");
        System.out.println("* Exit");
    }

    private void runCommandLoop() {
        String command;
        do {
            command = readCommand();
            handleCommand(command);
        } while (!command.equals("exit"));
    }

    private String readCommand() {
        System.out.print("Enter command: ");
        String userInput = input.readString("").toLowerCase();

        return userInput;
    }

    private void handleCommand(String command) {
        switch (command) {
            case "exit":
                break;
            case "register new owner":
                registerNewOwner();
                break;
            case "remove owner":
                removeOwner();
                break;
            case "register new dog":
                registerNewDog();
                break;
            case "remove dog":
                removeDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list owners":
                listOwners();
                break;
            case "increase age":
                increaseAge();
                break;
            case "give dog to owner":
                giveDogToOwner();
                break;
            case "remove dog from owner":
                removeDogFromOwner();
                break;
            default:
                System.out.println("Error: Wrong command! ");
        }
    }

    private void registerNewOwner() {
        String nameOfNewOwner;
        boolean acceptedName = false;

        // Loop för kontroll av namn
        do {
            nameOfNewOwner = input.readString("Enter owner's name: ");
            if (!isEmpty(nameOfNewOwner)) {
                acceptedName = true;
                nameOfNewOwner = normalizeString(nameOfNewOwner);
            }
        } while (!acceptedName);

        // Skapa en ny ägare med normaliserade namnet
        Owner newOwner = new Owner(nameOfNewOwner);

        // Lägg till ny ägare i OwnerCollection
        boolean ownerAdded = ownerCollection.addOwner(newOwner);
        if (ownerAdded) {
            System.out.println(nameOfNewOwner + " is added.");
        } else {
            System.out.println("Error:  " + nameOfNewOwner + " is already registered! ");
        }
    }

    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: no owners registered. ");
            return;
        }
        String ownerToRemove = input.readString("Enter the name of owner that you want to remove: ");

        if (isEmpty(ownerToRemove)) {
            return;
        }

        // Normalisera namn då de används senare
        ownerToRemove = normalizeString(ownerToRemove);

        Owner o = ownerCollection.getOwner(ownerToRemove); // Hämta ägare från ownerColection med normaliserat namn

        if (o == null) {
            System.out.println("Error: " + ownerToRemove + " is not registered! ");
            return;
        }

        // Hämta listan över hundar som tillhör den ägaren (o)
        ArrayList<Dog> dogs = o.getDogs();

        // For each loop för att iterera över ägarens hundar
        for (Dog dog : dogs) {
            dog.setOwner(null); // Ta bort ägare från hunden
            String dogName = dog.getName();
            dogCollection.removeDog(dogName); // Ta bort hunden från hundregistret
        }
        boolean ownerRemoved = ownerCollection.removeOwner(ownerToRemove);

        if (ownerRemoved) {
            System.out.println(ownerToRemove + " is removed from the register. ");
        }
    }

    private void removeDog() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs are registered. ");
            return;
        }

        String dogName = readNonEmptyString("Enter the name of the dog to remove: ");
        if (dogName == null) {
            return;
        }

        dogName = normalizeString(dogName);

        Dog dogToRemove = dogCollection.getDog(dogName);
        if (dogToRemove == null) {
            System.out.println("Error: the dog " + dogName + " is not registered! ");
            return;
        }

        if (dogToRemove.getOwner() != null) {
            dogToRemove.setOwner(null);
        }

        dogCollection.removeDog(dogToRemove);
        System.out.println("The dog " + dogName + " has been removed from the register. ");
    }

    private void registerNewDog() {
        String nameNewDog = getValidInput("What's the name of the dog that you wish to add? ");

        // Kontrollera om hunden redan är registrerad
        if (dogCollection.getDog(nameNewDog) != null) {
            System.out.println("Error: This dog is already registered. ");
            return;
        }

        // Skapa en ny hund och lägg till i registret
        String breedNewDog = getValidInput("Enter the breed: ");
        int ageNewDog = input.readInt("Enter the age: ");

        // Läs in vikt som text så att vi kan rensa bort bokstäver
        String weightInput = input.readString("Enter the dog's weight (you can write e.g. 20kg): ");
        weightInput = weightInput.replaceAll("[^0-9]", ""); // tar bort allt som inte är siffror

        if (weightInput.isEmpty()) {
            System.out.println("Error: please enter a valid number for weight!");
            return; // avslutar metoden om inga siffror hittades
        }

        int weightNewDog = Integer.parseInt(weightInput);

        Dog newDog = new Dog(normalizeString(nameNewDog), normalizeString(breedNewDog), ageNewDog, weightNewDog);
        boolean successfullyAdded = dogCollection.addDog(newDog);

        if (successfullyAdded) {
            System.out.println(nameNewDog + " is now registered. ");
        }
    }

    // Metod för att lista alla hundar
    private void listDogs() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: There are no dogs registered! ");
            return;
        }
        double minTailLength = input.readDouble("What is the minimum tail length? ");
        // Filtrera hundarna baserat på svanslängd
        ArrayList<Dog> dogs = dogCollection.filterDogsByTailLength(minTailLength);

        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }

    private void listOwners() {
        ArrayList<Owner> owners = ownerCollection.getOwners();
        if (owners.isEmpty()) {
            System.out.println("Error: There are no owners registered! ");
            return;
        }
        // Skriv ut information om varje ägare
        for (Owner owner : owners) {
            System.out.println(owner);
        }
    }

    private void increaseAge() {
        ArrayList<Dog> dogs = dogCollection.getDogs();

        if (dogs.isEmpty()) {
            System.out.println("Error: There are no dogs registered! ");
            return;
        }

        String dogIncreasedAge = input.readString("Enter name of the dog that you want to increase the age of: ");

        if (isEmpty(dogIncreasedAge)) {
            return;
        }

        boolean updatedDog = false;
        dogIncreasedAge = normalizeString(dogIncreasedAge);

        // Öka åldern för den valda hunden med ett år
        for (Dog dog : dogs) {
            if (dogIncreasedAge.equals(dog.getName())) {
                dog.increaseAge(1);
                System.out.println("You have increased the age one year of " + dogIncreasedAge);
                updatedDog = true;
                break;
            }
        }
        if (!updatedDog) {
            System.out.println("Error: the chosen dog is not registered! ");
        }
    }

    private void giveDogToOwner() {
        // Hämta listor över hundar och ägare
        ArrayList<Dog> dogs = dogCollection.getDogs();
        ArrayList<Owner> owners = ownerCollection.getOwners();

        // Kontrollera om det finns registrerade ägare eller hundar
        if (owners.isEmpty() || dogs.isEmpty()) {
            System.out.println("Error: There are no owners or dogs registered!");
            return;
        }

        // Läs in namnet på hunden från användaren och normalisera det
        String dogName = normalizeString(input.readString("Enter name of the dog: "));
        Dog dog = dogCollection.getDog(dogName);

        if (dog == null || dog.getOwner() != null) {
            System.out.println("Error - "
                    + (dog == null ? dogName + " has not been registered." : dogName + " already has an owner! "));
            return;
        }

        // Läs in namnet på ägaren från användaren
        String ownerName = normalizeString(input.readString("Enter name of the owner: "));
        Owner owner = ownerCollection.getOwner(ownerName);

        // Kontroll om ägaren finns registrerad
        if (owner == null) {
            System.out.println("Error: " + ownerName + " is not registered! ");
            return;
        }
        dog.setOwner(owner);
        System.out.println(ownerName + " is now set as the owner to " + dogName + "!");
    }

    public void removeDogFromOwner() {

        // Hämta listor över ägare och hundar från registren
        ArrayList<Owner> owners = ownerCollection.getOwners();
        ArrayList<Dog> dogs = dogCollection.getDogs();

        if (dogs.isEmpty()) {
            System.out.println("Error: There are no dogs registered! ");
            return;
        }
        if (owners.isEmpty()) {
            System.out.println("Error: There are no owners registered! ");
            return;
        }
        // Läs in namnet på hunden från användaren
        String dogName = input.readString("Enter name of the dog that you want to remove from owner: ");
        if (isEmpty(dogName)) {
            return;
        }
        dogName = normalizeString(dogName);

        // Hämta hunden från registret
        Dog dog = dogCollection.getDog(dogName);

        // Kontroll om hunden finns registrerad
        if (dog == null) {
            System.out.println("Error: " + dogName + " is not registered! ");
            return;
        }
        // Kontrollera om hunden har en ägare och ta bort ägaren
        if (dog.getOwner() != null) {
            dog.setOwner(null);
            System.out.println(dogName + " is removed from the owner! ");
        }
    }

    // Metod för att läsa in en icke-tom sträng
    private String readNonEmptyString(String prompt) {
        String value;
        do {
            value = input.readString(prompt);
        } while (isEmpty(value)); // Loopar tills en icke-tom sträng hittats
        return value;
    }

    // Metod för att få giltig input från användaren
    private String getValidInput(String prompt) {
        String inputString;
        boolean validInput = false;

        do {
            inputString = input.readString(prompt);
            if (!isEmpty(inputString)) {
                validInput = true;
                inputString = normalizeString(inputString);
            }
        } while (!validInput);
        return inputString;
    }

    private void shutDown() {
        System.out.println("The program is shutting down, goodbye!");
    }

    private String normalizeString(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    // Kontrollera om en sträng är tom eller enbart består av blanksteg
    private boolean isEmpty(String string) {
        if (string.isEmpty() || string.trim().isEmpty()) { // Empty?
            System.out.println("Error: Blank input! ");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new DogRegister().start();
    }
}