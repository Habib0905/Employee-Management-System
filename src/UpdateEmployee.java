import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfname, tfaddress, tfphone, tfemail, tfsalary, tfjob, tfhours, tfbasesalary, tfemptype;
    JDateChooser dcdob;
    JButton add, back;
    JLabel lblhours;
    JComboBox<String> cbEmployeeType;
    int empId;

    UpdateEmployee(int empId) {
        this.empId = empId;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);
        
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);
        

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        
        JFormattedTextField tfsalary = new JFormattedTextField(formatter);
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);
        
        JLabel labelbasesalary = new JLabel("Base Salary");
        labelbasesalary.setBounds(400, 200, 150, 30);
        labelbasesalary.setFont(new Font("serif", Font.PLAIN, 20));
        labelbasesalary.setVisible(false);
        add(labelbasesalary);

        JFormattedTextField tfbasesalary = new JFormattedTextField(formatter);
        tfbasesalary.setBounds(600, 200, 150, 30);
        tfbasesalary.setVisible(false);
        add(tfbasesalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labelemptype = new JLabel("Employee Type");
        labelemptype.setBounds(400, 150, 150, 30);
        labelemptype.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemptype);

        
        String emptype[] = {"Part Time", "Full Time"};
        cbEmployeeType = new JComboBox<>(emptype);
        cbEmployeeType.setBackground(Color.WHITE);
        cbEmployeeType.setBounds(600, 150, 150, 30);
        cbEmployeeType.setEnabled(false);
        cbEmployeeType.setEditable(false);
        add(cbEmployeeType);

        lblhours = new JLabel("Hours");
        lblhours.setBounds(400, 250, 100, 30);
        lblhours.setFont(new Font("serif", Font.PLAIN, 20));
        lblhours.setVisible(false);
        add(lblhours);
        
        
        JFormattedTextField tfhours = new JFormattedTextField(formatter);
        tfhours.setBounds(600, 250, 150, 30);
        tfhours.setVisible(false);
        add(tfhours);

        JLabel labeldesignation = new JLabel("Designation");
        labeldesignation.setBounds(50, 350, 150, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        tfjob = new JTextField();
        tfjob.setBounds(200, 350, 150, 30);
        add(tfjob);

         try {
            EmployeeManager manager = new EmployeeManager();
            
            String empType = manager.employeeDAO.getEmployeeType(empId);
            
            if(empType.equals("Part-Time")){
                PartTime employee = (PartTime)manager.employeeDAO.getEmployee(empId);
                
             tfname.setText(employee.getName());               
                String dateAsString = employee.getDob();

                try {                  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                    Date date = sdf.parse(dateAsString);
                    dcdob.setDate(date);
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
                tfaddress.setText(employee.getAddress());
                tfsalary.setValue(employee.getSalary());
                tfemail.setText(employee.getEmail());
                cbEmployeeType.setSelectedItem("Part Time");
                tfbasesalary.setValue(employee.getBaseSalary());
                tfhours.setValue(employee.getHours());
                tfjob.setText(employee.getJob());
         }
            else{
                FullTime employee = (FullTime)manager.employeeDAO.getEmployee(empId);
                tfname.setText(employee.getName());               
                String dateAsString = employee.getDob();

                try {                  
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                    Date date = sdf.parse(dateAsString);
                    dcdob.setDate(date);
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
                tfaddress.setText(employee.getAddress());
                tfsalary.setValue(employee.getSalary());
                tfemail.setText(rs.getString("email"));
                tfemptype.setText(rs.getString("emp_type"));
                tfbasesalary.setText(employee);
                tfhours.setText(rs.getString("hours"));
                tfjob.setText(rs.getString("job"));
            }
            
                
                
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        add = new JButton("Update Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);

        cbEmployeeType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) cbEmployeeType.getSelectedItem();
                if (selectedType.equals("Part Time")) {
                    lblhours.setVisible(true);
                    tfhours.setVisible(true);
                    labelbasesalary.setVisible(true);
                    tfbasesalary.setVisible(true);
                    labelsalary.setVisible(false);
                    tfsalary.setVisible(false);
                } else {
                    lblhours.setVisible(false);
                    tfhours.setVisible(false);
                    labelbasesalary.setVisible(false);
                    tfbasesalary.setVisible(false);
                    labelsalary.setVisible(true);
                    tfsalary.setVisible(true);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String email = tfemail.getText();
            String empType = (String) cbEmployeeType.getSelectedItem();
            String job = tfjob.getText();
            
            double hours = 0.0; 
            double baseSalary = 0.0;
            double salary = 0.0;
            try {
                String input = tfhours.getText(); 
                hours = Double.parseDouble(input); 
            } catch (NumberFormatException e) {

            }
            
            try {
                String input = tfbasesalary.getText(); 
                baseSalary = Double.parseDouble(input); 
            } catch (NumberFormatException e) {

            }
            
            try {
                String input = tfsalary.getText(); 
                salary = Double.parseDouble(input); 
            } catch (NumberFormatException e) {

            }
            
            try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = inputFormat.parse(dob);
            dob = outputFormat.format(date);

                
            } catch (Exception e) {               
                e.printStackTrace();
}
            


            try {
                EmployeeManager manager = new EmployeeManager();
                EmployeeFactory employeeFactory = new EmployeeFactory();
       
                
                if (empType.equals("Part Time")){
                    Employee partTimeEmployee = employeeFactory.createPartTimeEmployee(name, email, address, dob, job, "Part-Time", baseSalary, hours);
                    manager.insertEmployee(partTimeEmployee);
                }
                else{
                     Employee fullTimeEmployee = employeeFactory.createFullTimeEmployee(name, email, address, dob, job, "Full-Time", salary);
                     manager.insertEmployee(fullTimeEmployee);
                }
                
                JOptionPane.showMessageDialog(null, "Details added successfully");
                setVisible(false);
                //new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            //new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}

