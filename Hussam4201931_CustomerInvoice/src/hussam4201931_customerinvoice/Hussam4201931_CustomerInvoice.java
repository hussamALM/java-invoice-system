package hussam4201931_customerinvoice;

import java.util.*;

public class Hussam4201931_CustomerInvoice {

    public static int getsize() {//1
        Scanner scan = new Scanner(System.in);
        System.out.print("please enter the number of customers:");
        int num1 = scan.nextInt();
        return num1;
    }

    public static int getnumberofitems() {//4
        Scanner scan = new Scanner(System.in);
        System.out.print("\n please enter the number of items:");
        int num = scan.nextInt();
        return num;

    }

    public static void addCustomerinvoice(String[] customersname, String[][] items, int[][] prices, int numberofcustomer) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < numberofcustomer; i++) {

            System.out.print("\n please enter the name of the customer=>" + (i + 1) + ":");
            customersname[i] = scan.next();
            for (int j = 0; j < items[i].length; j++) {
                System.out.print("\n please enter the name of item=>" + (j + 1) + ":");
                items[i][j] = scan.next();
                prices[i][j] = (int) (1 + Math.random() * 100);
            }
        }
    }

    public static int[] settotal(int[][] itemprice, int[] customertotalprice) {
        int total = 0;
        for (int i = 0; i < itemprice.length; i++) {
            total = 0;
            for (int j = 0; j < itemprice[0].length; j++) {
                total += itemprice[i][j];
                customertotalprice[i] = total;
            }
        }
        return customertotalprice;
    }

    public static int getcustomenamerbyindex(String customer_name, String[] customersname) {
        for (int i = 0; i < customersname.length; i++) {

            if (customer_name.equalsIgnoreCase(customersname[i])) {
                return i;
            }

        }
        return -1;
    }

    public static int getcustomermaxpriceindex(int[] total) {
        double maximum = total[0];
        int position = 0;
        for (int i = 0; i < total.length; i++) {
            if (total[i] > maximum) {
                maximum = total[i];
                position = i;
            }
        }
        return position;
    }

    public static void printcustomersinformations(String[] customername, int[] total, String[][] itemsname, int[][] itemprice) {
        for (int i = 0; i < customername.length; i++) {
            System.out.print("\n customer=>" + (i + 1) + " name:" + customername[i] + "\t total:" + total[i]);
            System.out.println();
            for (int j = 0; j < customername.length; j++) {
                System.out.print("\t item:" + "(" + (j + 1) + ") " + itemsname[i][j] + "/" + itemprice[i][j]);
            }
        }
    }

    public static void printcustomerinformations(int index, String[] customername, int[] total, String[][] itemsname, int[][] itemprice) {
        System.out.print("\n customer=>" + (index + 1) + " name:" + customername[index] + "\t\n total:" + total[index]);
        for (int j = 0; j < customername.length; j++) {
            System.out.print("\t\n item:" + "(" + (j + 1) + ") " + itemsname[index][j] + "/" + itemprice[index][j]);
        }
    }

    public static void printcustomerinformationbyname(String[] customersname, int[] customertotalprice, String[][] itemsname, int[][] itemprice) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n \n search for customer by name:");
        String name = scan.nextLine();
        int customerindex = getcustomenamerbyindex(name, customersname);
        if (customerindex != -1) {
            printcustomerinformations(customerindex, customersname, customertotalprice, itemsname, itemprice);
        } else {
            System.out.println("\n the customer you looking for is not there");
        }
    }

    public static void updateprice(String[] customersname, String[][] itemsname, int[][] itemprice, int[] customerinvoice) {

        Scanner scan = new Scanner(System.in);
        String name, item;
        int newprice;
        System.out.print(" \nplease enter the customer you want to update his item's price:");
        name = scan.next();

        int index = getcustomenamerbyindex(name, customersname);
        if (index != -1) {
            System.out.print(" \nplease enter the item you want to update its price:");

            item = scan.next();
            for (int col = 0; col < itemsname[index].length; col++) {
                if (itemsname[index][col].equalsIgnoreCase(item)) {
                    System.out.print(" \nplease enter new price for item " + item + ":");
                    newprice = scan.nextInt();
                    System.out.print(" \nplease new price for item " + item + ":" + newprice);
                    itemprice[index][col] = newprice;
                    settotal(itemprice, customerinvoice);
                    System.out.print(" \n one recorde has been updated successfly and new information as bellow");
                    printcustomerinformations(index, customersname, customerinvoice, itemsname, itemprice);

                    return;
                }
            }
            System.out.print(" \n the item :" + item + " :is not taken by " + name);
        } else {
            System.out.println(" this customer name is not there");
        }

    }

    public static void menu() {
        System.out.println("(1)search for the customer by name  (2)the customer with maximum invoice");
        System.out.println("(3)all customers informations       (4)update price");
        System.out.println("(0)exit");

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberofcustomers = getsize();
        String[] customersname = new String[numberofcustomers];
        int numberofitems = getnumberofitems();
        String[][] itemsname = new String[numberofcustomers][numberofitems];
        int[][] itemprice = new int[numberofcustomers][numberofitems];
        addCustomerinvoice(customersname, itemsname, itemprice, numberofcustomers);
        int[] customerinvoice = new int[numberofcustomers];
        int[] customertotalprice = settotal(itemprice, customerinvoice);
        String customer_name = "";
        int customerindex = getcustomenamerbyindex(customer_name, customersname);
        int thepostionofmaximuminvoice = getcustomermaxpriceindex(customertotalprice);
        int choice;
        do {
            System.out.println();
            menu();
            System.out.println("\n please enter your choice:");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    printcustomerinformationbyname(customersname, customertotalprice, itemsname, itemprice);
                    break;
                case 2:
                    System.out.print("\n the customer with maximum totaol price:");
                    printcustomerinformations(thepostionofmaximuminvoice, customersname, customertotalprice, itemsname, itemprice);
                    break;
                case 3:
                    System.out.print("\n all customers informations:");
                    printcustomersinformations(customersname, customertotalprice, itemsname, itemprice);
                    break;
                case 4:
                    updateprice(customersname, itemsname, itemprice, customerinvoice);
                    break;
            }
        } while (choice != 0);
        System.out.println("see ya next time");
    }
}
