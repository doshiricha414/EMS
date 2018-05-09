import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{

Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;

MainFrame()
{

c=getContentPane();
c.setLayout(new FlowLayout());
btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);

btnAdd.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent ae){
AddFrame a = new AddFrame();
dispose();
}
});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
ViewFrame v = new ViewFrame();
dispose();
}
});


btnUpdate.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
UpdateFrame u = new UpdateFrame();
dispose();
}
});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
DeleteFrame d = new DeleteFrame();
dispose();
}
});

setSize(500,150);
setLocationRelativeTo(null);
setTitle("Employee Management System");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}//end of constructor

public static void main(String arg[]){
MainFrame a = new MainFrame();
}//end of main
}//end of class

class DatabaseHandler
{
static Connection con;//only 1 connection is needed,so static

static void getCon()
{
try
{
DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());   //loading the driver
con=DriverManager.getConnection
("jdbc:oracle:thin:@localhost:1521:xe","system","hello123");//getting the connection
}
catch(SQLException e)
{
System.out.println(e);
}
}//end of getCon

public void addEmployee(int id,String name)
{
getCon();
try
{
String sql="insert into employee values(?,?)";
PreparedStatement pst=con.prepareStatement(sql);//for dynamic input
pst.setInt(1,id);
pst.setString(2,name);
int r =pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"records inserted");
}

catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"insert issue");
}
}//end of addEmployee

public void updateEmployee(int id,String name)
{
getCon();
try
{
String sql="update employee set ename=? where eid=?";
PreparedStatement pst=con.prepareStatement(sql);//for dynamic input
pst.setString(1,name);
pst.setInt(2,id);
int r =pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"records updated");
}

catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),"update issue");
}
}//end of updateEmployee

public void deleteEmployee(int id)
{
getCon();
try
{
String sql="delete from employee where EID=?";
PreparedStatement pst=con.prepareStatement(sql);//for dynamic input
pst.setInt(1,id);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"records deleted");
}

catch(SQLException e)
{
JOptionPane.showMessageDialog(new JDialog(),e);
}
}//end of deleteEmployee

public String viewEmployee()
{
getCon();
StringBuffer sb = new StringBuffer();
try
{
Statement s1 = con.createStatement();
String s2 = "select * from employee";
ResultSet rs = s1.executeQuery(s2);
while(rs.next())
{
int id = rs.getInt(1);
String name = rs.getString(2);
sb.append("Id:"+id+"Name:"+name+"\n");

}//end of while

}
catch(SQLException e){}
//end of try

return sb.toString();
}//End of viewEmployee



}//end of class




/*
Compile : javac -cp ojdbc14.jar *.java
Run : java -cp ojdbc14.jar;. MainFrame


*/

//98214siku















