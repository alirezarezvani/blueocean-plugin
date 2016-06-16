package io.jenkins.blueocean.rest.model;

import org.kohsuke.stapler.WebMethod;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;
import org.kohsuke.stapler.json.JsonResponse;
import org.kohsuke.stapler.verb.DELETE;

/**
 * API endpoint for an organization that houses all the pipelines.
 *
 * @author Kohsuke Kawaguchi
 */
public abstract class BlueOrganization extends Resource {
    public static final String NAME="name";
    public static final String PIPELINES="pipelines";

    @Exported(name = NAME)
    public abstract String getName();

    public abstract BluePipelineContainer getPipelines();

    /**
     * A set of users who belong to this organization.
     *
     * @return {@link BlueUserContainer}
     */
    public abstract BlueUserContainer getUsers();

    @DELETE @WebMethod(name = "delete")
    public abstract void deleteOrganiztion();
}

