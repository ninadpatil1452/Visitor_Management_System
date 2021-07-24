package home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;



public class Datasource {
    public static final String DB_NAME = "visitor.db";
    public static final String CONNECTION_STRING="jdbc:sqlite:D:\\databases\\"+DB_NAME;

    public static final String TABLE_VISITOR = "visitor";
    public static final String COLUMN_VISITOR_DATE = "date";
    public static final String COLUMN_VISITOR_NAME = "name";
    public static final String COLUMN_VISITOR_NUMBER = "number";
    public static final String COLUMN_VISITOR_INTIME = "intime";
    public static final String COLUMN_VISITOR_PURPOSE = "purpose";

    public static final String TABLE_EMPLOYEE = "employee";
    public static final String COLUMN_EMPLOYEE_ID = "id";
    public static final String COLUMN_EMPLOYEE_NAME = "name";
    public static final String COLUMN_EMPLOYEE_NUMBER = "number";
    public static final String COLUMN_EMPLOYEE_DEPARTMENT = "department";
    public static final String COLUMN_EMPLOYEE_POST = "post";

    public static final String TABLE_EMPLOYEEVISITOR = "employeeVisitor";
    public static final String COLUMN_EMPLOYEEVISITOR_ID = "id";
    public static final String COLUMN_EMPLOYEEVISITOR_NAME = "name";
    public static final String COLUMN_EMPLOYEEVISITOR_DATE = "date";
    public static final String COLUMN_EMPLOYEEVISITOR_TIME = "time";
    public static final String COLUMN_EMPLOYEEVISITOR_NUMBER = "number";
    public static final String COLUMN_EMPLOYEEVISITOR_DEPARTMENT = "department";



    private static Datasource instance = new Datasource();

    private Datasource(){

    }
    public static Datasource getInstance(){
        return instance;
    }

    public static final String QUERY_VISITOR_DISPLAY =
            "SELECT " + TABLE_VISITOR + '.'+COLUMN_VISITOR_DATE+", "+TABLE_VISITOR+'.'+COLUMN_VISITOR_INTIME+", "+
                    TABLE_VISITOR+'.'+COLUMN_VISITOR_NAME+", "+TABLE_VISITOR+'.'+COLUMN_VISITOR_NUMBER+", "+
                    TABLE_VISITOR+'.'+COLUMN_VISITOR_PURPOSE+" FROM "+TABLE_VISITOR;

    public  static  final String INSERT_VISITOR = "INSERT INTO "+TABLE_VISITOR+'('+COLUMN_VISITOR_NAME+", "+
            COLUMN_VISITOR_NUMBER+", "+COLUMN_VISITOR_PURPOSE+", "+COLUMN_VISITOR_INTIME+", "+COLUMN_VISITOR_DATE+
            " ) VALUES (?, ?, ?, time('now','localtime'), date('now'))";

    public static final String INSERT_EMPLOYEE = "INSERT INTO "+TABLE_EMPLOYEE+'('+COLUMN_EMPLOYEE_NAME+", "+
            COLUMN_EMPLOYEE_NUMBER+", "+COLUMN_EMPLOYEE_DEPARTMENT+","+COLUMN_EMPLOYEE_POST+" ) VALUES (?,?,?,?)";

    public static final String GET_EMPLOYEE_ID = "SELECT "+COLUMN_EMPLOYEE_ID+" FROM "+TABLE_EMPLOYEE+" WHERE "
            +COLUMN_EMPLOYEE_ID+" = (SELECT MAX(id) FROM "+TABLE_EMPLOYEE+" )";

    public static final String QUERY_EMPLOYEE_DISPLAY =
            "SELECT "+TABLE_EMPLOYEE+'.'+COLUMN_EMPLOYEE_ID+", "+TABLE_EMPLOYEE+'.'+COLUMN_EMPLOYEE_NAME+", "+
                    TABLE_EMPLOYEE+'.'+COLUMN_EMPLOYEE_NUMBER+", "+TABLE_EMPLOYEE+'.'+COLUMN_EMPLOYEE_DEPARTMENT+", "+
                    TABLE_EMPLOYEE+'.'+COLUMN_EMPLOYEE_POST+" FROM "+TABLE_EMPLOYEE;

    public static final String QUERY_EMPLOYEEVISITOR_DISPLAY = "SELECT * FROM "+TABLE_EMPLOYEE+" WHERE id = ( ? ) ";

    public static final String QUERY_EMPLOYEEVISITOR_LIST = "SELECT * FROM "+TABLE_EMPLOYEEVISITOR;

    public static final String INSERT_EMPLOYEEVISITOR = "INSERT INTO "+TABLE_EMPLOYEEVISITOR+'('+
            COLUMN_EMPLOYEEVISITOR_ID+", "+COLUMN_EMPLOYEEVISITOR_DATE+", "+COLUMN_EMPLOYEEVISITOR_TIME+", "+
            COLUMN_EMPLOYEEVISITOR_NAME+", "+ COLUMN_EMPLOYEEVISITOR_NUMBER+", "+COLUMN_EMPLOYEEVISITOR_DEPARTMENT+
            " ) VALUES (?,date('now'),time('now','localtime'),?,?,?)";

