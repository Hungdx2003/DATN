package client.user;

import java.sql.*;
import java.util.*;

import client.objects.UserObject;

public interface user {
	boolean addUser(UserObject item);
	boolean editUser(UserObject item);
	boolean changePassword(UserObject item);
	boolean delUser(UserObject item);
	boolean isUsernameExists(String username);
	boolean isEmailExists(String email);
	
	ArrayList<ResultSet> getUser(UserObject similar, int at, byte total);
	ResultSet getUser(int id);
	ResultSet getUser(String email, String userpass);
}
