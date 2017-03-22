package bg.softuni.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.entity.PostModel;
@Local
public interface PostServiceLocal {
	List<PostModel> findAllPosts();

	PostModel save(PostModel entity);

	PostModel update(PostModel entity);

	void delete(PostModel entity);

	PostModel findById(Long id);

	List<PostModel> getAllPostsforDisplay();
}
