package com.rest.pundraherbs.model;

public class ReviewInfo {
	private Long reviewId;
	private String reviewedBy;
	private String reviewComment;

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	@Override
	public String toString() {
		return "ReviewInfo [reviewId=" + reviewId + ", reviewedBy=" + reviewedBy + ", reviewComment=" + reviewComment + "]";
	}

}