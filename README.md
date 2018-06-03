# jms-2.0-poc
This application an poc for <b><i><u>JMS-2.0</u></i></b> which uses annotations to define and use JMS modules.

This example demonstrates use of the following key JMS annotations:

<ul>
  <li><i><b>@JMSDestinationDefinitions</b></i></li>
  <li><i><b>@JMSDestinationDefinition</b></i></li>
  <li><i><b>@JMSConnectionFactoryDefinition</b></i></li>
  <li><i><b>@JMSConnectionFactory</b></i></li>
</ul>

The application is packaged as a simple <i>war</i> and was tested on <b>Welogic 12.2.1</b>.

This application can also be treated as an example for using <b>CDI</b> in a war (beans.xml in WEB-INF)

### The loadBalancer
This app is used to <i>load balance</i> the inbound traffic for weblogic server to multiple weblogic managed servers in a cluster in Round Robin fashion.
