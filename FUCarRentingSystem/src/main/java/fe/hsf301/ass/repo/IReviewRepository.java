package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.Review;
import fe.hsf301.ass.pojo.ReviewId;

public interface IReviewRepository {
	void save(Review review);
    void delete(ReviewId reviewID);
    Review findById(ReviewId reviewID);
    void update(Review review);
    List<Review> findAll();
}
