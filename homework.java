import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class homework  {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/apihomework?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "amdxpgood";
    public static void main(String[] args) {

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                Vertx vertx = Vertx.vertx();
                HttpServer httpServer = vertx.createHttpServer();

                Router router = Router.router(vertx);

                router.route(HttpMethod.POST, "/api/:username").handler(routingContext -> {
                   routingContext.response().putHeader("Content-Type", "application/json;charset=UTF-8");
                    String username = routingContext.request().getParam("username");

                    String newUsername = "";
                    String key = "";
                    int postfix = 0;
                    Connection conn = null;
                    Statement stmt = null;
                   try {
                       conn = DriverManager.getConnection(DB_URL, USER, PASS);
                       stmt = conn.createStatement();
                       String sql;
                       sql = "SELECT * FROM apihomework.member";
                       ResultSet rs = stmt.executeQuery(sql);
                       Map<String, Object> members = new HashMap<String, Object>();
                       while(rs.next()){
                           key  = rs.getString("userId");
                           String name = rs.getString("username");
                           members.put(name,""); //放進map中，讓之後可以判斷有無這個使用者
                       }
                       while (members.containsKey(username)){
                           postfix=postfix +1;
                           username =  username + postfix;
                       }
                       newUsername = username;

                       sql = "insert into member (username) values ('" + newUsername +"')";
                       Boolean rs2 = stmt.execute(sql);
                       if (rs2){
                           System.out.println("sucess");
                       }
                   } catch (Exception e){
                       System.out.println("exception" + e);
                    }finally{

                    try{
                        if(stmt!=null) stmt.close();
                    }catch(SQLException se2){
                    }
                    try{
                        if(conn!=null) conn.close();
                    }catch(SQLException se){
                        se.printStackTrace();
                    }
                }

                    JsonObject json = new JsonObject().put("userId", key).put("username", newUsername);
                    routingContext.response().end(json.toString());
                });


                httpServer
                        .requestHandler(router::accept)
                        .listen(8091);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}