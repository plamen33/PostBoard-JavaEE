package bg.softuni.web.beans;

import java.util.Iterator;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.entity.PostModel;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;

@ManagedBean(name = "editPostBean")
@ViewScoped
public class EditPostBean {

	@Inject
	HttpServletRequest request;
	
	@EJB
	PostServiceLocal postService;

	private PostModel post;

	//private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
	//		+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	//private Pattern pattern;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			post = postService.findById(Long.valueOf(id));
		}
	}

	public String updateAction() {

		if (!validate()) {
			return null;
		}
		
		postService.update(post);
		
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