    private Connection conn;

    private PreparedStatement queryVisitorDisplay;
    private PreparedStatement insertVisitor;
    private PreparedStatement insertEmployee;
    private PreparedStatement getEmployeeId;
    private PreparedStatement insertEmployeeVisitor;
    private PreparedStatement queryEmployeeVisitor;
    private PreparedStatement queryEmployeeVisitorList;

    public boolean open (){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryVisitorDisplay = conn.prepareStatement(QUERY_VISITOR_DISPLAY);
            insertVisitor = conn.prepareStatement(INSERT_VISITOR, Statement.RETURN_GENERATED_KEYS);
            insertEmployee = conn.prepareStatement(INSERT_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            getEmployeeId = conn.prepareStatement(GET_EMPLOYEE_ID);
            insertEmployeeVisitor = conn.prepareStatement(INSERT_EMPLOYEEVISITOR,Statement.RETURN_GENERATED_KEYS);
            queryEmployeeVisitor = conn.prepareStatement(QUERY_EMPLOYEEVISITOR_DISPLAY,Statement.RETURN_GENERATED_KEYS);
            queryEmployeeVisitorList = conn.prepareStatement(QUERY_EMPLOYEEVISITOR_LIST);
            return true;

        }catch (SQLException e){
            System.out.println("Couldn't connect to database "+e.getMessage());
            return false;
        }
    }

    public void close(){
        try {
            if (queryVisitorDisplay!= null){
                queryVisitorDisplay.close();
            }
            if (insertVisitor!=null){
                insertVisitor.close();
            }
            if(insertEmployee!=null){
                insertEmployee.close();
            }
            if (getEmployeeId!=null){
                getEmployeeId.close();
            }
            if (insertEmployeeVisitor!=null){
                insertEmployeeVisitor.close();
            }
            if (queryEmployeeVisitor!=null){
                queryEmployeeVisitor.close();
            }
            if (queryEmployeeVisitorList!=null){
                queryEmployeeVisitorList.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            System.out.println("Couldn't close connection "+e.getMessage());
        }
    }

    public void insertVisitor (String name, String number, String purpose ) throws SQLException{

        insertVisitor.setString(1,name);
        insertVisitor.setString(2,number);
        insertVisitor.setString(3,purpose);
        int results = insertVisitor.executeUpdate();

        if(results!=1){
            throw new SQLException("Couldn't insert visitor !");
        }

    }

    public  void insertEmployee(String name, String number, String department,String post) throws SQLException{
        insertEmployee.setString(1,name);
        insertEmployee.setString(2,number);
        insertEmployee.setString(3,department);
        insertEmployee.setString(4,post);
        int results = insertEmployee.executeUpdate();

        if (results!=1){
            throw  new SQLException("Couldn't insert employee !");
        }
    }

    public void insertEmployeeVisitor(String id) throws SQLException{
        queryEmployeeVisitor.setString(1, id);

        ResultSet result = queryEmployeeVisitor.executeQuery();

        insertEmployeeVisitor.setString(1,result.getString("id"));
        insertEmployeeVisitor.setString(2,result.getString("name"));
        insertEmployeeVisitor.setString(3,result.getString("number"));
        insertEmployeeVisitor.setString(4,result.getString("department"));
        int ans = insertEmployeeVisitor.executeUpdate();

        if(ans!=1){
            throw  new SQLException("Couldn't insert employee!");
        }

    }

    public String getEmployeeId() throws SQLException {
        ResultSet results = getEmployeeId.executeQuery();

        return results.getString("id");
    }

    public ObservableList<Employee> queryEmployee(){

        try (Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(QUERY_EMPLOYEE_DISPLAY)){

            ObservableList<Employee> employees = FXCollections.observableArrayList();

            while (result.next()){
                employees.add(new Employee(result.getInt("id"), result.getString("name"),
                         result.getString("number"),result.getString("department"),
                        result.getString("post")));
            }
            return employees;

        }catch (SQLException e){
            System.out.println("Query Failed: "+e.getMessage());
            return null;
        }
    }

    public ObservableList<Employee> queryEmployeeVisitorList(){
        try (Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(QUERY_EMPLOYEEVISITOR_LIST)){
            ObservableList<Employee> employees = FXCollections.observableArrayList();

            while (result.next()){
                employees.add(new Employee(result.getInt("id"),result.getString("date"),
                        result.getString("time"),result.getString("name"),
                        result.getString("number"),result.getString("department")));
            }
            return employees;

        }catch(SQLException e){
            System.out.println("Query Failed : "+e.getMessage());
            return null;
        }
    }


    public ObservableList<Visitor> queryVisitor(){

        try(Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(QUERY_VISITOR_DISPLAY)){
            ObservableList<Visitor> visitors =  FXCollections.observableArrayList();

            while (result.next()){
                visitors.add(new Visitor(result.getString("name"),result.getString("number"),
                        result.getString("purpose"), result.getString("date"), result.getString("intime")));
            }
            return visitors;

        }catch(SQLException e){
            System.out.println("Query Failed : "+e.getMessage());
            return null;
        }
    }

}
