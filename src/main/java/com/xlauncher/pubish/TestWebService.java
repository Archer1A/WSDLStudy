package com.xlauncher.pubish;

import org.apache.axis2.client.Options;
import org.apache.axiom.soap.SOAP11Constants;
import org.apache.axis2.client.ServiceClient;

public class TestWebService {
    public static void main(String[] args) throws Exception{
        ServiceClient sender = new ServiceClient();
        Options option = new Options();
        option.setSoapVersionURI(SOAP11Constants.SOAP_ENCODING_NAMESPACE_URI);
        option.setAction("");
    }
}
