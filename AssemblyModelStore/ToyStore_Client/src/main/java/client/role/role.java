package client.role;

import java.sql.ResultSet;
import java.util.ArrayList;

import client.objects.RoleObject;

public interface role {
	boolean addRole(RoleObject item);
	boolean editRole(RoleObject item);
	boolean delRole(RoleObject item);
	
	ArrayList<ResultSet> getRole(RoleObject similar, int at, byte total);
	ResultSet getRole(int id);
}
