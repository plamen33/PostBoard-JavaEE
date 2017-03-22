package bg.softuni.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.softuni.entity.PostModel;
import bg.softuni.service.PostServiceLocal;

@ManagedBean(name = "listPostsBean")
@ViewScoped
public class ListPostsBean {
	@EJB
	PostServiceLocal postService;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/page/editPost";
	}

	public String createAction() {
		return "/page/createPost";
	}
	

	public List<PostModel> getAllPosts() {
		
		return postService.getAllPostsforDisplay();
	}
	
}
