package client.objects;
import lombok.Data;

@Data
public class NewsObject {
	private int news_id;
	private String news_title;
	private String news_content;
	private String description;
	private String news_author;
	private String news_cover_image;
	private String news_create_date;
	private int news_created_by;
}
