package com.podio.contact;

import org.codehaus.jackson.annotate.JsonProperty;

public class Profile extends ProfileUpdate {

	/**
	 * The id of the profile
	 */
	private int profileId;

	/**
	 * The id of the user
	 */
	private Integer userId;

	@JsonProperty("profile_id")
	public int getProfileId() {
		return profileId;
	}

	@JsonProperty("profile_id")
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
