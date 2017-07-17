package Controller;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "OwnerService")
public class OwnerService {

    @WebMethod(operationName = "loginOwner")
    public String loginOwner(@WebParam(name = "nic") String nic, @WebParam(name = "password") String pword) {
        try {
            return new Owner().loginOwner(nic, pword);
        } catch (Exception e) {
        }
        return "";
    }

    @WebMethod(operationName = "getVehicles")
    public String getVehicles(@WebParam(name = "nic") String nic) {
        return new Owner().getVehicles(nic);
    }
}
