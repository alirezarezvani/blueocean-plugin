package io.jenkins.blueocean.security;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstraction to represent login details of an authentication provider.
 * Must be Jackson serializable
 *
 * @author Vivek Pandey
 */

/**
 * This enables Json polymerphism when mapped to Java
 * At run time, while serializing the listed class inside @JsonSubTypes will have field authProvider
 * field added.
 *
 * While deserializing in to Java, Jackson will look at authProvider field and if it's "github"
 * then will deserialize json in to GithubLoginDetails.
 *
 * Implementers of LoginDetails must ensure not to add their own authProvider field.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "authProvider")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GithubLoginDetails.class, name = "github")
        })
public interface LoginDetails {}
