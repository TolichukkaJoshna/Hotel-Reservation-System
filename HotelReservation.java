import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class HotelReservation{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Hotel Reservation System!");
        System.out.println("1. Register/Login");
        System.out.println("2. Browse Hotels");
        System.out.println("3. Exit");
        System.out.println("Choose any one of them");
        int t=sc.nextInt();
        User  user=new User();
        switch(t){
            case 1:
                boolean a=user.login();
                if(a){
                    System.out.println("\nLogin Successful!\n");
                }
                else{
                    System.out.println("Login Unsucccessfull! Invalid Code Entered Please Verify your Code");
                    return;
                }
                break;
            case 2:
                user.hotels();
                return;
            case 3:
                return;
            default:
                System.out.println("Invalid Choose");
                break;
        }
        System.out.println("\nAvailable Hotels:");
        String[] hotel=user.hotels();
        System.out.println("\nSelect the Hotel you want to Book:");
        int m=sc.nextInt();
        String[] rooms={"Single Room","Double Room","suite"};
        int[] p={100,150,300};
        boolean b=false;
        int r=0;
        long price=0;
        String checkin="";
        String checkout="";
        long nights=0;
        long total=0;
        while(b==false){
            if(m>0 && m<5){
                b=true;
                System.out.println("\nRooms at "+hotel[m-1]+" : ");
                for(int i=0;i<rooms.length;i++){
                    System.out.println((i+1)+". "+rooms[i]+" ($" +p[i]+" per night)");
                }
                System.out.println("\nselect the room you want to book:");
                r=sc.nextInt();
                boolean c=false;
                while(c==false){
                    if(r>0 && r<4){
                        c=true;
                        System.out.println("\nSelected Room : "+rooms[r-1]);
                    }
                    else{
                        System.out.println("invalid choice try again:");
                        r=sc.nextInt();
                    }
                }
                System.out.println("\nEnter Check-in Date (YYYY-MM-DD) : ");
                sc.nextLine();
                checkin=sc.nextLine();
                System.out.println("Enter Check-out Date (YYYY-MM-DD) : ");
                checkout=sc.nextLine();
                nights=user.date(checkin,checkout);
                System.out.println("\nReservation Summary: \n");
                System.out.println("Hotel: "+hotel[m-1]);
                System.out.println("Room: "+rooms[r-1]);
                System.out.println("Check-in: "+checkin);
                System.out.println("Check-out: "+checkout);
                boolean n=false;
                while(n==false){
                    if(nights>0){
                        System.out.println("Total Nights: "+nights);
                        n=true;
                    }
                    else{
                        System.out.println("Please make sure You enter the dates correctly!");
                        System.out.println("\nEnter Check-in Date (YYYY-MM-DD) : ");
                        checkin=sc.nextLine();
                        System.out.println("Enter Check-out Date (YYYY-MM-DD) : ");
                        checkout=sc.nextLine();
                        nights=user.date(checkin,checkout);
                    }
                }
                total=p[r-1]*nights;
                System.out.println("Total Price: "+total);

            }
            else{
                System.out.println("invalid chocie Try Again :");
                m=sc.nextInt();
            }
        }
        System.out.println("\nDo you want to Confirm Reservation(yes/no): ");
        String str=sc.nextLine();
        boolean tr=false;
        while(tr==false){
        if(str.equals("yes") || str.equals("Yes") || str.equals("YES")){
             user.payment(total);
             tr=true;
        }
        else if(str.equals("no")||str.equals("No")||str.equals("NO")){
            return;
        }
        else{
            System.out.println("Please enter yes/no: ");
            str=sc.nextLine();
        }
         }
        System.out.println("\nYour Reservation details are :\n");
        System.out.println("Hotel: "+hotel[m-1]);
        System.out.println("Room: "+rooms[r-1]);
        System.out.println("Check-in: "+checkin);
        System.out.println("Check-out: "+checkout);
        System.out.println("Total Nights: "+nights);
        user.time();
        System.out.println("1. Logout");
        int c=sc.nextInt();
        boolean l=false;
        while(l==false){
        if(c==1){
            l=true;
            System.out.println("\nThanks for the Booking!");
            System.out.println("Hope you will enjoy the stay!\n");
        }
        else{
            System.out.println("Please Enter 1 to Logout");
            c=sc.nextInt();
        }
        }
    }
}

class User{
    static String name;
    static String email;
    static String password;
    static int uc;
   public boolean login(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\nEnter Your Name : ");
        name=sc.nextLine();
        formatter();
        System.out.println("Enter Your Email :");
        User.email=sc.nextLine();
        System.out.println("Enter Your Mail ID Password :");
        User.password=sc.nextLine();
        System.out.println("Enter the below provided OTP :");
        int rc=random();
        System.out.println(rc);
        User.uc=sc.nextInt();
        if(rc==uc){
            return true;
        }
        return false;    
   }
   public int random(){
    Random ran=new Random();
    int rc=ran.nextInt(10000);
    return rc;
   }
   public void formatter(){
        Formatter f=new Formatter();
        f.format("\nWelcome, %s!\n",User.name);
        System.out.println(f);
        f.close();
   }

   public void time(){
    LocalDateTime currentDateTime=LocalDateTime.now();
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss\n");
    String formattedDate=currentDateTime.format(formatter);
    System.out.println(formattedDate);
    }    
   public String[] hotels(){
    String hotel[]={"Grand Plaza (Location: New York)","Sea View Resort (Location: Miami)","Taj Lake Palace (Location: Udaipur)","Marina Bay Sands (Location: Singapore)","Taj Hotel (Loacation: Mumbai)"};
    for(int i=0;i<hotel.length;i++){
        System.out.println((i+1)+". "+hotel[i]);
    }
    return hotel;
}
public void payment(long price){
    Scanner sc=new Scanner(System.in);
    System.out.println("\nEnter Payment Mode");
    System.out.println("1. Credit Card");
    System.out.println("2. UPI");
    int pay=sc.nextInt();
    if(pay==1){
        System.out.println("\nEnter Credit Card details:");
        System.out.println("Enter credit card number: ");
        int acc=sc.nextInt();
        System.out.println("Enter your credit pin :");
        int pin=sc.nextInt();
        System.out.println("Enter "+price+" rupees: ");
        int amo=sc.nextInt();
        boolean a=false;
        while(a==false){
            if(price==amo){
                a=true;
                System.out.println("\nPayment Successful!\n");
            }
            else{
                System.out.println("\nInvalid amount entered please Enter correct amount :");
                amo=sc.nextInt();
            }
        }
    }
    else if(pay==2){
        System.out.println("Enter your UPI pin:");
        int pin=sc.nextInt();
        System.out.println("\nEnter "+price+" rupees: ");
        int amo=sc.nextInt();
        boolean a=false;
        while(a==false){
            if(price==amo){
                a=true;
                System.out.println("\nPayment Successful!\n");
            }
            else{
                System.out.println("\nInvalid amount entered please Enter correct amount :");
                amo=sc.nextInt();
            }
        }
    }
}
   public long date(String str1,String str2) {
	String dateBeforeString = str1;
	String dateAfterString = str2;
		
	//Parsing the date
	LocalDate dateBefore = LocalDate.parse(dateBeforeString);
	LocalDate dateAfter = LocalDate.parse(dateAfterString);
		
	//calculating number of days in between
	long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		
	//displaying the number of days
	return noOfDaysBetween;
   } 
}