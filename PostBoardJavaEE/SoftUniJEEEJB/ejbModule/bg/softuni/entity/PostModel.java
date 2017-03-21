package bg.softuni.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bg.softuni.entity.base.BaseDomainObject;

@Entity
@Table(name = "POSTS")
public class PostModel extends BaseDomainObject {
	private static final long serialVersionUID = 1L;
	private UserModel author;
	private String title;
	private String content;
	private Date date;
	

	@Column(name = "title", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 4000, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JoinColumn(name = "author_id", referencedColumnName = "id")
	@ManyToOne
	public UserModel getAuthor() {
		return author;
	}


	public void setAuthor(UserModel author) {
		this.author = author;
	}
	
	// Constructors:
	
	public PostModel(Long id, UserModel author, String title, String content, Date date) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.date = date;
		
	}
	public PostModel() {

	}

}
