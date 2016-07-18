import React, { Component, PropTypes } from 'react';
import Extensions, { dataType } from '@jenkins-cd/js-extensions';
import { Link } from 'react-router';
import {
    Page,
    PageHeader,
    Title,
    PageTabs,
    TabLink,
    WeatherIcon,
    Favorite,
} from '@jenkins-cd/design-language';
import { buildOrganizationUrl, buildPipelineUrl } from '../util/UrlUtils';

const { object } = PropTypes;

export default class PipelinePage extends Component {
    render() {
        const { pipeline } = this.context;
        const { organization, name, fullName } = pipeline || {};
        const orgUrl = buildOrganizationUrl(organization);
        const activityUrl = buildPipelineUrl(organization, fullName, 'activity');

        if (!pipeline) {
            return null; // Loading...
        }

        const baseUrl = buildPipelineUrl(organization, fullName);

        return (
            <Page>
                <PageHeader>
                    <Title>
                        <div style={{display:'none'}}>{JSON.stringify(pipeline)}</div>
                        <WeatherIcon score={pipeline.weatherScore} size="large" />
                        <h1>
                            <Link to={orgUrl}>{organization}</Link>
                            <span> / </span>
                            <Link to={activityUrl}>{name}</Link>
                        </h1>
                        <Favorite className="dark-yellow" />
                    </Title>
                    <PageTabs base={baseUrl}>
                        <Extensions.Renderer extensionPoint="pipeline.main.navigation" filter={dataType(pipeline._jobClass)} pipeline={pipeline} baseLink={baseUrl} />
                    </PageTabs>
                </PageHeader>
                {React.cloneElement(this.props.children, { pipeline })}
            </Page>
        );
    }
}

PipelinePage.propTypes = {
    children: object,
};

PipelinePage.contextTypes = {
    location: object,
    pipeline: object,
};
