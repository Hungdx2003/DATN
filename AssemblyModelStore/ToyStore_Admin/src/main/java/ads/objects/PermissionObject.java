package ads.objects;
import lombok.Data;

@Data
public class PermissionObject {
	private int permission_id; 
	private int role_id; 
	private String object_name; 
	private String object_display_name; 
	private boolean can_view;
	private boolean can_add;
	private boolean can_edit;
	private boolean can_delete;
}
