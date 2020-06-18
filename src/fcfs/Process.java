/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

/**
 *
 * @author Mony's HP Probook
 */
public class Process {
    String processname;
    float Bursttime; //given
    float Arrivaltime; //given
    float servicetime; //sum of bursttimes after this porcess
    float waitingtime; //servicetime-arrivetime
    float endingtime; //servicetime+bursttime
    public void settimes(String name,float bt,float at)
    {
        processname=name;
        Bursttime=bt;
        Arrivaltime=at;
    }
    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public float getBursttime() {
        return Bursttime;
    }

    public void setBursttime(float Bursttime) {
        this.Bursttime = Bursttime;
    }

    public float getArrivaltime() {
        return Arrivaltime;
    }

    public void setArrivaltime(float Arrivaltime) {
        this.Arrivaltime = Arrivaltime;
    }

    public float getServicetime() {
        return servicetime;
    }

    public void setServicetime(float servicetime) {
        this.servicetime = servicetime;
    }

    public float getWaitingtime() {
        return waitingtime;
    }

    public void setWaitingtime(float waitingtime) {
        this.waitingtime = waitingtime;
    }

    public float getEndingtime() {
        return endingtime;
    }

    public void setEndingtime(float endingtime) {
        this.endingtime = endingtime;
    }
    
}
