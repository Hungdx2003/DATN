package client.objects;

import lombok.Data;

@Data
public class UserObject {
	private int user_id;
	private String user_name;
	private String user_pass;
	private String user_fullname;
	private String user_gender;
	private String user_birthday;
	private String user_mobilephone;
	private String user_email;
	private String user_address;
	private int user_roles;
	private String user_created_date;
	private String user_last_modified;
	private boolean user_deleted;
}
