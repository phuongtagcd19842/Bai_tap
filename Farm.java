import java.lang.reflect.Array;
import java.util.Properties;
import java.util.Scanner;
import java.lang.Math;

public class Farm {
    public static abstract class Animal
    {
        Scanner sc = new Scanner(System.in);

        //thuoc tinh cua gia suc
        private String series;
        private double weight;
        private int age;

        //phuong thuc khoi tao cho doi tuong
        public Animal() {
        }
        public Animal(String series, double weight, int age) {
            this.series = series;
            this.weight = weight;
            this.age = age;
        }

        //Property cho cac thuoc tinh cua lop doi tuong --> getter and setter
        public String getSeries() {
            return this.series;
        }

        public void setSeries(String value) {
            this.series = value;
        }

        public double getWeight() {
            return this.weight;
        }

        public void setWeight(double value) {
            this.weight = value;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int value) {
            this.age = value;
        }

        //Nhap mot doi tuong
        public void Input() {
            System.out.print("Enter series: ");
            setSeries(sc.nextLine());
            System.out.print("Enter weight: ");
            setWeight(sc.nextDouble());
            System.out.print("Enter age: ");
            setAge(sc.nextInt());
        }

        public String ToString()
        {
            return "Series: "+this.series+", weight: "+this.weight+", age: "+this.age;
        }

