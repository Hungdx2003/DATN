package ads.role;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.RoleObject;

public interface role {
	boolean addRole(RoleObject item);
	boolean editRole(RoleObject item);
	boolean delRole(RoleObject item);
	
	ArrayList<ResultSet> getRoles(RoleObject similar);
	ArrayList<ResultSet> getRoleDetail(int id);
	ResultSet getRole(int id);
	ResultSet getRole(String name);
}
