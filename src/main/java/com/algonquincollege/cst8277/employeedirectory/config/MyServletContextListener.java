/***************************************************************************f******************u************zz*******y**
 * File: MyServletContextListener.java
 * Course materials (20W) CST 8277
 * @author (original) Mike Norman
 *
 */
package com.algonquincollege.cst8277.employeedirectory.config;

import static javax.faces.application.ProjectStage.Development;
import static javax.faces.application.ProjectStage.PROJECT_STAGE_PARAM_NAME;
import static javax.faces.application.ViewHandler.FACELETS_REFRESH_PERIOD_PARAM_NAME;
import static javax.faces.validator.BeanValidator.ENABLE_VALIDATE_WHOLE_BEAN_PARAM_NAME;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebListener;

/**
 * Programatically replace <code>web.xml</code> setup with this {@link ServletContextListener} <br/>
 * <br/>
 * Note: some <code>web.xml</code> elements cannot be programatically replaced, for example <code>&lt;welcome-file-list&gt;</code>
 *
 * <pre>
 * {@code
   <web-app
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    version="4.0">

    <context-param>
      <param-name>javax.faces.PROJECT_STAGE</param-name>
      <param-value>Development</param-value>
    </context-param>
    <context-param>
      <param-name>javax.faces.FACELETS_REFRESH_PERIOD</param-name>
      <param-value>0</param-value>
    </context-param>
    <context-param>
      <param-name>javax.faces.validator.ENABLE_VALIDATE_WHOLE_BEAN</param-name>
      <param-value>true</param-value>
    </context-param>

    <servlet>
      <servlet-name>Faces Servlet</servlet-name>
      <servlet-class>javax.faces.webapp.FacesServlet</servlet-class>
      <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
      <servlet-name>Faces Servlet</servlet-name>
      <url-pattern>*.xhtml</url-pattern>
    </servlet-mapping>
 </web-app>
   }
   </pre>
 *
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    public static final String DEVELOPMENT_STAGE = Development.name();

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContextListener.super.contextInitialized(sce);
        ServletContext sc = sce.getServletContext();

        //Registering the JSF Servlet
        Dynamic facesServlet = sc.addServlet("Faces Servlet", FacesServlet.class.getName());
        //Specifying the Servlet Mapping
        facesServlet.addMapping("*.xhtml");
        //Setting Priority, 0 or higher for eager, if negative then it's lazy
        facesServlet.setLoadOnStartup(1);

        sc.setInitParameter(FACELETS_REFRESH_PERIOD_PARAM_NAME, "0");
        sc.setInitParameter(PROJECT_STAGE_PARAM_NAME, DEVELOPMENT_STAGE);
        sc.setInitParameter(ENABLE_VALIDATE_WHOLE_BEAN_PARAM_NAME, "true");
    }
}