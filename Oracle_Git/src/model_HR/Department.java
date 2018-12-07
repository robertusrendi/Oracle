/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_HR;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyOracle;

/**
 *
 * @author admin
 */
public class Department {

    private int deparment_ID;
    private String department_name;

    private ArrayList<Employee> listEmployees = new ArrayList<Employee>();
    private ArrayList<Employee> managers = new ArrayList<Employee>();
    private Employee manager;

    public ArrayList<Employee> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<Employee> managers) {
        this.managers = managers;
    }

    public Department() {
    }

    public Department(int deparment_ID, String department_name) {
        this.deparment_ID = deparment_ID;
        this.department_name = department_name;
    }

    /**
     * Fungsi untuk membaca daftar/table employee lalu dipindahkan ke list
     * daftar employees;
     */
    public void readEmployees() {
        try {
            // buat koneksi
            MyOracle ora = new MyOracle("172.23.9.185", "1521", "orcl", "MHS175314086", "MHS175314086");
            //step2 create  the connection object
            Connection con = ora.getConnection();
            //step3 create the statement object
            Statement stmt = con.createStatement();
            //step4 execute query
            String query = "select * from employees where department_id =" + getDeparment_ID();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
//                System.out.println(rs.getString(1) + "  " + rs.getString(2));
                Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
//                if (rs.getInt(11) == deparment_ID) {
                listEmployees.add(emp);
//                }
            }
            //step5 close the connection object
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Fungsi untuk membaca manager sebuah departemen
     */
    public void readManager() {
        try {
            // buat koneksi
            MyOracle ora = new MyOracle("172.23.9.185", "1521", "orcl", "MHS175314086", "MHS175314086");
            //step2 create  the connection object
            Connection con = ora.getConnection();
            //step3 create the statement object
            Statement stmt = con.createStatement();
            //step4 execute query
            String query = "select distinct d.employee_id, d.first_name, d.last_name,d.department_id, d.manager_id from employees e \n" 
                            +"join employees d on e.manager_id = d.employee_id \n" + "where e.department_id = "+ getDeparment_ID();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
//                System.out.println(rs.getString(1) + "  " + rs.getString(2));
                Employee emp = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3));
//                if (rs.getInt(10) == deparment_ID) {
                managers.add(emp);
//                }
            }
            //step5 close the connection object
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the deparment_ID
     */
    public int getDeparment_ID() {
        return deparment_ID;
    }

    /**
     * @param deparment_ID the deparment_ID to set
     */
    public void setDeparment_ID(int deparment_ID) {
        this.deparment_ID = deparment_ID;
    }

    /**
     * @return the department_name
     */
    public String getDepartment_name() {
        return department_name;
    }

    /**
     * @param department_name the department_name to set
     */
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    /**
     * @return the listEmployees
     */
    public ArrayList<Employee> getListEmployees() {
        return listEmployees;
    }

    /**
     * @param listEmployees the listEmployees to set
     */
    public void setListEmployees(ArrayList<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    /**
     * @return the manager
     */
    public Employee getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(Employee manager) {
        this.manager = manager;
    }

}
