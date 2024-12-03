import java.util.Scanner;

//creating a class that keeps a fleet of different types of vehicles
 class FleetOfVehicles {
    //creaiting an array of vehicles
    private Vehicle[] fleet;
    public static final int MAX_VEHICLES = 100;

    public FleetOfVehicles() {
        fleet = new Vehicle[MAX_VEHICLES];
    }

    public Vehicle[] getFleet() {
        return this.fleet;
    }


    public void addVehicle(Vehicle aV) {
        for (int i = 0; i < fleet.length; i++) {
            if (fleet[i] == null) {
                fleet[i] = aV;
                return;
            }
        }
        //it reaches here the array is full
        System.out.println("The fleet of vehicles is full!");
    }

    //being able to remove a vehicle
    public void removeVehicle(Vehicle aV) {
        for (int i = 0; i < fleet.length; i++) {
            if (fleet[i] != null && fleet[i].equals(aV)) {
                fleet[i] = null;
                return;
            } 
        }
        //it reaches here then the vehicle was not found
        System.out.println("The vehicle was not found");
    }

   
    static Scanner key;

    //adding main method
    public static void main(String[] args) {
        key = new Scanner(System.in); // Construct the key

        System.out.println("Welcome to the fleet manager");
        FleetOfVehicles fOfV = new FleetOfVehicles(); // Creates a new instance of the FleetOfVehicles to be used

        boolean quit = false;
        while (!quit) 
        {
            printOptions();
            int pick = key.nextInt();
            key.nextLine();
            switch (pick) {
                case 1: //adding vehicle
                    fOfV.addVehicle(makeAVehicleDialog());
                    break;
                case 2: //removing vehicle
                    fOfV.removeVehicle(makeAVehicleDialog());
                    break;
                case 9:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println("The Fleet currently");
            printFleet(fOfV);
        }
        System.out.println("Goodbye!");
    }

    //displays the options to the user
    public static void printOptions() {
        System.out.println("Enter 1: to add a Vehicle\nEnter 2: to remove a Vehicle\nEnter 9 to quit");
    }

    //returns an instance of a vehicle based on user input
    public static Vehicle makeAVehicleDialog() {
        Vehicle retV;
        int pick = 0;
        System.out.println("Enter 1: if it is a car\nEnter 2: if it is a truck\nEnter 3: if it is unclassified");
        pick = key.nextInt();
        key.nextLine();
        while (pick != 1 && pick != 2 && pick != 3) {
            System.out.println("Invalid choice pick again");
            pick = key.nextInt();
            key.nextLine();
        }
        System.out.println("Enter the manufacturer's name");
        String manuName = key.nextLine();
        System.out.println("Enter the number of cylinders");
        int cylinders = key.nextInt();
        key.nextLine();
        System.out.println("Enter the owner's name");
        String ownersName = key.nextLine();

        switch (pick) {
            case 1: // constructing a car
                System.out.println("Enter the car's gas mileage");
                double mileage = key.nextDouble();
                key.nextLine();
                System.out.println("Enter the number of passengers");
                int passengers = key.nextInt();
                key.nextLine();
                retV = new Car(manuName, cylinders, ownersName, mileage, passengers);
                break;
            case 2: // constructing a truck
                System.out.println("Enter the truck's load capacity");
                double loadCap = key.nextDouble();
                key.nextLine();
                System.out.println("Enter the truck's towing capacity");
                double towCap = key.nextDouble();
                key.nextLine();
                retV = new Truck(manuName, cylinders, ownersName, loadCap, towCap);
                break;
            default:
                retV = new Vehicle(manuName, cylinders, ownersName);
        }

        return retV;
    }

    public static void printFleet(FleetOfVehicles fV) {
        for (Vehicle v : fV.getFleet()) {
            if (v == null)
                continue;
            System.out.println(v);
            System.out.println();
        }
    }
}

// creating the base class: Vehicle
class Vehicle {
    private String manufacturerName;
    private int numCylinders;
    private String ownerName;

    public Vehicle() {
        this.manufacturerName = "Unknown";
        this.numCylinders = 1;
        this.ownerName = "Unknown";
    }

    public Vehicle(String manufacturerName, int numCylinders, String ownerName) {
        if (numCylinders <= 0) {
            throw new IllegalArgumentException("Number of cylinders must be greater than 0.");
        }
        this.manufacturerName = manufacturerName;
        this.numCylinders = numCylinders;
        this.ownerName = ownerName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public int getNumCylinders() {
        return numCylinders;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setNumCylinders(int numCylinders) {
        if (numCylinders <= 0) {
            throw new IllegalArgumentException("Number of cylinders must be greater than 0.");
        }
        this.numCylinders = numCylinders;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return numCylinders == vehicle.numCylinders &&
                manufacturerName.equals(vehicle.manufacturerName) &&
                ownerName.equals(vehicle.ownerName);
    }

    @Override
    public String toString() {
        return "Manufacturer's Name: " + manufacturerName + "\n" +
                "Number Of Cylinders: " + numCylinders + "\n" +
                "Owner's Name: " + ownerName;
    }
}

// derived class: Truck
class Truck extends Vehicle {
    private double loadCapacity;
    private double towingCapacity;

    public Truck() {
        super();
        this.loadCapacity = 0.0;
        this.towingCapacity = 0.0;
    }

    public Truck(String manufacturerName, int numCylinders, String ownerName, double loadCapacity, double towingCapacity) {
        super(manufacturerName, numCylinders, ownerName);
        if (loadCapacity < 0 || towingCapacity < 0) {
            throw new IllegalArgumentException("Capacity values must be nonnegative.");
        }
        this.loadCapacity = loadCapacity;
        this.towingCapacity = towingCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        if (loadCapacity < 0) {
            throw new IllegalArgumentException("Load capacity must be nonnegative.");
        }
        this.loadCapacity = loadCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        if (towingCapacity < 0) {
            throw new IllegalArgumentException("Towing capacity must be nonnegative.");
        }
        this.towingCapacity = towingCapacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Truck truck = (Truck) obj;
        return Double.compare(truck.loadCapacity, loadCapacity) == 0 &&
                Double.compare(truck.towingCapacity, towingCapacity) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Load Capacity: " + loadCapacity + "\n" +
                "Towing Capacity: " + towingCapacity;
    }
}

// derived class: Car
class Car extends Vehicle {
    private double gasMileage;
    private int numPassengers;

    public Car() {
        super();
        this.gasMileage = 0.0;
        this.numPassengers = 0;
    }

    public Car(String manufacturerName, int numCylinders, String ownerName, double gasMileage, int numPassengers) {
        super(manufacturerName, numCylinders, ownerName);
        if (gasMileage < 0 || numPassengers < 0) {
            throw new IllegalArgumentException("Mileage and passenger count must be nonnegative.");
        }
        this.gasMileage = gasMileage;
        this.numPassengers = numPassengers;
    }

    public double getGasMileage() {
        return gasMileage;
    }

    public int getNumPassengers() {
        return numPassengers;
    }
}
