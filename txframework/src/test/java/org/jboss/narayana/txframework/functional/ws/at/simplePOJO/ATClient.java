/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.narayana.txframework.functional.ws.at.simplePOJO;

import com.arjuna.mw.wst11.client.JaxWSHeaderContextProcessor;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ATClient {

    public static AT newInstance() throws Exception {

        URL wsdlLocation = new URL("http://localhost:8080/echo_service/ATService?wsdl");
        QName serviceName = new QName("http://www.jboss.com/functional/at/", "ATService");
        QName portName = new QName("http://www.jboss.com/functional/at/", "AT");

        Service service = Service.create(wsdlLocation, serviceName);
        AT client = service.getPort(portName, AT.class);

        /*
           Add client handler chain
        */
        BindingProvider bindingProvider = (BindingProvider) client;
        List<Handler> handlers = new ArrayList<Handler>(1);
        handlers.add(new JaxWSHeaderContextProcessor());
        bindingProvider.getBinding().setHandlerChain(handlers);

        return client;
    }
}
