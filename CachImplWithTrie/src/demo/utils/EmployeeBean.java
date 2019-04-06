package demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

public class EmployeeBean {
    static PrefixTrie expensePrefixTrieCache = new PrefixTrie();
    static PrefixTrie expensePrefixTrieCache1;
    public EmployeeBean() {
    }
    public static void main(String ar[]) {
    	Connection con;
        try {  
                Class.forName("oracle.jdbc.driver.OracleDriver");  
                con=DriverManager.getConnection("jdbc:oracle:thin:@host:port:sid","username","password");  
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("SELECT DISTINCT UPPER(LOCATION) FROM EXPENSE_LOCATION_COUNTRY WHERE geography_element1 = 'United States'");
               
                int count = 0;
                
                while(rs.next() && count < 1000)  
                {
                        String word =rs.getString(1);
                        count++;
                        System.out.println(word+" Inserted, count "+ count);
                        if (word != null)
                        	expensePrefixTrieCache.insert(word);
                }
        }
        catch(Exception e){ 
                System.out.println("Exception "+e);
        }
        EmployeeBean e= new EmployeeBean();
        Collection<String> l = e.suggestItemsFromCache();
        System.out.println(l);
    }
    
    public Collection<String> suggestItemsFromCache() {
        String key = "TrieCache";
        System.out.println("call JS LOCATION");
        JcsLocation jc = new JcsLocation();
        System.out.println("PUT IN CACHE");
        jc.putInCache(expensePrefixTrieCache);
        System.out.println("RETRIEVE LOCATION");
        expensePrefixTrieCache1 = jc.retrieveFromCache(key);
        System.out.println("FND LOCATION");
        return expensePrefixTrieCache1.autoComplete("C");
        //return null;
       
    }
}