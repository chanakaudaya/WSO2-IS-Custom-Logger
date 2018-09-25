package datapublisher.internal;

import org.wso2.carbon.event.stream.core.EventStreamService;
import org.wso2.carbon.identity.application.authentication.framework.AuthenticationDataPublisher;
import org.wso2.carbon.registry.api.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;

import java.util.ArrayList;
import java.util.List;

public class CustomAuditLoggerDataHolder {
    private EventStreamService publisherService;
    private RealmService realmService;
    private RegistryService registryService;
    private List<AuthenticationDataPublisher> dataPublishers = new ArrayList<>();

    private static CustomAuditLoggerDataHolder
            serviceHolder = new CustomAuditLoggerDataHolder();

    private CustomAuditLoggerDataHolder() {

    }

    public static CustomAuditLoggerDataHolder getInstance() {

        return serviceHolder;
    }

    public EventStreamService getPublisherService() {

        return publisherService;
    }

    public void setPublisherService(EventStreamService publisherService) {

        this.publisherService = publisherService;
    }

    public void setRealmService(RealmService realmService) {

        this.realmService = realmService;
    }

    public void setRegistryService(RegistryService registryService) {

        this.registryService = registryService;
    }

    public RealmService getRealmService() {

        return realmService;
    }

    public RegistryService getRegistryService() {

        return registryService;
    }

    public List<AuthenticationDataPublisher> getDataPublishers() {

        return dataPublishers;
    }
}