        public double AmountOfMilk(double weight, int age)
        {
            double amountOfMilk = 0;
            return amountOfMilk;
        }
    }
    // lop doi tuong: Cow
    public static class Cow extends Animal {
        @Override
        public double AmountOfMilk(double weight, int age)
        {
            double amountOfMilk = 0;
            if(age>=2 && age<=5)
            {
                amountOfMilk = Math.max(0,(weight-age)/10);
            }
            else
            {
                amountOfMilk = 0;
            }
            return amountOfMilk;
        }
    }
    //lop doi tuong: Goat
    public static class Goat extends Animal
    {
        @Override
        public double AmountOfMilk(double weight, int age)
        {
            double amountOfMilk = 0;
            if(age>=1 && age<=2)
            {
                amountOfMilk = weight/10;
            }
            else if(age>2 && age<=4)
            {
                amountOfMilk = weight/15;
            }
            else
            {
                amountOfMilk = 0;
            }
            return amountOfMilk;
        }
    }
    //Danh sach gia suc trong trang trai
    public static void ListOfAnimal(Animal[] animal, int numOfAnimal)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("---Enter the animal's information---");
        for(int i=0; i<numOfAnimal; i++)
        {
            System.out.println("Animal "+(i+1)+": ");
            System.out.print("Type of animal: \n1.Cow\n2.Goat\nYour choice: ");
            int choice = sc.nextInt();
            if(choice == 1)
            {
                animal[i] = new Cow();
            }
            else if(choice == 2)
            {
                animal[i] = new Goat();
            }
            animal[i].Input();
        }
        System.out.println("The list of animal: ");
        for(int i=0; i<numOfAnimal; i++)
        {
            System.out.println(animal[i].ToString());
        }
    }
    //tinh tong luong sua bo
    public static Double SumOfMilkCow(Animal[] animal, int numOfAnimal)
    {
        double sumOfMilkCow = 0;
        for(int i=0; i<numOfAnimal; i++)
        {
            if(animal[i] instanceof Cow)
            {
                sumOfMilkCow = sumOfMilkCow + animal[i].AmountOfMilk(((Cow) animal[i]).getWeight(), ((Cow) animal[i]).getAge());
            }
        }
        return sumOfMilkCow;
    }
    //tinh tong luong sua de
    public static Double SumOfMilkGoat(Animal[] animal, int numOfAnimal)
    {
        double sumOfMilkGoat = 0;
        for(int i=0; i<numOfAnimal; i++)
        {
            if(animal[i] instanceof Goat)
            {
                sumOfMilkGoat = sumOfMilkGoat + animal[i].AmountOfMilk(((Goat) animal[i]).getWeight(), ((Goat) animal[i]).getAge());
            }
        }
        return sumOfMilkGoat;
    }
    //tim gia suc cho luong sua nhieu nhat
    public static void MaxMilkAnimal(Animal[] animal, int numOfAnimal)
    {
        double max = animal[0].AmountOfMilk(((Animal) animal[0]).getWeight(), ((Animal) animal[0]).getAge());
        int index = 0;
        for(int i=0; i<numOfAnimal; i++)
        {
            if(max<animal[i].AmountOfMilk(((Animal) animal[i]).getWeight(), ((Animal) animal[i]).getAge()))
            {
                max = animal[i].AmountOfMilk(((Animal) animal[i]).getWeight(), ((Animal) animal[i]).getAge());
                index = i;
            }
        }
        System.out.println("\nThe animal that gives the most milk:\n"+animal[index].ToString());
        System.out.println("The amount of that animal's milk: "+max);
    }
    //Tim gia suc theo ma so
    public static  void FindAnimalSeries(Animal[] animal, int numOfAnimal)
    {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        System.out.print("Enter series of animal you want to find: ");
        String x = sc.nextLine();
        for(int i=0; i<numOfAnimal; i++)
        {
            if(x.equals(animal[i].getSeries()))
            {
                index = i;
                System.out.println("Found the animal: " + animal[index].ToString());
            }
        }
        if(index == -1)
        {
            System.out.println("Not found the animal");
        }
    }
    //tim gia suc theo tuoi
    public static void FindAnimalAge(Animal[] animal, int numOfAnimal)
    {
        Scanner sc = new Scanner(System.in);
        int index = -1;

        System.out.print("Enter age of animal you want to find: ");
        int x = sc.nextInt();
        for(int i=0; i<numOfAnimal; i++)
        {
            if(x == animal[i].getAge())
            {
                index = i;
                System.out.println("Found the animal: "+animal[index].ToString());
            }
        }
        if(index == -1)
        {
            System.out.println("Not found the animal");
        }
    }
    //Sap xep cac gia suc trong nong trai tang dan theo luong sua
    public static void AscendingMilk(Animal[] animal, int numOfAnimal)
    {
        Animal temp = animal[0];
        for(int i=0; i<numOfAnimal-1; i++)
        {
            for(int j=i+1; j<numOfAnimal; j++)
            {
                if(animal[i].AmountOfMilk(animal[i].getWeight(), animal[i].getAge()) > animal[j].AmountOfMilk(animal[j].getWeight(), animal[j].getAge()))
                {
                    temp = animal[j];
                    animal[j] = animal[i];
                    animal[i] = temp;
                }
            }
        }
    }
    public static void DescendingAge(Animal[] animal, int numOfAnimal)
    {
        Animal temp = animal[0];
        for(int i=0; i<numOfAnimal-1; i++)
        {
            for(int j=i+1; j<numOfAnimal; j++)
            {
                if(animal[i].getAge()<animal[j].getAge())
                {
                    temp = animal[j];
                    animal[j] = animal[i];
                    animal[i] = temp;
                }
            }
        }
    }
    public static void Display(Animal[] animal, int numOfAnimal)
    {
        for(int i=0; i<numOfAnimal; i++)
        {
            System.out.println(animal[i].ToString());
            System.out.println("Amount of milk: "+animal[i].AmountOfMilk(animal[i].getWeight(), animal[i].getAge()));
        }
    }
    public static void main(String[] args) {
        /*
        Cow c = new Cow();
        c.Input();
        System.out.print(c.ToString());
        System.out.println("\nAmount of cow's milk: "+ c.AmountOfMilk(c.getWeight(), c.getAge()));
        Goat g = new Goat();
        g.Input();
        System.out.print(g.ToString());
        System.out.print("\nAmount of goat's milk: "+g.AmountOfMilk(g.getWeight(), g.getAge()));
         */
        Scanner sc = new Scanner(System.in);
        //nhap so luong gia suc trong nong trai
        System.out.print("Enter the number of animal: ");
        int numOfAnimal = sc.nextInt();
        Animal[] animal = new Animal[numOfAnimal];
        ListOfAnimal(animal, numOfAnimal);
        /*
        System.out.println("The sum of amount of the cows' milk: "+SumOfMilkCow(animal, numOfAnimal));
        System.out.println("The sum of amount of the goats' milk: "+SumOfMilkGoat(animal, numOfAnimal));
        MaxMilkAnimal(animal, numOfAnimal);
        FindAnimalSeries(animal, numOfAnimal);
        FindAnimalAge(animal, numOfAnimal);
         */
        System.out.println("\n---The list of animal after sorting in ascending order according to the amount of milk---");
        AscendingMilk(animal, numOfAnimal);
        Display(animal, numOfAnimal);
        System.out.println("\n---The list of animal after sorting in descending order according to the age---");
        DescendingAge(animal, numOfAnimal);
        Display(animal, numOfAnimal);
    }
}
