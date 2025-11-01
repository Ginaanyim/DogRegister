public class Dog {
    // Instansvariabler för hundens egenskaper
    private String name;
    private String breed;
    private int age;
    private int weight;
    private double tailLength;

    private Owner owner;

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    private static final double TAIL_LENGTH_CALCULATION_DENOMINATOR = 10;

    // Konstruktor för att skapa en hund med specifika egenskaper
    public Dog(String name, String breed, int age, int weight) {
        // Tilldela instansvariablerna och normalisera
        this.name = normalizeString(name);
        this.breed = normalizeString(breed);
        this.age = age;
        this.weight = weight;

        tailLengthCalculation();
    }

    // Metod för att normalisera en sträng
    private String normalizeString(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char currentLetter = str.charAt(i);

            if (currentLetter == ' ' || currentLetter == '-') {
                str = str.substring(0, i + 1) + str.substring(i + 1, i + 2).toUpperCase() + str.substring(i + 2);
            }
        }
        return str;
    }

    // Metod för att beräkna svanslängd baserat på hundras
    private void tailLengthCalculation() {
        this.tailLength = this.age * (double) this.weight / TAIL_LENGTH_CALCULATION_DENOMINATOR;
        String[] dauchshundTailLength = { "Dachshund", "Tax" };

        // Sätt svanslängd till konstant för vissa raser
        for (int i = 0; i < dauchshundTailLength.length; i++) {
            if (this.breed.equals(dauchshundTailLength[i])) {
                this.tailLength = DACHSHUND_TAIL_LENGTH;
            }
        }
    }

    public double getTailLength() {
        return tailLength;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    // Skapa en strängrepresentation av hunden
    public String toString() {
        if (this.owner == null) {
            return this.name + " " + this.breed + " " + this.age + " years " + this.weight + " kg " + this.tailLength
                    + " cm";
        }
        return this.name + " " + this.breed + " " + this.age + " years " + this.weight + " kg " + this.tailLength
                + " cm " + "Owner: " + this.owner.getName();
    }

    // Metod för att öka hundens ålder med ett angivet antal år och beräkna
    // svanslängd
    public void increaseAge(int yearsToIncrease) {
        if (yearsToIncrease > 0) {
            this.age += yearsToIncrease;
        }

        if (this.age + yearsToIncrease < 0) {
            this.age = Integer.MAX_VALUE;
        }
        tailLengthCalculation();
    }

    // Metod för att sätta ägare för hunden och uppdatera ägarens hundlista
    public boolean setOwner(Owner newOwner) {
        if (newOwner != null) {
            if (owner != null) {
                return false;
            } else {
                owner = newOwner;
                owner.addDog(this);
            }
        } else if (owner != null) {
            owner.removeDog(this);
            owner = null;
        } else {
            return false;
        }
        return true;
    }

    // Metod för att hämta och returnera ägare av hunden
    public Owner getOwner() {
        return owner;
    }
}