package client.objects;
import lombok.Data;

@Data
public class ImageObject {
	private int image_id;
	private String image_url;
	private String type;
	private String upload_date;
	private boolean active;
}
