
package model;


public class ResponseData {
    private String reqNO , operatingBALANCE , responseCODE , responseMSG ;

    public String getReqNO() {
        return reqNO;
    }

    public void setReqNO(String reqNO) {
        this.reqNO = reqNO;
    }

    public String getOperatingBALANCE() {
        return operatingBALANCE;
    }

    public void setOperatingBALANCE(String operatingBALANCE) {
        this.operatingBALANCE = operatingBALANCE;
    }

    public String getResponseCODE() {
        return responseCODE;
    }

    public void setResponseCODE(String responseCODE) {
        this.responseCODE = responseCODE;
    }

    public String getResponseMSG() {
        return responseMSG;
    }

    public void setResponseMSG(String responseMSG) {
        this.responseMSG = responseMSG;
    }

    @Override
    public String toString() {
        return " reqNO is " +reqNO+" , operatingBALANCE is "+operatingBALANCE+" ,  responseCODE is "+responseCODE
                +" , responseMSG is "+responseMSG; //To change body of generated methods, choose Tools | Templates.
    }  
}
