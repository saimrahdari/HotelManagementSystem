import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Hotel{
    static int id = 0, room_count = 0, room_rent_temp = 0, MAX_CUSTOMERS = 100, MAX_ROOMS = 100;
    static long cnic = 0;
    static char branch;
    static int[] cost = new int[MAX_CUSTOMERS]; 
    static String[] name = new String[MAX_CUSTOMERS];
    static String[] address = new String[MAX_CUSTOMERS];
    static int[] month = new int[MAX_CUSTOMERS];
    static int[] day = new int[MAX_CUSTOMERS];
    static int[] year = new int[MAX_CUSTOMERS];
    static String[] contacts = new String[MAX_CUSTOMERS];
    static ArrayList<String> list_ids = new ArrayList<String>(MAX_CUSTOMERS);
    static ArrayList<Integer> list = new ArrayList<Integer>();

    static boolean isValidCNIC(String cnic) {
        if (Pattern.matches("[a-zA-Z]+", cnic) == false && cnic.length() == 13) {
                return true;
        } else
        return false;
    }

    static boolean isValidCNIC2(String cnic){

        boolean room_check = true;

        if(list_ids.size() == 0)
            room_check = false;
        else if(!(list_ids.contains(cnic)))
            room_check = false;
        else 
            room_check = true;
        return room_check;
    }

    public static void setCNIC() {
        Scanner input = new Scanner(System.in);
        String cnic = "";
        boolean check = true;
        while(check){
        System.out.println("Enter your CNIC Without Dashes(-)");
        try
        {   cnic = input.nextLine();
            if (isValidCNIC(cnic) && !isValidCNIC2(cnic)) {
                list_ids.add(cnic);
                check = false;
            } else {
                throw new Exception();
            }
        } catch(Exception e) {
            System.out.println("Invalid CNIC Number");
        }
    }
}


    static boolean check_special(String inputString){
        String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i=0; i < inputString.length() ; i++)
        {
            char ch = inputString.charAt(i);
            if(specialCharactersString.contains(Character.toString(ch))) {
                return true;
            }    
            else if(i == inputString.length()-1)     
                return false;
        }
        return false;
    }

    public static void customer_panel(){
        Scanner input = new Scanner(System.in);
        String add;
        boolean check = true;
        room_count = 0;

        while(check){
        System.out.println("Enter your Name:");
        name[id] = input.nextLine();
        if(check_special(name[id]))
            System.out.println("Invalid Name!");
        else
            check = false;
    }

        do {
            System.out.println("Enter Day of Stay(1-31): ");
            try {
                day[id] = input.nextInt();
                if (!(day[id] >= 1 && day[id] <= 31)) {
                    System.out.println("Not a valid Input.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer!");
                input.next();
            }	
        } while (!(day[id] >= 1 && day[id] <= 31));


        do {
            System.out.println("Enter Month of Stay (1-12): ");
            try {
                month[id] = input.nextInt();
                if (!(month[id] > 0 && month[id] < 13)) {
                    System.out.println("Not a valid Input.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer!");
                input.next();
            }	
        } while (!(month[id] > 0 && month[id] < 13));


        do {
            System.out.println("Enter Year of Stay: ");
            try {
                year[id] = input.nextInt();
                if (!(year[id] == 2020 || year[id] == 2021)) {
                    System.out.println("Not a valid Input.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer!");
                input.next();
            }	
        } while (!(year[id] == 2020 || year[id] == 2021));


        System.out.println("Enter your adress:");
        add = input.nextLine();
        address[id] = input.nextLine();

        setCNIC();

        check = true;

        while(check){
            System.out.println("Enter your Contact No. : ");
            contacts[id] = input.next();
    
            if (contacts[id].length() == 11 && (contacts[id].substring(0,2).equals("03")) && !check_special(contacts[id]))
                check = false;
            else
                System.out.println("Invalid Contact Number");
        }

        cost[id] = 0;
        main_menu_customer();
    }

    public static void room_cancelation(){
        Scanner input = new Scanner(System.in);
        int room = 0, ind = 0;
        System.out.println(list);
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println("\tROOM CANCELATION MENU");
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println();
        do {
            System.out.println("Enter your Room Number to Cancel: ");
            try {
                room = input.nextInt();
                if (!(room > 0 && room <120))
                    System.out.println("Not a valid Input.");
                else {
                    if(list.contains(room)){
                        ind = list.indexOf(room);
                        list.remove(ind);
                        cost[id] = cost[id] - room_rent_temp;
                        room_count--;
                        System.out.println("Room Canceled");
                    } else {
                        System.out.println("Room Not Found");
                    }
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer!");
                input.next();
            }	
        } while (!(room > 0 && room <120));
    }

    public static void roomselection_rent(){
        Scanner input = new Scanner(System.in);
        char choice;
        boolean choice_bool = true;
        while(choice_bool){
        System.out.println("Press 1 to book Room\nPress 2 to Cancel Room");
        choice = input.next().charAt(0);
        if(choice == '1'){
            if(room_count > 0)
                break;
        choice_bool = false;
        int rent = 0, nights,room_no;
        char inp;
        boolean check = true, room_check = true;
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println("\tROOM SELECTION MENU");
        System.out.println(" ---------------------------------------------------------- ");
        while(check){

            System.out.println("We have " + MAX_ROOMS + " ROOMS LEFT! HURRY UP!");

            System.out.println();
            System.out.println("Press 1 for single bed room ---- RS 2000");
            System.out.println("Press 2 for double bed room ---- RS 3000");
            System.out.println("Press 3 for family room -------- RS 5000");
            System.out.println("Press 4 for suite ---------------RS 10000");
            inp = input.next().charAt(0);
            if(inp == '1'){
                room_count++;
                System.out.println("Enter the number of nights you want to stay?");
                nights = input.nextInt();
                room_no = (((int) (Math.random()*(20 - 1))) + 1);
                while(room_check){
                room_no = (((int) (Math.random()*(20 - 1))) + 1);
                    if(list.size() == 0){
                        list.add(room_no);
                        room_check = false;
                    }
                    else if(list.contains(room_no))
                        room_check = true;
                    else if(!(list.contains(room_no))){
                        list.add(room_no);
                        room_check = false;
                    }
                }
                System.out.println("You chose the Single Bed Room. Your room number is " + room_no);
                rent = nights*2000;
                check = false;
                MAX_ROOMS--;
            }
            else if(inp == '2'){
                room_count++;
                System.out.println("Enter the number of nights you want to stay?");
                nights = input.nextInt();
                room_no = (((int) (Math.random()*(50 - 21))) + 21);
                while(room_check){
                    room_no = (((int) (Math.random()*(50 - 21))) + 21);
                        if(list.size() == 0){
                            list.add(room_no);
                            room_check = false;
                        }
                        else if(list.contains(room_no))
                            room_check = true;
                        else if(!(list.contains(room_no))){
                            list.add(room_no);
                            room_check = false;
                        }
                    }
                System.out.println("You chose the Double Bed Room. Your room number is " + room_no);
                rent = nights*3000;
                check = false;
                MAX_ROOMS--;
            }
            else if(inp == '3'){
                room_count++;
                System.out.println("Enter the number of nights you want to stay?");
                nights = input.nextInt();
                room_no = (((int) (Math.random()*(90 - 51))) + 51);
                while(room_check){
                    room_no = (((int) (Math.random()*(90 - 51))) + 51);
                        if(list.size() == 0){
                            list.add(room_no);
                            room_check = false;
                        }
                        else if(list.contains(room_no))
                            room_check = true;
                        else if(!(list.contains(room_no))){
                            list.add(room_no);
                            room_check = false;
                        }
                    }
                System.out.println("You chose the Family Room. Your room number is " + room_no);
                rent = nights*5000;
                check = false;
                MAX_ROOMS--;
            }
            else if(inp == '4'){
                room_count++;
                System.out.println("Enter the number of nights you want to stay?");
                nights = input.nextInt();
                room_no = (((int) (Math.random()*(120 - 91))) + 91);
                while(room_check){
                    room_no = (((int) (Math.random()*(120 - 91))) + 91);
                        if(list.size() == 0){
                            list.add(room_no);
                            room_check = false;
                        }
                        else if(list.contains(room_no))
                            room_check = true;
                        else if(!(list.contains(room_no))){
                            list.add(room_no);
                            room_check = false;
                        }
                    }
                System.out.println("You chose the Suite. Your room number is " + room_no);
                rent = nights*8000;
                check = false;
                MAX_ROOMS--;
            }
            else if(inp == '5'){
                System.out.println("You have chosen to Exit. Thank you for using Room Selection Menu.");
                return;
            }
            else
                System.out.println("Invalid Input!");
        }
    System.out.println();
    System.out.println("Your Room Rent is: " + rent);
    cost[id] += rent;
    room_rent_temp += rent;
    choice_bool = false;
        } else if (choice == '2'){
            room_cancelation();
            choice_bool = false;
        }
        else
            System.out.println("Invalid Input");
    }
    main_menu_customer();
}
    
    public static void laundrybill(){
        Scanner input = new Scanner(System.in);
        int t_cost = 0, no_laundry;
        char inp;
        boolean check = true;        
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println("\tLAUNDRY SELECTION MENU");
        System.out.println(" ---------------------------------------------------------- ");

        while(check){
            System.out.println("Press 1 for Shirts");
            System.out.println("Press 2 for Pants");
            System.out.println("Press 3 for Dresscoats");
            System.out.println("Press 4 for Sweaters");
            System.out.println("Press 5 to exit Laundry Selection Menu : ");

            inp = input.next().charAt(0);

            if(inp == '1'){
                System.out.println("How many shirts you want to give for Laundry: ");
                no_laundry = input.nextInt();
                t_cost = no_laundry * 20;
                check = false;
            }
            else if(inp == '2'){
                System.out.println("How many pants you want to give for Laundry: ");
                no_laundry = input.nextInt();
                t_cost = no_laundry * 35;
                check = false;
            }
            else if(inp == '3'){
                System.out.println("How many dresscoats you want to give for Laundry: ");
                no_laundry = input.nextInt();
                t_cost = no_laundry * 70;
                check = false;
            }
            else if(inp == '4'){
                System.out.println("How many sweaters you want to give for Laundry: ");
                no_laundry = input.nextInt();
                t_cost = no_laundry * 50;
                check = false;
            }
            else if(inp == '5'){
                System.out.println("You have chosen to Exit. Thank you for using Laundry Menu.");
                main_menu_customer();
            }
            else
                System.out.println("Invalid Input!");
        }

    System.out.println();
    System.out.println("Your total Laundry Bill is: " + t_cost);
    cost[id] += t_cost;
    laundrybill();
    }

    public static void restaurantbill(){
        Scanner input = new Scanner(System.in);
        int t_cost = 0, no_res;
        char inp;
        boolean check = true;
        System.out.println(" ---------------------------------------------------------- ");
        System.out.println("\tWELCOME TO OUR RESTAURANT");
        System.out.println(" ---------------------------------------------------------- ");

        while(check){
            System.out.println("Press 1 for Water ---------- RS10");
            System.out.println("Press 2 for Breakfast ------ RS150");
            System.out.println("Press 3 for Lunch ---------- RS300");
            System.out.println("Press 4 for Dinner --------- RS500");
            System.out.println("Press 5 to exit Restaurant Menu : ");

            inp = input.next().charAt(0);

            if(inp == '1'){
                System.out.println("Enter the quantity: ");
                no_res = input.nextInt();
                t_cost = no_res * 10;
                check = false;
            }
            else if(inp == '2'){
                System.out.println("Enter the quantity: ");
                no_res = input.nextInt();
                t_cost = no_res * 150;
                check = false;
            }
            else if(inp == '3'){
                System.out.println("Enter the quantity: ");
                no_res = input.nextInt();
                t_cost = no_res * 300;
                check = false;
            }
            else if(inp == '4'){
                System.out.println("Enter the quantity: ");
                no_res = input.nextInt();
                t_cost = no_res * 500;
                check = false;
            }
            else if(inp == '5'){
                System.out.println("You have chosen to Exit. Thank you for using Restaurant Menu.");
                main_menu_customer();
            }
            else
                System.out.println("Invalid Input!");
        }

    System.out.println();
    System.out.println("Your total Restaurant Bill is: RS" + t_cost);
    cost[id] += t_cost;
    restaurantbill();
    }

    public static void totalcost() throws IOException {
        System.out.println("You have been checked out!");
        System.out.println();
        System.out.println("Total Cost = " + cost[id]);
        System.out.println();
        System.out.println("Thanks for using our Hotel Management System!");

        BufferedWriter bw = new BufferedWriter( new FileWriter("hotel_db.txt",true));

    	bw.write(name[id]+"|"+list_ids.get(id)+"|"+address[id]+"|"+contacts[id]+"|"+cost[id]);
    	bw.flush();
    	bw.newLine();
    	bw.close();
        id++;
        panels();
    }

    public static void admin_update_record() throws IOException{

            String newName = "", newAddr, newContact = "", record, ID,record2;

            int newC = 0;

            boolean check = true;
                
            File db = new File("hotel_db.txt");
            File tempDB = new File("hotel_db_temp.txt");
            
            BufferedReader br = new BufferedReader( new FileReader(db) );
            BufferedWriter bw = new BufferedWriter( new FileWriter(tempDB) );
                        
            Scanner input = new Scanner(System.in);
            
            System.out.println(" ---------------------------------------------------------- ");
            System.out.println("\t\t Update Customer Record"); 
            System.out.println(" ---------------------------------------------------------- ");
            System.out.println();

            System.out.println("Enter the Customer CNIC: ");
            ID = input.nextLine();	    		
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
            System.out.println("|\tName\t\tCNIC\t\t\tAddress\t\t\tContact No.\t\tTotal Cost\t|");
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
           
            while( (record = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(record,"|");
                if( record.contains(ID) ) {
                    System.out.println("|\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t|");
                }
            }	    		
            System.out.println("|	                                            	                                                        |");
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
           
        br.close();
        
        while(check){
            System.out.println("Enter the new Name: ");
            newName = input.nextLine();  

            if(check_special(newName))
                System.out.println("Invalid Name!");
            else
                check = false;
        }

        System.out.println("Enter the new Address: ");
        newAddr = input.nextLine();

        check = true;

        while(check){
        System.out.println("Enter the new Contact No. :");
        newContact = input.next();

        if (newContact.length() == 11 && newContact.substring(0,2).equals("03") && !check_special(newContact))
            check = false;
        else
            System.out.println("Invalid Contact Number");
    }

        check = false;

        
        do {
            System.out.println("Enter the new Total Cost: ");
            try {
                newC = input.nextInt();
                check = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Must enter an integer!");
                input.next();
            }	
        } while (!check);

        check = true;
        
        BufferedReader br2 = new BufferedReader( new FileReader(db) );
            
        while( (record2 = br2.readLine() ) != null ) {    			
            if(record2.contains(ID)) {
                bw.write(newName+"|"+ID+"|"+newAddr+"|"+newContact+"|"+newC);
            } else {
                bw.write(record2);	
            }    			
            bw.flush();
            bw.newLine();
        }
        
        bw.close();
        br2.close();    		
        db.delete();    		
        boolean success = tempDB.renameTo(db);
    }

    public static void admin_search_record() throws IOException{

        String ID,record;
        Scanner input = new Scanner(System.in);
        
        BufferedReader br = new BufferedReader( new FileReader("hotel_db.txt") );

        System.out.println(" ---------------------------------------------------------- ");        
        System.out.println("\t\t Search Customer Record\n");
        System.out.println(" ---------------------------------------------------------- ");
    
        
        System.out.println("Enter the Customer CNIC: ");
        ID = input.nextLine();
        
        System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
        System.out.println("|\tName\t\tCNIC\t\t\tAddress\t\t\tContact No.\t\tTotal Cost\t|");
        System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
        
        while( ( record = br.readLine() ) != null ) {
            
            StringTokenizer st = new StringTokenizer(record,"|");
            if( record.contains(ID) ) {
                System.out.println("|\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t|");
            }
        }
        
        System.out.println("|	                                            	                                                        |");
        System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
           
        br.close();
    }

    public static void admin_view_record() throws IOException{
            BufferedReader br = new BufferedReader( new FileReader("hotel_db.txt") );
            String record;
                
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
            System.out.println("|\tName\t\tCNIC\t\t\tAddress\t\t\tContact No.\t\tTotal Cost\t|");
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
            
            while( (record = br.readLine() ) != null) {
                    
                StringTokenizer st = new StringTokenizer(record,"|");
                    
                System.out.println("|\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t\t"+st.nextToken()+"\t\t"+st.nextToken()+"\t\t|");
            }
            System.out.println("|	                                            	                                                        |");
            System.out.println(" --------------------------------------------------------------------------------------------------------------- ");
            br.close();    		
    }

    public static void admin_delete_record() throws IOException{
    	Scanner input =  new Scanner(System.in);
    	String ID, record;
    		
    	File tempDB = new File("hotel_db_temp.txt");
    	File db = new File("hotel_db.txt");
    		
    		
    	BufferedReader br = new BufferedReader( new FileReader( db ) );
    	BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
    		
        System.out.println(" ---------------------------------------------------------- ");
    	System.out.println("\t\t DELETE CUSTOMER RECORD\n");
        System.out.println(" ---------------------------------------------------------- ");

    	System.out.println("Enter the Customer's CNIC: ");
    	ID =  input.nextLine();
    		
    		
    	while( ( record = br.readLine() ) != null ) {	
    		if( record.contains(ID) ) 
                continue;
                
    		bw.write(record);
    		bw.flush();
    		bw.newLine();
    	}
    		
    	    br.close();
    	    bw.close();
    		
    		db.delete();
    		
    		tempDB.renameTo(db);
 
    }

    public static void admin_panel(){
        Scanner input = new Scanner(System.in);
        String userName = "", password = "";
        boolean check = true;
        char ch;
        try {
            while(check){
            System.out.println("Enter Username: ");
            userName = input.next();

            if(branch == '1'){
                if(userName.trim().equals("admin-isb"))
                    check = false;
                else
                    System.out.println("Invalid Username!");
            } else if(branch == '2'){
                if(userName.trim().equals("admin-lhr"))
                    check = false;
                else
                    System.out.println("Invalid Username!");
            } else if(branch == '3'){
                if(userName.trim().equals("admin-khi"))
                    check = false;
                else
                    System.out.println("Invalid Username!");
            }
        }   

            System.out.println("Enter Password: ");
            password = input.next();
            if(password.trim().equals("admin")){
                System.out.println();
                System.out.println("Hello " + userName);
                check = true;
                while(check){
                    System.out.println();
                    System.out.println("1. Update Record");
                    System.out.println("2. View All Records");
                    System.out.println("3. Search Record");
                    System.out.println("4. Delete Record");
                    System.out.println("Press any other key to exit");
                    ch = input.next().charAt(0);
                    try {
                    if(ch == '1')
                        admin_update_record();
                    else if(ch == '2')
                        admin_view_record();
                    else if(ch == '3')
                        admin_search_record();
                    else if(ch == '4')
                        admin_delete_record();
                    else {
                        panels();
                        return;
                    }
                }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
        } else
            System.out.println("Invalid User!");
    }
        catch (InputMismatchException e) {
            System.out.println("Invalid Input!");
            input.next();
        }	
    }

    public static void main_menu_customer(){
        Scanner input = new Scanner(System.in);
        char choice;
        System.out.println();
        System.out.println(" --------------------------------------------------------------- ");
        System.out.println("          \t             WELCOME TO THE MAIN MENU       ");
        System.out.println(" --------------------------------------------------------------- ");

        System.out.println();
        System.out.println(" Press 1 for Room Selection and Rent Menu\t   Press 2 for Laundry Menu\n\n Press 3 for Restaurant Menu\t\t\t   Press 4 to Checkout \n\n\t\t         Press any other key to exit: ");
        choice = input.next().charAt(0);
        switch (choice) {
            case '1': if(room_count > 0){
                System.out.println();
                System.out.println("You have already booked for the room!");
                roomselection_rent();
            }   else
                roomselection_rent();
                break;
            case '2':
                laundrybill();
                break;
            case '3':
                restaurantbill();
                break;
            case '4':
                try {
                    totalcost();
            }
                catch(IOException e) {
                e.printStackTrace();
            }
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public static void panels(){
        Scanner input = new Scanner(System.in);
        char confirm = 'y', choice;
        while (confirm == 'y'){
            System.out.println();
            while(confirm == 'y'){ 
            System.out.println("Select a particular Panel: ");
            System.out.println();
            System.out.println("_________________________________");
            System.out.println();
            System.out.println("1. Admin Panel\t2. Customer Panel");
            choice = input.next().charAt(0);
            if(choice == '1'){
                admin_panel();
                confirm = 'n';
            }
            else if(choice == '2'){
                customer_panel();
                confirm = 'n';
            }
            else
                System.out.println("Invalid Input!");
            }
            confirm = 'y';
            System.out.println("Press 'y' to continue");
            confirm = input.next().charAt(0);
        }
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        char confirm = 'y';
        System.out.println();
        System.out.println(" ---------------------------------------------------------------- ");
        System.out.println("                 WELCOME TO OUR HOTEL           ");
        System.out.println(" ---------------------------------------------------------------- ");
        while(confirm == 'y') {
        System.out.println();
        System.out.println("Press 1 for Islamabad Branch\nPress 2 for Lahore Branch\nPress 3 for Karachi Branch: ");
        branch = input.next().charAt(0); 
        if(branch == '1'){
            System.out.println("You opted for ISLAMABAD BRANCH");
            confirm = 'n';
        }
        else if(branch == '2'){
            System.out.println("You opted for LAHORE BRANCH");
            confirm = 'n';
        }
        else if(branch == '3'){
            System.out.println("You opted for KARACHI BRANCH");
            confirm = 'n';
        }
        else {
            System.out.println("INVALID INPUT");
            confirm = 'y';
        }
        System.out.println();
    }
        panels();
    }
}