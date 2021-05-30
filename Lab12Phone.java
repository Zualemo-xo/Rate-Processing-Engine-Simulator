import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
import java.text.*;


public class Lab12Phone extends Application {

	@Override
	public void start(Stage primaryStage)
	{
        
        Label lab1=new Label("PhoneNo");
        Label lab2=new Label("Name");
        Label lab3=new Label("Bill Date");
        Label lab4=new Label("Amount");
        Label lab5=new Label("Payment Due Date");

        TextField tf1=new TextField();  
        TextField tf2=new TextField(); 
        TextField tf3=new TextField();
        TextField tf4=new TextField(); 
        TextField tf5=new TextField(); 
    
        
        Button btn1 = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();
        Button btn5 = new Button();
        Button btn6 = new Button();
        Button btn7 = new Button();
        Button btn8 = new Button();
        btn1.setText("Create Table");
        btn2.setText("Insert");
        btn3.setText("Update Bill");
        btn4.setText("Delete particular");
        btn5.setText("Display");
        btn6.setText("Display wrt date");
        btn7.setText("Display wrt amount");
        btn8.setText("Display wrt due date");

        
        //setting scene
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.addRow(0,lab1,tf1);
        grid.addRow(1,lab2,tf2);
        grid.addRow(2,lab3,tf3);
        grid.addRow(3,lab4,tf4);
        grid.addRow(4,lab5,tf5);

        grid.add(btn1,0,6);
        grid.add(btn2,1,6);
        grid.add(btn3,2,6);
        grid.add(btn4,3,6);
        grid.add(btn5,0,7);
        grid.add(btn6,1,7);
        grid.add(btn7,2,7);
        grid.add(btn8,3,7);

        Scene scene = new Scene(grid, 500, 500);
        
        primaryStage.setTitle(" Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Create Table
        btn1.setOnAction(new EventHandler<ActionEvent>() 
        {  
            @Override
             public void handle(ActionEvent event) {
		          Connection conn = null;
		    Statement stmt = null;
		    try{
		       Class.forName("com.mysql.jdbc.Driver");
		       System.out.println("Connecting to a selected database...");
		       conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
		       System.out.println("Connected database successfully...");
		       
		       System.out.println("Creating table in given database...");
		       stmt = conn.createStatement();
		       
		       
		       String sql = "CREATE TABLE phone(phno VARCHAR(50),name VARCHAR(50),billdate DATE,amount INTEGER,paymentdate DATE)"; 
		       stmt.executeUpdate(sql);
		       System.out.println("succeefully created");
		
		
		
		         }    
		    catch(SQLException se){
		       //Handle errors for JDBC
		       se.printStackTrace();
		    }  catch(Exception e){
		       //Handle errors for Class.forName
		       e.printStackTrace();
		    } finally{
		       //finally block used to close resources
		       try{
		          if(stmt!=null)
		             conn.close();
		       }  catch(SQLException se){
		       } // do nothing
		       try {
		          if(conn!=null)
		             conn.close();
		       }  catch(SQLException se){
		          se.printStackTrace();
		       } //end finally try
		    } //end try
		             }   
		 });
        
        //Insert records
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    
                    Connection conn = null;
                    Statement stmt = null;
                    
                    
                    try {
                        //STEP 2: Register JDBC driver
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                      
                    
                    System.out.println("Connecting to a selected database...");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
                    System.out.println("Connected database successfully...");
                    
                    //STEP 4: Execute a query
                    stmt = conn.createStatement();
                    System.out.println(tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText());
                    String sql = "INSERT INTO phone VALUES ( '"+tf1.getText()+"' , '"+tf2.getText()+"' , "+ "STR_TO_DATE('"+tf3.getText()+"', '%d/%m/%Y') ,"+Integer.parseInt(tf4.getText()) +" ,  "+ "STR_TO_DATE('"+tf5.getText()+"', '%d/%m/%Y'))";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        
        //update
        btn3.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                	
                    //newWindow.close();
                    try {
                        
                        Connection conn = null;
                        Statement stmt = null;
                        
                        
                        try {
                            //STEP 2: Register JDBC driver
                            Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                          
                        
                        System.out.println("Connecting to a selected database...");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
                        System.out.println("Connected database successfully...");
                        String newph=tf1.getText();
                        String amt=tf4.getText();
                        //STEP 4: Execute a query
                        stmt = conn.createStatement();
                        //System.out.println(tf1.getText()+tf2.getText()+tf3.getText()+tf4.getText()+tf5.getText());
                        //String sql = "INSERT INTO phone VALUES ( '"+tf1.getText()+"' , '"+tf2.getText()+"' , "+ "STR_TO_DATE('"+tf3.getText()+"', '%d/%m/%Y') ,"+Integer.parseInt(tf4.getText()) +" ,  "+ "STR_TO_DATE('"+tf5.getText()+"', '%d/%m/%Y'))";
                        String sql="update phone set amount= "+amt+" where phno="+newph;
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                
            }
            });

                
                

       //delete
        btn4.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
                	
                    //newWindow.close();
                    try {
                        
                        Connection conn = null;
                        Statement stmt = null;
                        
                        
                        try {
                            //STEP 2: Register JDBC driver
                            Class.forName("com.mysql.jdbc.Driver");
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                          
                        
                        System.out.println("Connecting to a selected database...");
                        conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
                        System.out.println("Connected database successfully...");
                        String newph=tf1.getText();
                        //STEP 4: Execute a query
                        stmt = conn.createStatement();
                        String sql="delete from phone where phno= "+newph;
                        System.out.println(sql);
                        stmt.executeUpdate(sql);
                        System.out.println("Deleted sucessfully...");
                
                    } catch (SQLException ex) {
                        Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                
            }
            });   
            

        
        //display all records
        btn5.setOnAction(new EventHandler<ActionEvent>() {  
            @Override
             public void handle(ActionEvent event) {
          Connection conn = null;
			    Statement stmt = null;
			    try{
			       //STEP 2: Register JDBC driver
			       Class.forName("com.mysql.jdbc.Driver");
			       System.out.println("Connecting to a selected database...");
			       conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
			       System.out.println("Connected database successfully...");
			       stmt = conn.createStatement();
			    
			 String sql1 = "SELECT * FROM phone "; 
			 //WHERE DOB = '1975-07-24'
			 //stmt.executeQuery(sql1);
			
			       ResultSet rs = stmt.executeQuery(sql1);
			       //STEP 5: Extract data from result set
			       while(rs.next()){
			          //Retrieve by column name
			          
			          
			          String phno = rs.getString("phno");
			          String name = rs.getString("name");
			          String billdate = rs.getString("billdate");
			          int amount  = rs.getInt("amount");
			          String paymentdate = rs.getString("paymentdate");
			          //Display values
			          System.out.print("\nPhone no: " + phno);
			          System.out.print(", Name: " + name);
			          System.out.print(", Bill date: " + billdate);
			          System.out.print(", Amount: " + amount);
			          System.out.print(", Payment date: " + paymentdate);

			       }
			          System.out.println();

			
			         }    catch(SQLException se){
			       //Handle errors for JDBC
			       se.printStackTrace();
			    }  catch(Exception e){
			       //Handle errors for Class.forName
			       e.printStackTrace();
			    } finally{
			       //finally block used to close resources
			       try{
			          if(stmt!=null)
			             conn.close();
			       }  catch(SQLException se){
			       } // do nothing
			       try {
			          if(conn!=null)
			             conn.close();
			       }  catch(SQLException se){
			          se.printStackTrace();
			       } //end finally try
			    } //end try
             }   
    });  
        
        btn6.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	
              Label labA=new Label("Date to be searched(DD/MM/YYYY):");
              TextField tfA=new TextField();  
              Button btnA= new Button ("Process");


              GridPane secondaryLayout=new GridPane();
              secondaryLayout.addRow(0,labA,tfA);

              secondaryLayout.addRow(2,btnA);
              Scene secondScene = new Scene(secondaryLayout, 400, 400);
              // New window (Stage)
              Stage newWindow = new Stage();
              newWindow.setTitle("Second Stage");
              newWindow.setScene(secondScene);
              secondaryLayout.setAlignment(Pos.CENTER);

              newWindow.setX(primaryStage.getX() + 200);
              newWindow.setY(primaryStage.getY() + 100);

 
                newWindow.show();
                btnA.setOnAction(new EventHandler<ActionEvent>() {
 
		            @Override
		            public void handle(ActionEvent event) {
		            	
		            	
	                    try {
	                        
	                        Connection conn = null;
	                        Statement stmt = null;
	                        
	                        
	                        try {
	                            //STEP 2: Register JDBC driver
	                            Class.forName("com.mysql.jdbc.Driver");
	                        } catch (ClassNotFoundException ex) {
	                            Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        
	                          
	                        
	                        System.out.println("Connecting to a selected database...");
	                        conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
	                        System.out.println("Connected database successfully...");
	                    	String dat="'"+(String)tfA.getText()+"'";
	                        stmt = conn.createStatement();

	                        String datz=tfA.getText();
	                        //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	                        java.sql.Date sqlDate=null;
	                        try {
	                            java.util.Date utilDate = format.parse(datz);
	                             sqlDate = new java.sql.Date(utilDate.getTime());
	                            System.out.println(sqlDate);
	                        } catch (ParseException e) {
	                            e.printStackTrace();
	                        }

	                        String sql="select * from phone where  billdate = "+"'"+sqlDate+"'";
	                        System.out.println(sql);
	                        ResultSet rs =stmt.executeQuery(sql);
	     			       while(rs.next()){
	     			          String phno = rs.getString("phno");
	     			          String name = rs.getString("name");
	     			          String billdate = rs.getString("billdate");
	     			          int amount  = rs.getInt("amount");
	     			          String paymentdate = rs.getString("paymentdate");
	     			          //Display values
	     			          System.out.print("\nPhone no: " + phno);
	     			          System.out.print(", Name: " + name);
	     			          System.out.print(", Bill date: " + billdate);
	     			          System.out.print(", Amount: " + amount);
	     			          System.out.print(", Payment date: " + paymentdate);

	     			       }
	     			          System.out.println(); 
	                    } catch (SQLException ex) {
	                        Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
	                    }
		            }
		                });
            }
        });
        
        
        
        //>1000 amount display
        btn7.setOnAction(new EventHandler<ActionEvent>() {  
            @Override
             public void handle(ActionEvent event) {
          Connection conn = null;
			    Statement stmt = null;
			    try{
			       //STEP 2: Register JDBC driver
			       Class.forName("com.mysql.jdbc.Driver");
			       System.out.println("Connecting to a selected database...");
			       conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
			       System.out.println("Connected database successfully...");
			       stmt = conn.createStatement();
			    
			 String sql1 = "SELECT * FROM phone WHERE amount > 1000"; 

			
			       ResultSet rs = stmt.executeQuery(sql1);
			       //STEP 5: Extract data from result set
			       while(rs.next()){
			          //Retrieve by column name
			          
			          
			          String phno = rs.getString("phno");
			          String name = rs.getString("name");
			          String billdate = rs.getString("billdate");
			          int amount  = rs.getInt("amount");
			          String paymentdate = rs.getString("paymentdate");
			          //Display values
			          System.out.print("\nPhone no: " + phno);
			          System.out.print(", Name: " + name);
			          System.out.print(", Bill date: " + billdate);
			          System.out.print(", Amount: " + amount);
			          System.out.print(", Payment date: " + paymentdate);

			       }
			          System.out.println();

			
			         }    catch(SQLException se){
			       //Handle errors for JDBC
			       se.printStackTrace();
			    }  catch(Exception e){
			       //Handle errors for Class.forName
			       e.printStackTrace();
			    } finally{
			       //finally block used to close resources
			       try{
			          if(stmt!=null)
			             conn.close();
			       }  catch(SQLException se){
			       } // do nothing
			       try {
			          if(conn!=null)
			             conn.close();
			       }  catch(SQLException se){
			          se.printStackTrace();
			       } //end finally try
			    } //end try
             }   
    });  
        
        
        btn8.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
            	
              Label labA=new Label("Date to be searched(DD/MM/YYYY):");
              TextField tfA=new TextField();  
              Button btnA= new Button ("Process");


              GridPane secondaryLayout=new GridPane();
              secondaryLayout.addRow(0,labA,tfA);

              secondaryLayout.addRow(2,btnA);
              Scene secondScene = new Scene(secondaryLayout, 400, 400);
              // New window (Stage)
              Stage newWindow = new Stage();
              newWindow.setTitle("Second Stage");
              newWindow.setScene(secondScene);
              secondaryLayout.setAlignment(Pos.CENTER);

              newWindow.setX(primaryStage.getX() + 200);
              newWindow.setY(primaryStage.getY() + 100);

 
                newWindow.show();
                btnA.setOnAction(new EventHandler<ActionEvent>() {
 
		            @Override
		            public void handle(ActionEvent event) {
		            	
		            	
	                    try {
	                        
	                        Connection conn = null;
	                        Statement stmt = null;
	                        
	                        
	                        try {
	                            //STEP 2: Register JDBC driver
	                            Class.forName("com.mysql.jdbc.Driver");
	                        } catch (ClassNotFoundException ex) {
	                            Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
	                        }
	                        
	                          
	                        
	                        System.out.println("Connecting to a selected database...");
	                        conn = DriverManager.getConnection("jdbc:mysql://localhost/STUDENTS", "root", "root");
	                        System.out.println("Connected database successfully...");
	                    	String dat="'"+(String)tfA.getText()+"'";
	                        stmt = conn.createStatement();

	                        String datz=tfA.getText();
	                        //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	                        java.sql.Date sqlDate=null;
	                        try {
	                            java.util.Date utilDate = format.parse(datz);
	                             sqlDate = new java.sql.Date(utilDate.getTime());
	                            System.out.println(sqlDate);
	                        } catch (ParseException e) {
	                            e.printStackTrace();
	                        }

	                        String sql="select * from phone where  paymentdate <= "+"'"+sqlDate+"'";
	                        System.out.println(sql);
	                        ResultSet rs =stmt.executeQuery(sql);
	     			       while(rs.next()){
	     			          String phno = rs.getString("phno");
	     			          String name = rs.getString("name");
	     			          String billdate = rs.getString("billdate");
	     			          int amount  = rs.getInt("amount");
	     			          String paymentdate = rs.getString("paymentdate");
	     			          //Display values
	     			          System.out.print("\nPhone no: " + phno);
	     			          System.out.print(", Name: " + name);
	     			          System.out.print(", Bill date: " + billdate);
	     			          System.out.print(", Amount: " + amount);
	     			          System.out.print(", Payment date: " + paymentdate);

	     			       }
	     			          System.out.println(); 
	                    } catch (SQLException ex) {
	                        Logger.getLogger(qq.class.getName()).log(Level.SEVERE, null, ex);
	                    }
		            }
		                });
            }
        });
        
        
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   System.out.println("Hello!");
	        launch(args);	}

}
