package com.podio.org;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.podio.BaseAPI;
import com.podio.space.Space;
import com.sun.jersey.api.client.GenericType;

public class OrgAPI {

	private final BaseAPI baseAPI;

	public OrgAPI(BaseAPI baseAPI) {
		this.baseAPI = baseAPI;
	}

	/**
	 * Creates a new organization
	 * 
	 * @param data
	 *            The data for the new organization
	 * @return The data for the newly created organization
	 */
	public OrganizationCreateResponse createOrganization(OrganizationCreate data) {
		return baseAPI.getApiResource("/org/")
				.entity(data, MediaType.APPLICATION_JSON_TYPE)
				.post(OrganizationCreateResponse.class);
	}

	/**
	 * Updates an organization with new name and logo. Note that the URL of the
	 * organization will not change even though the name changes.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param data
	 *            The new data
	 */
	public void updateOrganization(int orgId, OrganizationCreate data) {
		baseAPI.getApiResource("/org/" + orgId)
				.entity(data, MediaType.APPLICATION_JSON_TYPE).put();
	}

	/**
	 * Gets the organization with the given id.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The organization
	 */
	public Organization getOrganization(int orgId) {
		return baseAPI.getApiResource("/org/" + orgId)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(Organization.class);
	}

	/**
	 * Deletes the organization with the given id. This will also delete all
	 * spaces under the organization (see the delete space operation for
	 * details)
	 * 
	 * @param orgId
	 *            The id of the organization
	 */
	public void deleteOrganization(int orgId) {
		baseAPI.getApiResource("/org/" + orgId)
				.accept(MediaType.APPLICATION_JSON_TYPE).delete();
	}

	/**
	 * Returns a list of all the organizations and spaces the user is member of.
	 * 
	 * @return The organizations the user is member of
	 */
	public List<OrganizationWithSpaces> getOrganizations() {
		return Arrays.asList(baseAPI.getApiResource("/org/")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(OrganizationWithSpaces[].class));
	}

	/**
	 * Returns the organization with the given full URL. The URL does not have
	 * to be truncated to the root, it can be to any resource on the URL.
	 * 
	 * @param url
	 *            The URL to find the organization for
	 * @return The organization
	 */
	public OrganizationMini getOrganizationByURL(String url) {
		return baseAPI.getApiResource("/org/url").queryParam("url", url)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(OrganizationMini.class);
	}

	/**
	 * Returns interesting statistics for this organization. Only org creator is
	 * allowed to see this.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The statistics
	 */
	public OrganizationStatistics getOrganizationStatistics(int orgId) {
		return baseAPI.getApiResource("/org/" + orgId + "/statistics")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(OrganizationStatistics.class);
	}

	/**
	 * Returns the organizations and spaces that the logged in user shares with
	 * the specified user. The organizations and spaces will be returned sorted
	 * by name.
	 * 
	 * @param userId
	 *            The id of the user
	 * @return The organizations with spaces that are shared with the user
	 */
	public List<OrganizationWithSpaces> getSharedOrganizations(int userId) {
		return baseAPI.getApiResource("/org/shared/" + userId)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<OrganizationWithSpaces>>() {
				});
	}

	/**
	 * Return the space with the given URL on the space. To get the space
	 * related to http://company.podio.com/intranet, first lookup the
	 * organization on "company" and then the space using this function using
	 * the URL "intranet".
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @param url
	 *            The url fragment for the space
	 * @return The matching space
	 */
	public Space getSpaceByURL(int orgId, String url) {
		return baseAPI.getApiResource("/org/" + orgId + "/space/url/" + url)
				.accept(MediaType.APPLICATION_JSON_TYPE).get(Space.class);
	}

	/**
	 * Returns all the spaces for the organization.
	 * 
	 * @param orgId
	 *            The id of the organization
	 * @return The spaces in the organization
	 */
	public List<Space> getSpaces(int orgId) {
		return baseAPI.getApiResource("/org/" + orgId + "/space/")
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Space>>() {
				});
	}
}
