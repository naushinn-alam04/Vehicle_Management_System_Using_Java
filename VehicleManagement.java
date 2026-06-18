import java.util.*;
import java.io.*;

class Vehicle
{
    String regNo;
    String carName;
    String brand;
    double price;

    String fuelType;
    String fuelStatus;
    double mileage;

    String condition;
    String nextServiceDate;

    Vehicle(String regNo,
            String carName,
            String brand,
            double price,
            String fuelType,
            String fuelStatus,
            double mileage,
            String condition,
            String nextServiceDate)
    {
        this.regNo = regNo;
        this.carName = carName;
        this.brand = brand;
        this.price = price;

        this.fuelType = fuelType;
        this.fuelStatus = fuelStatus;
        this.mileage = mileage;

        this.condition = condition;
        this.nextServiceDate = nextServiceDate;
    }

    @Override
    public String toString()
    {
        return regNo + "," +
               carName + "," +
               brand + "," +
               price + "," +
               fuelType + "," +
               fuelStatus + "," +
               mileage + "," +
               condition + "," +
               nextServiceDate;
    }

    public String display()
    {
        return "\n--------------------------------" +
               "\nReg No : " + regNo +
               "\nCar Name : " + carName +
               "\nBrand : " + brand +
               "\nPrice : ₹" + price +
               "\nFuel Type : " + fuelType +
               "\nFuel Status : " + fuelStatus +
               "\nMileage : " + mileage + " km/l" +
               "\nCondition : " + condition +
               "\nNext Service Date : " + nextServiceDate +
               "\n--------------------------------";
    }
}

public class VehicleManagement
{
    static final String FILE_NAME =
            "vehicles.txt";

    /* ADD VEHICLE */
    static void addVehicle()
    {
        Scanner sc =
            new Scanner(System.in);

        try(FileWriter fw =
                new FileWriter(FILE_NAME,true))
        {
            System.out.print(
                "Enter Registration Number : ");
            String regNo =
                sc.nextLine();

            System.out.print(
                "Enter Car Name : ");
            String carName =
                sc.nextLine();

            System.out.print(
                "Enter Brand : ");
            String brand =
                sc.nextLine();

            System.out.print(
                "Enter Price : ");
            double price =
                Double.parseDouble(
                    sc.nextLine());

            System.out.print(
                "Enter Fuel Type : ");
            String fuelType =
                sc.nextLine();

            System.out.print(
                "Enter Fuel Status (Full/Half/Low) : ");
            String fuelStatus =
                sc.nextLine();

            System.out.print(
                "Enter Mileage (km/l) : ");
            double mileage =
                Double.parseDouble(
                    sc.nextLine());

            System.out.print(
                "Enter Condition (Good/Average/Poor) : ");
            String condition =
                sc.nextLine();

            System.out.print(
                "Enter Next Service Date (DD-MM-YYYY) : ");
            String nextServiceDate =
                sc.nextLine();

            Vehicle v =
                new Vehicle(
                    regNo,
                    carName,
                    brand,
                    price,
                    fuelType,
                    fuelStatus,
                    mileage,
                    condition,
                    nextServiceDate
                );

            fw.write(v.toString());
            fw.write("\n");

            System.out.println(
                "\nVehicle Added Successfully.");
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : " + e.getMessage());
        }
    }

