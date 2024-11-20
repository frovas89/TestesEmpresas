package br.com.frovas.dto;

import java.util.List;

public class FinalProducerIntervalDTO {

	private List<ProducerIntervalDTO> min;
	private List<ProducerIntervalDTO> max;

	public String fullPrint() {
		String result = "";
		result += "min: \n";
		for (ProducerIntervalDTO dto : min) {
			result += "[ " + dto.getProducer() + "," + dto.getInterval() + "," + dto.getPreviousWin() + "," + dto.getFollowingWin() + "]\n";
		}
		result += "max: \n";
		for (ProducerIntervalDTO dto : max) {
			result += "[ " + dto.getProducer() + "," + dto.getInterval() + "," + dto.getPreviousWin() + "," + dto.getFollowingWin() + "]\n";
		}
		return result;
	}

	/*
	 * Getters and Setters
	 */
	public List<ProducerIntervalDTO> getMin() {
		return min;
	}
	public void setMin(List<ProducerIntervalDTO> min) {
		this.min = min;
	}
	public List<ProducerIntervalDTO> getMax() {
		return max;
	}
	public void setMax(List<ProducerIntervalDTO> max) {
		this.max = max;
	}


}
