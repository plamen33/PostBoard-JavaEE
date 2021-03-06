package bg.softuni.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import bg.softuni.entity.PostModel;
import bg.softuni.entity.UserModel;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class PostService
 */
@Stateless
public class PostService implements PostServiceLocal {

	
	@PersistenceContext
    protected EntityManager entityManager;
    
	
	@SuppressWarnings("unchecked")
	@Override
    public List<PostModel> findAllPosts() {
        String query = "select model from PostModel model order by upper(model.id) asc";
        Query q = entityManager.createQuery(query);

        return q.getResultList();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PostModel> getAllPostsforDisplay() {
		String query = "select new PostModel(postmodel.id, postmodel.author, postmodel.title, postmodel.content, postmodel.date) from PostModel postmodel group by postmodel.id order by upper(postmodel.date) desc";
		Query q = entityManager.createQuery(query);

		return q.getResultList();
	}

    @Override
    public PostModel save(PostModel entity) {
    	
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public PostModel update(PostModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(PostModel entity) {
        //entityManager.remove(entity);
        
        PostModel toRemove = entityManager.merge(entityManager.find(PostModel.class, entity.getId()));
        entityManager.remove(toRemove);
    }

    @Override
    public PostModel findById(Long id) {
        try {
            PostModel instance = entityManager.find(PostModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
	
	
	
    /**
     * Default constructor. 
     */
    public PostService() {
        // TODO Auto-generated constructor stub
    }

}
