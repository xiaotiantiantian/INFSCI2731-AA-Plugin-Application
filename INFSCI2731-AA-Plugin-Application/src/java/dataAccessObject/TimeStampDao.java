/*
 * This class contains methods to manipulate timestamp
 */
package dataAccessObject;

import java.sql.Connection;
import DbConnect.DbConnection;
import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
import model.TimeStamp;
import java.text.SimpleDateFormat;


/**
 *
 * @author Siwei Jiao
 */
public class TimeStampDao {
    private Connection connection;
        Statement st = null;
        ResultSet rs = null;
               
        String sql = "";
        long autoKey;

        
        public TimeStampDao() {
            //connect to database and select the record
            connection = DbConnection.getConnection();	
        }
        
        /**
         * create a new timestamp by using current time
         * @return the auto generated key of the newly inserted db record
         */
        public long setUpTimeStamp() {
            Date dateTime = new Date();            
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");            
//            String createTime = sdf.format(dateTime);
            
            try {
                sql = "INSERT INTO INFSCI2731.timestamps (create_time) values (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);  
                // Parameters start with 1
                preparedStatement.setTimestamp(1, new Timestamp(dateTime.getTime()));               
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if(rs.next())
                    return autoKey = rs.getLong(1);
                else
                    return 0;
                
		} catch (SQLException e) {
			e.printStackTrace();
                    return -1;

		}
           
        }
        
}
