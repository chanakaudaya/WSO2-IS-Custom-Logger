# WSO2-IS-Custom-Logger
This project contains implementation of a custom log publisher which prints session information to AUDIT log file

# Instructions to use
- Clone the repository
- Build the source code with maven (mvn clean install)
- Copy the jar file created in /target directory to IS_HOME/repository/components/dropins directory
- Enable the event publisher in IS_HOME/repository/conf/identity/identity.xml file

        <EventListener type="org.wso2.carbon.identity.core.handler.AbstractIdentityMessageHandler"
                       name="datapublisher.impl.CustomAuditLogger"
                       orderId="11" enable="true"/>
                       
                       
- Restart the WSO2 Identity Server
- Now enable SSO for an application as mentioned in below documentation
(WSO2 IS documentation)[https://docs.wso2.com/display/IS541/Configuring+Single+Sign-On]
- When you log into the travelocity.com application as mentioned in the above documentation, you will see a log entry 
within the IS_HOME/repository/logs/audit.log file similar to below text.

[2018-09-25 16:39:57,646]  INFO {AUDIT_LOG}-  Logging from custom logger - SessionID created :1031ee5a85ec54112991a2201619eaa6e449a1d03408cdb82739eb9a6ef0e744 for user : admin@wso2.com 

[2018-09-25 16:40:06,268]  INFO {AUDIT_LOG}-  Logging from custom logger - SessionID terminated :1031ee5a85ec54112991a2201619eaa6e449a1d03408cdb82739eb9a6ef0e744 for user : admin@wso2.com 