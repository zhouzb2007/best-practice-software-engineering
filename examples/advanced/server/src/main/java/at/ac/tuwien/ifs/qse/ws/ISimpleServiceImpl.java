package at.ac.tuwien.ifs.qse.ws;

import javax.jws.WebService;

@WebService(serviceName="simpleService",
        endpointInterface="at.ac.tuwien.ifs.qse.ws.ISimpleService")

public class ISimpleServiceImpl implements ISimpleService {

    public String getServerStatus() {
        return "Server is running";
    }

}
