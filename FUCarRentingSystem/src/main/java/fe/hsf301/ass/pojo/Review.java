package fe.hsf301.ass.pojo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Review")
public class Review {
	@EmbeddedId
	private ReviewId id;

	@Column(nullable = false)
	private int reviewStar;

	@Column(nullable = false)
	private String comment;

	@ManyToOne
	@MapsId("customerID")
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@MapsId("carID")
	@JoinColumn(name = "carID", nullable = false)
	private Car car;

	public Review() {
		super();
	}

	public Review(ReviewId id, int reviewStar, String comment, Customer customer, Car car) {
		super();
		this.id = id;
		this.reviewStar = reviewStar;
		this.comment = comment;
		this.customer = customer;
		this.car = car;
	}

	public Review(int reviewStar, String comment, Customer customer, Car car) {
		super();
		this.reviewStar = reviewStar;
		this.comment = comment;
		this.customer = customer;
		this.car = car;
	}
	
}
