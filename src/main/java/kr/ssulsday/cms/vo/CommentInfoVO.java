package kr.ssulsday.cms.vo;

public class CommentInfoVO {
	
	private int comment_id;
	private String user_id;
	private String username;
	private int post_id;
	private String comment_write_time;
	private String comment_content;
	
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getComment_write_time() {
		return comment_write_time;
	}
	public void setComment_write_time(String comment_write_time) {
		this.comment_write_time = comment_write_time;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	@Override
	public String toString() {
		return "CommentInfoVO [comment_id=" + comment_id + ", user_id=" + user_id + ", post_id=" + post_id
				+ ", comment_write_time=" + comment_write_time + ", comment_content=" + comment_content + "]";
	}
}