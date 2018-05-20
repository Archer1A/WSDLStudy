package com.xlauncher.pubish;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;


public class Client {

    public static void main(String[] args) {
        String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
        String method = "getWeather";
        Object[] param = new Object[]{"杭州","1"};
        String namespaceUrl = "http://www.webxml.com.cn/";
        Class[] opReturnType = new Class[] {String[].class};
        String action = "http://WebXml.com.cn/getRegionCountry";
        String result = axis2RPCInvoke(wsdlUrl, method,action, param, namespaceUrl, opReturnType);
        System.out.println(result);
    }
    public static String axis2RPCInvoke(String wsdpUrl, String methodName,String action,
                                        Object[] parameter, String namespaceURI,
                                        Class[] returnType){

        Object[] ret = null;
        RPCServiceClient serviceClient = null;
        try {
            serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            //options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            EndpointReference targetEPR = new EndpointReference(wsdpUrl);
            //options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            options.setTo(targetEPR);
            options.setAction(action);
            QName opQName = new QName(namespaceURI, methodName);

            ret = serviceClient.invokeBlocking(opQName, parameter, returnType);
        }catch (AxisFault e ){
            e.printStackTrace();
        }

        return ((String[])ret[0])[0];
    }
}