    /* VIEW VEHICLES */
    static void viewVehicles()
    {
        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(FILE_NAME))
        )
        {
            String line;
            boolean found = false;

            System.out.println(
                "\n===== VEHICLE RECORDS =====");

            while((line = br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data.length < 9)
                    continue;

                Vehicle v =
                    new Vehicle(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        data[4],
                        data[5],
                        Double.parseDouble(data[6]),
                        data[7],
                        data[8]
                    );

                System.out.println(
                    v.display());

                found = true;
            }

            if(!found)
            {
                System.out.println(
                    "No Vehicles Found.");
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : " + e.getMessage());
        }
    }
        /* SEARCH VEHICLE */
    static void searchVehicle()
    {
        Scanner sc =
            new Scanner(System.in);

        System.out.print(
            "Enter Registration Number : ");

        String regNo =
            sc.nextLine();

        boolean found = false;

        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(FILE_NAME))
        )
        {
            String line;

            while((line = br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data[0]
                    .equalsIgnoreCase(regNo))
                {
                    Vehicle v =
                        new Vehicle(
                            data[0],
                            data[1],
                            data[2],
                            Double.parseDouble(data[3]),
                            data[4],
                            data[5],
                            Double.parseDouble(data[6]),
                            data[7],
                            data[8]
                        );

                    System.out.println(
                        "\n===== VEHICLE FOUND =====");

                    System.out.println(
                        v.display());

                    found = true;
                    break;
                }
            }

            if(!found)
            {
                System.out.println(
                    "Vehicle Not Found.");
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : " + e.getMessage());
        }
    }

    /* UPDATE VEHICLE */
    static void updateVehicle()
    {
        Scanner sc =
            new Scanner(System.in);

        System.out.print(
            "Enter Registration Number : ");

        String regNo =
            sc.nextLine();

        File inputFile =
            new File(FILE_NAME);

        File tempFile =
            new File("temp.txt");

        boolean found = false;

        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(inputFile));

            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(tempFile))
        )
        {
            String line;

            while((line = br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data[0]
                    .equalsIgnoreCase(regNo))
                {
                    found = true;

                    System.out.println(
                        "\n1. Update Car Name");
                    System.out.println(
                        "2. Update Brand");
                    System.out.println(
                        "3. Update Price");
                    System.out.println(
                        "4. Update Fuel Status");
                    System.out.println(
                        "5. Update Mileage");
                    System.out.println(
                        "6. Update Condition");
                    System.out.println(
                        "7. Update Service Date");

                    System.out.print(
                        "\nEnter Choice : ");

                    int choice =
                        Integer.parseInt(
                            sc.nextLine());

                    switch(choice)
                    {
                        case 1:
                            System.out.print(
                                "New Car Name : ");
                            data[1] =
                                sc.nextLine();
                            break;

                        case 2:
                            System.out.print(
                                "New Brand : ");
                            data[2] =
                                sc.nextLine();
                            break;

                        case 3:
                            System.out.print(
                                "New Price : ");
                            data[3] =
                                sc.nextLine();
                            break;

                        case 4:
                            System.out.print(
                                "New Fuel Status : ");
                            data[5] =
                                sc.nextLine();
                            break;

                        case 5:
                            System.out.print(
                                "New Mileage : ");
                            data[6] =
                                sc.nextLine();
                            break;

                        case 6:
                            System.out.print(
                                "New Condition : ");
                            data[7] =
                                sc.nextLine();
                            break;

                        case 7:
                            System.out.print(
                                "New Service Date : ");
                            data[8] =
                                sc.nextLine();
                            break;

                        default:
                            System.out.println(
                                "Invalid Choice");
                    }

                    line =
                        String.join(",",
                                    data);
                }

                bw.write(line);
                bw.newLine();
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : " + e.getMessage());
        }

        if(found)
        {
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println(
                "\nVehicle Updated Successfully.");
        }
        else
        {
            tempFile.delete();

            System.out.println(
                "\nVehicle Not Found.");
        }
    }

    /* DELETE VEHICLE */
    static void deleteVehicle()
    {
        Scanner sc =
            new Scanner(System.in);

        System.out.print(
            "Enter Registration Number : ");

        String regNo =
            sc.nextLine();

        File inputFile =
            new File(FILE_NAME);

        File tempFile =
            new File("temp.txt");

        boolean found = false;

        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(inputFile));

            BufferedWriter bw =
                new BufferedWriter(
                    new FileWriter(tempFile))
        )
        {
            String line;

            while((line = br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data[0]
                    .equalsIgnoreCase(regNo))
                {
                    found = true;
                    continue;
                }

                bw.write(line);
                bw.newLine();
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : " + e.getMessage());
        }

        if(found)
        {
            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println(
                "\nVehicle Deleted Successfully.");
        }
        else
        {
            tempFile.delete();

            System.out.println(
                "\nVehicle Not Found.");
        }
    }
        /* MAINTENANCE REPORT */
    static void maintenanceReport()
    {
        Scanner sc =
            new Scanner(System.in);

        System.out.print(
            "Enter Registration Number : ");

        String regNo =
            sc.nextLine();

        boolean found = false;

        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(FILE_NAME))
        )
        {
            String line;

            while((line = br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data[0]
                    .equalsIgnoreCase(regNo))
                {
                    found = true;

                    String fuelStatus =
                        data[5];

                    double mileage =
                        Double.parseDouble(
                            data[6]);

                    String condition =
                        data[7];

                    String serviceDate =
                        data[8];

                    System.out.println(
                        "\n====================================");

                    System.out.println(
                        "       MAINTENANCE REPORT");

                    System.out.println(
                        "====================================");

                    System.out.println(
                        "Reg No : " + data[0]);

                    System.out.println(
                        "Car Name : " + data[1]);

                    System.out.println(
                        "Brand : " + data[2]);

                    System.out.println(
                        "Fuel Status : "
                        + fuelStatus);

                    System.out.println(
                        "Mileage : "
                        + mileage
                        + " km/l");

                    System.out.println(
                        "Condition : "
                        + condition);

                    System.out.println(
                        "Next Service Date : "
                        + serviceDate);

                    System.out.println(
                        "\n--------------------------------");

                    System.out.println(
                        "FUEL EFFICIENCY REPORT");

                    System.out.println(
                        "--------------------------------");

                    if(mileage < 10)
                    {
                        System.out.println(
                            "Status : Poor");

                        System.out.println(
                            "Advice : Engine Inspection Recommended");
                    }
                    else if(mileage <= 15)
                    {
                        System.out.println(
                            "Status : Average");

                        System.out.println(
                            "Advice : Regular Maintenance Recommended");
                    }
                    else
                    {
                        System.out.println(
                            "Status : Good");

                        System.out.println(
                            "Advice : Fuel Efficiency Excellent");
                    }

                    System.out.println(
                        "\n--------------------------------");

                    System.out.println(
                        "VEHICLE HEALTH REPORT");

                    System.out.println(
                        "--------------------------------");

                    if(condition.equalsIgnoreCase("Poor"))
                    {
                        System.out.println(
                            "Health Status : Critical");

                        System.out.println(
                            "Advice : Immediate Service Required");
                    }
                    else if(condition.equalsIgnoreCase("Average"))
                    {
                        System.out.println(
                            "Health Status : Moderate");

                        System.out.println(
                            "Advice : Service Recommended Soon");
                    }
                    else
                    {
                        System.out.println(
                            "Health Status : Good");

                        System.out.println(
                            "Advice : Vehicle In Good Condition");
                    }

                    System.out.println(
                        "\n--------------------------------");

                    System.out.println(
                        "UPCOMING SERVICE SCHEDULE");

                    System.out.println(
                        "--------------------------------");

                    System.out.println(
                        "Next Service Date : "
                        + serviceDate);

                    System.out.println(
                        "\n--------------------------------");

                    System.out.println(
                        "OVERALL ADVICE");

                    System.out.println(
                        "--------------------------------");

                    if(fuelStatus.equalsIgnoreCase("Low"))
                    {
                        System.out.println(
                            "⚠ Refuel Vehicle Immediately");
                    }

                    if(condition.equalsIgnoreCase("Poor"))
                    {
                        System.out.println(
                            "⚠ Service Centre Visit Required");
                    }

                    if(mileage < 10)
                    {
                        System.out.println(
                            "⚠ Check Engine Performance");
                    }

                    if(condition.equalsIgnoreCase("Good")
                       && mileage > 15
                       && !fuelStatus.equalsIgnoreCase("Low"))
                    {
                        System.out.println(
                            "✓ Vehicle Performing Optimally");
                    }

                    System.out.println(
                        "\n====================================");

                    break;
                }
            }

            if(!found)
            {
                System.out.println(
                    "Vehicle Not Found.");
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : "
                + e.getMessage());
        }
    }
        static final String SERVICE_FILE =
            "serviceBookings.txt";

    /* SERVICE BOOKING */
    static void serviceBooking()
    {
        Scanner sc =
            new Scanner(System.in);

        try(FileWriter fw =
                new FileWriter(
                    SERVICE_FILE,
                    true))
        {
            System.out.print(
                "Enter Registration Number : ");

            String regNo =
                sc.nextLine();

            System.out.print(
                "Enter Service Date (DD-MM-YYYY) : ");

            String serviceDate =
                sc.nextLine();

            System.out.print(
                "Enter Service Time (HH:MM) : ");

            String serviceTime =
                sc.nextLine();

            System.out.println(
                "\nSERVICE TYPES");

            System.out.println(
                "1. Oil Change");

            System.out.println(
                "2. Full Service");

            System.out.println(
                "3. Engine Repair");

            System.out.println(
                "4. Brake Service");

            System.out.println(
                "5. Battery Service");

            System.out.print(
                "\nSelect Service Type : ");

            int choice =
                Integer.parseInt(
                    sc.nextLine());

            String serviceType;

            switch(choice)
            {
                case 1:
                    serviceType =
                        "Oil Change";
                    break;

                case 2:
                    serviceType =
                        "Full Service";
                    break;

                case 3:
                    serviceType =
                        "Engine Repair";
                    break;

                case 4:
                    serviceType =
                        "Brake Service";
                    break;

                case 5:
                    serviceType =
                        "Battery Service";
                    break;

                default:
                    serviceType =
                        "General Service";
            }

            fw.write(
                regNo + "," +
                serviceDate + "," +
                serviceTime + "," +
                serviceType);

            fw.write("\n");

            System.out.println(
                "\n==========================");

            System.out.println(
                "SERVICE BOOKED SUCCESSFULLY");

            System.out.println(
                "==========================");

            System.out.println(
                "Registration No : "
                + regNo);

            System.out.println(
                "Service Date : "
                + serviceDate);

            System.out.println(
                "Service Time : "
                + serviceTime);

            System.out.println(
                "Service Type : "
                + serviceType);

            System.out.println(
                "==========================");
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : "
                + e.getMessage());
        }
    }

    /* VIEW SERVICE BOOKINGS */
    static void viewServiceBookings()
    {
        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(
                        SERVICE_FILE))
        )
        {
            String line;

            System.out.println(
                "\n===== SERVICE BOOKINGS =====");

            while((line =
                    br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                System.out.println(
                    "\nRegistration No : "
                    + data[0]);

                System.out.println(
                    "Service Date : "
                    + data[1]);

                System.out.println(
                    "Service Time : "
                    + data[2]);

                System.out.println(
                    "Service Type : "
                    + data[3]);

                System.out.println(
                    "------------------------");
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : "
                + e.getMessage());
        }
    }
        /* GENERATE BILL */
    static void generateBill()
    {
        Scanner sc =
            new Scanner(System.in);

        System.out.print(
            "Enter Registration Number : ");

        String regNo =
            sc.nextLine();

        System.out.print(
            "Enter Insurance Cost : ");

        double insuranceCost =
            Double.parseDouble(
                sc.nextLine());

        System.out.print(
            "Enter Repair Cost : ");

        double repairCost =
            Double.parseDouble(
                sc.nextLine());

        String serviceType = "";
        double serviceCost = 0;

        try(
            BufferedReader br =
                new BufferedReader(
                    new FileReader(
                        SERVICE_FILE))
        )
        {
            String line;

            while((line =
                    br.readLine()) != null)
            {
                String[] data =
                    line.split(",");

                if(data[0]
                    .equalsIgnoreCase(regNo))
                {
                    serviceType =
                        data[3];
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(
                "Error : "
                + e.getMessage());
        }

        if(serviceType.equalsIgnoreCase(
            "Oil Change"))
        {
            serviceCost = 1500;
        }
        else if(serviceType.equalsIgnoreCase(
            "Full Service"))
        {
            serviceCost = 5000;
        }
        else if(serviceType.equalsIgnoreCase(
            "Engine Repair"))
        {
            serviceCost = 10000;
        }
        else if(serviceType.equalsIgnoreCase(
            "Brake Service"))
        {
            serviceCost = 3000;
        }
        else if(serviceType.equalsIgnoreCase(
            "Battery Service"))
        {
            serviceCost = 2500;
        }
        else
        {
            serviceCost = 2000;
        }

        double subtotal =
            insuranceCost +
            repairCost +
            serviceCost;

        double gst =
            subtotal * 0.18;

        double total =
            subtotal + gst;

        System.out.println(
            "\n===================================");

        System.out.println(
            "         VEHICLE BILL");

        System.out.println(
            "===================================");

        System.out.println(
            "Registration No : "
            + regNo);

        System.out.println(
            "Service Type : "
            + serviceType);

        System.out.println(
            "\nInsurance Cost : ₹"
            + insuranceCost);

        System.out.println(
            "Repair Cost : ₹"
            + repairCost);

        System.out.println(
            "Service Cost : ₹"
            + serviceCost);

        System.out.println(
            "\nSubtotal : ₹"
            + subtotal);

        System.out.println(
            "GST (18%) : ₹"
            + gst);

        System.out.println(
            "-----------------------------------");

        System.out.println(
            "TOTAL BILL : ₹"
            + total);

        System.out.println(
            "===================================");
    }
        /* MAIN METHOD */
    public static void main(String[] args)
    {
        Scanner sc =
            new Scanner(System.in);

        int choice;

        do
        {
            System.out.println(
                "\n====================================");

            System.out.println(
                " VEHICLE MANAGEMENT SYSTEM ");

            System.out.println(
                "====================================");

            System.out.println(
                "1. Add Vehicle");

            System.out.println(
                "2. View Vehicles");

            System.out.println(
                "3. Search Vehicle");

            System.out.println(
                "4. Update Vehicle");

            System.out.println(
                "5. Delete Vehicle");

            System.out.println(
                "6. Maintenance Report");

            System.out.println(
                "7. Service Booking");

            System.out.println(
                "8. View Service Bookings");

            System.out.println(
                "9. Generate Bill");

            System.out.println(
                "10. Exit");

            System.out.print(
                "\nEnter Choice : ");

            choice =
                Integer.parseInt(
                    sc.nextLine());

            switch(choice)
            {
                case 1:
                    addVehicle();
                    break;

                case 2:
                    viewVehicles();
                    break;

                case 3:
                    searchVehicle();
                    break;

                case 4:
                    updateVehicle();
                    break;

                case 5:
                    deleteVehicle();
                    break;

                case 6:
                    maintenanceReport();
                    break;

                case 7:
                    serviceBooking();
                    break;

                case 8:
                    viewServiceBookings();
                    break;

                case 9:
                    generateBill();
                    break;

                case 10:
                    System.out.println(
                        "\nThank You!");
                    break;

                default:
                    System.out.println(
                        "\nInvalid Choice.");
            }

        }
        while(choice != 10);
    }
}