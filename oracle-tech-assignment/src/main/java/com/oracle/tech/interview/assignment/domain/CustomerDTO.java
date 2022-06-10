package com.oracle.tech.interview.assignment.domain;

public class CustomerDTO {

	private int customertId;

	private int contractId;

	private String geoZone;

	private String teamCode;

	private String projectCode;

	private int buildDuration;

	public CustomerDTO() {
	}

	public CustomerDTO(int customertId, int contractId, String geoZone, String teamCode, String projectCode,
			int buildDuration) {
		this.customertId = customertId;
		this.contractId = contractId;
		this.geoZone = geoZone;
		this.teamCode = teamCode;
		this.projectCode = projectCode;
		this.buildDuration = buildDuration;
	}

	public int getCustomertId() {
		return customertId;
	}

	public void setCustomertId(int customertId) {
		this.customertId = customertId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getGeoZone() {
		return geoZone;
	}

	public void setGeoZone(String geoZone) {
		this.geoZone = geoZone;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public int getBuildDuration() {
		return buildDuration;
	}

	public void setBuildDuration(int buildDuration) {
		this.buildDuration = buildDuration;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customertId=" + customertId + ", contractId=" + contractId + ", geoZone=" + geoZone
				+ ", teamCode=" + teamCode + ", projectCode=" + projectCode + ", buildDuration=" + buildDuration + "]";
	}

}
