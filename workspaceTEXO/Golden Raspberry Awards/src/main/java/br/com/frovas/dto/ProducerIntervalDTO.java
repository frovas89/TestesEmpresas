package br.com.frovas.dto;

public class ProducerIntervalDTO {

	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;


	/*
	 * Getters and Setters
	 */

	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public Integer getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(Integer previousWin) {
		this.previousWin = previousWin;
	}
	public Integer getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(Integer followingWin) {
		this.followingWin = followingWin;
	}
}
