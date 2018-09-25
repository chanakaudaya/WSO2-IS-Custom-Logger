package datapublisher.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.CarbonConstants;
import org.wso2.carbon.identity.application.authentication.framework.AuthenticationDataPublisher;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;
import org.wso2.carbon.identity.application.authentication.framework.context.SessionContext;
import org.wso2.carbon.identity.application.authentication.framework.model.AuthenticatedUser;
import org.wso2.carbon.identity.application.authentication.framework.util.FrameworkConstants;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.core.handler.AbstractIdentityMessageHandler;
import org.wso2.carbon.identity.core.model.IdentityEventListenerConfig;
import org.wso2.carbon.identity.core.util.IdentityUtil;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;


public class CustomAuditLogger extends AbstractIdentityMessageHandler implements
        AuthenticationDataPublisher {

    private static final Log AUDIT_LOG = CarbonConstants.AUDIT_LOG;
    public static final Log LOG = LogFactory.getLog(CustomAuditLogger.class);


    @Override
    public String getName() {
        return "AuditDataPublisher";
    }

    @Override
    public int getPriority(MessageContext messageContext) {
        return 1;
    }

    @Override
    public void publishAuthenticationStepSuccess(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, Map<String, Object> map) {

    }

    @Override
    public void publishAuthenticationStepFailure(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, Map<String, Object> map) {

    }

    @Override
    public void publishAuthenticationSuccess(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, Map<String, Object> map) {

    }

    @Override
    public void publishAuthenticationFailure(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, Map<String, Object> map) {

    }

    @Override
    public void publishSessionCreation(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, SessionContext sessionContext, Map<String, Object> map) {
        String sessionId = (String) map.get(FrameworkConstants.AnalyticsAttributes.SESSION_ID);
        String userId = ((AuthenticatedUser) map.get(FrameworkConstants.AnalyticsAttributes.USER)).getAuthenticatedSubjectIdentifier();
        AUDIT_LOG.info("Logging from custom logger - SessionID created :" + sessionId + " for user : " + userId);
    }

    @Override
    public void publishSessionUpdate(HttpServletRequest httpServletRequest, AuthenticationContext authenticationContext, SessionContext sessionContext, Map<String, Object> map) {
        String sessionId = (String) map.get(FrameworkConstants.AnalyticsAttributes.SESSION_ID);
        String userId = ((AuthenticatedUser) map.get(FrameworkConstants.AnalyticsAttributes.USER)).getAuthenticatedSubjectIdentifier();
        AUDIT_LOG.info("Logging from custom logger - SessionID updated :" + sessionId + " for user : " + userId);
    }

    @Override
    public void publishSessionTermination(HttpServletRequest request, AuthenticationContext context,
                                          SessionContext sessionContext, Map<String, Object> unmodifiableMap) {
        String sessionId = (String) unmodifiableMap.get(FrameworkConstants.AnalyticsAttributes.SESSION_ID);
        String userId = ((AuthenticatedUser) unmodifiableMap.get(FrameworkConstants.AnalyticsAttributes.USER)).getAuthenticatedSubjectIdentifier();
        AUDIT_LOG.info("Logging from custom logger - SessionID terminated :" + sessionId + " for user : " + userId);
    }

    @Override
    public boolean isEnabled(MessageContext messageContext) {
        IdentityEventListenerConfig identityEventListenerConfig = IdentityUtil.readEventListenerProperty
                (AbstractIdentityMessageHandler.class.getName(), this.getClass().getName());

        if (identityEventListenerConfig == null) {
            return true;
        }

        return Boolean.parseBoolean(identityEventListenerConfig.getEnable());
    }

    @Override
    public boolean canHandle(MessageContext messageContext) {
        return true;
    }
}
