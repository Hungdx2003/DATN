package ads.viewModel;
import lombok.Data;

@Data
public class RoleDetailViewModel {
	private int role_id; 
	private String role_name;
	private int permission_id; 
	private String object_name; 
	private String object_display_name; 
	private boolean can_view;
	private boolean can_add;
	private boolean can_edit;
	private boolean can_delete;
}
