package datapublisher.internal;

import java.util.Collections;

import datapublisher.impl.CustomAuditLogger;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.identity.application.authentication.framework.AuthenticationDataPublisher;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkConstants;
import org.wso2.carbon.identity.core.handler.MessageHandlerComparator;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * @scr.component name="org.wso2.carbon.identity.data.publisher.authn" immediate="true"
 * @scr.reference name="registry.service"
 * interface="org.wso2.carbon.registry.core.service.RegistryService"
 * cardinality="1..1" policy="dynamic" bind="setRegistryService"
 * unbind="unsetRegistryService"
 * @scr.reference name="realm.service" interface="org.wso2.carbon.user.core.service.RealmService"
 * cardinality="1..1" policy="dynamic" bind="setRealmService"
 * unbind="unsetRealmService"
 * @scr.reference name="identity.authentication.data.publisher"
 * interface="org.wso2.carbon.identity.application.authentication.framework.AuthenticationDataPublisher"
 * cardinality="0..n" policy="dynamic" bind="setAuthenticationDataPublisher"
 * unbind="unsetAuthenticationDataPublisher"
 */
public class CustomAuditLoggerServiceComponent {
    protected void activate(ComponentContext context) {

        BundleContext bundleContext = context.getBundleContext();
        bundleContext
                .registerService(AuthenticationDataPublisher.class.getName(), new CustomAuditLogger(), null);
    }

    protected void setRealmService(RealmService realmService) {

        CustomAuditLoggerDataHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        CustomAuditLoggerDataHolder.getInstance().setRealmService(null);
    }

    protected void setRegistryService(RegistryService registryService) {

        CustomAuditLoggerDataHolder.getInstance().setRegistryService(registryService);
    }

    protected void unsetRegistryService(RegistryService registryService) {

        CustomAuditLoggerDataHolder.getInstance().setRegistryService(null);
    }

    protected void setAuthenticationDataPublisher(AuthenticationDataPublisher publisher) {
        if (publisher != null && !FrameworkConstants.AnalyticsAttributes.AUTHN_DATA_PUBLISHER_PROXY.equalsIgnoreCase
                (publisher.getName())) {
            CustomAuditLoggerDataHolder.getInstance().getDataPublishers().add(publisher);
            Collections.sort(CustomAuditLoggerDataHolder.getInstance().getDataPublishers(),
                    new MessageHandlerComparator(null));
            Collections.reverse(CustomAuditLoggerDataHolder.getInstance().getDataPublishers());
        }
    }

    protected void unsetAuthenticationDataPublisher(AuthenticationDataPublisher publisher) {
        CustomAuditLoggerDataHolder.getInstance().getDataPublishers().remove(publisher);
    }

}
