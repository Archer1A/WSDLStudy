package com.xlauncher.pubish;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TestWebService {
   // public static void main(String[] args) throws Exception{
     //   ServiceClient sender = new ServiceClient();
       // Options option = new Options();
        //option.setTransportInProtocol(Constants.TRANSPORT_HTTP);
       // option.setAction("http://www.webxml.com.cn");
        //EndpointReference epfs =  new EndpointReference("http://www.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl");
       // option.setTransportInProtocol(Constants.TRANSPORT_HTTP);
        //option.setTo(epfs);
        //OMFactory fac = OMAbstractFactory.getOMFactory();
        //OMNamespace omNs = fac.createOMNamespace("http://www.webxml.com.cn", "");
        //OMElement theCityCode = fac.createOMElement("theCityCode", omNs);
        //OMElement  data = fac.createOMElement("getWeather", omNs);
        //OMElement theUserID = fac.createOMElement("theUserId", omNs);
        //theCityCode.setText("北京");
        //theUserID.setText("");
        //data.addChild(theCityCode);
        //data.addChild(theUserID);
        //OMElement result  = sender.sendReceive(data);
        //System.out.println(result);
    //}
   public static void main(String[] args) throws Exception{
       RPCServiceClient serviceClient = new RPCServiceClient();
       Options options = new Options();
       options.setAction("http://WebXml.com.cn/TranslatorString");
       EndpointReference targetEPR = new EndpointReference("http://fy.webxml.com.cn/webservices/EnglishChinese.asmx?wsdl");
       options.setTo(targetEPR);
       options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
       serviceClient.setOptions(options);
       OMFactory fac = OMAbstractFactory.getOMFactory();
       OMNamespace omNs = fac.createOMNamespace("http://WebXml.com.cn/", "");
       OMElement method = fac.createOMElement("TranslatorString ", omNs);
       OMElement wordKey = fac.createOMElement("wordKey",omNs);
       wordKey.setText("随便");
       method.addChild(wordKey);
       method.build();
       OMElement result = serviceClient.sendReceive(method);
       System.out.println(result);
       System.exit(0);
    }

    /***
     * 解析XML，将获取到的数据封装到list中
     * @param element
     * @return
     */
    public static List<String> getResults(OMElement element) {
        if (element == null) {
            return null;
        }
        Iterator iterator = element.getChildElements();
        Iterator innerItr;
        List<String> list = new ArrayList<String>();
        OMElement result = null;
        while (iterator.hasNext()) {
            result = (OMElement) iterator.next();
            innerItr = result.getChildElements();
            while(innerItr.hasNext()){
                OMElement result2 = (OMElement)innerItr.next();
                if(result2!=null){
                    String text = result2.getText();
                    if(text!=null && !("").equals(text)){
                        list.add(text);
                    }
                }
            }
        }
        return list;
    }
}
