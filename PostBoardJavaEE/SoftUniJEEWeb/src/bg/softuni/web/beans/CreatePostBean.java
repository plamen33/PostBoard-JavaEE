package bg.softuni.web.beans;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import bg.softuni.web.utils.GeneralUtils;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.UserModel;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "createPostBean")
@ViewScoped
public class CreatePostBean {


	@Inject
	HttpServletRequest request;
	
	@EJB
	UserServiceLocal userService;
	
	@EJB
	PostServiceLocal postService;

	private PostModel post;
	



	@PostConstruct
	public void init() {
		post = new PostModel();
		
	}

	public String createAction() {

		if (!validate()) {
			return null;
		}	
		
		UserModel user = (UserModel) GeneralUtils.getLoggedUser(request);
		post.setDate(new Date());
		post.setAuthor(user);
		postService.save(post);
		
		return "/page/listPosts?faces-redirect=true";
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}


	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(post.getTitle())) {
			MessageUtils.addErrorMessage("title", "error.required.title");
			hasErrors = true;
		}

		if (StringUtils.isBlank(post.getContent())) {
			MessageUtils.addErrorMessage("error.required.content");
			hasErrors = true;
		}



		if (hasErrors) {
			return false;
		}

		return true;
	}

	/**
	 * Verifies if a error messages are present in the context
	 */
	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		for (; messages.hasNext();) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}

		return false;
	}
	
	
}
