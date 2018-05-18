package com.xlauncher.pubish;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;


public class Client {

    public static void main(String[] args) {
        String wsdlUrl = "http://127.0.0.1:12345/weather?wsdl";
        String method = "queryWeather";
        Object[] param = new Object[]{"hangzhou"};
        String namespaceUrl = "http://impl.service.xlauncher.com/";
        Class[] opReturnType = new Class[] {String[].class};
        String result = axis2RPCInvoke(wsdlUrl, method, param, namespaceUrl, opReturnType);
        System.out.println(result);
    }
    public static String axis2RPCInvoke(String wsdpUrl, String methodName,
                                        Object[] parameter, String namespaceURI,
                                        Class[] returnType){

        Object[] ret = null;
        RPCServiceClient serviceClient = null;
        try {
            serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            EndpointReference targetEPR = new EndpointReference(wsdpUrl);
            options.setTo(targetEPR);
           // options.setAction(methodName);
            QName opQName = new QName(namespaceURI, methodName);

            ret = serviceClient.invokeBlocking(opQName, parameter, returnType);
        }catch (AxisFault e ){
            e.printStackTrace();
        }

        return ((String[])ret[0])[0];
    }
}
