package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.ReviewDAO;
import fe.hsf301.ass.pojo.Review;
import fe.hsf301.ass.pojo.ReviewId;

public class ReviewRepositoryImpl implements IReviewRepository {
	
	private ReviewDAO reviewDAO;

    public ReviewRepositoryImpl(String fileConfig) {
        reviewDAO = new ReviewDAO(fileConfig);
    }

	@Override
	public void save(Review review) {
		// TODO Auto-generated method stub
		reviewDAO.save(review);
	}

	@Override
	public void delete(ReviewId reviewID) {
		// TODO Auto-generated method stub
		reviewDAO.delete(reviewID);
	}

	@Override
	public Review findById(ReviewId reviewID) {
		// TODO Auto-generated method stub
		return reviewDAO.findById(reviewID);
	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		reviewDAO.update(review);
	}

	@Override
	public List<Review> findAll() {
		// TODO Auto-generated method stub
		return reviewDAO.findAll();
	}

}
