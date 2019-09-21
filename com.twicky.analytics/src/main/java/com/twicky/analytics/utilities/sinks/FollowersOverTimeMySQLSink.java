/**
 * Code adapted from here: https://www.cocobolo.top/linux/2019/07/12/Flink%E8%87%AA%E5%AE%9A%E4%B9%89Sink(MySQL).html
 */
package com.twicky.analytics.utilities.sinks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import com.twicky.dto.TweetDTO;

public class FollowersOverTimeMySQLSink extends RichSinkFunction<Tuple3<String, Integer, Date>>{
    private PreparedStatement state ;
    private Connection conn ;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        conn = getConnection();
        //System.out.println("conn=" + conn);
        String sql = "insert into FollowersOverTimeResults(userScreenName,followers,timestamp) values(?, ?, ?);";
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
    public void invoke(Tuple3<String, Integer, Date> results, Context context) throws Exception {
    	System.out.println(results.f0 + " " + results.f1 + " " + results.f2);
        state.setString(1,results.f0);
        state.setInt(2,results.f1);
        state.setTimestamp(3,new java.sql.Timestamp(results.f2.getTime()));
        state.executeUpdate();
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