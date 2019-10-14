/**
 * Code adapted from here: https://www.cocobolo.top/linux/2019/07/12/Flink%E8%87%AA%E5%AE%9A%E4%B9%89Sink(MySQL).html
 */
package com.twicky.analytics.utilities.sinks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import com.twicky.analytics.utilities.UsersToProfilePics;
import com.twicky.dto.TweetDTO;

public class SimilarAccountsMySQLSink extends RichSinkFunction<Tuple2<String,ArrayList<Tuple2<String,Integer>>>>{
    private PreparedStatement state ;
    private Connection conn ;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        conn = getConnection();
        //System.out.println("conn=" + conn);
        String sql = "INSERT INTO SimilarAccountsResults(userAndOtherUserId, user, otherUser, numberOfSimilarAccounts, profilePic) values(?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE numberOfSimilarAccounts=?;";
        state = this.conn.prepareStatement(sql);
        //System.out.println("state=" + state);
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (conn != null) {
            conn.close();
        }
        if (state != null) {
            state.close();
        }
    }

    @Override
    public void invoke(Tuple2<String,ArrayList<Tuple2<String,Integer>>> results, Context context) throws Exception {
    	String user = results.f0;
    	ArrayList<Tuple2<String, Integer>> allOtherUsers = new ArrayList<Tuple2<String, Integer>>();
    	allOtherUsers = results.f1;
    	for (Tuple2<String, Integer> otherUserTuple : allOtherUsers) {
    		String otherUser = otherUserTuple.f0;
    		String userAndOtherUserId = user + "." + otherUser;
    		int numberOfSimilarAccounts = otherUserTuple.f1;
	        state.setString(1,userAndOtherUserId);
	        state.setString(2,user);
	        state.setString(3, otherUser);
	        state.setInt(4, numberOfSimilarAccounts);
	        state.setString(5, UsersToProfilePics.getProfilePicUrl(user));
	        state.setInt(6, numberOfSimilarAccounts);
	        state.executeUpdate();
	        
	        // We need to update the number of similar accounts for the other user as well.
	        String oppositeUserAndOtherUserId = otherUser + "." + user;
	        state.setString(1,  oppositeUserAndOtherUserId);
	        state.setString(2, otherUser);
	        state.setString(3, user);
	        state.setInt(4, numberOfSimilarAccounts);
	        state.setString(5, UsersToProfilePics.getProfilePicUrl(otherUser));
	        state.setInt(6, numberOfSimilarAccounts);
	        state.executeUpdate();
    	}
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            String jdbc = "com.mysql.cj.jdbc.Driver";
            Class.forName(jdbc);
            String url = "jdbc:mysql://localhost:3308/twicky";
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection(url +
                "?useUnicode=true" +
                "&useSSL=false" +
                "&characterEncoding=UTF-8" +
                "&serverTimezone=UTC",
                user,
                password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}