package ads.permission;

import java.sql.ResultSet;
import java.util.ArrayList;

import ads.objects.PermissionObject;

public interface permission {
	boolean addPermission(PermissionObject item);
	boolean editPermission(PermissionObject item);
	boolean delPermission(PermissionObject item);
	
	ArrayList<ResultSet> getPermission(PermissionObject similar, int at, byte total);
	ArrayList<ResultSet> getObjectName(PermissionObject similar);
	ResultSet getPermission(int id, String objname);
}
