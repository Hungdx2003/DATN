package ads.user;

import java.sql.*;
import java.util.*;

import ads.objects.UserObject;

public interface user {
	boolean addUser(UserObject item);
	boolean editUser(UserObject item);
	boolean delUser(UserObject item);
	
	ArrayList<ResultSet> getUser(UserObject similar);
	ResultSet getUser(int id);
	ResultSet getUser(String username, String userpass);
}
