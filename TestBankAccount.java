package BankAccountAssignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestBankAccount {
	public static void main(String[] args) throws NegativeBalanceException, LowBalanceException,InsufficientBalanceException,IOException, ClassNotFoundException, SQLException{
		BankAccount emp = null;
		boolean exit = false;
		Connection con =null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			con = DriverManager.getConnection(url,"system","Prerana");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Scanner sc = new Scanner(System.in);
			do {
			 int ch =0;
			 System.out.println("\n1. Create account  \n2. Check Balance \n3. Deposit the amount \n4. Withdraw the amount \n5. Exit ");  
		     System.out.println("\nEnter your choice: ");  
		     ch = Integer.parseInt(br.readLine());
		     switch(ch) {
		     case 1:
		    	 try {
					
		    		System.out.println("Enter Account Number :");
		 			int account_number = Integer.parseInt(br.readLine());
		 			System.out.println("Enter Cust Name :");
		 			String name = br.readLine();
		 			System.out.println("Enter account_type :");
		 			String account_type = br.readLine();
		 			System.out.println("Enter account balance :");
		 			double balance = Double.parseDouble(br.readLine());
		 			
		 			if(account_type.equalsIgnoreCase("Saving") && balance < 1000) {
		 	        	 try {
		 					throw new LowBalanceException("\nSrroy, We can't create Account. Balance is less than minimum balance");
		 				} catch (LowBalanceException e) {
		 					System.out.println(e.getMessage());
		 				}
		 	         }
		 	         else if(account_type.equalsIgnoreCase("Current") && balance < 5000) {
		 	        	 try {
		 					throw new LowBalanceException("\nSrroy, We can't create Account. Balance is less than minimum balance");
		 				} catch (LowBalanceException e) {
		 					System.out.println(e.getMessage());
		 				}
		 	         }
		 	         else{
		 	        	 String sql = "insert into customer values("+account_number+",'"+name+"','"+account_type+"',"+balance+")";	//Dynamic Query
		 	        	 Statement st = con.createStatement();
		 	        	 int rows = st.executeUpdate(sql);
		 	        	 if(rows>0) {
		 	        		 System.out.println("Account is created Successfully !!!");
		 	        	 }
		 			}
		    	 	}catch (IOException e) {
						e.printStackTrace();
					}
					catch(ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
		    	 break; 
		    	 
		     case 2:
		    	 System.out.println("Enter ACCOUNT NO : ");
		    	 int acc_no = sc.nextInt();
				 String sql = "Select * from customer where ACCOUNT_NO = ?";
				 PreparedStatement ps =con.prepareStatement(sql);
				 ps.setInt(1, acc_no);
				 ResultSet rs = ps.executeQuery();
				 if(rs.next()) {
					sql = "select balance from customer where account_no=?";
					ps = con.prepareStatement(sql);
					ps.setInt(1, acc_no);
					int rows = ps.executeUpdate();
					if(rows>0) {
						System.out.println("Current Account balance is: "+rs.getInt("balance"));
					}
				 }
				 else {
					System.out.println("Account no does not exists !!!");
				 }
				 break;
		    	
		    	 
		     case 3:
				System.out.println("Enter ACCOUNT NO : ");
				acc_no = sc.nextInt();
				sql = "Select * from customer where ACCOUNT_NO = ?";
				ps =con.prepareStatement(sql);
				ps.setInt(1, acc_no);
				rs = ps.executeQuery();
				if(rs.next()) {
					System.out.println("Enter amount to be deposite : ");
					int depo_amt = sc.nextInt();
					if(depo_amt<0) {
						System.out.println("Deposite amount cannot be negative !!! Please enter appropriate amount !!!");
					}
					else {
						sql = "Update customer set balance = balance + "+depo_amt+" where ACCOUNT_NO = ?";
						ps = con.prepareStatement(sql);
						ps.setInt(1, acc_no);
						int rows = ps.executeUpdate();
						if(rows>0) {
							System.out.println("Amount deposited Successfully !!!");
						}
					}
				}
				else {
					System.out.println("Account no does not exists !!!");
				}
				break; 
				 
		     case 4:
				System.out.println("Enter ACCOUNT NO : ");
				acc_no = sc.nextInt();
				sql = "Select * from customer where ACCOUNT_NO = ?";
				ps =con.prepareStatement(sql);
				ps.setInt(1, acc_no);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("account_type")=="Saving" && rs.getInt("balance")<=1000) {
			        	 try {
							throw new InsufficientBalanceException("We can't withdraw amount, Insufficient balance");
						} catch (InsufficientBalanceException e) {
							System.out.println(e.getMessage());
						}
			         }
					else if(rs.getString("account_type")=="Current" && rs.getInt("balance")<=5000) {
			        	 try {
							throw new InsufficientBalanceException("We can't withdraw amount, Insufficient balance");
						} catch (InsufficientBalanceException e) {
							System.out.println(e.getMessage());
						}
			         }
					else {
						System.out.println("Enter amount to be Withdraw : ");
						int depo_amt = sc.nextInt();
						sql = "Update customer set balance = balance - "+depo_amt+" where ACCOUNT_NO = ?";
						ps = con.prepareStatement(sql);
						ps.setInt(1, acc_no);
						int rows = ps.executeUpdate();
						if(rows>0) {
							System.out.println("Amount withdraw Successfully !!!");
						}
					}
				}
				else {
					System.out.println("Account no does not exists !!!");
				}
				break; 
				 
		     case 5:
		    	 System.out.println("Thank You...");  
		         break;  
		     }
			
			}while(!exit) ;
	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
